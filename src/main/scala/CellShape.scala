
class CellShape(parent: Main,  override val initpos: Point2d) extends ShapePolyObj(parent, (new CellDims(parent)).rectcoords) {

  var state = 0
  var prevState = 1

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
