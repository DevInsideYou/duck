package dev.insideyou
package duck

import cats.data.*
import cats.syntax.all.*
import eu.timepit.refined.*
import eu.timepit.refined.api.*
import eu.timepit.refined.auto.*
import eu.timepit.refined.boolean.*
import eu.timepit.refined.collection.*
import eu.timepit.refined.numeric.*
import eu.timepit.refined.string.*
import io.github.arainko.ducktape.*

import Dog.*

final case class Dog(
    name: Name,
    breed: Breed,
    age: Age,
  )

object Dog:
  export Name.Value as Name
  export Breed.Value as Breed
  export Age.Value as Age

  private type NonEmptyAndTrimmed = NonEmpty And Trimmed

  object Name:
    opaque type Value = String Refined NonEmptyAndTrimmed
    def Value(value: String): String Either Value = refineV(value)
    given Transformer[Value, String] = identity

  object Breed:
    opaque type Value = String Refined NonEmptyAndTrimmed
    def Value(value: String): String Either Value = refineV(value)
    given Transformer[Value, String] = identity

  object Age:
    opaque type Value = Int Refined (Positive And Less[25])
    def Value(value: Int): String Either Value = refineV(value)
    given Transformer[Value, Int] = identity

  final case class Raw(
      name: String,
      breed: String,
      age: Int,
    )

  object Raw:
    def fromDog(dog: Dog): Raw =
      dog.to[Raw]

  def fromRaw(raw: Raw): EitherNec[String, Dog] =
    (
      Name(raw.name).toEitherNec,
      Breed(raw.breed).toEitherNec,
      Age(raw.age).toEitherNec,
    ).parMapN(Dog.apply)
