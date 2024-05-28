package uvm

import core._

abstract class UvmObject extends Class

abstract class UvmReportObject extends UvmObject

abstract class UvmComponent extends UvmReportObject

abstract class UvmAgent extends UvmComponent

abstract class UvmDriver extends UvmComponent

abstract class UvmEnv extends UvmComponent

abstract class UvmMonitor extends UvmComponent

abstract class UvmRoot extends UvmComponent

abstract class UvmScoreboard extends UvmComponent

