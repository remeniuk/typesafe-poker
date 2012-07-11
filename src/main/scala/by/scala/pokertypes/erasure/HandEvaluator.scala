package by.scala.pokertypes.erasure

import by.scala.pokertypes.variance._

/**
 * User: remeniuk
 */

object HandEvaluator {

  def evaluate[T <: Suit : Manifest](hand: Hand[T]) = {
    println("Type parameter class: " + manifest[T].erasure)
    if (manifest[T] <:< manifest[Spades]) println("Spades")
    else println("Random")
  }

  val hand1: Hand[Spades] = new Hand[Nothing].add(new Card[Spades]("A")).add(new Card[Spades]("Q"))
  val hand2: Hand[Suit] = new Hand[Nothing].add(new Card[Spades]("A")).add(new Card[Hearts]("Q"))

  println(evaluate(hand1))
  println(evaluate(hand2))

}