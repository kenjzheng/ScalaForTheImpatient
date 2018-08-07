package Chapter10

/**
  * Created by Ken.J.Zheng on 8/3/2018.
  */
object Questions extends App {
  //SavingAccount is abstract, when we create object, it needs concrete implementation
  /*val a1 = new SavingAccount {
    override def log(msg: String): Unit = ???
  }*/

  //since trait ConsoleLogger has implemented method Log, we can mix-in to abstract class SavingAccount, to make it a 'Class'.
  val a2 = new SavingAccount with ConsoleLogger
  a2.log("test a2")

  //since trail FileLogger doesn't implement method Log, we can't mix-in
  //val a3 = new SavingAccount with FileLogger

  //question 1
  val egg = new java.awt.geom.Ellipse2D.Double(5, 10, 20, 30) with RectangleLike
  egg.translate(10, -10)
  egg.grow(10, 20)
  println(egg.x, egg.y)
  //moved from 5 to 15, 10 to 0

  //question 2
  val point1 = new OrderedPoint(10, 5)
  val point2 = new OrderedPoint(5, 10)

  println(point1 > point2)

  //question 4
  val encryptLog = new LogMessage()
  println(encryptLog.log("hello"))
  encryptLog.key = -3
  println(encryptLog.log("hello"))
}


//code samples in the book
trait Logger {
  def log(msg: String) // An abstract method
}
trait ConsoleLogger extends Logger {
  def log(msg: String):Unit = {println(msg)} //An concrete method
}
trait FileLogger extends Logger {
  def log(msg:String)
}
class Account {

}
abstract class SavingAccount extends Account with Logger {
  log("saving account")
}

//1. The java.awt.Rectangle class has useful methods translate and grow that are unfortunately absent from classes
// such as java.awt.geom.Ellipse2D. In Scala, you can fix this problem. Define a trait RectangleLike with concrete
// methods translate and grow.

// this question has nothing to do with java.awt.Rectangle, should be asked to add two methods translate
// and grow in Ellipse2D as Rectangle's method
trait RectangleLike {
  //ref to 10.13 Self Types
  this : java.awt.geom.Ellipse2D =>
    def translate(x:Int,y:Int): Unit = {
      setFrame(getX + x,getY + y,getWidth,getHeight)
    }
    def grow(x:Int,y:Int):Unit = {
      setFrame(getX, getY, getWidth+x, getHeight+y)
    }
}

//2. Define a class OrderedPoint by mixing scala.math.Ordered[Point] into java.awt.Point.
//Use lexicographic ordering, i.e. (x, y) < (x’, y’) if x < x’ or x = x’ and y < y’.
import java.awt.Point
//can't use OrderedPoint(x:Int, y:Int), it has conflicts with superclass Point's x, y.
class OrderedPoint(pointX:Int, pointY:Int) extends Point(pointX, pointY) with scala.math.Ordered[Point] {
  def compare(that:Point): Int ={
    //compare x, then y if x equal
    if(this.x < that.x) -1
    else if(this.x == that.x && this.y < that.y) -1
    else 1
  }
}

//4. Provide a CryptoLogger trait that encrypts the log messages with the Caesar cipher. The key should be 3 by default,
// but it should be overridable by the user. Provide usage examples with the default key and a key of –3.
class LogMessage extends CryptoLogger

trait CryptoLogger {
  private val letters = Array('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'm',
    'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'x', 'y', 'z')
  private val numbers = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26)

  private val letterMap: Map[Char, Int] = letters.zip(numbers).toMap
  private val numberMap: Map[Int, Char] = numbers.zip(letters).toMap

  var key = 3

  def log(message: String): String = {
    CaesarCipher(message)
  }

  private def CaesarCipher(str: String): String = {
    str.map(char => {
      var nextIndex = letterMap(char) + key
      if (nextIndex > 26)
        nextIndex = nextIndex - 26
      else if (nextIndex < 1)
        nextIndex = 26 + nextIndex

      numberMap(nextIndex)
    })
  }
}




