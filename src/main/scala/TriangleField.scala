//import oscP5.OscMessage
//
//import scala.collection.mutable.ArrayBuffer
//import scala.util.Random
//
//case class TriangleField(parent: Main) extends Scene(parent) {
//  val rnd = Random
//  val numShapes = 60
//  val initcenters: List[Point2d] = {
//
//    val randfield = (0 until numShapes).map(
//      i => Point2d(parent.fwidth*rnd.nextFloat(),parent.fheight*rnd.nextFloat())
//    )
//    randfield.sortWith((p1,p2) => p1.y <= p2.y).toList
//  }//Point2d.ring(parent.center*(1/2), 80, numShapes)
//
//  var centers = initcenters
//
//  val field = new ArrayBuffer[ShapePolyObj](numShapes)
//
//  centers.zipWithIndex.foreach{
//    case (pnt,idx) => field += shape(pnt)
//  }
//  println("number of shapes"+field.length)
//  override def init(): Unit = {
//
//      field.foreach(shp => shp.init())
//    }
//
//  override def control(): Unit = field.foreach(shp => shp.control())
//
//  override def curscene(): Unit = field.foreach(shp => shp.curscene())
//
//  val osc = Signal.tri(.5)
//  var colorrot = 0
//  override def oscEvent(oscmsg: OscMessage): Unit = {
//    super.oscEvent(oscmsg)
//    val addr = oscmsg.addrPattern
//    //var rxval = .0
//    //var ryval = .0
//    if (addr == "/3/xyM_l") {
//      colorrot = (colorrot+(360 * oscmsg.get(1).floatValue()).toInt)
//      println(colorrot)
//
////      angle = parent.fheight * 2*(oscmsg.get(0).floatValue - .5).toFloat
//      //      this.pos = Point2d(rxval.toFloat,ryval.toFloat)
//    }
//  }
//  def fiteration(shp: Triangle): Unit = {
////    val curh = (360 * osc.signal()).toInt
////    print(s"\r ${osc.signal()}")
////    shp.h = curh
//    shp.h = (shp.h + colorrot) % 360
//    shp.color = parent.color(shp.h, shp.s, shp.b)
//  }
//
//  def shape(pnt: Point2d) = new Triangle(parent,pnt) {
//    h = rnd.nextInt(360)
//    color = parent.color(h, s, b)
//    override def iteration(): Unit = fiteration(this)
//  }
//}
//
//case class TriangleCoords() {
//  val coords = List(
//    Point2d(0,0),
//    Point2d(-100,-100),
//    Point2d(100,-100)
//  )
//}
//class Triangle(parent: Main, override val initpos: Point2d) extends ShapePolyObj(parent, TriangleCoords().coords){
////  var length: Float = (parent.scale/8).toFloat
////  var spread: Float = 1/2
//
//  scale = 1//(parent.scale/8).toFloat
//
////  override def init(): Unit = {
////    super.init()
////    shape.setVertex(0, pos.toPVector)
////
////  }
//
//
//
//  var h = 0
//  var b = 100
//  var s = 100
//
//  color = parent.color(h,s,b)
//
//  var changeRate =  .8
//
//  pos = initpos
//  //val speed = 10
//
//  override def oscEvent(oscmsg: OscMessage): Unit = {
//    super.oscEvent(oscmsg)
//    val addr = oscmsg.addrPattern
//    //var rxval = .0
//    //var ryval = .0
//    if (addr == "/3/xyM_l") {
//      h = (h+(360 * oscmsg.get(1).floatValue()).toInt) % 360
//      println(h)
//      color = parent.color(h,s,b)
//      angle = parent.fheight * 2*(oscmsg.get(0).floatValue - .5).toFloat
////      this.pos = Point2d(rxval.toFloat,ryval.toFloat)
//    }
//  }
//
//  override def control(): Unit = {}
//
//
//  override def iteration(): Unit = {
//
////    val angle = spread*Math.PI
////    val halfwidth = length*Math.tan(angle)
////    val verts = Vector(
////      pos.toPVector(),
////      pos.toPVector().add(Point2d(-halfwidth.toFloat, -length).toPVector()),
////      pos.toPVector().add(Point2d(halfwidth.toFloat, -length).toPVector())
////    )
////
////    verts.zipWithIndex.foreach{
////      case (v,i) => shape.setVertex(i,v)
////    }
//
////    h = (h*(1-changeRate) + Main.rnd.nextInt(360)*changeRate).toInt
//
////    color = parent.color(h,s,b)
//  }
//}
