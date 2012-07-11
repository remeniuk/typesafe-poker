package by.scala.pokertypes.magic

import actors.Actor
import by.scala.pokertypes.magic.UnionType._
import shapeless._
import Card._
import Player._

/**
 * User: remeniuk
 */

object Examples {

  val deck = List[Card]()
  val dealer = new Dealer[Preflop](deck)
  val typesafeDealer = new Dealer[Preflop](deck)

  val players = List[Actor]()
  val desk = new Actor {
    def act() {}
  }

  //val typesafeDealer = new TypesafeDealer[Preflop](List[Card]())

  val typesafePlayer1 = new Player[Preflop, NoCards]()
  val typesafePlayer2 = new Player[Preflop, NoCards]()
  val typesafePlayer3 = new Player[Preflop, NoCards]()

  val (typesafeDealer2, typesafePlayer1WithCards) = typesafeDealer.dealCards[Preflop2](typesafePlayer1)
  // typesafeDealer2.dealCards(typesafePlayer1WithCards)
  val (typesafeDealer3, typesafePlayer2WithCards) = typesafeDealer2.dealCards[Preflop1](typesafePlayer2)

  import StageOrder._

  dealer.deal[Preflop2](players(0))
    .deal[Preflop1](players(1))
    .deal[Flop](desk)
    .deal[Turn](desk)
    .deal[River](desk)

  val handEvaluator = new HandEvaluator {
    def evaluate[Cards <% List[Card]](cards: Cards) = 0

    def compare[T: (HandCards |∨| (RiverCards :: HandCards))#λ](hand1: T, hand2: T) = 0
  }

  handEvaluator.compare(typesafePlayer1WithCards.cards, typesafePlayer2WithCards.cards)

  val handCards = new Card :: new Card :: HNil
  val communityCards = new Card :: new Card :: new Card :: new Card :: new Card :: HNil

  handEvaluator.compare(handCards, handCards)
  handEvaluator.compare(communityCards :: handCards, communityCards :: handCards)
  //handEvaluator.compare(handCards, communityCards)

}