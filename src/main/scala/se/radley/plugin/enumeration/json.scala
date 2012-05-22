package se.radley.plugin.enumeration

import play.api.mvc._
import play.api.data.format.Formatter
import play.api.libs.json.{Writes, JsString, JsValue, Reads}

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
          enum.withName(s)
        } catch {
          case _ => throw new RuntimeException("Enumeration expected")
        }
      }
      case _ => throw new RuntimeException("String expected")
    }
  }
}