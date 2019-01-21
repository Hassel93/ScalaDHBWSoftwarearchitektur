import scala.io.Source.fromURL
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class UrlCrawler(val url: String) {

  def getUrlContent():String = {
    scala.io.Source.fromURL(url).mkString 
  }
}