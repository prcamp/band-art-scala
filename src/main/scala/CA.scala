import scala.collection.mutable
import scala.util.Random
import processing.core.{PConstants => pc}

case class CA(parent: Main) extends Scene(parent) {

  /**
    * modified from:
    * A Processing implementation of Game of Life
    * By Joan Soler-Adillon
    *
    * Most basic modification: it runs on a torus instead of a plane.
    *
    * Controls:
    * SPACE - run the simulation without stopping
    * s  - pause or step the simulation
    * r  - randomly generate a layout
    * c  - generate random color scheme (and step the simulation)
    * 1,2,3 - special starting points.
    */

  val dims = CellDims(parent)

  var cells = IndexedSeq[IndexedSeq[Cell]]()

  cells = (0 until dims.numCellsX).map(
    i => (0 until dims.numCellsY).map(
      j => Cell(parent, Point2d(i * dims.cellSize, j * dims.cellSize))
    )
  )
  val rnd = Random
  var rps = 0
  val balance = 3 // any number
  val numspecies = 2 * balance + 1

  // How likely for a cell to be alive at start (in percentage)
  val probabilityOfAliveAtStart = 50

  // Variables for timer
  val interval = 0
  val lastRecordedTime = 0
  var stepping = 0

  // Periodic boundary conditions?
  val toroidal = 1

  val v_fader1 = 0.0f
  val v_fader2 = 0.0f
  val v_fader3 = 0.0f
  val v_fader4 = 0.0f
  val v_fader5 = 0.0f
  val v_toggle1 = 0.0f
  val v_toggle2 = 0.0f
  val v_toggle3 = 0.0f
  val v_toggle4 = 0.0f

  val pause = true

  val colarray = new mutable.ArrayBuffer[Int](numspecies)
  (0 until numspecies).foreach(
    i => colarray += parent.color(100 - rnd.nextInt(100), 100, 100)
  )

  def gencolor() {
    (0 until numspecies).foreach(
      i => colarray(i) = parent.color(100 - rnd.nextInt(100), 100, 100)
    )
  }

  override def init(): Unit = {
    parent.colorMode(pc.HSB, 100)
    gencolor()
    initialize(1)
    cells.flatten.foreach(c => c.init())
  }

  override def control(): Unit = {
    if (parent.key == 'r' || parent.key == 'R') {
      initialize(4)
      iteration()
    }
    if (parent.key == '1') {
      initialize(1)
      iteration()
    }
    if (parent.key == '2') {
      initialize(2)
      iteration()
    }
    if (parent.key == '3') {
      initialize(3)
      iteration()
    }
    if (parent.key == 'm' && rps == 0) {
      rps = 1
      initialize(1)
      iteration()
    }
    if (parent.key == 'm' && rps == 1) {
      rps = 0
      initialize(1)
      iteration()
    }
    if (parent.key == 's') {
      stepping = 0
      iteration()
    }
    if (parent.key == ' ') {
      stepping = 1
    }
    if (parent.key == 'c') {
      gencolor()

    }
  }

  override def curscene(): Unit = {
    if (stepping == 1) {
      iteration()
    }

  }

  private val nbrs = Vector(
    (-1, -1),
    (-1, 0),
    (-1, 1),
    (0, -1),
    (0, 1),
    (1, -1),
    (1, 0),
    (1, 1)
  )

  def wrapIdx(idx: (Int, Int)): (Int, Int) = {
    val x =
      if (idx._1 >= dims.numCellsX) 0
      else if (idx._1 < 0) dims.numCellsX - 1
      else idx._1
    val y =
      if (idx._2 >= dims.numCellsY) 0
      else if (idx._2 < 0) dims.numCellsY - 1
      else idx._2
    (x, y)
  }

  def iteration(): Unit = {
    cells.flatten.foreach(c => c.previousState = c.state)
    cells.zipWithIndex.foreach {
      case (row, xidx) =>
        row.zipWithIndex.foreach {
          case (cell, yidx) => {
            if (rps == 1) {
              val nbrcoord = nbrs(rnd.nextInt(8))
              val nbridx = wrapIdx((xidx + nbrcoord._1, yidx + nbrcoord._2))
              val m = Math.min(cell.previousState, cells(nbridx._1)(nbridx._2).previousState)
              val M = Math.max(cell.previousState, cells(nbridx._1)(nbridx._2).previousState)
              if (M - m <= balance) {
                cell.state = M
              } else {
                cell.state = m
              }
            } else {
              val nbrscoords = nbrs.map {
                case (i, j) => wrapIdx((xidx + i, yidx + j))
              }
              val livenbrs = nbrscoords.foldLeft(0) {
                case (acc, (i, j)) => acc + cells(i)(j).previousState
              }
              if (cell.previousState == 1) {
                if (livenbrs < 2 || livenbrs > 3) cell.state = 0
              } else {
                if (livenbrs == 3) cell.state = 1
              }
            }
            parent.pushMatrix()
            cell.curscene()
            parent.popMatrix()
          }
        }
    }
  }

  def initialize(n: Int): Unit = {
    if (rps == 1) {
      n match {
        case 1 => //rps
          (0 until dims.numCellsX).foreach(i =>
            (0 until dims.numCellsY).foreach(
              j => {
                cells(i)(j).state =
                  ((i / (dims.numCellsX + .001)) * numspecies).toInt
                cells(i)(j).previousState = cells(i)(j).state
              }
            )
          )
        case 2 =>
          (0 until dims.numCellsX).foreach(i =>
            (0 until dims.numCellsY).foreach(
              j => {
                cells(i)(j).state = ((i / (dims.numCellsX + .001)) * numspecies).toInt
                cells(i)(j).previousState = cells(i)(j).state
              }
            )
          )
        case 3 =>
          (0 until dims.numCellsX).foreach(i =>
            (0 until dims.numCellsY).foreach(
              j => {
                cells(i)(j).state = ((i * j / (dims.numCellsX * dims.numCellsY + .001)) * numspecies).toInt
                cells(i)(j).previousState = cells(i)(j).state
              }
            )
          )
        case 4 =>
          cells.flatten.foreach(c => {
            c.state = rnd.nextInt(numspecies)
            c.previousState = c.state
          })
        case _ =>
      }
    } else {
      n match {
        case 1 =>
          (0 until dims.numCellsX).foreach(i =>
            (0 until dims.numCellsY).foreach(
              j => {
                if (i == (parent.fwidth / (2 * dims.cellSize) + 1).toInt) cells(i)(j).state = 1
                else cells(i)(j).state = 0
                cells(i)(j).previousState = cells(i)(j).state
              }
            )
          )
        case 2 =>
          (0 until dims.numCellsX).foreach(i =>
            (0 until dims.numCellsY).foreach(
              j => {
                if (j == (parent.fheight / (2 * dims.cellSize) + 1).toInt) cells(i)(j).state = 1
                else cells(i)(j).state = 0
                cells(i)(j).previousState = cells(i)(j).state
              }
            )
          )
        case 3 =>
          (0 until dims.numCellsX).foreach(i =>
            (0 until dims.numCellsY).foreach(
              j => {
                if (i == (parent.fwidth / (2 * dims.cellSize) + 1).toInt || j == (parent.fheight / (2 * dims.cellSize) + 1).toInt) cells(i)(j).state = 1
                else cells(i)(j).state = 0
                cells(i)(j).previousState = cells(i)(j).state
              }
            )
          )
        case 4 =>
          cells.flatten.foreach(c => {
            c.state = rnd.nextInt(2)
            c.previousState = c.state
          })
      }
    }
  }
}

case class CellDims(parent: Main) {
  val cellSize = 10
  val rectcoords = List(
    Point2d(0, 0),
    Point2d(0, cellSize),
    Point2d(cellSize, cellSize),
    Point2d(cellSize, 0)
  )

  val numCellsX = parent.fwidth / cellSize
  val numCellsY = parent.fheight / cellSize
}

case class Cell(parent: Main, initpos: Point2d) extends ShapeObj(parent, CellDims(parent).rectcoords) {

  var color = 0

  var state: Int = 0
  var previousState: Int = 0

  pos = initpos
  //val speed = 10

  override def control(): Unit = {}

  override def curscene(): Unit = {
    shape.setFill(color)
    //    star.endShape()
    parent.shape(shape)
  }
}
