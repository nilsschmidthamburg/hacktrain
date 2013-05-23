package hacktrain

import org.scalatra._
import scalate.ScalateSupport
import model.Station
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json._
import model.Train

class HacktrainServlet extends HacktrainServerStack with JacksonJsonSupport  {
  // Sets up automatic case class to JSON output serialization, required by the JValueResult trait.
  protected implicit val jsonFormats: Formats = DefaultFormats.withBigDecimal
  
  // set content type to json for all requests EXCEPT Root ("/")
  before("""^\/(.+)""".r) {
    contentType = formats("json")
  }
  
  get("/") {
    <html>
      <body>
        <h1>API</h1>

	  	<h2>Trains</h2>
        <h4>List trains</h4>
        <p>
          You can find all trains running on a given time between two sations by calling:<b>/trains(:when, :origin, :destination)</b><br/>
          <ul>
            <li>E.g: <a href="/trains?when=1369295409origing=1234&amp;destination=5342" > /trains?when=1369295409origing=1234&amp;destination=5342</a></li>
          </ul>
        </p>

	  	<h2>Stations</h2>
        <h4>Find stations</h4>
        <p>
          You can find stations starting with a given name by calling:<b>/stations(:name)</b><br/>
          <ul>
            <li>E.g: <a href="/stations?name=ham" > /stations?name=ham</a></li>
          </ul>
        </p>
      </body>
    </html>
  }

  get("/trains") {
    Train.all
  }
  
  get("/stations") {
    params.get("name").map(Station.byName(_)).getOrElse(Seq[Station]())
  }
}
