// https://www.openprocessing.org/sketch/431881
class WebOfLines {

}
//
//val p = new Array[Dummy#Pa](200)
//val limit = 100
//
//def setup(): Unit = {
//  var i = 0
//  while ( {
//    i < p.length
//  }) {
//    p(i) = new Dummy#Pa
//
//    {
//      i += 1; i - 1
//    }
//  }
//  size(900, 900)
//  background(255)
//  noFill
//  stroke(0)
//  strokeWeight(1)
//}
//
//def draw(): Unit = {
//  fill(255, 10)
//  noStroke
//  rect(0, 0, width, height)
//  var i = 0
//  while ( {
//    i < p.length
//  }) {
//    p(i).show(i)
//
//    {
//      i += 1; i - 1
//    }
//  }
//}
//
//class Pa() {
//  x = random(width)
//  y = random(height)
//  val a = random(TWO_PI)
//  vx = cos(a) * 5
//  vy = sin(a) * 5
//  var x = .0
//  var y = .0
//  var vx = .0
//  var vy = .0
//
//  def show(index: Int): Unit = {
//    x += vx
//    y += vy
//    var i = index + 1
//    while ( {
//      i < p.length
//    }) {
//      val d = dist(x, y, p(i).x, p(i).y)
//      if (d < limit) {
//        stroke(0, map(d, limit / 2, limit, 255, 0))
//        line(x, y, p(i).x, p(i).y)
//      }
//
//      {
//        i += 1; i - 1
//      }
//    }
//    x = lm(x, width)
//    y = lm(y, width)
//  }
//}
//
//def lm(a: Float, b: Float): Float = {
//  if (a < 0) return a + b
//  if (a > b) return a - b
//  a
//}