import scala.concurrent.{ Future, Promise, Await }
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{ Failure, Success }
import scala.concurrent.Future._
import io.StdIn._
import scala.concurrent.duration._
import scala.collection.mutable.TreeSet
import scala.collection.mutable.ArrayBuffer
import scala.concurrent.ExecutionContext

object HelloWorld {
  def main(args: Array[String]): Unit = {
    val starter = 1
    val ender = 5000000
    
    println(1+Math.random())
    
    val asyncPrimes: Future[Unit] = HelloWorld.asyncMethod(starter, ender)
    asyncPrimes.onComplete(_ => println(Primes.ts.size))
    
    println(1+Math.random())
    
    Await.ready(asyncPrimes, Duration.Inf)
    
    
  }

  def asyncMethod(start: Int, end: Int): Future[Unit] = Future {
    val a = Array.range(start, end)
    Primes.ts ++= a.filter(p => Primes.isPrime(p))
  }
}