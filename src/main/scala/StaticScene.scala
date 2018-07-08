import scala.collection.immutable
import scala.collection.mutable.ArrayBuffer
import scala.util.Random

case class StaticScene(parent: Main) extends Scene(parent){
  var numCellsX = 21
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

  val numspecies = 6
  var colarray = ArrayBuffer[Int]()
  (0 until numspecies).foreach(i => colarray += rnd.nextInt(361))

  def rndColor(): Int = {
    val h = colarray(rnd.nextInt(numspecies))
    parent.color(h,100,100)
  }

  def shape(parent: Main, pnt: Point2d): ShapeObj = new RectShape(parent,pnt,coords) {}

  override def init(): Unit = {
    field.flatten.foreach(shp => {
//      shp.state = rnd.nextInt(2)
//      shp.prevState = shp.state
      shp.color = rndColor()//parent.color(,shp.s,shp.b)
      shp.init()
  })
  }

  override def control(): Unit = {
    if (parent.key == 'c') {
      colarray = ArrayBuffer[Int]()
      (0 until numspecies).foreach(i => colarray += rnd.nextInt(361))
      field.flatten.foreach(shp => shp.curscene())
    }
  }

  override def curscene(): Unit = {
    fiteration()
    field.flatten.foreach(shp => shp.curscene())
  }

  def fiteration(): Unit = {
    field.flatten.foreach(
      shp => {
        shp.color = rndColor()
      }
    )
  }
}



class RectShape(parent: Main,  override val initpos: Point2d, vertices: List[Point2d]) extends ShapePolyObj(parent, vertices) {

  var h = 0
  var b = 100
  var s = 100
  var state = 0
  var prevState = 1

  color = parent.color(h,s,b)

  pos = initpos.copy()

  override def control(): Unit = { }

  override def iteration(): Unit = {}
}

case class CellDims(parent: Main) {
  val cellSize = 4
  val rectcoords = List(
    Point2d(0, 0),
    Point2d(0, cellSize)*2,
    Point2d(cellSize, cellSize)*2,
    Point2d(cellSize, 0)*2
  )

  val numCellsX = parent.fwidth / cellSize
  val numCellsY = parent.fheight / cellSize
}
