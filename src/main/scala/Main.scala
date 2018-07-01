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

  var movingX = 15

  override def draw(): Unit = {
    background(0)
    stroke(255)
    var i = 0
    while ( {
      i < 20
    }) {
      stroke(255, 0 + i * 12)
      point(movingX + i * 10, 25)
      point(movingX + 5 + i * 10, 35)

      {
        i += 1; i
      }
    }
    movingX = movingX + 5
    if (movingX >= 640) movingX = 0
    Thread.sleep(50)
  }

  override def keyPressed(): Unit = {
  }
}




