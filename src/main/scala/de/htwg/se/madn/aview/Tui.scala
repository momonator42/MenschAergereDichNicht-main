package de.htwg.se.madn
package aview
import model.FieldComponent.FieldInterface
import scala.util.{Try,Success,Failure}
import util.Observer
import scala.io.StdIn.readLine
import scala.util.Random
import Controller.controllerComponent.ControllerInterface
import scala.io.StdIn.readLine

class Tui(controller: ControllerInterface) extends Observer:
  controller.add(this)
  override def update: Unit =  println(controller.toString)
  var diceVal = 0;
  var turn = "A"

  def round(player:String)={

    print("Spieler "+ player+" ist an der Reihe\n")
    if(controller.nochAlle(player)){
      print(player + " darf 3x wuerfeln\n")
      if(controller.Alleda(player))println("du hast es rausgeschafft")
      turn = player
    } else{
      diceVal =  controller.throwDice
      print(player + " hat eine "+ diceVal +" gewuerfelt\n")
      println("Mit welcher Figur moechtest du Fahren (1-4)")
      turn = player
    }
  }


  def processInputLine(input:String) = {
    input match {
      case "n1" => controller.newGame(1)//new Game with one Player
      case "n2" => controller.newGame(2)//new Game with two Players
      case "n3" => controller.newGame(3)//new Game with three Players
      case "n4" => controller.newGame(4)//new Game with four Players
      case "q"  => sys.exit(0)
      case "a"  => round("A")
      case "b"  => round("B")
      case "c"  => round("C")
      case "d"  => round("D")
      case "1"  => if(controller.getFigureFromField(turn,1).playerName != "X") controller.move(controller.getFigureFromField(turn,1),diceVal)
      case "2"  => if(controller.getFigureFromField(turn,2).playerName != "X") controller.move(controller.getFigureFromField(turn,2),diceVal)
      case "3"  => if(controller.getFigureFromField(turn,3).playerName != "X") controller.move(controller.getFigureFromField(turn,3),diceVal)
      case "4"  => if(controller.getFigureFromField(turn,4).playerName != "X") controller.move(controller.getFigureFromField(turn,4),diceVal)
      //case "save"  => controller.save
      //case "load"  => controller.load
      case _ => 
    }
  }