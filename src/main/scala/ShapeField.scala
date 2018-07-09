import Colors.ColorCoords
import processing.core.{PConstants => pc}
import scala.collection.mutable.ArrayBuffer

abstract class ShapeField(parent: Main) extends Scene(parent) {
  var continuousIter = false

  val initposField: IndexedSeq[Point2d]

  def nbrs(i: Int): Seq[Int] = Seq()

  def fiteration(): Unit = {
    //colarray = Colors.gencolors(parent, colormode, numcolors)
//    field.foreach(
//      shp => {
//        shp.clrcoords0 = shp.clrcoords
//        shp.clrcoords = Colors.randomColor(colarray)
//      }
//    )
  }

  def shape(parent: Main, pnt: Point2d): ShapeObj

  val numcolors: Int
  var colormode = "bw"
  var colarray: ArrayBuffer[ColorCoords] = Colors.gencolors(parent, colormode, numcolors)

  var field = IndexedSeq[ShapeObj]()

  override def init(): Unit = {
    colarray = Colors.gencolors(parent, colormode, numcolors)
    initposField.foreach(
      pos => field = field :+ shape(parent, pos)
    )
    field.foreach(shp => {
      shp.init()
      shp.clrcoords = Colors.randomColor(colarray)
      shp.clrcoords0 = shp.clrcoords
    })
  }

  def replaceColor(): Unit = {
    field.foreach(
      shp => {
        shp.clrcoords = Colors.randomColor(colarray)
        shp.clrcoords0 = shp.clrcoords
      }
    )
  }

  override def control(): Unit = {
    field.foreach(shp => shp.control())
    if (parent.key == ' ') {
      continuousIter = !continuousIter
    } else if (parent.key == 'c') {
      colarray = Colors.gencolors(parent, colormode, numcolors)
      replaceColor()
      fiteration()
    } else if (parent.key == 'b') {
      colormode = "bw"
      colarray = Colors.gencolors(parent, colormode, numcolors)
      replaceColor()
      fiteration()
    } else if (parent.key == 'n') {
      colormode = "neon"
      colarray = Colors.gencolors(parent, colormode, numcolors)
      replaceColor()
      fiteration()
    } else if (parent.key == 'm') {
      colormode = "rand"
      colarray = Colors.gencolors(parent, colormode, numcolors)
      replaceColor()
      fiteration()
    } else if (parent.key == pc.TAB) {
      continuousIter = false
      fiteration()
    }

  }

  override def curscene(): Unit = {
    if (continuousIter) fiteration()
    field.foreach(shp => shp.curscene())
  }

}

