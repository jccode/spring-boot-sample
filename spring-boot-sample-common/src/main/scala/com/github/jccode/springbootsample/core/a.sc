
import scala.beans.BeanProperty

object ErrorCode extends Enumeration {
  type ErrorCode = Value
  val NONE = Value(0)
  val NO_DATA = Value(100)
  val AUTH_ERROR = Value(200)
}

class Error[D](@BeanProperty val code: ErrorCode.ErrorCode, @BeanProperty val message: String, @BeanProperty val data: Option[D])


object Error {
  def apply[D](message: String): Error[D] = new Error(ErrorCode.NONE, message, None)
  def apply[D](code: ErrorCode.ErrorCode, message: String): Error[D] = new Error(code, message, None)

}

Error[Double]("hello")
Error[Int](ErrorCode.NO_DATA, "hello")

Option(3)
Option(null)
Some(null)
