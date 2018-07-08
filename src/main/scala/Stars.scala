import scala.collection.mutable.ArrayBuffer

case class Stars(parent: Main) extends Scene(parent) {
  val numStars = 20
  val initcenters: List[Point2d] = Point2d.ring(parent.center, 80, numStars)
  var centers = initcenters

  val field = new ArrayBuffer[ShapePolyObj](numStars)
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
//    print(s"\r ${osc.signal()}")
    shp.h = curh
    shp.color = parent.color(shp.h, shp.s, shp.b)
  }

  def shape(pnt: Point2d) = new StarShape(parent,pnt) {
    override def iteration(): Unit = fiteration(this)
  }


}



