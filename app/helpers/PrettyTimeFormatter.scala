package helpers

import org.ocpsoft.prettytime.PrettyTime
import java.util.Locale

object PrettyTimeFormatter {

  val formatter = new PrettyTime(new Locale("en"))
  
}