package by.scala.pokertypes.magic

/**
 * User: remeniuk
 */

sealed trait Stage

trait Preflop extends Stage

trait PostPreflop extends Stage

trait Preflop2 extends Preflop

trait Preflop1 extends Preflop

trait Flop extends PostPreflop

trait Turn extends PostPreflop

trait River extends PostPreflop

trait Showdown extends PostPreflop

trait StageOrder[Last <: Stage, Current <: Stage]

object StageOrder {

  implicit val gotoPreflop2 = new StageOrder[Preflop, Preflop2] {}
  implicit val gotoPreflop1 = new StageOrder[Preflop2, Preflop1] {}
  implicit val gotoFlop = new StageOrder[Preflop1, Flop] {}
  implicit val gotoTurn = new StageOrder[Flop, Turn] {}
  implicit val gotoRiver = new StageOrder[Turn, River] {}

}