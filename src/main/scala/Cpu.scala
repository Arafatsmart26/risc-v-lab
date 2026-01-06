import chisel3._
import chisel3.util._

class Cpu extends Module {
  val io = IO(new Bundle {
    // senere: memory/bus/uart osv.
  })

  // -------------------------
  // PC (program counter)
  // -------------------------
  val pc = RegInit(0.U(32.W))

  // 32-bit instruction register (fetched instruction)
  val instr = Wire(UInt(32.W))

  // -------------------------
  // FETCH stage (hardcoded)
  // -------------------------
  // addi x1, x0, 0x123  => 0x12300093
  instr := "h12300093".U

  // "Next PC" (for nu bare pc + 4)
  pc := pc + 4.U
}

object CpuMain extends App {
  println("Generating CPU Verilog...")
  emitVerilog(
    new Cpu(),
    Array("--target-dir", "generated")
  )
}
