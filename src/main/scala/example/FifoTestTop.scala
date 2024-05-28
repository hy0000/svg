package example

import scala.language.{implicitConversions, postfixOps}
import core.*
import uvm.*

import java.lang

class FifoTestTop extends TestTop {
  val dut = new FiFoDut
  val wAgent = StreamAgent(dut.wIf, dut.wcd)
  val rAgent = StreamAgent(dut.rIf, dut.rcd)
}

class FiFoDut extends Dut {
  val wIf = new StreamIf(16)
  val rIf = new StreamIf(8)
  val rcd = ClockDomain(400 MHz)
  val wcd = ClockDomain(200 MHz)

  setTop("fifo.v")
  connect(
    "rvalid" -> rIf.valid,
    "rready" -> rIf.ready,
    "rdata" -> rIf.data,
    "rclk" -> rcd.clk,
    "wvalid" -> wIf.valid,
    "wready" -> wIf.ready,
    "wdata" -> wIf.data,
    "wclk" -> wcd.clk,
    "rstn" -> rcd.rstn.&(wcd.rstn)
  )
}

class StreamIf(dataWidth: Int) extends Interface {
  val valid = Logic(1)
  val ready = Logic(1)
  val data  = Logic(dataWidth)
}

class StreamAgent(streamIf: StreamIf, cd: ClockDomain) extends UvmAgent {
  val drv = StreamDriver(streamIf, cd)
  val mon = StreamMonitor(streamIf, cd)
}
class StreamDriver(streamIf: StreamIf, cd: ClockDomain) extends UvmDriver {
  def drive[T<:UvmTransaction](tr: T, vif: StreamIf): Unit = {

  }
}
class StreamMonitor(streamIf: StreamIf, cd: ClockDomain) extends UvmMonitor
