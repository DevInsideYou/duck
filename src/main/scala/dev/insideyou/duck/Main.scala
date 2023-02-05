package dev.insideyou
package duck

import cats.syntax.all.*

@main def Main(args: String*): Unit =
  println("─" * 27)

  val good = Dog.Raw("snoopy", "poodle", 5)
  println(Dog.fromRaw(good).map(_.green))
  println("─" * 27)
  println(Dog.fromRaw(good).map(Dog.Raw.fromDog).map(_.green))

  println("─" * 137)

  val bad = Dog.Raw("", "  poodle  ", 30)
  println(Dog.fromRaw(bad).leftMap(_.map(_.red)))
  println("─" * 137)
  println(Dog.fromRaw(bad).map(Dog.Raw.fromDog).leftMap(_.map(_.red)))

  println("─" * 137)

extension (self: Any)
  def green: String =
    Console.GREEN + self + Console.RESET

  def red: String =
    Console.RED + self + Console.RESET
