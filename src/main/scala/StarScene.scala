
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

  var h = 0
  var b = 100
  var s = 100

  color = parent.color(h,s,b)

  var changeRate =  .8

  pos = initpos.copy()
  //val speed = 10

  override def control(): Unit = {

    if (parent.key == 'n') {
      parent.translate(initpos.x, initpos.y)
      parent.shape(shape)
      pos = initpos.copy()
      color = parent.color(0,255,255)
    }
    if (parent.keyCode == 37) {
      h = Math.max(h - 10,0)
      color = parent.color(h,s,b)
      //      pos + Point2d(-10,0)
    }
    if (parent.keyCode == 39) {
      h = Math.min(h + 10,255)
      color = parent.color(h,s,b)
      //pos = pos + Point2d(10,0)
    }
    if (parent.keyCode == 38) {
      b = Math.max(b - 10,0)
      color = parent.color(h,s,b)
      //pos = pos + Point2d(0,-10)
    }
    if (parent.keyCode == 40) {
      b = Math.min(b + 10,255)
      color = parent.color(h,s,b)
      //pos = pos + Point2d(0,10)
    }
  }

  override def iteration(): Unit = {
    pos = pos*(1-changeRate) + Point2d(parent.mouseX,parent.mouseY)*changeRate
    h = (h*(1-changeRate) + Main.rnd.nextInt(360)*changeRate).toInt
    color = parent.color(h,s,b)
  }
}

case class StarScene(parent: Main) extends Scene(parent) {

  var pos: Point2d = parent.center

  val initpos: Point2d = parent.center
  val star = StarShape(parent, initpos)

  override def init(): Unit = star.init()

  override def control(): Unit =  star.control()

  override def curscene(): Unit = {
    print(s"\r pos: $pos")

    star.curscene()

  }
}
