package de.htwg.se.madn
package model.FieldComponent.fieldBaseImpl

import model.FieldComponent.FieldInterface
import model.FigureComponent.FigureBaseImpl.Figure
import model.FigureComponent.FigureInterface
import scala.annotation.switch


case class Field(override val data:Vector[FigureInterface]) extends FieldInterface(data) {

  override def toString: String = {
    def inner(value: FigureInterface) : Option[FigureInterface] = if (!(value.number == -1)) Some(value) else None
    val list:List[Option[FigureInterface]] =  data.map(inner(_)).toList

    def outer(list : List[Option[FigureInterface]]) : List[String] = {
      for (
          maybeFigure <- list) yield maybeFigure match {
        case Some(b) => " "+ b.toString() + " |"
        case None => "    |"
      }
    }
    ("+") + ("----+" * data.length) + "\n" + "|"+outer(list).mkString + "\n" + ("+") + ("----+" * data.length) + "\n"
  }

  def move(figur:FigureInterface,anzahlFelder:Int): Field = {
    val index = data.indexOf(figur)
    println(index)
    index match{
      case -1 => Field(data)
      case _ => {
        val dataUpdated = data.updated(index,Figure("",-1))
        Field(dataUpdated.updated(index+anzahlFelder,figur))
      }
    }
  }
}
