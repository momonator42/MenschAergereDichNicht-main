package de.htwg.se.madn
package model.FieldComponent

import model.FigureComponent.FigureInterface
import java.lang.reflect.Field

trait FieldInterface(val data: Vector[FigureInterface]) {
  def move(figur:FigureInterface,anzahlFelder:Int): FieldInterface
}

