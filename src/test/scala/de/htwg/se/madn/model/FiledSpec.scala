package de.htwg.se.madn
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

import model.FieldComponent.FieldInterface
import model.FieldComponent.fieldBaseImpl._
import model.FigureComponent.FigureBaseImpl.Figure
import model.FigureComponent.FigureInterface
import scala.language.postfixOps

class FiledSpec extends AnyWordSpec with Matchers:
  "Field" should {
    "create a small Filed of 2 cells" in {
      val field  = Field(Vector.fill(2)(Figure("",-1)))
      field.toString should contain only ('+', '-', '|', ' ', '\n')
      field.toString should contain allOf ('+', '-', '|', ' ', '\n')
      field.toString.count(_ == '+') should equal(6)
      field.toString.count(_ == '-') should equal(16)
      field.toString.count(_ == '|') should equal (3)
    }
    "create a big Filed of 10 cells" in {
      val field2  = Field(Vector.fill(10)(Figure("",-1)))
      field2.toString should contain only ('+', '-', '|', ' ', '\n')
      field2.toString should contain allOf ('+', '-', '|', ' ', '\n')
      field2.toString.count(_ == '+') should equal(22)
      field2.toString.count(_ == '-') should equal(80)
      field2.toString.count(_ == '|') should equal (11)
    }
    "move a Figure which is not in the field" in {
      val figur = Figure("A",1)
      val field2  = Field(Vector.fill(10)(Figure("",-1)))
      //val field3 =  Field(field2.data.updated(0,figur))
      field2.move(figur,1).data should equal (field2.data)
    }
    "move a Figure by 2 spaces" in {
       val figur = Figure("A",1)
       val field2  = Field(Vector.fill(10)(Figure("",-1)))
       val field3 =  Field(field2.data.updated(0,figur))
       field3.move(figur,2).data(2).toString should equal (figur.toString)
    }
    "move a Figure in an non empty space" in {
      val figur = Figure("A",1)
      val figur2 = Figure("B",1)
      val field2  = Field(Vector.fill(10)(Figure("",-1)))
      val field3 =  Field(field2.data.updated(0,figur))
      val field4 =  Field(field3.data.updated(2,figur2))
      field4.move(figur,2).data(2).toString should equal (figur.toString)
    }
  }