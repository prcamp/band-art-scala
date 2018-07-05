
case class StarCoords() {
  val coords = Seq(
    Point2d(0,-50),
    Point2d(14, -20),
    Point2d(47, -15),
    Point2d(23, 7),
    Point2d(29, 40),
    Point2d(0, 25),
    Point2d(-29, 40),
    Point2d(-23, 7),
    Point2d(-47, -15),
    Point2d(-14, -20)
  )
}

case class StarShape(parent: Main, initpos: (Int,Int)) extends ShapeObj(parent, (new StarCoords).coords) {

  var r = 255
  var b = 0
  var g = 0

  var color = parent.color(r,g,b)

  var pos: Point2d = Point2d(initpos._1,initpos._2)
  //val speed = 10


  override def control(): Unit = {

    if (parent.key == 'n') {
      pos = Point2d(initpos._1,initpos._2)
      color = parent.color(0,255,255)
    }
    if (parent.keyCode == 37) pos = {
      r = Math.max(r - 10,0)
      color = parent.color(r,g,b)
      pos + Point2d(-10,0)
    }
    if (parent.keyCode == 39) {
      r = Math.min(r + 10,255)
      color = parent.color(r,g,b)
      pos = pos + Point2d(10,0)
    }
    if (parent.keyCode == 38) {
      b = Math.max(r - 10,0)
      color = parent.color(r,g,b)
      pos = pos + Point2d(0,-10)
    }
    if (parent.keyCode == 40) {
      b = Math.min(r + 10,255)
      color = parent.color(r,g,b)
      pos = pos + Point2d(0,10)
    }
  }

  override def curscene(): Unit = {
    parent.translate(parent.mouseX,parent.mouseY)//pos.x,pos.y) // Works!
    //star.
    shape.setFill(color)
    //    star.endShape()
    parent.shape(shape)
  }
}

case class Star(parent: Main) extends Scene(parent) {

  // First create the shape
  val p = Point2d
//  var star: processing.core.PShape = _



  val basex = parent.fwidth/2
  val basey = parent.fheight/2
  //val star = StarShape(parent,(basex,basey))
  var pos: Point2d = Point2d(basex,basey)
  //val speed = 10

  val star = StarShape(parent,(basex,basey))

  override def init(): Unit = {
    star.init()
//    star= parent.createShape()
//    def v(p:Point2d) = star.vertex(p.x,p.y)
//    // You can set fill and stroke
//    star.beginShape()
//    star.fill(0,0,255)
//    coords.foreach(
//      pnt => v(pos + pnt)
//    )
//    star.endShape()
  }

  override def control(): Unit = {
    star.control()
  }

  override def curscene(): Unit = {
    print(s"\r pos: $pos")
    parent.background(0)
    //    init()
    //    parent.translate(pos.x,pos.y)
    //    coords.coords.foreach(
    //      pnt => v(pos + pnt)
    //    )
    //    star.beginShape()
    parent.pushMatrix()
    star.curscene()
    parent.popMatrix()
  }
}

