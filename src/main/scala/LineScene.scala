import processing.core.{PShape, PConstants => pc}

case class LineScene(parent: Main) extends ShapeField(parent){
  var numShapes = 1000
  var initposField0 = (0 until numShapes).map(
    _ => parent.randomPoint()
  )
  override val initposField: IndexedSeq[Point2d] =
    initposField0.sortWith{
      case (pnt1,pnt2) => pnt1.y < pnt2.y
    }

  var rotationFctr = 0.001

  override def fiteration(): Unit = {
    super.fiteration()
    field.foreach(
      shp => shp.angle = (rotationFctr*shp.angle).toFloat
    )

  }

  override def shape(parent: Main, pnt: Point2d): ShapeObj =
    new LineShape(parent,pnt)

  override val numcolors: Int = 27
}

object LinePShape {
  def apply(parent: Main, initpnt: Point2d): PShape = {
    val shape = parent.createShape()
    shape.beginShape()
    shape.vertex(-parent.scale.toFloat,initpnt.y)
    shape.vertex(parent.fwidth.toFloat+parent.scale.toFloat,initpnt.y)
    shape.strokeWeight(10)
    shape.stroke(Colors.randomColor().toColor(parent))
    shape.endShape()
    shape
  }
}

class LineShape(parent: Main, override val initpos: Point2d) extends ShapeObj(parent,LinePShape(parent,initpos)) {
  var weight = 10

  override def iteration(): Unit = {
    shape.setStrokeWeight(weight)
    shape.setStroke(this.clrcoords.toColor(parent))
  }

  override def init(): Unit = {


  }

  override def control(): Unit = {}
}
