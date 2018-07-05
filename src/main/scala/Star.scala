
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

case class StarShape(parent: Main, override val initpos: Point2d) extends ShapeObj(parent, (new StarCoords).coords) {

  var r = 255
  var b = 0
  var g = 0

  var color = parent.color(r,g,b)

  pos = initpos.copy()
  //val speed = 10

  override def control(): Unit = {

    if (parent.key == 'n') {
      parent.translate(initpos.x,initpos.y)
      parent.shape(shape)
      pos = initpos.copy()
      color = parent.color(0,100,100)
    }
    if (parent.keyCode == 37) pos = {
      r = Math.max(r - 10,0)
      color = parent.color(r,g,b)
      pos + Point2d(-10,0)
    }
    if (parent.keyCode == 39) {
      r = Math.min(r + 10,100)
      color = parent.color(r,g,b)
      pos = pos + Point2d(10,0)
    }
    if (parent.keyCode == 38) {
      b = Math.max(r - 10,0)
      color = parent.color(r,g,b)
      pos = pos + Point2d(0,-10)
    }
    if (parent.keyCode == 40) {
      b = Math.min(r + 10,100)
      color = parent.color(r,g,b)
      pos = pos + Point2d(0,10)
    }
  }

  override def curscene(): Unit = {
    pos = pos.*((1-.1).toFloat) + Point2d(parent.mouseX,parent.mouseY).*(0.1.toFloat)
    parent.background(0)
    //star.
    parent.pushMatrix()
    shape.setFill(color)
    parent.translate(pos.x,pos.y)//pos.x,pos.y) // Works!
    //    star.endShape()
    parent.shape(shape)
    parent.popMatrix()
  }
}

case class Star(parent: Main) extends Scene(parent) {

  // First create the shape
  val p = Point2d
//  var star: processing.core.PShape = _

  //val star = StarShape(parent,(basex,basey))
  var pos: Point2d = parent.center//Point2d(basex,basey)
  //val speed = 10

  val star = StarShape(parent,parent.center)

  override def init(): Unit = {
    star.init()
//    star= parent.createShape()
//    def v(p:Point2d) = star.vertex(p.x,p.y)
//    // You can set fill and stroke
//    star.beginShape()
//    star.fill(0,0,100)
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

    star.curscene()

  }
}

case class Stars(parent: Main) extends Scene(parent) {
  var field = List[ShapeObj]()
  //
  //  override def init(): Unit = {
  //    field = centers.map(pnt => StarShape(parent,pnt))
  //  }
  //
  val numStars = 20
  override def init(): Unit = {
    val centers = Point2d.ring(parent.center,20,numStars)
    field = centers.map(pnt => StarShape(parent,pnt))

  }

  override def control(): Unit = field.foreach(shp => shp.control())

  override def curscene(): Unit = field.foreach(shp => shp.curscene())
}