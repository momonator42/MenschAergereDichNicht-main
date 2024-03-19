package de.htwg.se.madn
package model.FieldComponent.fieldBaseImpl
import model.FigureComponent.FigureInterface
import model.FieldComponent.FieldInterface

trait Strategy {
  def move(figur:FigureInterface,anzahlFelder:Int,status:Boolean) :FieldInterface
}
