package scene.scene

import processing.core.PApplet



class ExampleScene1(parent: PApplet) extends Scene(parent) {
  var rectWidth = 1
  parent.draw()


  override def keyPressed(): Unit = {
    var keyIndex = -1
    val key = parent.key
    if (key >= 'A' && key <= 'Z') keyIndex = key - 'A'
    else if (key >= 'a' && key <= 'z') keyIndex = key - 'a'
    if (keyIndex == -1) { // If it's not a letter key, clear the screen
      parent.background(0)
    }
    else { // It's a letter key, fill a rectangle
      parent.fill(parent.millis % 255)
      val x = numericFuncs.onedim.rescale(keyIndex, (0, 25), (0, parent.width - rectWidth))
      parent.rect(x.toFloat, 0, rectWidth, parent.height)
    }
  }



}

/**
  * Keyboard.
  *
  * Click on the image to give it focus and press the letter keys
  * to create forms in time and space. Each key has a unique identifying
  * number. These numbers can be used to position shapes in space.
  */

