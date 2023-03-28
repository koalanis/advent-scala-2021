import scala.io.Source
import java.nio.file.{Paths,Files}

def getAdventDayDataPath(year: Int, dayNum: Int, sample: Boolean = false): Option[String] =
  val path = sys.env.get("ADVENT_HOME")
  val fileName = if (sample) "dataSample.txt" else "data.txt"
  val filePath = path match 
    case Some(value) => Some(s"$value/$year/data/day$dayNum/$fileName")
    case None => None
  filePath

def getAdventDayData(filePath: String): Option[List[String]] = {
    if (Files.exists(Paths.get(filePath))) Some(Source.fromFile(filePath).getLines().toList) else None
}


@main def template: Unit = {
  
  val year = 2021
  val day = 1
  val lines = getAdventDayDataPath(year, day, false)
                  .map(getAdventDayData)
                  .flatten
  
  lines match
    case None => ()
    case Some(list) => 
      println(list)
  
}
