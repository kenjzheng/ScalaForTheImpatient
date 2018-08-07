package Chapter8

/**
  * Created by Ken.J.Zheng on 8/2/2018.
  */
object Questions extends App {
  //question 1
  val checkAccount = new CheckingAccount(100)
  println(checkAccount.deposit(50))
  println(checkAccount.currentBalance)

  val bankAccount = new BankAccount(100)
  println(bankAccount.deposit(50))

  //question 2
  val saving = new SavingsAccount(100)
  println("first deposit " + saving.deposit(50))
  println("second deposit " + saving.deposit(50))
  println("third deposit " + saving.deposit(50))
  println("forth deposit " + saving.deposit(50))
  println("interest "+ saving.earnMonthlyInterest)
  println("fifth deposit " + saving.deposit(50))

  //question 4
  val orange = new SimpleItem(0.3,"orange")
  val fruitBundle = Bundle(SimpleItem(0.1,"apple"),SimpleItem(0.2,"pear"),orange)
  println(fruitBundle.price)
  val grape = new SimpleItem(0.4,"grape")
  fruitBundle.add(grape)
  println(fruitBundle.price)

  //question 7
  val square1 = new Square(width = 8)
  val square2 = new Square()
}

//1. Extend the following BankAccount class to a CheckingAccount class that charges $1 for every deposit and withdrawal.
class BankAccount(initialBalance: Double) {
  private var balance = initialBalance
  def currentBalance = balance
  def deposit(amount: Double) = { balance += amount; balance }
  def withdraw(amount: Double) = { balance -= amount; balance }
}

class CheckingAccount(initialBalance:Double) extends BankAccount(initialBalance) {
  override def withdraw(amount:Double)={
    super.withdraw(amount+1)
  }
  override def deposit(amount:Double)={
    super.deposit(amount-1)
  }
}

//2. Extend the BankAccount class of the preceding exercise into a class SavingsAccount that earns interest every month
//(when a method earnMonthlyInterest is called) and has three free deposits or withdrawals every month.
// Reset the transaction count in the earnMonthlyInterest method.
class SavingsAccount(initialBalance:Double) extends BankAccount(initialBalance){
  private var transactions = 3
  private val interestRate = 0.1D
  def earnMonthlyInterest: Double = {
    transactions = 3
    deposit(currentBalance*interestRate)
  }

  override def withdraw(amount:Double)={
    if(transactions>0) {
      transactions -= 1
      super.withdraw(amount)
    }
    else
      super.withdraw(amount+1)
  }

  override def deposit(amount:Double)={
    if(transactions>0){
      transactions -= 1
      super.deposit(amount)
    }
    else
      super.deposit(amount-1)
  }
}

//4. Define an abstract class Item with methods price and description. A SimpleItem is an item
//whose price and description are specified in the constructor. Take advantage of the fact that a
//val can override a def. A Bundle is an item that contains other items. Its price is the sum of
//the prices in the bundle. Also provide a mechanism for adding items to the bundle and a suitable description method.
abstract class Item {
  def price : Double
  def description : String
}

/*class SimpleItem(itemPrice:Double, itemDescription:String) extends Item {
  val price = itemPrice
  val description = itemDescription
}*/

class SimpleItem(val price:Double, val description:String) extends Item {}
object SimpleItem {
  def apply(price: Double,description: String): SimpleItem = new SimpleItem(price,description)
}

class Bundle extends Item {
  private val bundleItems = scala.collection.mutable.ArrayBuffer[Item]()

  def price:Double = {
    bundleItems.foldLeft(0.0D)((z,i)=> z + i.price)
  }

  def description:String = {
    ""
  }

  def add(item: Item):Unit = {
    bundleItems.append(item)
  }
}

object Bundle {
  def apply(items: Item *): Bundle = {
    val bundle = new Bundle()
    items.map (m => bundle.add(m))
    bundle
  }
}

//5. Design a class Point whose x and y coordinate values can be provided in a constructor.
//Provide a subclass LabeledPoint whose constructor takes a label value and x and y coordinates, such as
//new LabeledPoint("Black Thursday", 1929, 230.07)
class Point(x:Double,y:Double)
class LabeledPoint(label:String,x:Double,y:Double) extends Point(x,y)

//6. Define an abstract class Shape with an abstract method centerPoint and subclasses Rectangle and Circle.
// Provide appropriate constructors for the subclasses and override the centerPoint method in each subclass.
abstract class Shape {
  def centerPoint : (Double,Double)
}

class Rectangle(val centerPoint:(Double,Double)) extends Shape
class Circle(val centerPoint:(Double,Double)) extends Shape

//7. Provide a class Square that extends java.awt.Rectangle and has three constructors: one that constructs a
// square with a given corner point and width, one that constructs a square with corner (0, 0) and a given width,
// and one that constructs a square with corner (0, 0) and width 0.
class Square(corner:(Int,Int),width:Int) extends java.awt.Rectangle(corner._1,corner._2,width,width) {
  def this(){
    this((0,0),0)
  }
  def this(width:Int){
    this((0,0),width)
  }
}