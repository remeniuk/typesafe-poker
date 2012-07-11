package by.scala.pokertypes.magic

import shapeless._
import UnionType._
import Card._

/**
 * User: remeniuk
 */

trait HandEvaluator {

  def evaluate[Cards <% List[Card]](cards: Cards): Int

  def compare[T: (HandCards |∨| (RiverCards :: HandCards))#λ](hand1: T, hand2: T): Int

}