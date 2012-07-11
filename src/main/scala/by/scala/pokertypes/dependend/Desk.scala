package by.scala.pokertypes.dependend

import collection.mutable

/**
 * User: remeniuk
 */

case class Desk(id: Int) {

  case class Action(name: String)

  private val history = mutable.Queue[Action]()

  def log(action: Action) = history += action

}

object DeskExample {

  val desk1 = Desk(1)
  val desk2 = Desk(2)

  desk1.log(desk1.Action("Small Blind"))
  // desk1.log(desk2.Action("Small Blind")) doesn't compile

}