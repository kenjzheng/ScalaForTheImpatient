package Chapter13

/**
  * Created by Ken.J.Zheng on 8/6/2018.
  */
object Questions extends App {
  //1. Write a function that, given a string, produces a map of the indexes of all characters. For example,
  // indexes("Mississippi") should return a map associating 'M' with the set {0}, 'i' with the set {1, 4, 7, 10},
  // and so on. Use a mutable map of characters to mutable sets. How can you ensure that the set is sorted?
  def strIndex(str: String) = {
    str.zipWithIndex.groupBy(_._1).map(m => {
      m._1 -> m._2.map(m => m._2).toSet
    })
  }

  def strIndex2(str: String) = {
    val map = str.zipWithIndex.groupBy(_._1).map(m => {
      val set = scala.collection.mutable.HashSet[Int]()
      m._1 -> m._2.foldLeft(set)((z, i) => z += i._2)
    })
    val mutableMap = scala.collection.mutable.Map() ++ map
    mutableMap
  }

  println(strIndex("Mississippi"))
  println(strIndex2("Mississippi"))

  //2. Repeat the preceding exercise, using an immutable map of characters to lists.

  //3. Write a function that removes every second element from a ListBuffer. Try it two ways. Call remove(i)
  // for all even i starting at the end of the list. Copy every second element to a new list. Compare the performance.
  val test1 = scala.collection.mutable.ListBuffer("ab", "cd", "ef", "f", "gh", "ijk", "LMN", "123")
  val test2 = scala.collection.mutable.ListBuffer[String]()
  for (i <- test1.length - 1 to 0 by -1) {
    if (i % 2 != 0) {
      test2.append(test1(i))
      test1.remove(i)
    }
  }
  println(test1)
  println(test2.reverse)

  //4. Write a function that receives a collection of strings and a map from strings to integers. Return a collection of
  //integers that are values of the map corresponding to one of the strings in the collection. For example,
  //given Array("Tom", "Fred", "Harry") and Map("Tom" -> 3, "Dick" -> 4, "Harry" -> 5), return Array(3, 5).
  //Hint: Use flatMap to combine the Option values returned by get.
  def func1(array: Array[String], map: Map[String, Int]): Array[Int] = {
    val r = scala.collection.mutable.ArrayBuffer[Int]()
    array.foreach(a => {
      if (map.contains(a)) {
        r.append(map(a))
      }

    })
    r.toArray
  }

  println(func1(Array("Tom", "Fred", "Harry"), Map("Tom" -> 3, "Dick" -> 4, "Harry" -> 5)).mkString(","))

  //5. Implement a function that works just like mkString, using reduceLeft.
  def func2[T](c: collection.Iterable[T], sep: String) = {
    c.map(x => x.toString).reduceLeft(_ + sep + _)
  }

  println(func2(Array(1, 2, 3, 4, 5), ","))

  //8. Write a function that turns an array of Double values into a two-dimensional array. Pass the number of
  // columns as a parameter. For example, with Array(1, 2, 3, 4, 5, 6) and three columns, return Array(Array(1, 2, 3),
  // Array(4, 5, 6)). Use the grouped method.
  def grouped(array: Array[Double], col: Int): Array[Array[Double]] = {
    val test: Iterator[Array[Double]] = array.grouped(col)
    test.toArray
  }

  val g = grouped(Array(1, 2, 3, 4, 5, 6), 3)
  g.foreach(i => {
    println(i.mkString(","))
  })

}