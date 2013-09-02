object MegaGreeter {

  class MegaGreeter(var names: Seq[String] = Seq("World")) {

    private def nameList: Seq[String] = {
      names match {
        case seq: Seq[_] => seq.map(_.toString)
        case _ => Seq(names.toString())
      }
    }

    def names_=(newName: String):Unit = {
      if(newName==null)
        names = Seq()
      else
        this.names = Seq(newName)
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

  val mg = new MegaGreeter()                      //> mg  : MegaGreeter.MegaGreeter = MegaGreeter$MegaGreeter@6e5170a8
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