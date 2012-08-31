package models

import java.io.File
import play.api._
import play.api.libs.iteratee.{Input, Enumeratee, Enumerator}
import play.api.libs.concurrent.Promise
import play.api.Logger
import play.api.libs.json
import play.api.libs.json.Json
import play.api.libs.json.JsArray

case class Coordinate(latitude: BigDecimal, longitude: BigDecimal, cityname: String)

object CoordinatesStream {

  def fileLineStream(file: File) : Enumerator[String] = {
    val source = scala.io.Source.fromFile(file)
    val lines = source.getLines()

    Enumerator.fromCallback[String] (() => {
      val line = if (lines.hasNext) {
        Some(lines.next())
      } else {
        None
      }
      Promise.pure(line)
    }, source.close)
  }

  val lineParser: Enumeratee[String, Option[Coordinate]] = Enumeratee.map[String] {
    line =>
      val elements = Json.parse(line)
      try {
        Some(Coordinate(latitude  = (elements \ "latitude").as[String].toDouble,
                        longitude = (elements \ "longitude").as[String].toDouble,
                        cityname = (elements \ "cityName").as[String]))
      } catch {
        case e: Exception => Logger.error("error while parsing line: " + line, e); None
      }
  }

  val validCoordinate: Enumeratee[Option[Coordinate], Coordinate] = Enumeratee.mapInput[Option[Coordinate]] {
    case Input.El(maybe) if maybe.isDefined => Input.El(maybe.get)
    case other => Input.Empty // ignore invalid coordinates
  }

}