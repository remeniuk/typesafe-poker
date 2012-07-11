package by.scala.pokertypes.viewbounds

/**
 * User: remeniuk
 */

trait Card

class Hand(cards: List[Card]) {

  def weight = 1

}

object Hand {

  implicit def toHand(cards: List[Card]) = new Hand(cards)

}

class HandComparator {

  def compare[T <% Hand](hand1: T, hand2: T) = hand1.weight compareTo hand2.weight

}

object HandComparator {

  // 1.
  import Hand._
  List[Card]().weight

  // 2.
  new HandComparator().compare(List[Card](), List[Card]())

}

