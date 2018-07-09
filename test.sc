import scala.collection.mutable.ArrayBuffer

case class Signal(baseWave: Double => Double, var frequency: Double) {
  val time0 = System.currentTimeMillis()
  def signal(): Double = signal(System.currentTimeMillis())
  def signal(t: Long): Double = baseWave(frequency*(t - time0)/1000)
}

object Signal {
  def osc(frequency: Double) = new Signal(t => (Math.sin(2*Math.PI*t)+1)/2, frequency) {

  }
  def saw(frequency: Double) = new Signal(t => t % 1, frequency) {}
  def tri(frequency: Double) = new Signal(t => 2*Math.min(t % 1, 1 - t % 1),frequency) {}
}

val osc = Signal.osc(1)


osc.signal(0.toLong)

(0 until 5).foreach{
  _ => print("\rhi\n")//\n\r\t how are you? \n\r\t I'm fine thanks \n")
}

val x = ArrayBuffer[Int](1,2,3,4)

x.map(
  i => println(i)
)