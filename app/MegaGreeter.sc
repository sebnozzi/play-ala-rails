object MegaGreeter {

  class MegaGreeter(var names: Seq[String] = Seq("World")) {

    def names_=(newName: String):Unit = {
      if(newName==null)
        names = Seq()
      else
        this.names = Seq(newName)
    }

    private def chainedNames = names.mkString(", ")

    def sayHi() {
      if (names == null) {
        println("...")
      } else {
        names.foreach { name =>
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

  mg.names = null:String
  mg.sayHi
  mg.sayBye                                       //> Goodbye . Come back soon!

}