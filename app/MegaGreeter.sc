object MegaGreeter {

  class MegaGreeter(val names: Seq[String] = Seq("World")) {

    def this(name: String) = this(Seq(name))
    def this(name: Option[String]) = this(
      name.map(str => Seq(str)).getOrElse(Seq())
    )

    private def chainedNames = names.mkString(", ")

    def sayHi() {
      if (names.isEmpty) {
        println("...")
      } else {
        names.foreach { name =>
          println(s"Hello $name")
        }
      }
    }

    def sayBye() {
      if (names.isEmpty) {
        println("...")
      } else {
        println(s"Goodbye $chainedNames. Come back soon!")
      }
    }
  }

  {
    val mg = new MegaGreeter()
    mg.sayHi
    mg.sayBye
  }                                               //> Hello World
                                                  //| Goodbye World. Come back soon!

  {
    val mg = new MegaGreeter(name = "Zeke")
    mg.sayHi
    mg.sayBye
  }                                               //> Hello Zeke
                                                  //| Goodbye Zeke. Come back soon!

  {
    val mg = new MegaGreeter(List("Albert", "Brenda", "Charles",
      "Dave", "Engelbert"))
    mg.sayHi
    mg.sayBye
  }                                               //> Hello Albert
                                                  //| Hello Brenda
                                                  //| Hello Charles
                                                  //| Hello Dave
                                                  //| Hello Engelbert
                                                  //| Goodbye Albert, Brenda, Charles, Dave, Engelbert. Come back soon!

  {
    val mg = new MegaGreeter(name = None)
    mg.sayHi
    mg.sayBye
  }                                               //> ...
                                                  //| ...

}