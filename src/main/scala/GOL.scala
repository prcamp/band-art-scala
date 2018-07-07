import scala.collection.immutable
import scala.collection.mutable.ArrayBuffer
import scala.util.Random


case class GOL(parent: Main) extends Scene(parent) {
  val rnd = Random
  val cd = CellDims(parent)
  val numStars = 20

  val initcenters: immutable.IndexedSeq[immutable.IndexedSeq[Point2d]] =
    (0 until cd.numCellsX).map{
    i => (0 until cd.numCellsY).map{
      j => Point2d(i*cd.cellSize,j*cd.cellSize)
    }
  }
  var centers = initcenters
  var colarray = ArrayBuffer[Int](rnd.nextInt(360),rnd.nextInt(360))

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
    field.flatten.foreach(shp => {
      shp.state = rnd.nextInt(2)
      shp.prevState = shp.state
      shp.color = parent.color(colarray(shp.state),shp.s,shp.b)
      shp.init()
    })
  }

  override def control(): Unit = {
    if (parent.key == 'c') {
      colarray = ArrayBuffer[Int](rnd.nextInt(360),rnd.nextInt(360))
    }
    field.flatten.foreach(shp => shp.control())
  }

  override def curscene(): Unit = {
    fiteration()
    field.flatten.foreach(shp => shp.curscene())
  }

  def shape(parent: Main, pnt: Point2d): CellShape = new CellShape(parent,pnt) {}

  def fiteration(): Unit = {
    field.flatten.foreach(shp => shp.prevState = shp.state)
    field.zipWithIndex.foreach{
      case (col,xidx) =>
        col.zipWithIndex.foreach{
          case (cell,yidx) => {
            val livnbrs = nbrcoords(xidx,yidx).foldLeft(0){
              case (acc,(nx,ny)) => acc + field(nx)(ny).prevState
            }
            // if cell is alive
            if (cell.prevState == 1 && (livnbrs < 2 || livnbrs > 3)) cell.state=0
            else if (cell.prevState == 0 && livnbrs == 3) cell.state = 1
            cell.color = parent.color(colarray(cell.state),cell.s,cell.b)
          }
        }
    }

  }

  private val nbrs = List(
    (-1, -1),
      (-1, 0),
      (-1, 1),
      (0, -1),
      (0, 1),
      (1, -1),
      (1, 0),
      (1, 1)
    )
  def nbrcoords(i: Int, j: Int): List[(Int,Int)] =
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
