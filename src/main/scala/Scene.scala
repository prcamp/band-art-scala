import oscP5.{OscEventListener, OscMessage, OscStatus}
import processing.core.PApplet

trait Scenery {
  def init(): Unit
  def control(): Unit
  def curscene(): Unit
}

abstract class Scene(parent:PApplet) extends Scenery with OscEventListener {
  override def oscEvent(oscMessage: OscMessage): Unit = {
    print(s"\r osc message ${oscMessage.addrPattern()}, arguments: ${oscMessage.arguments().mkString(", ")}")
  }

  override def oscStatus(oscStatus: OscStatus): Unit = {
    print(s"\r osc status \n\t $oscStatus")
  }
}
