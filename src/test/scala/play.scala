trait PrivateAuthKey

trait PublicAuthKey

trait Assertion

trait Belief

trait TrustSystem

trait BitString {

  def bits: Seq[Boolean]

  def paddedBits(blockSize: Int): Seq[Boolean] = bits ++ Seq(true) ++
    Stream.fill(BigInt(blockSize - bits.size - 1).mod(blockSize).toInt)(false)

  sealed def asBytes: Seq[Byte] = ???

}

trait Anchor {

  def bits: BitString

}

trait TrustSystemSimulator

import org.scalatest._

object TrustSystemSimulatorSpec extends FreeSpec {

  "" - {



  }

}
