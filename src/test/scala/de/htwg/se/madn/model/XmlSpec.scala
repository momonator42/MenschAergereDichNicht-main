/*package de.htwg.se.madn
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

import com.google.inject.name.Names
import com.google.inject.{Guice, Inject}
import net.codingwell.scalaguice.InjectorExtensions._
import de.htwg.se.madn.madnModule

import model.PlayerComponent.PlayerInterface
import model.PlayerComponent.PlayerBaseImpl.Player
import model.HomeComponent.HomeInterface
import model.HomeComponent.HomeBaseImpl.Home
import model.FieldComponent.FieldInterface
import model.FieldComponent.fieldBaseImpl.Field
import de.htwg.se.madn.model.fileIoComponent.FileIOInterface


import Controller.controllerComponent.ControllerInterface
import Controller.controllerComponent.controllerBaseImpl._


class XmlSpec extends AnyWordSpec  with Matchers:
   "A GameField" when {
        "stored  in Xml" should {
            "store and load" in {
                var t: Array[Option[String]] = new Array[Option[String]](2)
                val injector = Guice.createInjector(new madnModule)
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
                val fileIo = injector.getInstance(classOf[FileIOInterface])
                fileIo.save(controller.player,controller.field,controller.home)
                controller.newGame(t,t,t)
                controller.player = fileIo.loadPlayer
                controller.field = fileIo.loadField
                controller.home = fileIo.loadHome

                controller.player.figuren should contain inOrder (Some("A1"),Some("A2"),Some("A3"),Some("A4"))
            }
        }
   }
  

*/