package se.radley.plugin.enumeration

import play.api.mvc._
import play.api.data.format.Formatter
import play.api.libs.json._
import play.api.libs.json.JsString

package object json {
  /**
   * Deserializer for Enumeration types.
   *
   * {{{
   * (Json \ "status").as(enum(Status))
   * }}}
   */
  def enum[E <: Enumeration](enum: E): Reads[E#Value] = new Reads[E#Value] {
    def reads(json: JsValue) = json match {
      case JsString(s) => {
        try {
          JsSuccess(enum.withName(s))
        } catch {
          case e: java.util.NoSuchElementException => JsError("Enumeration expected")
        }
      }
      case _ => JsError("String expected")
    }
  }
}
