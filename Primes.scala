import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.TreeSet

object Primes {
  var ts = TreeSet[Int]()
  def isPrime(n: Int): Boolean = (2 to math.sqrt(n).toInt) forall (x => n % x != 0)
 
}