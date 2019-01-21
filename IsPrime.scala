

trait IsPrime {
  def isPrime(n: Int): Boolean = (2 to math.sqrt(n).toInt) forall (x => n % x != 0)
}