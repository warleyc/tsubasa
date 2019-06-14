import _root_.io.gatling.core.scenario.Simulation
import ch.qos.logback.classic.{Level, LoggerContext}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory

import scala.concurrent.duration._

/**
 * Performance test for the MNpcDeck entity.
 */
class MNpcDeckGatlingTest extends Simulation {

    val context: LoggerContext = LoggerFactory.getILoggerFactory.asInstanceOf[LoggerContext]
    // Log all HTTP requests
    //context.getLogger("io.gatling.http").setLevel(Level.valueOf("TRACE"))
    // Log failed HTTP requests
    //context.getLogger("io.gatling.http").setLevel(Level.valueOf("DEBUG"))

    val baseURL = Option(System.getProperty("baseURL")) getOrElse """http://localhost:8080"""

    val httpConf = http
        .baseUrl(baseURL)
        .inferHtmlResources()
        .acceptHeader("*/*")
        .acceptEncodingHeader("gzip, deflate")
        .acceptLanguageHeader("fr,fr-fr;q=0.8,en-us;q=0.5,en;q=0.3")
        .connectionHeader("keep-alive")
        .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.10; rv:33.0) Gecko/20100101 Firefox/33.0")
        .silentResources // Silence all resources like css or css so they don't clutter the results

    val headers_http = Map(
        "Accept" -> """application/json"""
    )

    val headers_http_authentication = Map(
        "Content-Type" -> """application/json""",
        "Accept" -> """application/json"""
    )

    val headers_http_authenticated = Map(
        "Accept" -> """application/json""",
        "Authorization" -> "${access_token}"
    )

    val scn = scenario("Test the MNpcDeck entity")
        .exec(http("First unauthenticated request")
        .get("/api/account")
        .headers(headers_http)
        .check(status.is(401))
        ).exitHereIfFailed
        .pause(10)
        .exec(http("Authentication")
        .post("/api/authenticate")
        .headers(headers_http_authentication)
        .body(StringBody("""{"username":"admin", "password":"admin"}""")).asJson
        .check(header("Authorization").saveAs("access_token"))).exitHereIfFailed
        .pause(2)
        .exec(http("Authenticated request")
        .get("/api/account")
        .headers(headers_http_authenticated)
        .check(status.is(200)))
        .pause(10)
        .repeat(2) {
            exec(http("Get all mNpcDecks")
            .get("/api/m-npc-decks")
            .headers(headers_http_authenticated)
            .check(status.is(200)))
            .pause(10 seconds, 20 seconds)
            .exec(http("Create new mNpcDeck")
            .post("/api/m-npc-decks")
            .headers(headers_http_authenticated)
            .body(StringBody("""{
                "id":null
                , "teamName":null
                , "uniformBottomFp":"0"
                , "uniformUpFp":"0"
                , "uniformBottomGk":"0"
                , "uniformUpGk":"0"
                , "formationId":"0"
                , "captainCardId":"0"
                , "teamEffect1CardId":"0"
                , "teamEffect2CardId":"0"
                , "teamEffect3CardId":"0"
                , "npcCardId1":"0"
                , "npcCardId2":"0"
                , "npcCardId3":"0"
                , "npcCardId4":"0"
                , "npcCardId5":"0"
                , "npcCardId6":"0"
                , "npcCardId7":"0"
                , "npcCardId8":"0"
                , "npcCardId9":"0"
                , "npcCardId10":"0"
                , "npcCardId11":"0"
                , "tick":"0"
                }""")).asJson
            .check(status.is(201))
            .check(headerRegex("Location", "(.*)").saveAs("new_mNpcDeck_url"))).exitHereIfFailed
            .pause(10)
            .repeat(5) {
                exec(http("Get created mNpcDeck")
                .get("${new_mNpcDeck_url}")
                .headers(headers_http_authenticated))
                .pause(10)
            }
            .exec(http("Delete created mNpcDeck")
            .delete("${new_mNpcDeck_url}")
            .headers(headers_http_authenticated))
            .pause(10)
        }

    val users = scenario("Users").exec(scn)

    setUp(
        users.inject(rampUsers(Integer.getInteger("users", 100)) during (Integer.getInteger("ramp", 1) minutes))
    ).protocols(httpConf)
}
