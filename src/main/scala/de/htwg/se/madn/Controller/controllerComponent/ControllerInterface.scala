package de.htwg.se.madn
package Controller.controllerComponent

import util.Observable
import util.UndoManager
import scala.util.{Try,Success,Failure}
import scala.util.Random
import scala.util.control.Breaks._
import model.FigureComponent.FigureBaseImpl.Figure
import model.FieldComponent.FieldInterface
import model.FigureComponent.FigureInterface

//trait ControllerInterface(val Home:HomeInterface,val Field:FieldInterface,val Player:PlayerInterface) extends Observable{
trait ControllerInterface() extends Observable{
  def newGame(nPlayer:Int) : Unit
  def domove(figur:FigureInterface ,anzahl:Int): FieldInterface 
  def undo: FieldInterface
  def redo: FieldInterface
  //def save: Unit
  //def load: Unit
  def raus(spieler:String):FieldInterface
  def throwDice : Int
  def nochAlle(spieler:String) : Boolean 
  def checkField(index:Int): Try[FieldInterface]
  def backHome(space:FieldInterface)(index:Int):FieldInterface
  def Alleda(spieler:String): Boolean
  def reachedHome(figur:FigureInterface):FieldInterface
  def reachedEnd(figur: FigureInterface, anzahl: Int): FieldInterface
  def move(figur:FigureInterface,anzahl:Int):FieldInterface
  def anybodyWone(thisHome:FieldInterface):Option[String]
  def getFigureFromField(player:String,nummer:Int): FigureInterface
}
