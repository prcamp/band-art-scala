import processing.core.PApplet


abstract class Scene(parent:PApplet) {
  def init(): Unit = {}
  def control(): Unit = {}
  def scene(): Unit = {}
  //  def merge(other: Scene): Scene = {
  //    new Scene(this.parent) {
  //      override def init(): Unit = {
  //        super.init()
  //        //other.init()
  //      }
  //
  //      override def control(): Unit = {
  //        super.control()
  //        //other.control()
  //      }
  //
  //      override def scene(): Unit = {
  //        super.scene()
  //        other.scene()
  //      }
  //    }
  //  }
}