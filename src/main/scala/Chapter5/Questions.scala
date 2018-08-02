package Chapter5

import scala.beans.BeanProperty

/**
  * Created by Ken.J.Zheng on 8/1/2018.
  */
object Questions extends App {
  //question 1
  val counter = new Counter()
  counter.test(Int.MaxValue)
  counter.increment()
  println(counter.current())

  //question 2
  val account = new BankAcount()
  account.deposit(10)
  println(account.balance)
  //account.balance = 100
  println(account.withdraw(100))

  //question 3
  val time1 = new Time(8,5)
  val time2 = new Time(10,35)
  println(time1.before(time2))
  //time1.hours = 10
  println(time1.before(new Time(8,4)))

  //question 5
  val stuent = new Student
  stuent.name = "Ken"
  println(stuent.getName)
  println(stuent.lastname) //no getLastName since no @BeanProperty

  //question 6
  val person = new Person(-5)
  println(person.ages)

  //question 7
  val person2 = new Person2("Ken Zheng")
  println(person2.firstName)
  person2.name = "K Zheng"
  println(person2.firstName)
}

//1. Improve the Counter class in Section 5.1, “Simple Classes and Parameterless Methods,” on page 55
// so that it doesn’t turn negative at Int.MaxValue.
class Counter {
  private var value = 0 // You must initialize the field  

  def increment() {
    if(value != Int.MaxValue) //disable this to see what will happen
      value += 1
  }

  def current() = value

  def test(value: Int) = {
    this.value = value
  }
}

//2. Write a class BankAccount with methods deposit and withdraw, and a read-only property balance.
class BankAcount {
  private var amount = 0.0D

  def balance = amount

  def deposit(value: Double): Unit ={
    if(value>0) amount += value
  }

  def withdraw(value: Double): Double = {
    if(value>0 && value<=amount){
      amount -= value
      value
    }
    else
      0.0D
  }
}

//3. Write a class Time with read-only properties hours and minutes and a method before(other: Time): Boolean
// that checks whether this time comes before the other.
//A Time object should be constructed as new Time(hrs, min), where hrs is in military time format (between 0 and 23).
class Time(hrs:Int,min:Int) {
  def hours = hrs
  def minutes = min
  def before(other:Time):Boolean = {
    if(this.hours<other.hours)true
    else if(this.hours == other.hours && this.minutes<other.minutes) true
    else false
  }
}

//4. Reimplement the Time class from the preceding exercise so that the internal representation
// is the number of minutes since midnight (between 0 and 24 × 60 – 1).
// Do not change the public interface. That is, client code should be unaffected by your change.
class TimeRe(hrs:Int,min:Int) {
  def hours = hrs
  def minutes = hrs * 60 + min
  def before(other:TimeRe):Boolean = {
    if(this.minutes<other.minutes) true
    else false
  }
}

//5. Make a class Student with read-write JavaBeans properties name (of type String) and id (of type Long).
// What methods are generated? (Use javap to check.) Can you call the JavaBeans getters and setters in Scala? Should you?
class Student {
  @BeanProperty
  var name : String = ""

  @BeanProperty
  var id : Long = 0L

  var lastname : String = ""
}

//6. In the Person class of Section 5.1, “Simple Classes and Parameterless Methods,” on page 55,
//provide a primary constructor that turns negative ages to 0.
class Person(var ages:Int){
  if(ages<0) ages = 0
}

//7. Write a class Person with a primary constructor that accepts a string containing a first name, a space,
// and a last name, such as new Person("Fred Smith").
// Supply read-only properties firstName and lastName. Should the primary constructor parameter be a var, a val, or a plain parameter? Why?
class Person2(var name: String){ //parameter should be val, shouldn't be changed as constructor parameter.
  def firstName = name.split(" ")(0)
  def lastName = name.split(" ")(1)
}