import scala.util.Random

// https://www.openprocessing.org/sketch/167663
case class ColoredArrows1(parent: Main) extends Scene(parent){
  val rnd = Random
  override def init(): Unit = {}

  override def control(): Unit = {}

  override def curscene(): Unit = {
    parent.background(0)
    var x = 50
    while ( {
      x <= 700
    }) {
      var y = 50
      while ( {
        y <= 700
      }) {
        val angle = Math.atan2(parent.mouseY - y, parent.mouseX - x)

        parent.fill(rnd.nextInt(Math.max(parent.mouseX,1)), rnd.nextInt(Math.max(parent.mouseY,1)), 255)
        parent.pushMatrix
        parent.translate(x, y)
        parent.rotate(angle.toFloat)
        parent.triangle(-20, -8, 20, 0, -20, 8)
        parent.popMatrix

        y += 50
      }

      x += 50
    }
  }
}

