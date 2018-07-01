package oldscene

import processing.core.PApplet

case class Scene(parent:PApplet) {

  def keyPressed(): Unit = parent.setup()


}
