package Chapter14

/**
  * Created by U0068746 on 8/7/2018.
  */
object Questions extends App {
  //2. Using pattern matching, write a function swap that receives a pair of integers and returns the pair with the components swapped.
  def swap(pair:(Int,Int)):(Int,Int) = {
    pair match {
      case (x,y) => (y,x)
    }
  }

  println(swap((1,2)))

  //3. Using pattern matching, write a function swap that swaps the first two elements of an array provided its length is at least two.
  def swapArray(array: Array[Int]): Array[Int]={
    array match {
      case Array(x,y, rest @ _ *) => {
        val a1 = Array(y,x)
        val a2 = rest.toArray
        a1 ++ a2
      }
    }
  }
  println(swapArray(Array(1,2,3,4,5)).mkString(","))
}
