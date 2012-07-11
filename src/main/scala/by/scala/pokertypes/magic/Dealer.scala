package by.scala.pokertypes.magic

import actors.Actor
import by.scala.pokertypes.magic.Card._
import shapeless._

/**
 * User: remeniuk
 */
class Dealer[LastStage <: Stage](deck: List[Card]) {

  type IsCurrentStageValid[T <: Stage] = StageOrder[LastStage, T]

  def deal[CurrentStage <: Stage : IsCurrentStageValid : Manifest](recipient: Actor): Dealer[CurrentStage] =
    implicitly[Manifest[CurrentStage]].erasure match {
      case c if (c.isInstanceOf[Preflop]) => dealCards[CurrentStage](recipient, 2)
      case c if (c.isInstanceOf[Flop]) => dealCards[CurrentStage](recipient, 3)
      case c if (c.isInstanceOf[Turn] || c.isInstanceOf[Showdown]) => dealCards[CurrentStage](recipient, 1)
    }

  private def dealCards[NextStage <: Stage](recipient: Actor, count: Int) = {
    recipient ! deck.take(2)
    new Dealer[NextStage](deck.dropRight(2))
  }

  // alternative typesafe presentation
  def dealCards[NextStage <: Stage](player: Player[Preflop, NoCards])
                                   (implicit canDealPlayerCards: LastStage <:< Preflop,
                                    isValid: IsCurrentStageValid[NextStage]) =
    (new Dealer[NextStage](deck.drop(2)), player.withCards[Flop](deck.last :: deck.drop(1).last :: HNil))

}