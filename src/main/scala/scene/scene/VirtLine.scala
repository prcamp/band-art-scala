package scene.scene

import processing.core.{PApplet, PConstants}

case class VirtLine(var parent: PApplet, var x1: Float, var y1: Float, var x2: Float, var y2: Float) {

  isShading = false
  color = parent.color(0, 0, 255, 220)
  var isShading = false
  var left = false
  var color = 0

  def display(): Unit = {
    if (isShading) {
      val shade = parent.createShape
      shade.beginShape
      shade.fill(color)
      shade.noStroke
      if (left) {
        shade.vertex(0, y1)
        shade.vertex(x1, y1)
        shade.vertex(x2, y2)
        shade.vertex(0, y2)
      }
      else {
        shade.vertex(x1, y1)
        shade.vertex(640, y1)
        shade.vertex(640, y2)
        shade.vertex(x2, y2)
      }
      shade.endShape(PConstants.CLOSE)
      parent.shape(shade)
    }
    parent.line(x1, y1, x2, y2) // draw a line

  }

  def update(): Unit = {
    x1 = parent.random(540)
    y1 = 0
    x2 = parent.random(640)
    y2 = 360
    color = parent.color(parent.random(255), parent.random(255), parent.random(255), 200)
  }

  def setShade(left: Boolean): Unit = {
    isShading = !isShading
    this.left = left
  }

  // debug will print the four float values which
  // represent the two points of this object
  def debug(): Unit = {
    println("x1: " + x1 + ", y1: " + y1 + ", x2: ", +x2 + ", y2: " + y2)
  }

  // pointDebug prints out the four float values
  // in a pretty point looking format
  def pointDebug(): Unit = {
    println("p1: (" + x1 + ", " + y1 + ") p2: (", +x2 + ", " + y2 + ")")
  }

  def normalizeVector(): Unit = {
    // could/ should return a normalized vector
  }

  // will move only orthogonally to itself
  // if left is true it will move leftward
  def moveOrth(left: Boolean): Unit = {
    val v1 = x2 - x1
    val v2 = y2 - y1
    // solve for an orthogonal vector (a,b)
    val b = -v1 / v2
    //assumes "a" is 1
    // unitize the orthogonal vector
    val orthLen = Math.sqrt(Math.pow(1, 2) + Math.pow(b, 2))
    val orthU1 = 1 / orthLen
    val orthU2 = b / orthLen
    // Move by the orthogonal
    if (left) {
      x1 = (x1 - orthU1).toFloat
      x2 = (x2 - orthU1).toFloat
      y1 = (y1 - orthU2).toFloat
      y2 = (y2 - orthU2).toFloat
    }
    else {
      x1 = (x1 + orthU1).toFloat
      x2 = (x2 + orthU1).toFloat
      y1 = (y1 + orthU2).toFloat
      y2 = (y2 + orthU2).toFloat
    }
  }

  // Will move in a standard uniform direction
  def move(): Unit = { // assume every line is going down and to the right at 45 degrees
    x1 = (x1 + 0.85).toFloat
    y1 = (y1 + 0.85).toFloat
    x2 = (x2 + 0.85).toFloat
    y2 = (y2 + 0.85).toFloat
    // move right along the x
    //x1++;
    //x2++;
  }
}
