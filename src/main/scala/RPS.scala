import scala.collection.immutable
import scala.collection.mutable.ArrayBuffer
import scala.util.Random
import processing.core.{PConstants => pc}
case class RPS(parent: Main) extends Scene(parent) {
  val rnd = Random
  val cd = CellDims(parent)

  var colormode = "bw"

  val balance = 5 // any number
  val numspecies = 2 * balance + 1

  val initcenters: immutable.IndexedSeq[immutable.IndexedSeq[Point2d]] =
    (0 until cd.numCellsX).map{
    i => (0 until cd.numCellsY).map{
      j => Point2d(i*cd.cellSize,j*cd.cellSize)
    }
  }
  var centers = initcenters
  var colarray = ArrayBuffer[Int]()
  colarray = Colors.gencolors(parent,colormode,numspecies)
  var continuousIter = false
  val field: ArrayBuffer[ArrayBuffer[CellShape]] = new ArrayBuffer[ArrayBuffer[CellShape]]()
  centers.zipWithIndex.foreach{
    case (col,xidx) => {
      val row = new ArrayBuffer[CellShape]()
      col.zipWithIndex.foreach{
        case (pnt,yidx) =>  row += shape(parent,pnt)
      }
      field += row
    }
  }

  override def init(): Unit = {
    colarray = Colors.gencolors(parent,colormode,numspecies)
    field.flatten.foreach(shp => {
      shp.state = rnd.nextInt(numspecies)
      shp.prevState = shp.state
      shp.color = colarray(shp.state)
      shp.init()
    })
//    field.flatten.foreach(shp => shp.init())
  }

  override def control(): Unit = {
    val k = parent.key
    if (k == 'c') {
      colarray = Colors.gencolors(parent,colormode,numspecies)//ArrayBuffer[Int]()
//      (0 until numspecies).foreach(i => colarray += rnd.nextInt(361))
      field.flatten.foreach(shp => shp.curscene())
    } else if (k == ' ') continuousIter = !continuousIter
    else if (k == pc.TAB) fiteration()
    field.flatten.foreach(shp => shp.control())
  }

  override def curscene(): Unit = {
    if (continuousIter) fiteration()
    field.flatten.foreach(shp => shp.curscene())
  }

  def shape(parent: Main, pnt: Point2d): CellShape = new CellShape(parent,pnt) {}

  private val nbrs = IndexedSeq(
    (-1, -1),
    (-1, 0),
    (-1, 1),
    (0, -1),
    (0, 1),
    (1, -1),
    (1, 0),
    (1, 1)
  )
  def fiteration(): Unit = {
    field.flatten.foreach(shp => shp.prevState = shp.state)
    field.zipWithIndex.foreach{
      case (col,xidx) =>
        col.zipWithIndex.foreach{
          case (cell,yidx) => {
            val rndnbr = nbrcoords(xidx,yidx)(rnd.nextInt(nbrs.length))
            val m = Math.min(cell.prevState, field(rndnbr._1)(rndnbr._2).prevState)
            val M = Math.max(cell.prevState, field(rndnbr._1)(rndnbr._2).prevState)
            if (M - m <= balance) {
              cell.state = M
            } else {
              cell.state = m
            }
            cell.color = colarray(cell.state)
          }
        }
    }
  }

  def nbrcoords(i: Int, j: Int): IndexedSeq[(Int,Int)] =
    nbrs.map{
      case (k,l) => wrapIdx(i+k,j+l)
    }

  def wrapIdx(i: Int, j: Int): (Int, Int) = {
    val x =
      if (i >= cd.numCellsX) 0
      else if (i < 0) cd.numCellsX - 1
      else i
    val y =
      if (j >= cd.numCellsY) 0
      else if (j < 0) cd.numCellsY - 1
      else j
    (x, y)
  }
}
