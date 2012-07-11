package by.scala.pokertypes.magic

import by.scala.pokertypes.magic.UnionType._
import by.scala.pokertypes.magic.Card._
import shapeless._

/**
 * User: remeniuk
 */

//class Desk[GameStage <: Stage, Cards: (NoCards |∨| FlopCards |∨| TurnCards |∨| RiverCards)#λ](cards: Cards = HNil) {
//
//  def withCards[NewStage <: Stage, NewCards: (NoCards |∨| FlopCards |∨| TurnCards |∨| RiverCards)#λ](newCards: NewCards) =
//    new Desk[NewStage, NewCards](newCards)
//
//}
object Player {

  trait PlayerStageOrder[Current <: Stage, Next <: Stage]

  implicit val toFlop = new PlayerStageOrder[Preflop, Flop] {}

}

class Player[GameStage <: Stage, Cards: (NoCards |∨| HandCards)#λ](val cards: Cards = HNil) {

  import Player._

  type IsStageValid[Next <: Stage] = PlayerStageOrder[GameStage, Next]

  def withCards[NewStage <: Stage : IsStageValid](newCards: HandCards) =
    new Player[NewStage, HandCards](newCards)

}

//class TypesafeDealer[GameStage <: Stage](cards: List[Card]) {
//
//  def dealCards(player: Player[Preflop, NoCards])(implicit canDealPlayerCards: GameStage <:< Preflop) =
//    (new TypesafeDealer[GameStage](cards.drop(2)), player.withCards[Flop, HandCards](cards.last :: cards.drop(1).last :: HNil))
//
//}