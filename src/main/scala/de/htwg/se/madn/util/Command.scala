
package de.htwg.se.madn.util
import de.htwg.se.madn.model.FieldComponent.FieldInterface
trait Command {
  def doStep: FieldInterface
  def undoStep:FieldInterface
  def redoStep:FieldInterface
}
class UndoManager{
    private var undoStack: List[Command] = Nil
    private var redoStack: List[Command] = Nil
    def doStep(command:Command) : FieldInterface =
        undoStack = command :: undoStack
        command.doStep
    def undoStep(field:FieldInterface) : FieldInterface =
        undoStack match {
            case Nil => field
            case head :: stack => {
                val result = head.undoStep
                undoStack = stack
                redoStack = head :: redoStack
                result
            }
        }
    def redoStep(field:FieldInterface) : FieldInterface =
        redoStack match {
            case Nil => field
            case head::stack => {
                val result = head.redoStep
                redoStack = stack
                undoStack = head::undoStack
                result
            }
        }
    }