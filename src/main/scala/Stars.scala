

case class Stars(parent: Main) extends ShapeField(parent) {
  def shape(parent: Main, pnt: Point2d): ShapeObj = new StarShape(parent,pnt) {
    override def iteration(): Unit = fiteration(this)
  }
  val numStars = 20
  val initposField = Point2d.ring(parent.center, 80, numStars).toIndexedSeq

  val osc = Signal.tri(.5)

  def fiteration(shp: StarShape): Unit = {
    val curh = (360 * osc.signal()).toInt
//    print(s"\r ${osc.signal()}")
    shp.h = curh
    shp.color = parent.color(shp.h, shp.s, shp.b)
  }


}

