//https://www.openprocessing.org/sketch/432600
class OpArtClass1 {

}
//
//def setup(): Unit = {
//  size(800, 800)
//}
//
//def draw(): Unit = {
//  background(255)
//  val curveWidth = 10.0
//  val curveThickness = 10.0
//  val angleOffset = 0.9
//  val angleIncrement = map(mouseY, 0, height, 0.0, 0.2)
//  var angleA = 0.0
//  var angleB = angleA + angleOffset
//  val k = map(mouseX, 0, width, 2.0, 4.0)
//  var i = 0
//  while ( {
//    i < height
//  }) {
//    val waveColor = map(i, 0, mouseY + 10, 0, 255)
//    noStroke
//    pushMatrix
//    translate(0, 400)
//    fill(waveColor)
//    beginShape(QUAD_STRIP)
//    var x = 0
//    while ( {
//      x <= width
//    }) { //float y1 = i + (sin(angleA)* curveWidth);
//      //float y2 = i + (sin(angleB)* curveWidth);
//      val y1 = i + (sin(angleB) * curveWidth)
//      val y2 = i + (cos(angleA) * curveWidth)
//      vertex(x, y1)
//      vertex(x, y2 + curveThickness)
//      angleA += angleIncrement
//      angleB = angleA + angleOffset
//
//      x += 10
//    }
//    endShape
//    pushMatrix
//    scale(1.0, -1.0)
//    beginShape(QUAD_STRIP)
//    var x = 0
//    while ( {
//      x <= width
//    }) {
//      val y1 = i + (sin(angleB) * curveWidth)
//      val y2 = i + (cos(angleA) * curveWidth)
//      vertex(x, y1)
//      vertex(x, y2 + curveThickness)
//      angleA += angleIncrement
//      angleB = angleA + angleOffset
//
//      x += 10
//    }
//    endShape
//    popMatrix
//    popMatrix
//
//    i += curveWidth * k
//  }
//}
