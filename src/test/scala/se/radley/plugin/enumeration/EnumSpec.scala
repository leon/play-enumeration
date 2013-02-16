package se.radley.plugin.enumeration

import org.specs2.mutable.Specification
import play.api._
import play.api.mvc._
import play.api.test._
import play.api.test.Helpers._
import java.io.File
import play.api.Play.current
import play.api.libs.json._
import play.api.libs.json.Json._

object EnumSpec extends Specification {

  "Enumerations" should {
    "Be bindable in a form" in {
      import play.api.data._
      import play.api.data.Forms._
      import form.enum

      object Status extends Enumeration {
        val Free = Value("free")
        val Busy = Value("busy")
      }

      val statusForm = Form("status" -> enum(Status))

      val data = Map("status" -> "free")
      statusForm.bind(data).get must equalTo(Status.Free)
    }

    "Serialize and deserialize to JSON" in {
      import json.enum
      object Status extends Enumeration {
        val Free = Value("free")
      }

      val e = Status.Free
      val m = Map("status" -> e.toString)
      val jsonM = toJson(m)
      (jsonM \ "status").as(enum(Status)) must equalTo(e)
    }
  }
}