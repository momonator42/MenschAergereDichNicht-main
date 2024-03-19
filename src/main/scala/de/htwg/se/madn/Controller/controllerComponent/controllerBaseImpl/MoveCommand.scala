package de.htwg.se.madn
package Controller.controllerComponent.controllerBaseImpl

import model.FieldComponent.FieldInterface
import util.Observable
import util.Command
import util.UndoManager
import model.FigureComponent.FigureInterface

class MoveCommand(figur:FigureInterface,Anzahl:Int,controller:Controller) extends Command{
  override def doStep: FieldInterface =  controller.field.move(figur,Anzahl)

  override def undoStep: FieldInterface = controller.field.move(figur,-Anzahl)

  override def redoStep: FieldInterface = controller.field.move(figur,Anzahl)
}
