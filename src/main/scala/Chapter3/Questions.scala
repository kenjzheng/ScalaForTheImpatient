package Chapter3

import java.util

import scala.collection.mutable.ArrayBuffer

/**
  * Created by U0068746 on 7/31/2018.
  */
object Questions extends App {
  //1. Write a code snippet that sets a to an array of n random integers between 0 (inclusive) and n (exclusive).
  import scala.util.Random

  val n = 10
  val a: Array[Int] = for (i <- (0 to n - 1).toArray) yield Random.nextInt(n)
  println(a.mkString(","))

  //2. Write a loop that swaps adjacent elements of an array of integers.
  // For example, Array(1, 2, 3, 4, 5) becomes Array(2, 1, 4, 3, 5).
  val swap = Array(1, 2, 3, 4, 5, 6)
  for (i <- 0 to swap.length - 1 by 2) {
    if (i < swap.length - 1) {
      val tmp = swap(i)
      swap(i) = swap(i + 1)
      swap(i + 1) = tmp
    }
  }
  println(swap.mkString(","))

  //3. Repeat the preceding assignment, but produce a new array with the swapped values. Use for/yield.
  val swap2 = Array(1, 2, 3, 4, 5)
  val swap22 = for (i <- 0 to swap2.length - 1) yield
    if(swap2.length%2==0){
      if (i % 2 == 0)
        swap2(i + 1)
      else
        swap2(i - 1)
    }
    else {
      if(i == swap2.length-1) swap2(i)
      else {
        if (i % 2 == 0)
          swap2(i + 1)
        else
          swap2(i - 1)
      }
    }
  println(swap22.mkString(","))

  //4. Given an array of integers, produce a new array that contains all positive values of the original array, in their original order,
  // followed by all values that are zero or negative, in their original order.
  val a1 = Array(3,5,-1,0,2,-5,3,6,-2)
  val a11 = for (e <- a1 if e>0) yield e
  val a12 = for (e <- a1 if e<=0) yield e
  val a21 = a11 ++ a12
  println(a21.mkString(","))

  //5. How do you compute the average of an Array[Double]?
  val d1 = Array(2.1D, 2.2D,3D,4D,-1D)
  println("d1 average is "+ d1.sum / d1.length)

  //6. How do you rearrange the elements of an Array[Int] so that they appear in reverse sorted order?
  // How do you do the same with an ArrayBuffer[Int]?
  println(a1.reverse.mkString(","))
  val ab = new ArrayBuffer[Int]()
  ab.append(1,2,3,4,5)
  println(ab.reverse.mkString(","))

  //7. Write a code snippet that produces all values from an array with duplicates removed. (Hint: Look at Scaladoc.)
  println(a1.distinct.mkString(","))

  //8. Suppose you are given an array buffer of integers and want to remove all but the first negative number.
  val ai = new ArrayBuffer[Int]()
  ai.append(1,2,3,-1,-2,3,-4,5,6)
  //ai.append(1,2,3,-1,5,6)
  val negativeNumberIndex = (for (i <- 0 to ai.length-1 if ai(i)<0) yield i).tail
  for (i <- negativeNumberIndex.length-1 to 0 by -1){
    ai.remove(negativeNumberIndex(i))
  }
  println(ai.mkString(","))

  //9. Improve the solution of the preceding exercise by collecting the positions that should be moved and their target positions.
  // Make those moves and truncate the buffer. Don’t copy any elements before the first unwanted element.

  //10. Make a collection of all time zones returned by java.util.TimeZone.getAvailableIDs that are in America.
  // Strip off the "America/" prefix and sort the result.
  val allIds = java.util.TimeZone.getAvailableIDs
  val america = allIds.filter(i => i.startsWith("America/")).map(m => m.drop(8)).sorted
  println(america.mkString(","))

  //11. Import java.awt.datatransfer._ and make an object of type SystemFlavorMap with the call
  //Then call the getNativesForFlavor method with parameter DataFlavor.imageFlavor and get the return value as a Scala buffer.
  //(Why this obscure class? It’s hard to find uses of java.util.List in the standard Java library.)
  import java.awt.datatransfer._
  import scala.collection.JavaConversions.asScalaBuffer  //disable this and see what will happen to imageFalvorBuffer
  import scala.collection.mutable.Buffer
  val flavors = SystemFlavorMap.getDefaultFlavorMap().asInstanceOf[SystemFlavorMap]
  val imageFlavor: util.List[String] = flavors.getNativesForFlavor(DataFlavor.imageFlavor)
  val imageFalvorBuffer: Buffer[String] = flavors.getNativesForFlavor(DataFlavor.imageFlavor)
}
