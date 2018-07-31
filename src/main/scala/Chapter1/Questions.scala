package Chapter1

/**
  * Created by Ken.J.Zheng on 7/31/2018.
  */
object Questions extends App {
  //4. Scala lets you multiply a string with a number—try out "crazy" * 3 in the REPL.
  // What does this operation do? Where can you find it in Scaladoc?
  println("craxy"*3) //craxycraxycraxy

  //Scaladoc -> https://www.scala-lang.org/api/2.12.0-M4/scala/collection/immutable/StringOps.html

  //5. What does 10 max 2 mean? In which class is the max method defined?
  println(10 max 2)
  println(10.max(2))

  //scaladoc -> https://www.scala-lang.org/api/2.12.0-M4/scala/Int.html.
  /*def max(that: Int): Int
  Returns this if this > that or that otherwise.*/

  //6. Using BigInt, compute 2^1024
  val powBigInt:BigInt = 2
  println(powBigInt.pow(1024))

  val powInt:Int=2
  println(scala.math.pow(powInt,2))
  println(scala.math.pow(powInt,1024)) //Infinity

  //7. What do you need to import so that you can get a random prime as probablePrime(100, Random),
  // without any qualifiers before probablePrime and Random?
  //https://www.scala-lang.org/api/2.12.0-M4/scala/Int.html?search=probablePrime ->BigInt
  import scala.util.Random
  println(BigInt.probablePrime(100,Random))

  //8. One way to create random file or directory names is to produce a random BigInt and convert it to base 36, yielding a
  // string such as "qsnvbevtomcj38o06kul". Poke around Scaladoc to find a way of doing this in Scala.

  //according to explanation in https://en.wikipedia.org/wiki/Base36
  val baseInt : BigInt = 10


  //9. How do you get the first character of a string in Scala? The last character?
  val str = "character"
  println(str.last)
  println(str.head)

  //10. What do the take, drop, takeRight, and dropRight string functions do?
  //What advantage or disadvantage do they have over using substring?

  //answer:
  // take, drop methods are collection methods.
  // writing better error handling codes
}
