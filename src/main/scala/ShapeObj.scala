import processing.core.PShape

abstract class ShapeObj(parent: Main,vertices: Seq[Point2d]) extends Scene(parent) {

  val initpos: Point2d
  //  if (initpos.x == initpos.y && initpos.x == 0) {
//    initpos = parent.center
//  }
//  val shape: PShape
  var color: Int
  var pos: Point2d = initpos

  var shape = new PShape()

  def shape(vertices: Seq[Point2d]): PShape = {
    val shape = parent.createShape()
    def vert(pnt: Point2d):Unit = shape.vertex(pnt.x,pnt.y)
    shape.beginShape()
    shape.fill(color)
    vertices.foreach(
      pnt => vert(pos + pnt)
    )
    shape.endShape()
    shape
  }

  override def init(): Unit = {
    shape = shape(vertices)
  }
}
