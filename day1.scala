import scala.io.Source
import java.nio.file.{Paths,Files}


def determineDelta(t: (Int, Int), data: List[Int]): Option[(Int, Int)] = {
  val (line, i) = t
  if i > 0 then 
    Some((line, data(i-1)))
  else
    None
}

def determineDeltaTrend(t: (Int, Int), data: List[Int]): Option[(Int, Int)] = {
  val (line, i) = t
  if i >= 3  then
   Some((line + data(i-1) + data(i-2)), (data(i-1)+data(i-2)+data(i-3)))
  else
    None
}

def trend(t: (Int, Int)): Boolean = {
  t(0) > t(1)
}

def partOne(data: List[Int]): Unit = {
  val count = data.zipWithIndex
    .map{determineDelta(_, data)}
    .flatMap(_.map(trend))
    .count(identity)
  println(s"count=$count")
}


def partTwo(data: List[Int]): Unit = {
  val count = data.zipWithIndex
    .map{determineDeltaTrend(_, data)}
    .flatMap(_.map(trend))
    .count(identity)
  println(s"count=$count")}

@main def day1: Unit = {
 
  val year = 2021
  val day = 1
  val lines = getAdventDayDataPath(year, day, false)
                  .map(getAdventDayData)
                  .flatten

  lines match
    case None => ()
    case Some(list) =>
      val data  = list.map(_.toInt).toList
      partOne(data)
      partTwo(data)
}
