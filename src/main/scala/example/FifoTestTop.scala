package example

import core._

class FifoTestTop extends Module {
  val topIf = new Interface
  val dut = new Dut("/233.v")
  // dut <> TopIf
}
