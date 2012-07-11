package by.scala.pokertypes.higherorder

/**
 * User: remeniuk
 */

trait Game

trait Poker extends Game

trait BlackJack extends Game

class Desk[T <: Game](val playersCount: Int)

class GameDesk[T <: Game](playersCount: Int) extends Desk[T](playersCount)

class TournamentDesk[T <: Game](playersCount: Int) extends Desk[T](playersCount)

trait DeskRebalancer[D[DG <: Game] <: Desk[DG], G <: Game] {

  def rebalanceDesks(desks: List[D[G]]): List[D[G]] = {
    val avg = desks.map(_.playersCount).sum / desks.size
    desks.filter(_.playersCount > avg)
    /*...*/
  }

}

class PokerTournamentManager extends DeskRebalancer[TournamentDesk, Poker] {
  override def rebalanceDesks(desk: List[TournamentDesk[Poker]]) = {
    super.rebalanceDesks(desk)
    /*...*/
  }
}

