package de.htwg.se.madn
package aview
import aview.{Tui}
import model.FieldComponent.FieldInterface
import util.Observer
import scala.io.StdIn.readLine
import scala.swing._
import scala.swing.BorderPanel
import scala.swing.event._
import java.awt.TextArea
import scala.util.Random
import Controller.controllerComponent.ControllerInterface
import io.AnsiColor._
import scala.swing.Color


class GUI(controller: ControllerInterface) extends MainFrame with Observer {

  controller.add(this)
  var anzahlSpieler = 0;

  override def update: Unit = {
    spielfeld.text = ""
    spielfeld.text = controller.toString
    repaint
  }

  val fieldpos: Array[Option[String]] = new Array[Option[String]](20)//40 
  var diceVal = 0;
  var turn = "A"
    

  object spielfeld extends TextPane {
    font = new Font("Consolas", 0, 19)
    preferredSize = new Dimension(1500, 350)
  }

  object player extends TextField { columns = 4 }
  object dice extends TextField { columns = 4 }
  object exchange extends TextField { columns = 1 }
  var spieler123 = new Array[Char](1)

   title = "Mensch ärgere dich nicht"
   iconImage = toolkit.getImage("dice.jpg")
   
   //test
   val start = new Button("START")
   val aButton = new Button("A")
   val bButton = new Button("B")
   val cButton = new Button("C")
   val dButton = new Button("D")
   val IButton = new Button("1")
   val IIButton = new Button("2")
   val IIIButton = new Button("3")
   val IVButton = new Button("4")
   val saveButton = new Button("SAVE")
   val loadButton = new Button("LOAD")

   val nextButton = new Button("neue Runde")
   val playerLabel = new Label("Anzahl Spieler: ")
   val emptyLabel1 = new Label("        ")
   val emptyLabel2 = new Label("        ")
   val emptyLabel3 = new Label("              ")
   contents = new BoxPanel(Orientation.Vertical){
    contents += new FlowPanel {
      contents += playerLabel 
      contents += player
      contents += start
      contents += emptyLabel3
      contents += saveButton
      contents += loadButton
    }
    contents += spielfeld
    contents += new FlowPanel{
      contents += dice
      contents += emptyLabel2
      contents += aButton
      contents += bButton
      contents += cButton
      contents += dButton
      contents += IButton
      contents += IIButton
      contents += IIIButton
      contents += IVButton
      contents += emptyLabel1
      contents += nextButton
      border = Swing.EmptyBorder(15, 10, 10, 10)
      
    }
      border = Swing.EmptyBorder(15, 10, 10, 10)
    }
   listenTo(start)
   listenTo(aButton)
   listenTo(bButton)
   listenTo(cButton)
   listenTo(dButton)
   listenTo(IButton)
   listenTo(IIButton)
   listenTo(IIIButton)
   listenTo(IVButton)
   listenTo(nextButton)
   listenTo(saveButton)
   listenTo(loadButton)
   reactions += {
     case ButtonClicked(`start`) => startGame
     //case ButtonClicked(`saveButton`) => controller.save
     //case ButtonClicked(`loadButton`) => controller.load
     case ButtonClicked(`aButton`) => round("A")
     case ButtonClicked(`bButton`) => round("B")
     case ButtonClicked(`cButton`) => round("C")
     case ButtonClicked(`dButton`) => round("D")
     case ButtonClicked(`IButton`) => 
       var a = controller.move(controller.getFigureFromField(turn,1),diceVal)
       if(a.data.length > 16){
          IButton.background = java.awt.Color.red;
          IButton.enabled = false
          if(a.data.length == 22){
            spielfeld.text = a.toString
            spielfeld.background = java.awt.Color.green
          }
       }
     case ButtonClicked(`IIButton`) => 
       var b = controller.move(controller.getFigureFromField(turn,2),diceVal)
       if(b.data.length > 16){
          IIButton.background = java.awt.Color.red;
          IIButton.enabled = false
          if(b.data.length == 22){
            spielfeld.text = b.toString
            spielfeld.background = java.awt.Color.green
          }
       }
     case ButtonClicked(`IIIButton`) => 
       var c = controller.move(controller.getFigureFromField(turn,3),diceVal)
       if(c.data.length > 16){
          IIIButton.background = java.awt.Color.red;
          IIIButton.enabled = false
          if(c.data.length == 22){
            spielfeld.text = c.toString
            spielfeld.background = java.awt.Color.green
          }
       }
     case ButtonClicked(`IVButton`) => 
       var d = controller.move(controller.getFigureFromField(turn,4),diceVal)
       if(d.data.length > 16){
          IVButton.background = java.awt.Color.red;
          IVButton.enabled = false
          if(d.data.length == 22){
            spielfeld.text = d.toString
            spielfeld.background = java.awt.Color.green
          }
       }
     case ButtonClicked(`nextButton`) => 
       dice.background = java.awt.Color.white
        IButton.background = java.awt.Color.cyan;
        IButton.enabled = true
        IIButton.background = java.awt.Color.cyan;
        IIButton.enabled = true
        IIIButton.background = java.awt.Color.cyan;
        IIIButton.enabled = true
        IVButton.background = java.awt.Color.cyan;
        IVButton.enabled = true
       dice.text = ""
       if(anzahlSpieler > 0){
         aButton.background = java.awt.Color.green;
         aButton.enabled = true
       }
       if(anzahlSpieler > 1){
          bButton.background = java.awt.Color.green;
          bButton.enabled = true
       } 
       if(anzahlSpieler>2){
          cButton.background = java.awt.Color.green;
          cButton.enabled = true
       }
       if(anzahlSpieler > 3){
          dButton.background = java.awt.Color.green;
          dButton.enabled = true
       }
       
    } 
   centerOnScreen
   visible = true

  def startGame = {
    spielfeld.background = java.awt.Color.white
    val inserts = scala.collection.mutable.ArrayBuffer.empty[Option[String]]
        spieler123 = new Array[Char](player.text.toInt)
          var count1 = 0
        fieldpos.foreach(ins => {
          fieldpos(count1) = None: Option[String]
          count1 = count1 + 1
        })
       val Spielerbank = Array('A','B','C','D')
       var count = 0;
       spieler123.foreach(ins => {
        spieler123(count) = Spielerbank(count) 
        count = count + 1
        })
        anzahlSpieler = player.text.toInt
        if(anzahlSpieler > 0){
          aButton.background = java.awt.Color.green;
          aButton.enabled = true

          bButton.background = java.awt.Color.gray;
          bButton.enabled = false

          cButton.background = java.awt.Color.gray;
          cButton.enabled = false

          dButton.background = java.awt.Color.gray;
          dButton.enabled = false
        }
        if(anzahlSpieler > 1){
          aButton.background = java.awt.Color.green;
          bButton.background = java.awt.Color.green;
          aButton.enabled = true
          bButton.enabled = true

          cButton.background = java.awt.Color.gray;
          cButton.enabled = false

          dButton.background = java.awt.Color.gray;
          dButton.enabled = false
       } 
       if(anzahlSpieler>2){
          cButton.background = java.awt.Color.green;
          cButton.enabled = true

          dButton.background = java.awt.Color.gray;
          dButton.enabled = false
       }
       if(anzahlSpieler > 3){
          dButton.background = java.awt.Color.green;
          dButton.enabled = true
       }
        count = 0
        val hpmepos: Array[Option[String]] = new Array[Option[String]](player.text.toInt*4)
          hpmepos.foreach(ins => {
          hpmepos(count) = None: Option[String]
          count = count + 1
        })
       for (a <- spieler123) {
        for (b <- 1 until 5) {
          val d = a.toString + b.toString
          Some(d)
          inserts += Some(d)
        }
       }
       //controller.newGame(inserts.toArray, fieldpos, hpmepos)
       controller.newGame(anzahlSpieler)
  }
  
    def round(player:String)={

      dice.background = java.awt.Color.white
       dice.text = ""

      player match{
        case "A" => 
          aButton.background = java.awt.Color.red;
          aButton.enabled = false
        case "B" => 
          bButton.background = java.awt.Color.red;
          bButton.enabled = false
        case "C" => 
          cButton.background = java.awt.Color.red;
          cButton.enabled = false
        case "D" => 
          dButton.background = java.awt.Color.red;
          dButton.enabled = false
      }
      var alle = controller.nochAlle(player)
      if(alle == true){
        val v = controller.Alleda(player)
        if(v){
          dice.background = java.awt.Color.green;
          dice.text = "6"
        }else{
          dice.background = java.awt.Color.red;
        }
      } else{
        diceVal = controller.throwDice
        dice.text = diceVal.toString
      }
      turn = player
        
    }
}

