import Colors.ColorCoords
import oscP5.{OscMessage, OscStatus}
import processing.core.PShape

case class TriangleScene(parent: Main) extends ShapeField(parent){
  var numTris = 3000


  val numcolors = 20

  var initposField0 = (0 until numTris).map(
    _ => parent.randomPoint()
  )
  override val initposField: IndexedSeq[Point2d] =
    initposField0.sortWith{
      case (pnt1,pnt2) => pnt1.y < pnt2.y
    }

  override def shape(parent: Main, pnt: Point2d): ShapeObj =
    new TriangleShape(parent,pnt) {
      //clrcoords = Colors.randomColor(colarray)
    }

  var easing = .5
  //var fctr =   .5
  override def fiteration(): Unit = {
    super.fiteration()
    field.foreach(
      shp => {
        val newclr = Colors.randomColor(colarray)
        shp.clrcoords =
          ColorCoords(
            (shp.clrcoords.h*(1-easing) + easing*newclr.h).toInt,
            (shp.clrcoords.s*(1-easing) + easing*newclr.s).toInt,
            (shp.clrcoords.b*(1-easing) + easing*newclr.b).toInt)
      })
  }



  override def oscEvent(oscMessage: OscMessage): Unit = {
    super.oscEvent(oscMessage)
    val addr = oscMessage.addrPattern
    if (addr == "/2/push11") {
      colormode = "bw"
      colarray = Colors.gencolors(parent,colormode,numcolors)
      fiteration()
    } else if (addr == "/2/push12") {
      colormode = "neon"
      colarray = Colors.gencolors(parent,colormode,numcolors)
      fiteration()
    } else if (addr == "/2/fader11") {
      val h = oscMessage.get(0).floatValue().toInt
      colarray = colarray.map{
        case ColorCoords(_,s,b) => ColorCoords(h,s,b)
      }
      fiteration()
    } else if (addr == "/2/fader12") {
      val s = oscMessage.get(0).floatValue().toInt
      colarray = colarray.map{
        case ColorCoords(h,_,b) => ColorCoords(h,s,b)
      }
      fiteration()
    } else if (addr == "/2/fader13") {
      val b = oscMessage.get(0).floatValue().toInt
      colarray = colarray.map{
        case ColorCoords(h,s,_) => ColorCoords(h,s,b)
      }
      fiteration()
    } else if (addr == "/2/toggle17") {
//      val iter = oscMessage.get(0).floatValue().toInt
//      println(iter)
//      if (iter == 1) continuousIter = true
//      if (iter == 0) continuousIter = false
    } else if (addr == "/2/push1") {
//      continuousIter = false
//      fiteration()
    }


  }
  override def oscStatus(oscStatus: OscStatus): Unit = {
    super.oscStatus(oscStatus)
  }
}

object TrianglePShape {
  def apply(parent: Main, initpos: Point2d): PShape = {
    val shape = parent.createShape()
    shape.beginShape()
    shape.vertex(initpos.x,initpos.y)
    shape.vertex(initpos.x - 40, initpos.y + parent.fheight)
    shape.vertex(initpos.x + 40, initpos.y + parent.fheight)
    shape.fill(Colors.randomColor().toColor(parent))
    shape.endShape()
    shape
  }
}

class TriangleShape(parent: Main, override val initpos: Point2d) extends ShapeObj(parent,TrianglePShape(parent,initpos)) {


  override def init(): Unit = {

  }

  override def control(): Unit = {}
}
