package de.htwg.se.madn
package util


import model.FieldComponent.fieldBaseImpl.Field

trait Observer {
  def update: Unit
}

class Observable {
  var subscribers: Vector[Observer] = Vector()

  def add(s: Observer): Unit = subscribers = subscribers :+ s

  def remove(s: Observer): Unit = subscribers =
    subscribers.filterNot(o => o == s)

  def notifyObservers: Unit = subscribers.foreach(o => o.update)
}
