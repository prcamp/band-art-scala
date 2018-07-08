import processing.core.PShape

abstract case class ShapeObj(parent: Main, var shape: PShape) extends Scene(parent) {

  val initpos: Point2d

  var color: Int = Main.randomColor()
  var pos: Point2d = initpos
  var scale: Float = 1
  var angle: Float = 0

  def iteration(): Unit

  override def curscene(): Unit = {
    iteration()
    parent.pushMatrix()
    shape.setFill(color)
    //    parent.translate(-pos.x,-pos.y)
    if (scale != 1) {
      shape.scale(scale)
      scale = 1
    }

    parent.rotate(angle)
    parent.translate(pos.x,pos.y)//pos.x,pos.y) // Works!
    parent.shape(shape)
    parent.popMatrix()
  }
}

abstract class ShapePolyObj(parent: Main, vertices: Seq[Point2d]) extends ShapeObj(parent,new PShape()) {
  override def init(): Unit = {
//    super.init()
    shape = parent.createShape()
    def vert(pnt: Point2d):Unit = shape.vertex(pnt.x,pnt.y)
    shape.beginShape()
    shape.fill(color)
    vertices.foreach(
      pnt => vert(pos + pnt)
    )
    shape.endShape()
    shape
  }
}
