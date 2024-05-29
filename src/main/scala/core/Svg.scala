package core

import scala.math.BigDecimal

abstract class Svg

class Class extends Svg

class Module extends Svg

class Interface extends Svg {
  def <>(that: Interface) = that
}

class Dut extends Svg{
  def setTop(path: String): Unit = ???

  def connect(m: (String, Logic)*): Unit = ???

  def autoConnect(b: MultiData): Unit = ???
}

class ClockDomain {
  val clk = Logic(1)
  val rstn = Logic(1)
  var freq: Option[HertzNumber] = None
  
  def :=(that: ClockDomain) = that
  
  def runAt(freq: HertzNumber): Unit = ???
}

object ClockDomain {
  def apply() = new ClockDomain
}

class Logic(bits: Int) {

  def &(that: Logic) = that
}

class HertzNumber(value: BigDecimal)
class TimeNumber(value: BigDecimal)

given Conversion[Int, HertzNumber] = HertzNumber(_)
extension (value: Int)
  def MHz: HertzNumber = HertzNumber(value * BigDecimal(1e6))
  
given Conversion[Int, TimeNumber] = TimeNumber (_)
  extension (value: Int)
  def us: TimeNumber = TimeNumber(value * BigDecimal(1e6))

trait MultiData

trait Data

class Bundle extends Data, MultiData

class Coverage