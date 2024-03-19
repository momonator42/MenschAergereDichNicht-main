package de.htwg.se.madn
package Controller

import util.{Observer,Observable}
//import Controller.Controller
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

import aview.{Tui}
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import model.FieldComponent.FieldInterface
import model.FieldComponent.fieldBaseImpl._
import scala.util.{Try,Success,Failure}
import util.Observer
import de.htwg.se.madn.Controller.controllerComponent.ControllerInterface
import de.htwg.se.madn.Controller.controllerComponent.controllerBaseImpl._
import model.FigureComponent.FigureBaseImpl.Figure
import model.FigureComponent.FigureInterface
import java.{util => ju}

class ControllerSpec extends AnyWordSpec with Matchers:
  "A Controller" when {
    "observed by an Observer" should {
          val z = Field(Vector.fill(10)(Figure("",-1)))
          val f = Field(Vector(Figure("A",1),Figure("",-1),Figure("B",1)))
          val f2 = Field(Vector(Figure("",-1),Figure("",-1),Figure("A",1)))
          val f3 = Field(Vector(Figure("A",1),Figure("",-1),Figure("",-1)))
          def inner(spielername: String): List[Figure] = (1 until 5).map(idx => Figure(spielername,idx)).toList
          val player = Field(Vector(List("A","B","C","D").take(4).map(inner(_)).flatten).flatten)
          val controller = new Controller()
          val obs = new Obs
          controller.add(obs)
          "notify its Observer after a new game" in {
            controller.newGame(2)
            obs.updated should be(true)
            controller.player.toString.count(_ == '+') should equal(18)
            controller.home.toString.count(_ == '+') should equal(18)
            controller.field.toString.count(_ == '+') should equal(42)
          }
          "move a figure an do a step" in{
            controller.newGame(2)
            controller.field = f
            controller.domove(controller.field.data(0),2).toString should equal (f2.toString)
            obs.updated should be(true)
          }
          "undo a step" in {
            controller.newGame(2)
            controller.field = f3
            controller.field = controller.domove(controller.field.data(0),2)
            controller.undo
            controller.field.toString should equal(f3.toString)
            obs.updated should be(true)
          }
          "redo a step" in {
            controller.newGame(2)
            controller.field = f3
            controller.field = controller.domove(controller.field.data(0),2)
            controller.undo
            controller.field.toString should equal(f3.toString)
            controller.redo
            controller.field.toString should equal(f2.toString)
            obs.updated should be(true)
          }
          "check the player field" in{
            controller.newGame(2)
              controller.nochAlle("A") should equal(true)
              controller.player = f
              controller.nochAlle("A") should equal(false)
          }/*
          "save the game" in {
            var t: Array[Option[String]] = new Array[Option[String]](2)
                val controller = new Controller()
                controller.newGame(t,t,t)
                var x: Array[Option[String]] = new Array[Option[String]](20)
                var count1 = 0
                x.foreach(ins => {
                x(count1) = None: Option[String]
                count1 = count1 + 1
                })
                controller.field.figuren = x

                var y: Array[Option[String]] = Array(Some("A1"),Some("A2"),Some("A3"),Some("A4"))

                controller.player.figuren = y

                var z: Array[Option[String]] = new Array[Option[String]](4)
                var count2 = 0
                z.foreach(ins => {
                z(count2) = None: Option[String]
                count2 = count2 + 1
                })
                controller.home.figuren = z
                controller.save
                controller.newGame(t,t,t)
                controller.load

                controller.player.figuren should contain inOrder (Some("A1"),Some("A2"),Some("A3"),Some("A4"))
          }*/
          "throw the dice" in {
            controller.throwDice should be <=6
          }
          /*
          "try 3 times to leave the player field" in {
            controller.newGame(1)
            controller.Alleda("A") should be (true||false);
            
          }*/
          "move a figure out if the dice shows 6" in{
            controller.newGame(1)
            var a = controller.Alleda("A")
            while(!a){
              a=controller.Alleda("A")
            }
            controller.field.data(0).toString should equal(Figure("A",1).toString)
          }
          "send a figure back to the playerfield" in {
            controller.newGame(4)

            controller.raus("A")
            controller.raus("B")
            controller.raus("C")
            controller.raus("D")
            controller.player = controller.backHome(controller.player)(0)
            controller.player = controller.backHome(controller.player)(5)
            controller.player = controller.backHome(controller.player)(10)
            controller.player = controller.backHome(controller.player)(15)

            controller.player.toString should equal (player.toString)
          }

          "move a figure" in {
            controller.newGame(4)    

            controller.raus("A")
            controller.raus("B")
            controller.raus("C")
            controller.raus("D")

            val baseField = Field(controller.field.data)

            controller.move(Figure("A",3),3).toString should equal (baseField.toString)
            
            //Tests for player a
            controller.move(controller.field.data(0),6)
            controller.field.data(0).toString should equal(Figure("A",2).toString)
            
            controller.move(controller.field.data(0),4)
            controller.field.data(4).toString should equal(Figure("A",2).toString)

            controller.move(controller.field.data(4),18)
            controller.home.data(1).toString should equal(Figure("A",2).toString)

            controller.raus("A")
            controller.move(controller.field.data(0),6)
            controller.move(controller.field.data(5),1)
            controller.player.data(0).toString should equal(Figure("A",1).toString)

            //Test for player b,c,d
            controller.move(controller.field.data(6),14)
            controller.field.data(0).toString should equal(Figure("B",1).toString)

            controller.move(controller.field.data(0),15)
            controller.home.data(4).toString should equal("B1")

            controller.move(controller.field.data(10),10)
            controller.field.data(0).toString should equal("C1")

            controller.move(controller.field.data(0),15)
            controller.home.data(8).toString should equal("C1")

            controller.move(controller.field.data(15),5)
            controller.field.data(0).toString should equal("D1")

            controller.move(controller.field.data(0),18)
            controller.home.data(12).toString should equal("D1")
          }
          "end the Game" in{
            controller.newGame(1)
            controller.home = Field(controller.player.data.updated(3,Figure("",-1)))
            controller.field = Field(controller.field.data.updated(18,Figure("A",4)))
            controller.move(controller.field.data(18),5) 
            println(controller.home)
            controller.anybodyWone(controller.home) should be (Some("A"))
          }

          "leave the player field with the next figure" in {
            controller.newGame(4)

            controller.raus("A")
            controller.raus("B")
            controller.raus("C")
            controller.raus("D")

            controller.field.data should contain (Figure("A",1))
            controller.field.data should contain (Figure("B",1))
            controller.field.data should contain (Figure("C",1))
            controller.field.data should contain (Figure("D",1))
          }
          "get a Figure from a field" in {
            controller.newGame(4)
            controller.field = f
            controller.getFigureFromField("A",1).toString should equal("A1")
            controller.getFigureFromField("B",1).toString should equal("B1")
            controller.getFigureFromField("C",1).toString should equal("")
          }
        }
}

  case class Obs() extends Observer:
    var updated = false
    override def update: Unit = updated = true