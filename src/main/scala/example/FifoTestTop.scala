package example

import scala.language.{implicitConversions, postfixOps}
import core.*
import uvm.*

class FifoTestTop extends TestTop {
  // define components
  val dut = new FiFoDut
  val wAgent = StreamAgent(dut.wIf, dut.wcd)
  val rAgent = StreamAgent(dut.rIf, dut.rcd)
  val ref = FifoRef()
  val scb = FifoScb()

  // do connection
  wAgent.mon.ap.queue() >> ref.port
  scb.exp << ref.ap
  scb.act << rAgent.mon.ap

  // gen clk
  dut.rcd.runAt(100 MHz)
  dut.wcd.runAt(400 MHz)
  setTimeout(10000 us)

  // define test case
  val cov = FifoCov()
  val testCase = Seq(
    FifoTestCase01(),
    FifoTestCase02(),
  )
  
  // bind sequence
  testCase.foreach(_.tr >> wAgent.drv)
}

class FiFoDut extends Dut {
  val wIf = new StreamIf(16)
  val rIf = new StreamIf(8)
  val rcd = ClockDomain()
  val wcd = ClockDomain()

  setTop("fifo.v")
  //autoConnectByName(wIf, rIf, wcd, rcd)
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

class StreamDriver(vif: StreamIf, cd: ClockDomain) extends UvmDriver

class StreamMonitor(streamIf: StreamIf, cd: ClockDomain) extends UvmMonitor {
  val ap = ReadTransaction()
}

class ReadTransaction extends UvmTransaction

class WriteTransaction extends UvmTransaction

class FifoRef {
  val port = WriteTransaction()
  val ap = ReadTransaction()
}

class FifoScb{
  val exp, act = ReadTransaction()
}

class FifoSqr extends UvmSequencer

class FifoCov extends Coverage

class FifoTestBase extends UvmTest {
  val tr = WriteTransaction()
}

class FifoTestCase01 extends FifoTestBase 
class FifoTestCase02 extends FifoTestBase