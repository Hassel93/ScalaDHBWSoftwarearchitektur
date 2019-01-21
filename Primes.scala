import scala.collection.mutable.TreeSet

object Primes extends IsPrime{
  val set = TreeSet[Int]()
  
  def add(addSet:IndexedSeq[Int]):Unit={
    this.synchronized{
      set ++= (addSet)
    }
  }
  
  
}