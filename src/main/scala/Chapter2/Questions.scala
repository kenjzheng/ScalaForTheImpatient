package Chapter2
import scala.math.Numeric
/**
  * Created by Ken.J.Zheng on 7/31/2018.
  */
object Questions extends App {
  //1. The signum of a number is 1 if the number is positive, –1 if it is negative,
  // and 0 if it is zero. Write a function that computes this value.
  val test = 10
  println(test signum)

  def signum(number:Int):Int = {
    if(number>0) 1
    else if(number==0) 0
    else -1
  }
  println(signum(-5))

  //2. What is the value of an empty block expression {}? What is its type?
  val emptyBlock = {}
  println(emptyBlock.getClass)
  println(emptyBlock.asInstanceOf[Unit].getClass)

  val emptyBracket = ()
  println(emptyBracket.getClass)
  println(emptyBracket)

  //3. Come up with one situation where the assignment x = y = 1 is valid in Scala. (Hint: Pick a suitable type for x.)

  //4. Write a Scala equivalent for the Java loop
  for(i <- 10 to 1 by -1) println(i)
  for(j <- (1 to 10).reverse) println(j)

  //5. Write a procedure countdown(n: Int) that prints the numbers from n to 0.
  def countDown(n: Int): Unit = {
    if(n<0) println((n to 0).mkString(","))
    else println((n to 0 by -1).mkString(","))
  }

  countDown(4)
  countDown(-5)
  countDown(0)

  //6. Write a for loop for computing the product of the Unicode codes of all letters in a string.
  // For example, the product of the characters in "Hello" is 9415087488L.
  val s1 = "Hello"
  var p1 = 1L
  for(i <- 0 to s1.length-1){
    p1 = p1 * s1(i).toLong
  }
  println(p1)

  //7. Solve the preceding exercise without writing a loop. (Hint: Look at the StringOps Scaladoc.)
  import scala.collection.immutable.StringOps
  val s2 = new StringOps("Hello")
  val p2 = s2.foldLeft(1L)(_ * _.toLong)
  println(p2)

  //8. Write a function product(s : String) that computes the product, as described in the preceding exercises.
  def product(s: String):Long = {
    s.foldLeft(1L)(_ * _.toLong)
  }
  println(product("Hello"))

  //9. Make the function of the preceding exercise a recursive function.
  def recurProduct(s:String):Long = {
    if(s.length==0)
      1L
    else s.head.toLong*recurProduct(s.tail)
  }
  println(recurProduct("Hello"))

  //10. Write a function that computes x^n, where n is an integer.
  //x^n = y · y if n is even and positive, where y = x^n / 2.
  //x^n = x · x^n – 1 if n is odd and positive.
  //x^0 = 1.
  //x^n = 1 / x^–n if n is negative.

  def computeXn(x:Int,n:Int):Double = {
    if(n==0) 1
    else if(n>0 && n%2==0) {
      val tmp = computeXn(x,n/2)
      tmp * tmp
    }
    else if(n>0 && n%2!=0) x * computeXn(x,n-1)
    else 1/computeXn(x,math.abs(n))
  }

  println(computeXn(2,3))
  println(computeXn(2,0))
  println(computeXn(2,-2))
  println(computeXn(2,-10))
}
