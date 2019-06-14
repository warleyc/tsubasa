import _root_.io.gatling.core.scenario.Simulation
import ch.qos.logback.classic.{Level, LoggerContext}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory

import scala.concurrent.duration._

/**
 * Performance test for the MNpcCard entity.
 */
class MNpcCardGatlingTest extends Simulation {

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

    val scn = scenario("Test the MNpcCard entity")
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
            exec(http("Get all mNpcCards")
            .get("/api/m-npc-cards")
            .headers(headers_http_authenticated)
            .check(status.is(200)))
            .pause(10 seconds, 20 seconds)
            .exec(http("Create new mNpcCard")
            .post("/api/m-npc-cards")
            .headers(headers_http_authenticated)
            .body(StringBody("""{
                "id":null
                , "characterId":"0"
                , "shortName":null
                , "nickName":null
                , "teamId":"0"
                , "nationalityId":"0"
                , "rarity":"0"
                , "attribute":"0"
                , "modelId":"0"
                , "level":"0"
                , "thumbnailAssetsId":"0"
                , "cardIllustAssetsId":"0"
                , "playableAssetsId":"0"
                , "teamEffectId":"0"
                , "teamEffectLevel":"0"
                , "triggerEffectId":"0"
                , "action1Id":"0"
                , "action1Level":"0"
                , "action2Id":"0"
                , "action2Level":"0"
                , "action3Id":"0"
                , "action3Level":"0"
                , "action4Id":"0"
                , "action4Level":"0"
                , "action5Id":"0"
                , "action5Level":"0"
                , "stamina":"0"
                , "dribble":"0"
                , "shoot":"0"
                , "ballPass":"0"
                , "tackle":"0"
                , "block":"0"
                , "intercept":"0"
                , "speed":"0"
                , "power":"0"
                , "technique":"0"
                , "punching":"0"
                , "catching":"0"
                , "highBallBonus":"0"
                , "lowBallBonus":"0"
                , "personalityId":"0"
                , "uniformNo":"0"
                , "levelGroupId":"0"
                }""")).asJson
            .check(status.is(201))
            .check(headerRegex("Location", "(.*)").saveAs("new_mNpcCard_url"))).exitHereIfFailed
            .pause(10)
            .repeat(5) {
                exec(http("Get created mNpcCard")
                .get("${new_mNpcCard_url}")
                .headers(headers_http_authenticated))
                .pause(10)
            }
            .exec(http("Delete created mNpcCard")
            .delete("${new_mNpcCard_url}")
            .headers(headers_http_authenticated))
            .pause(10)
        }

    val users = scenario("Users").exec(scn)

    setUp(
        users.inject(rampUsers(Integer.getInteger("users", 100)) during (Integer.getInteger("ramp", 1) minutes))
    ).protocols(httpConf)
}
