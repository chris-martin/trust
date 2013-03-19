import java.security.SecureRandom
import org.neo4j.graphdb.factory.GraphDatabaseFactory
import org.neo4j.kernel.EmbeddedGraphDatabase
import org.neo4j.test.ImpermanentGraphDatabase
import scala.util.Random

trait PrivateAuthKey

trait PublicAuthKey

trait Statement

trait Belief

trait TrustSystem

case class Anchor(bits: Seq[Byte])

trait AnchorGenerator {
  def nextAnchor(): Anchor
}

class RandomAnchors(random: Random = new SecureRandom()) extends AnchorGenerator {

  def nextAnchor() = {
    val array = new Array[Byte](20)
    random.nextBytes(array)
    Anchor(array)
  }
}

class ConsecutiveAnchors(it: Iterator[Int] = Stream.from(0).iterator) extends AnchorGenerator {

  def nextAnchor() = {
    val array = new Array[Byte](4)
    java.nio.ByteBuffer.wrap(array).putInt(it.next())
    Anchor(array)
  }
}

trait TrustSystemSimulator {

  val graphDb: EmbeddedGraphDatabase = new ImpermanentGraphDatabase()

  val anchorGenerator = new ConsecutiveAnchors()
  def anchor: Anchor = anchorGenerator.nextAnchor()

  implicit class RichAnchor(a: Anchor) {
    def isA(b: Anchor): Statement = ???
  }

  def state(x: Any) = ???

}

import org.scalatest._

object TrustSystemSimulatorSpec extends FreeSpec {

  """A very simple to-do list. Each entry has a string that describes it and
    |a boolean flag indicating whether it is open or closed.
  """.stripMargin -
    new TrustSystemSimulator {

      val todoListEntry = anchor
      val firstEntry = anchor
      state(firstEntry isA todoListEntry)

    }

}
