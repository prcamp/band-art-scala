//
//
//case class StarField(parent: Main, centers: List[Point2d]) extends Scene(parent) {
//
//  var field = List[ShapeObj]()
//
//  override def init(): Unit = {
//    field = centers.map(pnt => StarShape(parent,pnt))
//  }
//
//  override def control(): Unit = {
//    field.foreach(shp => shp.control())
//  }
//
//  override def curscene(): Unit = {
//    field.foreach(shp => shp.curscene())
//  }
//}
//
//case class Stars(parent: Main) extends Scene(parent) {
//  val numStars = 15
//  val centers = Point2d.ring(parent.center,20,numStars)
//  val stars = StarField(parent, centers)
//
//  override def init(): Unit = stars.init()
//
//
//  override def control(): Unit = stars.control()
//
//  override def curscene(): Unit = stars.curscene()
//}
//
