package by.scala.pokertypes.magic

import shapeless._

/**
 * User: remeniuk
 */
object Card {

  type NoCards = HNil

  type HandCards = Card :: Card :: NoCards

  type FlopCards = Card :: Card :: Card :: NoCards

  type TurnCards = Card :: FlopCards

  type RiverCards = Card :: TurnCards

}

class Card