package uvm

import core._

abstract class UvmObject extends Class

abstract class UvmReportObject extends UvmObject

abstract class UvmComponent extends UvmReportObject

abstract class UvmAgent extends UvmComponent

abstract class UvmDriver extends UvmComponent {
  val seqItemPort: UvmTransaction = ???
}

abstract class UvmEnv extends UvmComponent

abstract class UvmMonitor extends UvmComponent

abstract class UvmRoot extends UvmComponent

abstract class UvmScoreboard extends UvmComponent

abstract class UvmTransaction extends UvmObject {
  def >>(that: UvmDriver): Unit = ???
  def >>(that: UvmTransaction): Unit = ???
  def <<(that: UvmTransaction): Unit = that >> this
  def queue(): UvmTransaction = ???
}

abstract class UvmTest

abstract class UvmSequencer {
  val seqItemExport: UvmTransaction = ???
}