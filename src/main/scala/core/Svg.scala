package core

import scala.annotation.targetName
import scala.math.BigDecimal
import scala.runtime.Nothing$

abstract class Svg

class Class

class Module extends Svg

class Interface extends Svg {
  def <>(that: Interface) = that
}

class Dut extends Svg{
  def setTop(path: String): Unit = {

  }

  def connect(m: (String, Logic)*): Unit = {

  }
}

class ClockDomain {
  val clk = Logic(1)
  val rstn = Logic(1)
  var freq: Option[HertzNumber] = None
  
  def :=(that: ClockDomain) = that
}

object ClockDomain {
  def apply() = new ClockDomain

  def apply(freq: HertzNumber): ClockDomain = {
    val cd = new ClockDomain
    cd.freq = Some(freq)
    cd
  }
}

class Logic(bits: Int) {

  def &(that: Logic) = that
}

class HertzNumber(value: BigDecimal)

given Conversion[Int, HertzNumber] = HertzNumber(_)
extension (value: Int)
  def MHz: HertzNumber = HertzNumber(value * BigDecimal(1e6))