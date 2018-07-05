import processing.core.{PApplet, PConstants => pc}


trait Scenery {
  def init(): Unit
  def control(): Unit
  def curscene(): Unit
}

abstract class Scene(parent:PApplet) extends Scenery {}


case class MovingDots(parent: Main) extends Scene(parent) {

  override def init(): Unit = {}

  //  val star = Star(parent)
  val basex = parent.fwidth/2
  val basey = parent.fheight/2//sketchHeight()/2
  val rnd = scala.util.Random
  private def rndPoint2d(): Point2d = Point2d(rnd.nextInt(30)-15,rnd.nextInt(30)-15)
  var dir = rndPoint2d
  var dir2 = rndPoint2d
  var pos = Point2d(basex,basey)
  var pos2 = Point2d(basex,basey)

  override def control(): Unit = {
    println(parent.keyCode + "\n")
    if (parent.key == 'n') {
      pos = Point2d(basex,basey)
      pos2 = Point2d(basex,basey)
      dir = rndPoint2d()
      dir2 = rndPoint2d()
    }

    if (parent.key == pc.LEFT) pos = pos + Point2d(-10,0)
    if (parent.key == pc.RIGHT) pos = pos + Point2d(10,0)
    if (parent.key == pc.UP) pos = pos + Point2d(0,-10)
    if (parent.key == pc.DOWN) pos = pos + Point2d(0,10)
  }
  override def curscene(): Unit ={
    print(s"\r pos: $pos, pos2: $pos2")

    pos = pos + dir
    pos2 = pos2 + dir2

    val p1 = Pointer(parent,pos)
    val p2 = Pointer(parent,pos2)

    //      {
    //        i += 1; i
    //      }

    //    pos.x = pos.x + 5
    //    if (movingX >= 640) movingX = 0
  }
}

case class Pointer(parent: PApplet, p: Point2d) {
  parent.stroke(255, 0)
  parent.stroke(255)
  parent.point(p.x,p.y)
}
//
//case class StarCoords() {
//  val coords = Seq(
//    Point2d(0,-50),
//    Point2d(14, -20),
//    Point2d(47, -15),
//    Point2d(23, 7),
//    Point2d(29, 40),
//    Point2d(0, 25),
//    Point2d(-29, 40),
//    Point2d(-23, 7),
//    Point2d(-47, -15),
//    Point2d(-14, -20)
//  )
//}
//
//case class Stars(parent: Main) extends Scene(parent) {
//  // First create the shape
//  val p = Point2d
//  val basex = parent.fwidth/2
//  val basey = parent.fheight/2
//  var pos = Point2d(basex,basey)
//  val verts = StarCoords().coords
//  val radial = 100
//  val numStars = 20
//
//  var baseShapes = List[PShape]()
//
//  override def init() = {
//    val centers = (0 until numStars).map(
//      i => {
//        val angle = (i.toFloat/(numStars+1).toFloat)*2*Math.PI
//        val radialOffset = Point2d(radial* Math.cos(angle).toFloat, radial * Math.sin(angle).toFloat)
//        //        println("angle: "+ angle + "ro"+radialOffset)
//        pos + radialOffset
//      }
//    )
//    baseShapes = centers.map {
//      cent => {
//        val shp = parent.createShape()
//        shp.beginShape
//        def v(p:Point2d) = shp.vertex(p.x,p.y)
//        // You can set fill and stroke
//        shp.fill(parent.color(0,0,255))
//        verts.foreach(
//          pnt => {
//            v(cent + pnt)
//          }
//        )
//        shp.endShape
//        shp
//      }
//    }.toList
//  }
//  override def control(): Unit = {
//
//    if (parent.key == 'n') pos = Point2d(basex,basey)
//    if (parent.keyCode == 37) pos = pos + Point2d(-10,0)
//    if (parent.keyCode == 39) pos = pos + Point2d(10,0)
//    if (parent.keyCode == 38) pos = pos + Point2d(0,-10)
//    if (parent.keyCode == 40) pos = pos + Point2d(0,10)
//  }
//
//  override def curscene(): Unit = {
//    print(s"\r pos: $pos")
////    parent.background(0)
////    init()
//    //    parent.translate(pos.x,pos.y)
//    baseShapes.zipWithIndex.foreach{ case (shp, idx) => {
//      if (idx == 5){
//        shp.setFill(parent.color(255,0,0))
//      }
//      parent.shape(shp)
//    }
//
//    }
//  }
//
//}


case class ImagePatch(parent: Main) extends Scene(parent) {

  import processing.core.PImage

  /**
    * Load and Display
    *
    * Images can be loaded and displayed to the screen at their actual size
    * or any other size.
    */
  var img: PImage = _ // Declare variable "a" of type PImage
  val imagePath = "resources/images/plaids/squareseries_symmetric_very_flat_1.jpg"
  override def init(): Unit = {
    // The image file must be in the data folder of the current sketch
    // to load successfully
    img = parent.loadImage(imagePath) // Load the image into the program
  }

  override def control(): Unit = {}

  override def curscene(): Unit = {
    parent.image(img, 0, 0)
    // Displays the image at point (0, height/2) at half of its size
    parent.image(img, 0, parent.fheight/2, img.width / 2, img.height / 2)
  }
}

case class ShapeIm(parent: Main) extends Scene(parent) {
  val imagePath = "resources/images/plaids/squareseries_symmetric_very_flat_1.jpg"

  import processing.core.PImage

  /**
    * Texture Triangle.
    *
    * Using a rectangular image to map a texture onto a triangle.
    */
  var img: PImage = null

  override def init(): Unit = {

    img = parent.loadImage(imagePath)
    parent.noStroke()
  }

  def curscene(): Unit = {
    parent.background(0)
    parent.translate(parent.fwidth / 2, parent.fheight / 2, 0)
    parent.rotateY(numericFuncs.onedim.rescale(parent.mouseX.toFloat, (0, parent.fwidth), (-Math.PI, Math.PI)).toFloat)
    parent.beginShape
    parent.texture(img)
    parent.vertex(-100, -100, 0, 0, 0)
    parent.vertex(100, -40, 0, 300, 120)
    parent.vertex(0, 100, 0, 200, 400)
    parent.endShape
  }

  override def control(): Unit = {}
}
