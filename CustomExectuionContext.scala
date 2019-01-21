
import java.util.concurrent.Executors
import scala.concurrent.ExecutionContext

object CustomExecutionContext {
  private val availableProcessors = Runtime.getRuntime.availableProcessors()

  implicit val nDExecutionContext = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(availableProcessors * 4)) // N is number of threads
}