package Chapter3

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
}
