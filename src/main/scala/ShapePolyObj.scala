import Colors.ColorCoords
import processing.core.{PShape, PConstants => pc}

abstract case class ShapeObj(parent: Main, var shape: PShape) extends Scene(parent) {

  var easing = .1

  val initpos: Point2d
  var clrcoords: Colors.ColorCoords = ColorCoords(156, 0, 80)
  var clrcoords0 = clrcoords

  var pos: Point2d = initpos
  var scale: Float = 1
  var angle: Float = 0

  def iteration(): Unit = {
    clrcoords = ColorCoords(
      (clrcoords0.h*(1-easing) + clrcoords.h*easing).toInt,
      (clrcoords0.s*(1-easing) + clrcoords.s*easing).toInt,
      (clrcoords0.b*(1-easing) + clrcoords.b*easing).toInt
    )
  }

  override def curscene(): Unit = {
    iteration()
    parent.pushMatrix()
    shape.setFill(clrcoords.toColor(parent))
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
    shape.fill(clrcoords.toColor(parent))
    vertices.foreach(
      pnt => vert(pos + pnt)
    )
    shape.endShape()
  }
}
