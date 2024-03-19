package de.htwg.se.madn
package model.FigureComponent.FigureBaseImpl
import model.FigureComponent.FigureInterface

final case class Figure (override val playerName:String, override val number:Int, override val state:Boolean=false) extends FigureInterface(playerName,number,state) {
    override def toString: String = if (number == -1) "" else  playerName + number
    def equals(x: Figure): Boolean =  {
        println(x.playerName)
        println(this.playerName)
        x.playerName == this.playerName && x.number == this.number}
}
