object MegaGreeter {

  class MegaGreeter(var names: Any = Seq("World")) {

    private def nameList: Seq[String] = {
      names match {
        case seq: Seq[_] => seq.map(_.toString)
        case _ => Seq(names.toString())
      }
    }

    private def chainedNames = nameList.mkString(", ")

    def sayHi() {
      if (names == null) {
        println("...")
      } else {
        nameList.foreach { name =>
          println(s"Hello $name")
        }
      }
    }

    def sayBye() {
      if (names == null) {
        println("...")
      } else {
        println(s"Goodbye $chainedNames. Come back soon!")
      }
    }
  }

  val mg = new MegaGreeter()                      //> mg  : MegaGreeter.MegaGreeter = MegaGreeter$MegaGreeter@32fac7c5
  mg.sayHi                                        //> Hello World
  mg.sayBye                                       //> Goodbye World. Come back soon!

  mg.names = "Zeke"
  mg.sayHi                                        //> Hello Zeke
  mg.sayBye                                       //> Goodbye Zeke. Come back soon!

  mg.names = List("Albert", "Brenda", "Charles",
    "Dave", "Engelbert")
  mg.sayHi                                        //> Hello Albert
                                                  //| Hello Brenda
                                                  //| Hello Charles
                                                  //| Hello Dave
                                                  //| Hello Engelbert
  mg.sayBye                                       //> Goodbye Albert, Brenda, Charles, Dave, Engelbert. Come back soon!

  mg.names = null
  mg.sayHi                                        //> ...
  mg.sayBye                                       //> ...

}