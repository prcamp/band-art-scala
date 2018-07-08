abstract class ShapeField(parent: Main) extends Scene(parent) {

  val initposField: IndexedSeq[Point2d]
  def nbrs(i: Int): Seq[Int] = Seq()
  def fiteration(): Unit  = {}
  def shape(parent:Main, pnt: Point2d): ShapeObj

  var field = IndexedSeq[ShapeObj]()

  override def init(): Unit = {
    initposField.foreach(
      pos => field = field :+ shape(parent, pos)
    )
    field.foreach(shp => shp.init())
  }


  override def control(): Unit = {
    field.foreach(shp => shp.control())
  }

  override def curscene(): Unit = {
    fiteration()
    field.foreach(shp => shp.curscene())
  }

}

