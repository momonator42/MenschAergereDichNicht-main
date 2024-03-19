package de.htwg.se.madn
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

import model.FigureComponent.FigureBaseImpl.Figure
import model.FigureComponent.FigureInterface
import scala.language.postfixOps

class FigureSpec extends AnyWordSpec with Matchers:
  "Figure" should {
    val figure1 = Figure("A",1,true)
    val figure2 = Figure("A",1,false)
    val figure3 = Figure("B",1,false)
    val figure4 = Figure("A",2,false)
    val figure5 = Figure("B",2)
    "compare two similar Figures as true" in {
      figure1.equals(figure1) should be(true)
    }
    "compare two similar Figures with different state as true" in {
      figure1.equals(figure2) should be(true)
    }
    "compare two different Figures as false" in {
      figure1.equals(figure3) should be(false)
    }
    "compare two different Figures in number as false" in {
      figure1.equals(figure4) should be(false)
    }
    "compare two totally different Figures as false" in {
      figure5.equals(figure2) should be(false)
    }
    "return a figure" in {
      figure1.toString should equal("A1")
    }
    "return nothing" in {
      val figureNone = Figure("",-1)
      figureNone.toString should equal("")
    }
  }