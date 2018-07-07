import processing.core.PVector

object Point2d {
  def ring(center:Point2d, radius: Float, numPoints: Int = 10, angleOffset: Float = 0): List[Point2d] = {
    (0 until numPoints).map(
      i => {
        val angle = angleOffset + (i.toFloat/(numPoints).toFloat)*2*Math.PI
        val radialOffset = Point2d(radius* Math.cos(angle).toFloat, radius * Math.sin(angle).toFloat)
        //        println("angle: "+ angle + "ro"+radialOffset)
        center + radialOffset
      }
    ).toList
  }
  //  def grid(parent: Main): List[Point2d] ={
  //
  //  }
}

case class Point2d(var x: Float, var y: Float) {
  def this(ix: Double, iy: Double) {
    this(ix.toFloat,iy.toFloat)
  }
  def +(other: Point2d): Point2d = {
    Point2d(x + other.x,y + other.y)
  }
  //  def ^() = (x,y)
  override def toString: String = s"x: $x, y: $y"
  def *(scale: Double):Point2d = new Point2d(scale*x,scale*y)
  def toPVector(): PVector = {
    new PVector(x,y)
  }
}

