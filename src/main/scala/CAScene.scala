case class CAScene(parent: Main) extends Scene(parent){

  val scenes = List(GOL, RPS) // CA // ShapeIm
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
