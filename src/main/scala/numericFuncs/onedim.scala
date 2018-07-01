package numericFuncs

object onedim {
  def rescale(value: Double, fromIntvl: (Double,Double), toIntvl: (Double,Double)): Double = {
    val rebased = value - fromIntvl._1
    val normalized = rebased / (fromIntvl._2-fromIntvl._1)
    val scaled = normalized * (toIntvl._2-toIntvl._1)
    scaled + toIntvl._1
  }
}
