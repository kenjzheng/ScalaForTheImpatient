package Chapter12

/**
  * Created by Ken.J.Zheng on 8/4/2018.
  */
object Questions extends App {
  def valueAtOneQuarter(factor: Double, f: (Double) => Double) = f(factor)
  println(valueAtOneQuarter(11.5, scala.math.ceil _ ))

  //1. Write a function values(fun: (Int) => Int, low: Int, high: Int) that yields a collection of function inputs
  // and outputs in a given range. For example, values(x => x * x, -5, 5) should produce a collection of
  // pairs (-5, 25), (-4, 16), (-3, 9), . . . , (5, 25).

  def values(fun: (Int)=>Int, low:Int, high:Int) = {
    (low to high).map(x => (x,fun(x)))
  }

  println(values(x=>x*x,-5,5))

  //2. How do you get the largest element of an array with reduceLeft?
  val array = Array(1,2,3,5,6,2,8,10,2,7)
  val largest = array.reduceLeft((x,y)=>{
    if(y>x) y else x})
  println(largest)

  //3. Implement the factorial function using to and reduceLeft, without a loop or recursion.
  println((1 to 4).reduceLeft(_ * _))

  //4. The previous implementation needed a special case when n < 1. Show how you can avoid this with foldLeft.
  // (Look at the Scaladoc for foldLeft. It’s like reduceLeft, except that the first value in the chain of
  // combined values is supplied in the call.)
  println((1 to 4).foldLeft(1)(_ * _))

  //5. Write a function largest(fun: (Int) => Int, inputs: Seq[Int]) that yields the largest value of a function
  //within a given sequence of inputs. For example, largest(x => 10 * x - x * x, 1 to 10) should return 25. Don’t use a loop or recursion.
  def largest(fun: (Int)=>Int, inputs:Seq[Int])= {
    inputs.map(fun).reduceLeft((x,y)=> if(y>x) y else x)
  }

  println(largest(x=>10*x-x*x, 1 to 10))

  //6. Modify the previous function to return the input at which the output is largest. For example,
  // largestAt(x => 10 * x - x * x, 1 to 10) should return 5. Don’t use a loop or recursion.
  def largest2(fun: (Int)=>Int, inputs:Seq[Int])={
    inputs.zipWithIndex.map(x => (fun(x._1),x._2+1)).reduceLeft((x,y)=>if(y._1>x._1)y else x)._2
  }
  println(largest2(x=>10*x-x*x, 1 to 10))

  //7. Write a function adjustToPair that receives a function of type (Int, Int) => Int and returns the
  //equivalent function that operates on a pair. For example, adjustToPair(_ * _)((6, 7)) is 42.
  //Then use this function in conjunction with map to compute the sums of the elements in pairs.
  def adjustToPair(func:(Int,Int) => Int) : ((Int,Int)) => Int = {
    (pair:(Int,Int)) => func(pair._1, pair._2)
  }
  val pairs = (1 to 10) zip (11 to 20)
  println(pairs.map(adjustToPair(_ * _)))
  println(pairs.map(adjustToPair(_ + _)))

  //println(pairs.map(m => m._1 * m._2))

  //8. In Section 12.8, “Currying,” on page 164, you saw the corresponds method used with two arrays of strings.
  // Make a call to corresponds that checks whether the elements in an array of strings have the lengths given in an array of integers.
  val a = Array("Hello", "World")
  val b = Array(5,10)
  println(a.corresponds(b)(_.length == _))  //corresponds -> Tests whether every element of this mutable indexed sequence relates to the corresponding element of another sequence by satisfying a test predicate.

  //9. Implement corresponds without currying. Then try the call from the preceding exercise. What problem do you encounter?
  val c = Array("Hello", "World")
  def myCorresponds(a: Array[String], b: Array[Int]): Boolean = {
    a.zip(b).foreach( x => {
      if (x._1.length != x._2) return  false
      }
    )
    true
  }
  println(myCorresponds(a,b))

  //10. Implement an unless control abstraction that works just like if, but with an inverted condition.
  //Does the first parameter need to be a call-by-name parameter? Do you need currying?


}

