import processing.core.{PShape, PConstants => pc}

case class CircleScene(parent: Main) extends Scene(parent) {

  val scenes = List(CircleSceneA0,CircleSceneA, CircleSceneB) // CA // ShapeIm
  val sceneapps = scenes.map(sc => sc(parent))
  var curidx = 0

  var thisscene = sceneapps(curidx)

  override def init(): Unit = {
    sceneapps.foreach(s => s.init())
  }

  override def control(): Unit = {

    if (parent.key == 's') {
      curidx = if (curidx < sceneapps.length - 1) curidx + 1 else 0
      thisscene = sceneapps(curidx)
    }

    thisscene.control()
  }

  override def curscene(): Unit = {
    thisscene.curscene()
  }
}

case class CircleSceneA0(parent: Main) extends ShapeField(parent){

  var gradius = 200

  val initposField = IndexedSeq(parent.center*2)




  override def fiteration(): Unit = {

  }

  override def shape(parent: Main, pnt: Point2d): ShapeObj =
    new CircleShape(parent,pnt) {
      radius = gradius
    }
}

case class CircleSceneA(parent: Main) extends ShapeField(parent){

  var gradius = 20

  var nbrcoords = IndexedSeq[Seq[Int]]()
  var initposField0 = sidebyside(gradius)


  override val initposField: IndexedSeq[Point2d] =
    initposField0.sortWith{
      case (pnt1,pnt2) => pnt1.y < pnt2.y
    }


  def sidebyside(gradius: Float): IndexedSeq[Point2d] = {
    val p1 = 0.5.toFloat
    val p2 = (Math.sqrt(3)/2).toFloat
    val spacing = gradius
    val xmin = -2*spacing
    val xmax = parent.fwidth +  2*spacing
    val ymin = -2*spacing
    val ymax = parent.fheight + 2*spacing
    var offset: Float = 0
    val numRows = ((xmax - xmin)/(spacing*p2)).toInt
    val numCols = ((ymax - ymin)/ spacing).toInt
    var gridpoints = IndexedSeq[Point2d]()
    (0 until numRows).foreach(
      i => {
        if (i%2 == 0) offset = spacing/2
        else offset = 0
        val x0 = xmin + offset
        (0 until numCols).foreach(
          j => {
            val x = x0 + i*spacing*p2
            val y = xmin + j*spacing
            gridpoints = gridpoints :+ Point2d(x,y)
          }
        )
      }
    )
    gridpoints
  }



//  int rownum = 0;
  //    float offset = 0;
  //    float p1 = 1/2;
  //    float p2 = sqrt(3)/2;
  //
  //    float fctr = intrpfctr;
  //
  //    for (float i = -spacing; i < height+spacing; i+= spacing*sqrt(3)/2) {
  //      rownum += 1;
  //      if ((rownum % 2)==0) {
  //        offset = spacing/2;
  //      } else {
  //        offset = 0;
  //      }
  //      for (float j = -spacing+offset; j < width+spacing; j += spacing) {
  //        //println(j, i);
  //        //fill(color(random(255),random(255),random(255)));
  //        //stroke(color(r,g,b));
  //        //nw
  //        Circle(j-2*fctr*spacing*p1,i-2*fctr*spacing*p2,spacing,r,g,b);
  //        //ne
  //        Circle(j+2*fctr*spacing*p1,i-2*fctr*spacing*p2,spacing,r,g,b);
  //        //e
  //        Circle(j+2*fctr*spacing*2*p1,i,spacing,r,g,b);
  //        //se
  //        Circle(j+2*fctr*spacing*p1,i+2*fctr*spacing*p2,spacing,r,g,b);
  //        //sw
  //        Circle(j-2*fctr*spacing*p1,i+2*fctr*spacing*p2,spacing,r,g,b);
  //        //w
  //        Circle(j-2*fctr*spacing*2*p1,i,spacing,r,g,b);
  //      }
  //    }
  //  }




  override def fiteration(): Unit = {

  }

  override def shape(parent: Main, pnt: Point2d): ShapeObj =
    new CircleShape(parent,pnt) {
      radius = gradius
    }
}


case class CircleSceneB(parent: Main) extends ShapeField(parent){

  var gradius = 20

  var nbrcoords = IndexedSeq[Seq[Int]]()
  var initposField0 = sidebyside(gradius)


  override val initposField: IndexedSeq[Point2d] =
    initposField0.sortWith{
      case (pnt1,pnt2) => pnt1.y < pnt2.y
    }


  def sidebyside(gradius: Float): IndexedSeq[Point2d] = {
    val p1 = 0.5.toFloat
    val p2 = (Math.sqrt(3)/2).toFloat
    val spacing = gradius
    val xmin = -2*spacing
    val xmax = parent.fwidth +  2*spacing
    val ymin = -2*spacing
    val ymax = parent.fheight + 2*spacing
    var offset: Float = 0
    val numRows = ((xmax - xmin)/(spacing*p2)).toInt
    val numCols = ((ymax - ymin)/ spacing).toInt
    var gridpoints = IndexedSeq[Point2d]()
    (0 until numRows).foreach(
      i => {
        if (i%2 == 0) offset = spacing/2
        else offset = 0
        val x0 = xmin + offset
        (0 until numCols).foreach(
          j => {
            val x = x0 + i*spacing*p2
            val y = xmin + j*spacing
            gridpoints = gridpoints :+ Point2d(x,y)
          }
        )
      }
    )
    gridpoints
  }



  //  int rownum = 0;
  //    float offset = 0;
  //    float p1 = 1/2;
  //    float p2 = sqrt(3)/2;
  //
  //    float fctr = intrpfctr;
  //
  //    for (float i = -spacing; i < height+spacing; i+= spacing*sqrt(3)/2) {
  //      rownum += 1;
  //      if ((rownum % 2)==0) {
  //        offset = spacing/2;
  //      } else {
  //        offset = 0;
  //      }
  //      for (float j = -spacing+offset; j < width+spacing; j += spacing) {
  //        //println(j, i);
  //        //fill(color(random(255),random(255),random(255)));
  //        //stroke(color(r,g,b));
  //        //nw
  //        Circle(j-2*fctr*spacing*p1,i-2*fctr*spacing*p2,spacing,r,g,b);
  //        //ne
  //        Circle(j+2*fctr*spacing*p1,i-2*fctr*spacing*p2,spacing,r,g,b);
  //        //e
  //        Circle(j+2*fctr*spacing*2*p1,i,spacing,r,g,b);
  //        //se
  //        Circle(j+2*fctr*spacing*p1,i+2*fctr*spacing*p2,spacing,r,g,b);
  //        //sw
  //        Circle(j-2*fctr*spacing*p1,i+2*fctr*spacing*p2,spacing,r,g,b);
  //        //w
  //        Circle(j-2*fctr*spacing*2*p1,i,spacing,r,g,b);
  //      }
  //    }
  //  }




  override def fiteration(): Unit = {

  }

  override def shape(parent: Main, pnt: Point2d): ShapeObj =
    new CircleShape(parent,pnt) {
      radius = gradius
    }
}

object CirclePShape {
  def apply(parent: Main, initpnt: Point2d): PShape = {
    val shape = parent.createShape(pc.ELLIPSE,initpnt.x,initpnt.y,20,20)
    shape.setFill(Colors.randomColor())
    shape
  }
}

class CircleShape(parent: Main, override val initpos: Point2d) extends ShapeObj(parent,new PShape()) {
  val refradius = 20
  var radius = 20

  override def iteration(): Unit = {
    shape.scale(radius/refradius)
  }

  override def init(): Unit = {
    shape = CirclePShape(parent,initpos)
    shape.setFill(Colors.randomColor())

  }

  override def control(): Unit = {}
}

//import scala.collection.mutable.ArrayBuffer
//
//case class Circle(parent: Main) extends Scene(parent) {
//  val numShapes = 20
//  val initcenters: List[Point2d] = Point2d.ring(parent.center, 80, numShapes)
//  var centers = initcenters
//
//  val field = new ArrayBuffer[ShapePolyObj](numShapes)
//  centers.zipWithIndex.foreach{
//    case (pnt,idx) => field += shape(pnt)
//  }
//  override def init(): Unit = {
//
//    field.foreach(shp => shp.init())
//  }
//
//  override def control(): Unit = field.foreach(shp => shp.control())
//
//  override def curscene(): Unit = field.foreach(shp => shp.curscene())
//
//  val osc = Signal.tri(.5)
//
//  def fiteration(shp: StarShape): Unit = {
//    val curh = (360 * osc.signal()).toInt
//    //    print(s"\r ${osc.signal()}")
//    shp.h = curh
//    shp.color = parent.color(shp.h, shp.s, shp.b)
//  }
//
//  def shape(pnt: Point2d) = new StarShape(parent,pnt) {
//    override def iteration(): Unit = fiteration(this)
//  }
//
//
//}
//
//case class CirclesHex(parent: Main) extends Scene(parent) {
//
//  def positions(spacing: Float, intrpfctr: Float,  ): List[Point2d] = {
//
//  }
//
//  val centers =
////  void drawCircles(float spacing,int r,int g,int b,float infctr) {
//    // initialize a bunch of variables:
//    int rownum = 0;
//    float offset = 0;
//    float p1 = 1/2;
//    float p2 = sqrt(3)/2;
//
//    float fctr = intrpfctr;
//
//    for (float i = -spacing; i < height+spacing; i+= spacing*sqrt(3)/2) {
//      rownum += 1;
//      if ((rownum % 2)==0) {
//        offset = spacing/2;
//      } else {
//        offset = 0;
//      }
//      for (float j = -spacing+offset; j < width+spacing; j += spacing) {
//        //println(j, i);
//        //fill(color(random(255),random(255),random(255)));
//        //stroke(color(r,g,b));
//        //nw
//        Circle(j-2*fctr*spacing*p1,i-2*fctr*spacing*p2,spacing,r,g,b);
//        //ne
//        Circle(j+2*fctr*spacing*p1,i-2*fctr*spacing*p2,spacing,r,g,b);
//        //e
//        Circle(j+2*fctr*spacing*2*p1,i,spacing,r,g,b);
//        //se
//        Circle(j+2*fctr*spacing*p1,i+2*fctr*spacing*p2,spacing,r,g,b);
//        //sw
//        Circle(j-2*fctr*spacing*p1,i+2*fctr*spacing*p2,spacing,r,g,b);
//        //w
//        Circle(j-2*fctr*spacing*2*p1,i,spacing,r,g,b);
//      }
//    }
//  }
//
//  override def init(): Unit = ???
//
//  override def control(): Unit = ???
//
//  override def curscene(): Unit = ???
//}
//
//
//class CircleScene(parent: Main) extends Scene(parent){
//  override def init(): Unit = {}
//
//  override def control(): Unit = {}
//
//  override def curscene(): Unit = {}
//}
//
///*
//
//
//// To Do:
//// animation
//// tempo control
//// center dots
//// on and dim action
//// random play and fade
////
//
//// The background function is a statement that tells the computer
//// which color (or gray value) to make the background of the display window
////background(0);
//
////// Sets the screen to be 640 pixels wide and 360 pixels high
////size(640, 360);
//
////// Set the background to black and turn off the fill color
////background(0);
////noFill();
//
////// The two parameters of the point() method each specify coordinates.
////// The first parameter is the x-coordinate and the second is the Y
////stroke(255);
////point(width * 0.5, height * 0.5);
////point(width * 0.5, height * 0.666666);
//
////// Coordinates are used for drawing all shapes, not just points.
////// Parameters for different functions are used for different purposes.
////// For example, the first two parameters to line() specify
////// the coordinates of the first endpoint and the second two parameters
////// specify the second endpoint
////stroke(0, 153, 255);
////line(0, height*0.53, width, height*0.33);
//
////// By default, the first two parameters to rect() are the
////// coordinates of the upper-left corner and the second pair
////// is the width and height
//////stroke(255, 153, 0);
//////rect(width*0.25, height*0.1, width * 0.5, height * 0.8);
//import oscP5.*;
//import netP5.*;
//OscP5 oscP5;
//import codeanticode.syphon.*;
//
//
//float v_fader1 = 0.0f;
//float v_fader2 = 0.0f;
//float v_fader3 = 0.0f;
//float v_fader4 = 0.0f;
//float v_fader5 = 0.0f;
//float v_toggle1 = 0.0f;
//float v_toggle2 = 0.0f;
//float v_toggle3 = 0.0f;
//float v_toggle4 = 0.0f;
//
//int magnitude = 1;
//int speed = 1;
//color red = 255;
//color blue = 0;
//color green = 0;
//int scng = 50;
//float intrpfctr = 0;
//float scl = 1;
////float nextfctr = 0;
//
////float time = 1;
////float basefreq = 1;
////float dt = .1;
//
////void oscillator() {
////  inrpfctr =
////}
//
////SyphonServer server;
//
//void settings() {
// //size(1080, 720, P3D);
// fullScreen();
// PJOGL.profile=1;
//}
//
//void setup() {
// //server = new SyphonServer(this, "moontemple_circles");
// //fullScreen();
// //background(0);
// //scale(1);
// //drawCircles(30);
// frameRate(50);
//  /* start oscP5, listening for incoming messages at port 8000 */
//  oscP5 = new OscP5(this,12346);
//  println();
// ////Midi stuff:
// //MidiBus.list(); // List all available Midi devices on STDOUT. This will show each device's index and name.
// //myBus = new MidiBus(this, 0, 1); // Create a new MidiBus object
// //myBus.sendTimestamps(false); // This is for mac, apparently...
//}
//
//void draw() {
// //int channel=0;
// //int pitch = 64;
// //int velocity = 127;
// background(0);
//
// //int number = 0;
// //int value = 90;
// //ControlChange change = new ControlChange(channel, number, value);
// scale(scl);
// loop();
// drawCircles(scng,red,blue,green,intrpfctr);
// delay(0);
// //server.sendScreen();
//
//}
//
////void controllerChange(ControlChange change) {
////  // Receive a controllerChange
////  println();
////  println("Controller Change:");
////  println("--------");
////  println("Channel:"+change.channel());
////  println("Number:"+change.number());
////  println("Value:"+change.value());
////}
//
//void oscEvent(OscMessage theOscMessage) {
//    String addr = theOscMessage.addrPattern();
//    float  val  = theOscMessage.get(0).floatValue();
//    println(addr);
//    if(addr.equals("/1/fader1"))        { red = round( 255*val); }
//    else if(addr.equals("/1/fader2"))   { green = round( 255*val); }
//    else if(addr.equals("/1/fader3"))   { blue = round( 255*val); }
//    else if(addr.equals("/1/fader4"))   { scng = round(100*val)+30; }
//    else if(addr.equals("/1/fader5"))   { intrpfctr = val; }
//    //else if(addr.equals("/1/toggle1"))  { v_toggle1 = val; }
//    //else if(addr.equals("/1/toggle2"))  { v_toggle2 = val; }
//    //else if(addr.equals("/1/toggle3"))  { v_toggle3 = val; }
//    //else if(addr.equals("/1/toggle4"))  { v_toggle4 = val; }
//    else {}
//}
//
////void controllerChange(ControlChange change) {
////  switch(change.number()) {
////    case 11:
////      red = 255*change.value()/127;
////      break;
////    case 12:
////      blue = 255*change.value()/127;
////      break;
////    case 13:
////      green = 255*change.value()/127;
////      break;
////    case 15:
////      //clear();
////      //noLoop();
////      //redraw();
////      //loop();
////      scng = change.value()+30;
////      break;
////    case 14:
////      intrpfctr = change.value()/127.0;
////      break;
////    //case 16:
////    //  scl = 2*change.value()/127;
////    //  break;
//
////    }
////}
//
//
//
////void midiMessage(MidiMessage message) { // You can also use midiMessage(MidiMessage message, long timestamp, String bus_name)
////  // Receive a MidiMessage
////  // MidiMessage is an abstract class, the actual passed object will be either javax.sound.midi.MetaMessage, javax.sound.midi.ShortMessage, javax.sound.midi.SysexMessage.
////  // Check it out here http://java.sun.com/j2se/1.5.0/docs/api/javax/sound/midi/package-summary.html
////  println();
////  println("MidiMessage Data:");
////  println("--------");
////  println("Status Byte/MIDI Command:"+message.getStatus());
////  for (int i = 1;i < message.getMessage().length;i++) {
////    println("Param "+(i+1)+": "+(int)(message.getMessage()[i] & 0xFF));
////  }
////}
//
//void Circle(float x, float y, float radius, int R, int G, int B) {
//  //stroke(255);
//  noFill();
//  stroke(color(R,G,B));
//  strokeWeight(radius/25);
//  ellipse(x,y,radius*2,radius*2);
//}
//
//void delay(int time) {
//  int current = millis();
//  while (millis () < current+time) Thread.yield();
//}
//
//void drawCircle(int x, int radius, int level) {
//  //float tt = 126 * level/4.0;
//  fill(color(random(255),random(255),random(255)));
//  ellipse(x, height/2, radius*2, radius*2);
//  if(level > 1) {
//    level = level - 1;
//    drawCircle(x - radius/2, radius/2, level);
//    drawCircle(x + radius/2, radius/2, level);
//  }
//}
//
////void draw() {
////  drawTarget(width*0.25, height*0.4, 200, 4);
////  drawTarget(width*0.5, height*0.5, 300, 10);
////  drawTarget(width*0.75, height*0.3, 120, 6);
////}
//
////void drawTarget(float xloc, float yloc, int size, int num) {
////  float grayvalues = 255/num;
////  float steps = size/num;
////  for (int i = 0; i < num; i++) {
////    fill(i*grayvalues);
////    ellipse(xloc, yloc, size - i*steps, size - i*steps);
////  }
////}
// */