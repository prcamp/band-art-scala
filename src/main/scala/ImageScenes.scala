
case class ImagePatch(parent: Main) extends Scene(parent) {

  import processing.core.PImage

  /**
    * Load and Display
    *
    * Images can be loaded and displayed to the screen at their actual size
    * or any other size.
    */
  var img: PImage = _ // Declare variable "a" of type PImage
  val imagePath = "resources/images/plaids/squareseries_symmetric_very_flat_1.jpg"
  override def init(): Unit = {
    // The image file must be in the data folder of the current sketch
    // to load successfully
    img = parent.loadImage(imagePath) // Load the image into the program
  }

  override def control(): Unit = {}

  override def curscene(): Unit = {
    parent.image(img, 0, 0)
    // Displays the image at point (0, height/2) at half of its size
    parent.image(img, 0, parent.fheight/2, img.width / 2, img.height / 2)
  }
}

case class ShapeIm(parent: Main) extends Scene(parent) {
  val imagePath = "resources/images/plaids/squareseries_symmetric_very_flat_1.jpg"

  import processing.core.PImage

  /**
    * Texture Triangle.
    *
    * Using a rectangular image to map a texture onto a triangle.
    */
  var img: PImage = null

  override def init(): Unit = {

    img = parent.loadImage(imagePath)
    parent.noStroke()
  }

  def curscene(): Unit = {
    parent.background(0)
    parent.translate(parent.fwidth / 2, parent.fheight / 2, 0)
    parent.rotateY(numericFuncs.onedim.rescale(parent.mouseX.toFloat, (0, parent.fwidth), (-Math.PI, Math.PI)).toFloat)
    parent.beginShape
    parent.texture(img)
    parent.vertex(-100, -100, 0, 0, 0)
    parent.vertex(100, -40, 0, 300, 120)
    parent.vertex(0, 100, 0, 200, 400)
    parent.endShape
  }

  override def control(): Unit = {}
}
