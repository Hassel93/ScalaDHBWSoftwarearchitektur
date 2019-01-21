import scala.concurrent.{ Future, Promise, Await }
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{ Failure, Success }
import scala.concurrent.Future._
import io.StdIn._
import scala.concurrent.duration._
import scala.collection.mutable.TreeSet
import scala.collection.mutable.ArrayBuffer
import scala.concurrent.ExecutionContext
import scala.io.Source
import scala.collection.parallel.immutable.ParRange
import scala.concurrent.Awaitable

object HelloWorld {
  def main(args: Array[String]): Unit = {

    //Nur zum zeigen der Nebenläufigkeit
    println("Start")

    val starter = 1
    val ender = 10000000

    val mode = 6

    val choosefunction = mode match {
      case 1 => ausführlich(starter, ender)
      case 2 => mittelkurz(starter, ender)
      case 3 => kurz1(starter, ender)
      case 4 => kurz2(starter, ender)
      case 5 => mehrereThreads(starter, ender)
      case 6 => onboardScalaLoesungParrallel(starter, ender)
    }

    for (line <- Source.fromFile("C:/Users/hassed/workspace/ScalaSoftwarearchitektur/src/Output.txt").getLines) {
      println(line)
    }

    Await.ready(choosefunction, Duration.Inf)

    println("Ende")
  }

  def ausführlich(starter: Int, ender: Int): Future[Unit] = {
    val asyncPrimes: Future[Unit] = {
      val p = Promise[Unit]()
      Future {
        p.success(Primes.add((starter to ender).filter(p => Primes.isPrime(p))))
      }
      p.future
    }

    asyncPrimes.onComplete {
      case Success(list) => {
        println(Primes.set.size)
      }
      case Failure(t) => println(Primes.set.size)
    }

    return asyncPrimes;
  }

  def mittelkurz(starter: Int, ender: Int): Future[Unit] = {
    val asyncPrimes = Future { Primes.add((starter to ender).filter(p => Primes.isPrime(p))) }
    asyncPrimes.onComplete(_ => println(Primes.set.size))
    return asyncPrimes
  }

  def kurz1(starter: Int, ender: Int): Future[Unit] = {
    val asyncPrimes = Future { Primes.add((starter to ender).filter(p => Primes.isPrime(p))) }.map(primeSetFromRange => { println(Primes.set.size) })
    return asyncPrimes
  }

  def kurz2(starter: Int, ender: Int): Future[Unit] = {
    val asyncPrimes = Future { (starter to ender).filter(p => Primes.isPrime(p)) }.map(primeSetFromRange => {
      Primes.add(primeSetFromRange)
      println(Primes.set.size)
    })
    return asyncPrimes
  }

  def mehrereThreads(starter: Int, ender: Int): Future[Unit] = {
    return Future.sequence(
      Seq(
        Future { Primes.add((starter to ender / 4 * 1).filter(p => Primes.isPrime(p))) },
        Future { Primes.add((ender / 4 * 1 + 1 to ender / 4 * 2).filter(p => Primes.isPrime(p))) },
        Future { Primes.add((ender / 4 * 2 + 1 + 1 to ender / 4 * 3).filter(p => Primes.isPrime(p))) },
        Future { Primes.add((ender / 4 * 3 + 1 to ender).filter(p => Primes.isPrime(p))) })).map(f => println(Primes.set.size))
  }
  
  
  def onboardScalaLoesungParrallel(starter: Int, ender: Int): Future[Unit] = {
    Future { (ParRange.apply(starter, ender, 1, true)).filter(p => Primes.isPrime(p)).toList.length }.map(f => println(f))
  }

}