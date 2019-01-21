

class Message(var message:Int) {
  def output(): Unit = {
    println(s"($message)")
  }
}