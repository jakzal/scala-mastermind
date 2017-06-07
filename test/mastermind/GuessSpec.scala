package mastermind

import org.junit.runner.RunWith
import org.scalamock.scalatest.MockFactory
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FlatSpec, Matchers}

@RunWith(classOf[JUnitRunner])
class GuessSpec extends FlatSpec with Matchers with MockFactory {
  "exactHits" should "return exact hits for secret and guess codes" in {
    val secretCode = stub[Code]
    val guessCode = stub[Code]

    (secretCode.exactHits _).when(guessCode) returns (2)

    val guess = new Guess(secretCode, guessCode)

    guess.exactHits() should be(2)
  }

  "colourHits" should "return colour only hits for secret and guess codes" in {
    val secretCode = stub[Code]
    val guessCode = stub[Code]

    (secretCode.colourHits _).when(guessCode) returns (3)

    val guess = new Guess(secretCode, guessCode)

    guess.colourHits() should be(3)
  }

  "isCodeBroken" should "return true if the last guess code matches the secret code" in {
    val guess = new Guess(Code("Red"), Code("Red"))

    guess.isCodeBroken() should be(true)
  }

  "isCodeBroken" should "return false if the last guess code does not match the secret code" in {
    val guess = new Guess(Code("Red"), Code("Blue"))

    guess.isCodeBroken() should be(false)
  }
}
