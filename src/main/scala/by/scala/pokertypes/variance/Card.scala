package by.scala.pokertypes.variance

/**
 * User: remeniuk
 */

trait Suit

trait Spades extends Suit

trait Hearts extends Suit

trait Clubs extends Suit

trait Diamonds extends Suit

class Card[+T <: Suit](rank: String)

class Hand[A <: Suit](cards: List[Card[A]] = Nil) {

  def add[B >: A <: Suit](card: Card[B]): Hand[B] = new Hand[B](card :: cards)

  def evaluate(evaluator: HandEvaluator[A]) = evaluator.evaluate(cards)

}

class HandEvaluator[-T <: Suit](val evaluate: List[Card[T]] => Int)

object HandEvaluator {

  implicit val flushEvaluator = new HandEvaluator((_: List[Card[Spades]]) => 1000)

  implicit val genericEvaluator = new HandEvaluator((_: List[Card[Suit]]) => 100)

}

object HandExample {

  import HandEvaluator._

  val hand1: Hand[Spades] = new Hand[Nothing].add(new Card[Spades]("A")).add(new Card[Spades]("Q"))
  val hand2: Hand[Suit] = new Hand[Nothing].add(new Card[Spades]("A")).add(new Card[Hearts]("Q"))

  def test = {
    println(hand1.evaluate(flushEvaluator))
    println(hand1.evaluate(genericEvaluator))
    //println(evaluate(cards))
    //println(evaluate(cards2))
  }

}
