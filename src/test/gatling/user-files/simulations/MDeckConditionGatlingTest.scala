import _root_.io.gatling.core.scenario.Simulation
import ch.qos.logback.classic.{Level, LoggerContext}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory

import scala.concurrent.duration._

/**
 * Performance test for the MDeckCondition entity.
 */
class MDeckConditionGatlingTest extends Simulation {

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

    val scn = scenario("Test the MDeckCondition entity")
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
            exec(http("Get all mDeckConditions")
            .get("/api/m-deck-conditions")
            .headers(headers_http_authenticated)
            .check(status.is(200)))
            .pause(10 seconds, 20 seconds)
            .exec(http("Create new mDeckCondition")
            .post("/api/m-deck-conditions")
            .headers(headers_http_authenticated)
            .body(StringBody("""{
                "id":null
                , "targetFormationGroupId":"0"
                , "targetCharacterGroupMinId":"0"
                , "targetCharacterGroupMaxId":"0"
                , "targetPlayableCardGroupMinId":"0"
                , "targetPlayableCardGroupMaxId":"0"
                , "targetRarityGroupId":"0"
                , "targetAttribute":"0"
                , "targetNationalityGroupMinId":"0"
                , "targetNationalityGroupMaxId":"0"
                , "targetTeamGroupMinId":"0"
                , "targetTeamGroupMaxId":"0"
                , "targetActionGroupMinId":"0"
                , "targetActionGroupMaxId":"0"
                , "targetTriggerEffectGroupMinId":"0"
                , "targetTriggerEffectGroupMaxId":"0"
                , "targetCharacterMinCount":"0"
                , "targetCharacterMaxCount":"0"
                , "targetPlayableCardMinCount":"0"
                , "targetPlayableCardMaxCount":"0"
                , "targetRarityMaxCount":"0"
                , "targetAttributeMinCount":"0"
                , "targetNationalityMinCount":"0"
                , "targetNationalityMaxCount":"0"
                , "targetTeamMinCount":"0"
                , "targetTeamMaxCount":"0"
                , "targetActionMinCount":"0"
                , "targetActionMaxCount":"0"
                , "targetTriggerEffectMinCount":"0"
                , "targetTriggerEffectMaxCount":"0"
                , "targetCharacterIsStartingMin":"0"
                , "targetCharacterIsStartingMax":"0"
                , "targetPlayableCardIsStartingMin":"0"
                , "targetPlayableCardIsStartingMax":"0"
                , "targetRarityIsStarting":"0"
                , "targetAttributeIsStarting":"0"
                , "targetNationalityIsStartingMin":"0"
                , "targetNationalityIsStartingMax":"0"
                , "targetTeamIsStartingMin":"0"
                , "targetTeamIsStartingMax":"0"
                , "targetActionIsStartingMin":"0"
                , "targetActionIsStartingMax":"0"
                , "targetTriggerEffectIsStartingMin":"0"
                , "targetTriggerEffectIsStartingMax":"0"
                }""")).asJson
            .check(status.is(201))
            .check(headerRegex("Location", "(.*)").saveAs("new_mDeckCondition_url"))).exitHereIfFailed
            .pause(10)
            .repeat(5) {
                exec(http("Get created mDeckCondition")
                .get("${new_mDeckCondition_url}")
                .headers(headers_http_authenticated))
                .pause(10)
            }
            .exec(http("Delete created mDeckCondition")
            .delete("${new_mDeckCondition_url}")
            .headers(headers_http_authenticated))
            .pause(10)
        }

    val users = scenario("Users").exec(scn)

    setUp(
        users.inject(rampUsers(Integer.getInteger("users", 100)) during (Integer.getInteger("ramp", 1) minutes))
    ).protocols(httpConf)
}
