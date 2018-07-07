import scala.collection.mutable.ArrayBuffer


case class TestFullscreen(parent: Main) extends Scene(parent) {
  val numStars = 20
//  println("in fullscreen test")
//  println(s"fwidth ${parent.fwidth} fheight ${parent.fheight}")
//  println(s"center ${parent.center}")
  val hspc = parent.fwidth/numStars
  val vspc = parent.fheight/numStars
  val initcenters: List[Point2d] =
    (0 until numStars).flatMap(
      i => (0 until numStars).map(
        j => Point2d(i*hspc,-j*vspc+i*hspc)
  )).toList
  var centers = initcenters

  val field = new ArrayBuffer[ShapeObj](numStars)
  centers.zipWithIndex.foreach{
    case (pnt,idx) => field += shape(pnt)
  }
  override def init(): Unit = {

      field.foreach(shp => shp.init())
    }

  override def control(): Unit = field.foreach(shp => shp.control())

  override def curscene(): Unit = field.foreach(shp => shp.curscene())

  val osc = Signal.tri(.5)

  def fiteration(shp: StarShape): Unit = {
    val curh = (360 * osc.signal()).toInt
    print(s"\r ${osc.signal()}")
    shp.h = curh
    shp.color = parent.color(shp.h, shp.s, shp.b)
  }

  def shape(pnt: Point2d) = new StarShape(parent,pnt) {
    override def iteration(): Unit = fiteration(this)
  }


}



