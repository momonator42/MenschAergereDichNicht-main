
package de.htwg.se.madn

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import net.codingwell.scalaguice.ScalaModule

import model.FieldComponent.FieldInterface

import model.FieldComponent._
import scala.util.{Try,Success,Failure}
import util.Observer
import scala.io.StdIn.readLine
import scala.util.Random
import Controller.controllerComponent.ControllerInterface
import Controller.controllerComponent._
import scala.io.StdIn.readLine
import model.fileIoComponent.FileIOInterface
import model.fileIoComponent._
import model.FigureComponent.FigureBaseImpl
import model.FigureComponent.FigureInterface



class madnModule extends AbstractModule {

    val defaultSize:Int = 9

    override def configure():Unit = {
        bind(classOf[FieldInterface]).to(classOf[fieldBaseImpl.Field])
        bind(classOf[ControllerInterface]).to(classOf[controllerBaseImpl.Controller])

        //bind(classOf[FileIOInterface]).to(classOf[fileIoJsonImpl.fileIoJsonImpl])
        //bind(classOf[FileIOInterface]).to(classOf[fileIoXmlImpl.fileIoXmlImpl])
    }
}
