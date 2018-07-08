import processing.core.{PShape, PConstants => pc}

case class ACircle(parent: Main) extends Scene(parent) {
  var shape = new PShape()
  val cent = parent.center
  override def init(): Unit = {
    shape = parent.createShape(pc.ELLIPSE,cent.x,cent.y,100,100)
    shape.setFill(Colors.randomColor())
  }

  override def control(): Unit = {}

  override def curscene(): Unit = {
    parent.pushMatrix()
    parent.shape(shape)
    parent.popMatrix()
  }
}
