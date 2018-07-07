import processing.core.PShape

abstract class ShapeObj(parent: Main,vertices: Seq[Point2d]) extends Scene(parent) {

  val initpos: Point2d
  //  if (initpos.x == initpos.y && initpos.x == 0) {
//    initpos = parent.center
//  }
//  val shape: PShape
  var color: Int = Main.randomColor()
  var pos: Point2d = initpos
  var scale: Float = 1
  var angle: Float = 0

  var shape: PShape = new PShape()//parent.createShape() //= shape(vertices)
  def vert(pnt: Point2d):Unit = shape.vertex(pnt.x,pnt.y)

  override def init(): Unit = {
    shape = parent.createShape()
    shape.beginShape()
    shape.fill(color)
    vertices.foreach(
      pnt => vert(pos + pnt)
    )
    shape.endShape()
    shape
  }

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
