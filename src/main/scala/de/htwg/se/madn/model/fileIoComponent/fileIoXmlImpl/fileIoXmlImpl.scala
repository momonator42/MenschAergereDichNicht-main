package de.htwg.se.madn
package model.fileIoComponent.fileIoXmlImpl

import com.google.inject.name.Names
import com.google.inject.{Guice, Inject}
import net.codingwell.scalaguice.InjectorExtensions._
import de.htwg.se.madn.madnModule

import model.FieldComponent.FieldInterface
import model.FieldComponent.fieldBaseImpl.Field
import de.htwg.se.madn.model.fileIoComponent.FileIOInterface

import scala.xml.{ NodeSeq, PrettyPrinter }

/*
class fileIoXmlImpl extends FileIOInterface{
  override def loadPlayer: PlayerInterface = {
    var player: FieldInterface= null
    val file = scala.xml.XML.loadFile("madnSavedGame.xml")
    val injector = Guice.createInjector(new madnModule)
    player = injector.getInstance(classOf[PlayerInterface])
    val play = (file\\"playerfiguren")
    val fig = (play \\ "value")
    var x: Array[Option[String]] = new Array[Option[String]](fig.length)
    var counter = 0;
    for (f <- fig) {
        if(f.text.equals("-1")){
            x(counter) = None
        }else{
            x(counter) = Some(f.text)
        }
        counter= counter+1
    }
    player.figuren = x
    player
  }

  override def loadField: FieldInterface = {
    var field: FieldInterface = null
    val file = scala.xml.XML.loadFile("madnSavedGame.xml")
    val injector = Guice.createInjector(new madnModule)
    field = injector.getInstance(classOf[FieldInterface])
    val play = (file\\"fieldfiguren")
    val fig = (play \\ "value")
    var x: Array[Option[String]] = new Array[Option[String]](fig.length)
    var counter = 0;
    for (f <- fig) {
        if(f.text.equals("-1")){
            x(counter) = None
        }else{
            x(counter) = Some(f.text)
        }
        counter= counter+1
    }
    field.figuren = x
    field
  }

  override def loadHome: HomeInterface = {
    var home: HomeInterface = null
    val file = scala.xml.XML.loadFile("madnSavedGame.xml")
    val injector = Guice.createInjector(new madnModule)
    home = injector.getInstance(classOf[HomeInterface])
    val play = (file\\"homefiguren")
    val fig = (play \\ "value")
    var x: Array[Option[String]] = new Array[Option[String]](fig.length)
    var counter = 0;
    for (f <- fig) {
        if(f.text.equals("-1")){
            x(counter) = None
        }else{
            x(counter) = Some(f.text)
        }
        counter= counter+1
    }
    home.figuren = x
    home
  }

  override def save(player:PlayerInterface,field:FieldInterface,home:HomeInterface) = {
    import java.io._
    val pw = new PrintWriter(new File("madnSavedGame.xml"))
    val prettyPrinter = new PrettyPrinter(120, 4)
    var p: Array[scala.xml.Elem] = new Array[scala.xml.Elem](player.figuren.length)
    var f: Array[scala.xml.Elem] = new Array[scala.xml.Elem](field.figuren.length)
    var h: Array[scala.xml.Elem] = new Array[scala.xml.Elem](home.figuren.length)
    var counter = 0;
    for(pl <- player.figuren){
        if(pl==None){
            p(counter) = <value>{"-1"}</value>
        }else{
            p(counter) = <value>{pl.get}</value>
        }
        counter= counter+1
    }

    var counter1 = 0;
    for(fi <- field.figuren){
        if(fi==None){
            f(counter1) = <value>{"-1"}</value>
        }else{
            f(counter1) = <value>{fi.get}</value>
        }
        counter1= counter1+1
    }

    var counter2 = 0;
    for(ho <- home.figuren){
        if(ho==None){
            h(counter2) = <value>{"-1"}</value>
        }else{
            h(counter2) = <value>{ho.get}</value>
        }
        counter2= counter2+1
    }


    val playerData = <playerfiguren>{ p }</playerfiguren>
    val fieldData = <fieldfiguren>{ f }</fieldfiguren>
    val homeData = <homefiguren>{ h }</homefiguren>
    val e: Array[scala.xml.Elem] = Array(playerData,fieldData,homeData)
    val game = <game>{e}</game>
    val xml = prettyPrinter.format(game)
    pw.write(xml)
    pw.close
  }
}
*/
