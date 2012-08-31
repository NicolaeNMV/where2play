package controllers

import play.api._

import play.api.mvc._
import play.api.libs.ws.WS
import play.api.Play.current
import libs.iteratee.Enumeratee
import libs.json.JsValue
import libs.json.Json._
import libs.EventSource
import models.Coordinate
import models.CoordinatesStream._

object Application extends Controller {

  def index = Action {
    Ok(views.html.index())
  }

  val asJson: Enumeratee[Coordinate, JsValue] = Enumeratee.map[Coordinate] {
    coordinate => toJson ( Map (
      "latitude" -> toJson(coordinate.latitude.toString()),
      "longitude" -> toJson(coordinate.longitude.toString())
    ) )
  }

  /**
    * Stream of server send events
   */
  def stream() = Action {
    Ok.stream(fileLineStream(Play.getExistingFile("conf/play.json").get) &> lineParser &> validCoordinate &> asJson ><> EventSource()).as("text/event-stream")
  }
}