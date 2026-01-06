import chisel3._
import chisel3.util._

class Cpu extends Module {
  val io = IO(new Bundle {
    // senere: memory/bus/uart osv.
  })

  // PC (program counter)
  val pc = RegInit(0.U(32.W))

  // 32-bit instruction register
  val instr = Wire(UInt(32.W))

  // FETCH stage (hardcoded)
  instr := "h12300093".U

  // Decode fields (I-type)
  val opcode = instr(6, 0)
  val rd     = instr(11, 7)
  val funct3 = instr(14, 12)
  val rs1    = instr(19, 15)
  val immI   = instr(31, 20).asSInt

  // Next PC
  pc := pc + 4.U
}

object CpuMain extends App {
  println("Generating CPU Verilog...")
  emitVerilog(
    new Cpu(),
    Array("--target-dir", "generated")
  )
}
