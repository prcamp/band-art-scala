import Colors.ColorCoords
import oscP5.{OscMessage, OscStatus}

import scala.collection.immutable
import scala.collection.mutable.ArrayBuffer
import scala.util.Random

case class StaticScene(parent: Main) extends Scene(parent){
  var numCellsX = 21
  val numcolors = 80
  var cellSize = parent.fwidth/numCellsX
  var numCellsY = parent.fheight/cellSize.toInt + 1
  val scale = 100
  val coords = List(
    Point2d(0, 0),
    Point2d(0, cellSize)*scale,
    Point2d(cellSize, cellSize)*scale,
    Point2d(cellSize, 0)*scale
  )

  val initcenters: immutable.IndexedSeq[immutable.IndexedSeq[Point2d]] =
    (0 until numCellsX).map{
      i => (0 until numCellsY).map{
        j => Point2d(i*cellSize,j*cellSize)
      }
    }

  val rnd = Random

  var centers = initcenters
  var continuousIter = false
  val field: ArrayBuffer[ArrayBuffer[ShapeObj]] = new ArrayBuffer[ArrayBuffer[ShapeObj]]()
  centers.zipWithIndex.foreach{
    case (col,xidx) => {
      val row = new ArrayBuffer[ShapeObj]()
      col.zipWithIndex.foreach{
        case (pnt,yidx) =>  row += shape(parent,pnt)
      }
      field += row
    }
  }

  var colormode = "bw"

  var colarray = Colors.gencolors(parent,colormode,numcolors)


//  def rndColor(): Int = {
//    Colors.randomColor(colarray)
//  }

  def shape(parent: Main, pnt: Point2d): ShapeObj = new RectShape(parent,pnt,coords) {}

  override def init(): Unit = {
    field.flatten.foreach(shp => {
//      shp.state = rnd.nextInt(2)
//      shp.prevState = shp.state
      shp.clrcoords = Colors.randomColor(colarray) //parent.color(,shp.s,shp.b)
      shp.init()
  })
  }

  override def control(): Unit = {
    if (parent.key == 'c') {
      colarray = Colors.gencolors(parent,colormode,numcolors)
      field.flatten.foreach(shp => shp.curscene())
    }
    if (parent.key == ' ') {
      continuousIter = ! continuousIter
    }
  }

  override def curscene(): Unit = {
    if (continuousIter) fiteration()
    field.flatten.foreach(shp => shp.curscene())
  }

  def fiteration(): Unit = {
    field.flatten.foreach(
      shp => {
        shp.clrcoords = Colors.randomColor(colarray)
      }
    )
  }

  override def oscEvent(oscMessage: OscMessage): Unit = {
    super.oscEvent(oscMessage)
    val addr = oscMessage.addrPattern
    if (addr == "/1/push2") {
      colormode = "bw"
      colarray = Colors.gencolors(parent,colormode,numcolors)
    } else if (addr == "/1/push5") {
      colormode = "neon"
      colarray = Colors.gencolors(parent,colormode,numcolors)
    } else if (addr == "/1/fader4") {
      val h = oscMessage.get(0).floatValue().toInt
      colarray = colarray.map{
        case ColorCoords(_,s,b) => ColorCoords(h,s,b)
      }
    } else if (addr == "/1/fader9") {
      val s = oscMessage.get(0).floatValue().toInt
      colarray = colarray.map{
        case ColorCoords(h,_,b) => ColorCoords(h,s,b)
      }
    } else if (addr == "/1/fader10") {
      val b = oscMessage.get(0).floatValue().toInt
      colarray = colarray.map{
        case ColorCoords(h,s,_) => ColorCoords(h,s,b)
      }

    } else if (addr == "/1/toggle17") {
      val iter = oscMessage.get(0).floatValue().toInt
      println(iter)
      if (iter == 1) continuousIter = true
      if (iter == 0) continuousIter = false
    } else if (addr == "/1/push1") {
      continuousIter = false
      fiteration()
    }


  }
  override def oscStatus(oscStatus: OscStatus): Unit = {
    super.oscStatus(oscStatus)
  }

}



class RectShape(parent: Main,  override val initpos: Point2d, vertices: List[Point2d]) extends ShapePolyObj(parent, vertices) {

  pos = initpos.copy()

  override def control(): Unit = { }

  override def iteration(): Unit = {}
}

