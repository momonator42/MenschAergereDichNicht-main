package de.htwg.se.madn
package model.fileIoComponent.fileIoJsonImpl

import com.google.inject.name.Names
import com.google.inject.{Guice, Inject}
import net.codingwell.scalaguice.InjectorExtensions._
import de.htwg.se.madn.madnModule

import model.FieldComponent.FieldInterface
import model.FieldComponent.fieldBaseImpl.Field
import de.htwg.se.madn.model.fileIoComponent.FileIOInterface
import play.api.libs.json._
import scala.io.Source

/*
class fileIoJsonImpl extends FileIOInterface{
  override def loadPlayer: FieldInterface = {
    var player: FieldInterface = null
    val source: String = Source.fromFile("madnSavedGame.json").getLines.mkString
    val json: JsValue = Json.parse(source)
    val injector = Guice.createInjector(new madnModule)
    player = injector.getInstance(classOf[FieldInterface])
    val size = (json \ "game"\"playerData" \ "size").get.toString.toInt
    var s: Array[String] = new Array[String](size)
    for(index<-0 until size){
        s(index) = (json \"game"\"playerData"\ "value")(index).as[String]
    }
    var x: Array[Option[String]] = new Array[Option[String]](size)
    var counter = 0;
    for (f <- s) {
        if(f.equals("-1")){
            x(counter) = None
        }else{
            x(counter) = Some(f)
        }
        counter= counter+1
    }
    player.figuren = x
    player
  }

  override def loadField: FieldInterface = {
    var field: FieldInterface = null
    val source: String = Source.fromFile("madnSavedGame.json").getLines.mkString
    val json: JsValue = Json.parse(source)
    val injector = Guice.createInjector(new madnModule)
    field = injector.getInstance(classOf[FieldInterface])
    val size = (json \ "game"\"fieldData" \ "size").get.toString.toInt
    var s: Array[String] = new Array[String](size)
    for(index<-0 until size){
        s(index) = (json \"game"\"fieldData"\ "value")(index).as[String]
    }
    var x: Array[Option[String]] = new Array[Option[String]](size)
    var counter = 0;
    for (f <- s) {
        if(f.equals("-1")){
            x(counter) = None
        }else{
            x(counter) = Some(f)
        }
        counter= counter+1
    }
    field.figuren = x
    field
  }

  override def loadHome: FieldInterface = {
    var home: FieldInterface = null
    val source: String = Source.fromFile("madnSavedGame.json").getLines.mkString
    val json: JsValue = Json.parse(source)
    val injector = Guice.createInjector(new madnModule)
    home = injector.getInstance(classOf[FieldInterface])
    val size = (json \"game"\ "homeData" \ "size").get.toString.toInt
    var s: Array[String] = new Array[String](size)
    for(index<-0 until size){
        s(index) = (json \"game"\"homeData"\ "value")(index).as[String]
    }
    var x: Array[Option[String]] = new Array[Option[String]](size)
    var counter = 0;
    for (f <- s) {
        if(f.equals("-1")){
            x(counter) = None
        }else{
            x(counter) = Some(f)
        }
        counter= counter+1
    }
    home.figuren = x
    home
  }

  override def save(player:FieldInterface,field:FieldInterface,home:FieldInterface) = {
    import java.io._
    val pw = new PrintWriter(new File("madnSavedGame.json"))
    pw.write(Json.prettyPrint(toJson(player,field,home)))
    pw.close
  }
  def toJson(player:FieldInterface,field:FieldInterface,home:FieldInterface) = {
    var p= Seq[String]()//Array[play.api.libs.json.JsLookupResult] = new Array[play.api.libs.json.JsLookupResult](player.figuren.length)
    var f =  Seq[String]() //= new Seq[(String, play.api.libs.json.JsString)](field.figuren.length)
    var h= Seq[String]()//Array[play.api.libs.json.JsLookupResult] = new Array[play.api.libs.json.JsLookupResult](home.figuren.length)

    var counter1 = 0;
    for(fi <- field.figuren){
        if(fi==None){
            f = f:+  "-1"
        }else{
            f = f:+  fi.get
        }
        counter1= counter1+1
    }

    var counter2 = 0;
    for(pl <- player.figuren){
        if(pl==None){
            p = p:+  "-1"
        }else{
            p = p:+  pl.get
        }
        counter2= counter2+1
    }

    var counter3 = 0;
    for(ho <- home.figuren){
        if(ho==None){
            h = h:+  "-1"
        }else{
            h = h:+  ho.get
        }
        counter3= counter3+1
    }

    Json.obj(
        "game" -> Json.obj(
            "playerData" -> Json.obj(
                "size" -> JsNumber(player.figuren.length),
                "value" -> p
            ),
            "fieldData" -> Json.obj(
                "size" -> JsNumber(field.figuren.length),
                "value" -> f
            ),
            "homeData" -> Json.obj(
                "size" -> JsNumber(home.figuren.length),
                "value" -> h
            )
        )
    )
  }
}
*/