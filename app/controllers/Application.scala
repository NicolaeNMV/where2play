package controllers

import play.api._
import play.api.mvc._

object Application extends Controller {

  def index = Action {
    Ok(views.html.index())
  }

  /**
    * Stream of server send events
   */
  def stream() = Action {
    Ok.stream(/* Nicolae JSON as Enumaratee or Iteratee I dont remember */ ><> EventSource()).as("text/event-stream")
  }

}