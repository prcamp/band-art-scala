//https://www.openprocessing.org/sketch/555063
class Ripples {

}
//
//import processing.core.PVector
//
//val nb = 55
//val step = 8
//val DIST = 50
//val DISTORTION = 15
//val parts = new Array[Array[Dummy#Part]](nb, nb)
//var mode = true
//
//def setup(): Unit = {
//  size(500, 500, P2D)
//  val dx = (width - nb * step) / 2
//  var i = 0
//  while ( {
//    i < nb
//  }) {
//    var j = 0
//    while ( {
//      j < nb
//    }) {
//      parts(i)(j) = new Dummy#Part(i * step + dx, j * step + dx)
//
//      {
//        j += 1; j - 1
//      }
//    }
//
//    {
//      i += 1; i - 1
//    }
//  }
//}
//
//def draw(): Unit = {
//  background(160)
//  val m = new PVector(mouseX, mouseY)
//  var i = 0
//  while ( {
//    i < nb
//  }) {
//    var j = 0
//    while ( {
//      j < nb
//    }) {
//      parts(i)(j).update(m)
//
//      {
//        j += 1; j - 1
//      }
//    }
//
//    {
//      i += 1; i - 1
//    }
//  }
//}
//
//class Part(val x: Int, val y: Int) {
//  pos = new PVector(x, y)
//  origin = pos.get
//  speed = new PVector(0, 0)
//  var pos = null
//  var speed = null
//  var origin = null
//
//  def update(m: PVector): Unit = {
//    var tmp = origin.get
//    tmp.sub(m)
//    val d = tmp.mag
//    val c = map(d, 0, DIST, 0, PI)
//    tmp.normalize
//    if (mode) tmp.mult(DISTORTION * sin(c))
//    if (d < DIST) {
//      strokeWeight(1 + 10 * abs(cos(c / 2)))
//      if (!mode) tmp.mult(DISTORTION * sin(c))
//    }
//    else strokeWeight(map(min(d, width), 0, width, 5, .1))
//    val target = new PVector(origin.x + tmp.x, origin.y + tmp.y)
//    tmp = pos.get
//    tmp.sub(target)
//    tmp.mult(-map(m.dist(pos), 0, 2 * width, .1, .01))
//    speed.add(tmp)
//    speed.mult(.87)
//    pos.add(speed)
//    point(pos.x, pos.y)
//  }
//}
//
//def mousePressed(): Unit = {
//  mode = !mode
//}