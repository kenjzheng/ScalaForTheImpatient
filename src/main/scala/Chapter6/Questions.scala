package Chapter6

/**
  * Created by Ken.J.Zheng on 8/1/2018.
  */
object Questions extends App {
  println(Conversions.gallonsToLiters(1))
  println(InchesToCentimeters(2))

  println(Origin.x, Origin.y)
  Origin.move(2,2)
  println(Origin.x, Origin.y)
  println(Origin.distance(5,6))

  //val point = new Point(3,4) - compile error, class Point has private access
  val point = Point(3,4) //this one is ok, created through companion object
  point.x = 5
}

//1. Write an object Conversions with methods inchesToCentimeters, gallonsToLiters, and milesToKilometers.
object Conversions {
  def inchesToCentimeters(inches:Double):Double = {
    inches * 2.54
  }

  def gallonsToLiters(gallons: Double):Double = {
    gallons * 3.78541
  }

  def milesToKilometers(miles: Double):Double = {
    miles * 1.60934
  }
}

//2. The preceding problem wasn’t very object-oriented. Provide a general superclass UnitConversion
// and define objects InchesToCentimeters, GallonsToLiters, and MilesToKilometers that extend it.
abstract class UnitConversion {}
object InchesToCentimeters extends UnitConversion {
  def apply(inches:Double) = {
    inches * 2.54
  }
}
object GallonsToLiters extends  UnitConversion {
  def apply(gallons:Double) = {
    gallons * 3.78541
  }
}
object MilesToKilometers extends UnitConversion {
  def apply(miles:Double) = {
    miles * 1.60934
  }
}

//3. Define an Origin object that extends java.awt.Point. Why is this not actually a good idea?
// (Have a close look at the methods of the Point class.)

// maybe the equal method in Point.
object Origin extends java.awt.Point {

}

//4. Define a Point class with a companion object so that you can construct Point instances as Point(3, 4), without using new.
class Point private (var x:Int, var y:Int) {

}
object Point {
  def apply(x:Int, y:Int) ={
    new Point(x,y)
  }
}

//5. Write a Scala application, using the App trait, that prints its command-line arguments in reverse order, separated by spaces.
// For example, scala Reverse Hello World should print World Hello.
//ignored

//6. Write an enumeration describing the four playing card suits so that the toString method returns ♣, ♦, ♥, or ♠.
//ignored

//7. Implement a function that checks whether a card suit value from the preceding exercise is red.
//ignored

//8. Write an enumeration describing the eight corners of the RGB color cube.
// As IDs, use the color values (for example, 0xff0000 for Red).
object CubeCornerColor extends Enumeration {
  val TopUpLeftColor = Value(0xff000,"Red")
  val TopUpRightColor = Value(0xffffff,"White")
  val TopLowerLeftColor = Value(0x000000,"Black")
  val TopLowerRightColor = Value(0x0000ff,"Blue")
  val BottomUpLeftColor = Value(0x7a7a7a, "Grey")
  //...
}
