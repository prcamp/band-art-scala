import processing.core.PApplet


object Main extends PApplet {
  def main(args: Array[String]): Unit = {
    PApplet.main("Main")
  }
}

class Main extends PApplet {

  override def settings(): Unit = {
    //fullScreen()
    size(640, 360)
  }

  override def setup(): Unit = {
  }


  val movingDots = MovingDots(this)

  override def draw(): Unit = {
    movingDots.scene()
    Thread.sleep(50)
  }

  override def keyPressed(): Unit = {
    movingDots.action()
  }
}

case class MovingDots(parent: PApplet) {

  var movingX = 15


  def action(): Unit = {
    if (parent.key == 'n') movingX = 15
  }
  def scene(): Unit ={
    parent.background(0)
    parent.stroke(255)
    var i = 0
    while ( {
      i < 20
    }) {
      parent.stroke(255, 0 + i * 12)
      parent.point(movingX + i * 10, 25)
      parent.point(movingX + 5 + i * 10, 35)

      {
        i += 1; i
      }
    }
    movingX = movingX + 5
    if (movingX >= 640) movingX = 0
  }


}


