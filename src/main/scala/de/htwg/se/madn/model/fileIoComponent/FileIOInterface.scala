package de.htwg.se.madn
package model.fileIoComponent

import model.FieldComponent.FieldInterface
import model.FieldComponent.fieldBaseImpl.Field

trait FileIOInterface {
  def loadPlayer: FieldInterface
  def loadField: FieldInterface
  def loadHome: FieldInterface

  def save(player:FieldInterface,filed:FieldInterface,home:FieldInterface): Unit

}
