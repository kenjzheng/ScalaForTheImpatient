package Chapter4

import java.util.Properties

import scala.collection.mutable

/**
  * Created by Ken.J.Zheng on 8/1/2018.
  */
object Questions extends App {
  //1. Set up a map of prices for a number of gizmos that you covet.
  // Then produce a second map with the same keys and the prices at a 10 percent discount.
  val gizmos = Map("g1"->2.1D,"g2"->3.4D,"t1"->8D,"t2"->11.2D)
  val nGizmos = gizmos.map(m => (m._1,m._2*0.9))
  for ((k,v) <- nGizmos) println(k +"->" + v)

  //2. Write a program that reads words from a file. Use a mutable map to count how often each word appears.
  //To read the words, simply use a java.util.Scanner:
  val words = mutable.Map[String,Int]()
  val in = new java.util.Scanner(new java.io.File("C:\\Workshop\\ScalaForTheImpatient\\src\\main\\scala\\Chapter4\\myfile.txt"))
  while (in.hasNext()) {
    val word: String = in.next()
    if(words.contains(word))
      words(word)=words(word)+1
    else
      words(word)=1
  }
  in.close()
  println(words)

  //3. Repeat the preceding exercise with an immutable map.
  var words2 = scala.collection.immutable.Map[String,Int]()
  val in2 = new java.util.Scanner(new java.io.File("C:\\Workshop\\ScalaForTheImpatient\\src\\main\\scala\\Chapter4\\myfile.txt"))
  while (in2.hasNext()){
    val word = in2.next()
    val count = words2.getOrElse(word,0)+1
    words2 = words2 + (word -> count)
  }
  in2.close()
  println(words2)

  //4. Repeat the preceding exercise with a sorted map, so that the words are printed in sorted order.
  var words3 = scala.collection.immutable.SortedMap[String,Int]()
  val in3 = new java.util.Scanner(new java.io.File("C:\\Workshop\\ScalaForTheImpatient\\src\\main\\scala\\Chapter4\\myfile.txt"))
  while (in3.hasNext()){
    val word = in3.next()
    val count = words3.getOrElse(word,0)+1
    words3 = words3 + (word -> count)
  }
  in3.close()
  println(words3)

  //5. Repeat the preceding exercise with a java.util.TreeMap that you adapt to the Scala API.
  import scala.collection.JavaConversions.mapAsScalaMap
  val words4: scala.collection.mutable.Map[String, Int] = new java.util.TreeMap[String, Int]()
  val in4 = new java.util.Scanner(new java.io.File("C:\\Workshop\\ScalaForTheImpatient\\src\\main\\scala\\Chapter4\\myfile.txt"))
  while (in4.hasNext()) {
    val word: String = in4.next()
    if(words4.contains(word))
      words4(word)=words4(word)+1
    else
      words4(word)=1
  }
  in4.close()
  println(words4)

  //6. Define a linked hash map that maps "Monday" to java.util.Calendar.MONDAY,
  //and similarly for the other weekdays. Demonstrate that the elements are visited in insertion order.
  val lhm = scala.collection.mutable.LinkedHashMap[String,Int]()
  lhm("Monday")=java.util.Calendar.MONDAY
  lhm("Wednesday")=java.util.Calendar.WEDNESDAY
  lhm("Tuesday")=java.util.Calendar.TUESDAY
  lhm("Thursday")=java.util.Calendar.THURSDAY
  lhm("Friday")=java.util.Calendar.FRIDAY
  lhm("Saturday")=java.util.Calendar.SATURDAY
  lhm("Sunday")=java.util.Calendar.SUNDAY
  println(lhm)

  //7. Print a table of all Java properties reported by the getProperties method of the java.lang.System class
  val properties: Properties = java.lang.System.getProperties
  var maxKeyLenght = 0
  properties.map(m => {
    if(m._1.toString.length>maxKeyLenght) maxKeyLenght = m._1.toString.length
  })
  properties.map(m => {
    println(m._1+" "*(maxKeyLenght - m._1.toString.length + 1)+" | "+m._2)
  })

  //8. Write a function minmax(values: Array[Int]) that returns a pair containing the smallest and the largest values in the array.
  def minmax(values: Array[Int]): (Int,Int)={
    var min = values(0)
    var max = values(0)

    values.map(m => {
      if (m>max) max = m
      if (m<min) min = m
    })
    (min,max)
  }

  println(minmax(Array(1,2,3,5,8,2,0,10)))

  //9. Write a function lteqgt(values: Array[Int], v: Int) that returns a triple containing
  //the counts of values less than v, equal to v, and greater than v.
  def lteggt(values: Array[Int], v: Int):(Int,Int,Int)={
    var lessCount = 0
    var equalCount = 0
    var greaterCount = 0

    values.map(m => {
      if(m>v) greaterCount += 1
      if(m==v) equalCount += 1
      if(m<v) lessCount +=1
    })
    (lessCount,equalCount,greaterCount)
  }

  println(lteggt(Array(1,2,3,5,8,2,0,10),5))

  //10. What happens when you zip together two strings, such as "Hello".zip("World")? Come up with a plausible use case.
  println("Hello".zip("World"))
  //usually you will use zipWithIndex on a collection to associate index with data in the collection.
}
