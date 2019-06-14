import _root_.io.gatling.core.scenario.Simulation
import ch.qos.logback.classic.{Level, LoggerContext}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory

import scala.concurrent.duration._

/**
 * Performance test for the MPlayableCard entity.
 */
class MPlayableCardGatlingTest extends Simulation {

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

    val scn = scenario("Test the MPlayableCard entity")
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
            exec(http("Get all mPlayableCards")
            .get("/api/m-playable-cards")
            .headers(headers_http_authenticated)
            .check(status.is(200)))
            .pause(10 seconds, 20 seconds)
            .exec(http("Create new mPlayableCard")
            .post("/api/m-playable-cards")
            .headers(headers_http_authenticated)
            .body(StringBody("""{
                "id":null
                , "modelId":"0"
                , "properPositionGk":"0"
                , "properPositionFw":"0"
                , "properPositionOmf":"0"
                , "properPositionDmf":"0"
                , "properPositionDf":"0"
                , "characterId":"0"
                , "nickName":null
                , "teamId":"0"
                , "nationalityId":"0"
                , "rarity":"0"
                , "attribute":"0"
                , "thumbnailAssetsId":"0"
                , "cardIllustAssetsId":"0"
                , "playableAssetsId":"0"
                , "teamEffectId":"0"
                , "triggerEffectId":"0"
                , "maxActionSlot":"0"
                , "initialActionId1":"0"
                , "initialActionId2":"0"
                , "initialActionId3":"0"
                , "initialActionId4":"0"
                , "initialActionId5":"0"
                , "initialStamina":"0"
                , "initialDribble":"0"
                , "initialShoot":"0"
                , "initialPass":"0"
                , "initialTackle":"0"
                , "initialBlock":"0"
                , "initialIntercept":"0"
                , "initialSpeed":"0"
                , "initialPower":"0"
                , "initialTechnique":"0"
                , "initialPunching":"0"
                , "initialCatching":"0"
                , "maxStamina":"0"
                , "maxDribble":"0"
                , "maxShoot":"0"
                , "maxPass":"0"
                , "maxTackle":"0"
                , "maxBlock":"0"
                , "maxIntercept":"0"
                , "maxSpeed":"0"
                , "maxPower":"0"
                , "maxTechnique":"0"
                , "maxPunching":"0"
                , "maxCatching":"0"
                , "maxPlusDribble":"0"
                , "maxPlusShoot":"0"
                , "maxPlusPass":"0"
                , "maxPlusTackle":"0"
                , "maxPlusBlock":"0"
                , "maxPlusIntercept":"0"
                , "maxPlusSpeed":"0"
                , "maxPlusPower":"0"
                , "maxPlusTechnique":"0"
                , "maxPlusPunching":"0"
                , "maxPlusCatching":"0"
                , "highBallBonus":"0"
                , "lowBallBonus":"0"
                , "isDropOnly":"0"
                , "sellCoinGroupNum":"0"
                , "sellMedalId":"0"
                , "characterBookId":"0"
                , "bookNo":"0"
                , "isShowBook":"0"
                , "levelGroupId":"0"
                , "startAt":"0"
                }""")).asJson
            .check(status.is(201))
            .check(headerRegex("Location", "(.*)").saveAs("new_mPlayableCard_url"))).exitHereIfFailed
            .pause(10)
            .repeat(5) {
                exec(http("Get created mPlayableCard")
                .get("${new_mPlayableCard_url}")
                .headers(headers_http_authenticated))
                .pause(10)
            }
            .exec(http("Delete created mPlayableCard")
            .delete("${new_mPlayableCard_url}")
            .headers(headers_http_authenticated))
            .pause(10)
        }

    val users = scenario("Users").exec(scn)

    setUp(
        users.inject(rampUsers(Integer.getInteger("users", 100)) during (Integer.getInteger("ramp", 1) minutes))
    ).protocols(httpConf)
}
