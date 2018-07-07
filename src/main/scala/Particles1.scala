//import processing.core.PVector
//
//// From the standard processing examples
//case class Particles1(parent: Main) extends Scene(parent) {
//
//}
//
//case class Particle(parent: Main, l: PVector) extends ShapeObj(parent){
//  var acceleration = new PVector(0, 0.05)
//  var velocity = new PVector(parent.random(-1, 1), parent.random(-2, 0))
//  pos = l.copy
//  lifespan = 255.0
//  var position = null
//  var velocity = null
//  var acceleration = null
//  var lifespan = .0
//
//  def run(): Unit = {
//    update()
//    display()
//  }
//
//  // Method to update position
//  def update(): Unit = {
//    velocity.add(acceleration)
//    position.add(velocity)
//    lifespan -= 1.0
//  }
//
//  // Method to display
//  def display(): Unit = {
//    stroke(255, lifespan)
//    fill(255, lifespan)
//    ellipse(position.x, position.y, 8, 8)
//  }
//
//  // Is the particle still useful?
//  def isDead = if (lifespan < 0.0) true
//  else false
//}
////
////import processing.core.PVector
//
///*
////
//var ps = null
//
//def setup(): Unit = {
//  size(640, 360)
//  ps = new Dummy#ParticleSystem(new PVector(width / 2, 50))
//}
//
//def draw(): Unit = {
//  background(0)
//  ps.addParticle()
//  ps.run()
//}
//
//
//// A class to describe a group of Particles
//// An ArrayList is used to manage the list of Particles
//
//class ParticleSystem(val position: PVector) {
//  origin = position.copy
//  particles = new ArrayList[Dummy#Particle]
//  var particles = null
//  var origin = null
//
//  def addParticle(): Unit = {
//    particles.add(new Dummy#Particle(origin))
//  }
//
//  def run(): Unit = {
//    var i = particles.size - 1
//    while ( {
//      i >= 0
//    }) {
//      val p = particles.get(i)
//      p.run()
//      if (p.isDead) particles.remove(i)
//
//      {
//        i -= 1; i + 1
//      }
//    }
//  }
//}
//
//
//// A simple Particle class
//
//*/