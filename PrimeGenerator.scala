import scala.concurrent.Future
import scala.collection.parallel.immutable.ParRange


class PrimeGenerator(val range:ParRange) extends IsPrime{
  def getResult():List[Int]={
    range.filter(p=> isPrime(p)).toList
  }
}