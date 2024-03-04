package example

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.example.CreateForm
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock._
import data.TestData
import org.scalatest.BeforeAndAfterAll
import org.scalatest.flatspec.AnyFlatSpec

import scala.concurrent.ExecutionContextExecutor
import scala.util.Try

class CreateFormTest extends AnyFlatSpec with BeforeAndAfterAll {
  implicit private val actorSystem: ActorSystem = ActorSystem()
  implicit private val actorMaterializer: ActorMaterializer = ActorMaterializer()
  implicit private val executionContext: ExecutionContextExecutor = actorSystem.dispatcher

  private val formId = TestData.formId

  private val wiremockServer = new WireMockServer(5001)

  override def beforeAll(): Unit = {
    wiremockServer.start()
    configureFor("localhost", 5001)
  }
  override def afterAll(): Unit = wiremockServer.stop()


  "CreateForm" should "creates a form successfully" in {
    val typeformBaseUrl = TestData.typeformBaseUrl
    val typeformAccessToken = TestData.typeformAccessToken
    val typeformWorkspace = TestData.typeformWorkspace
    val requestBody = TestData.requestBody

    stubFor(post(urlEqualTo("/forms"))
      .willReturn(aResponse()
        .withStatus(201)
        .withHeader("Location", s"https://api.typeform.com/forms/$formId")
      )
    )

    val createForm = CreateForm(
      typeformBaseUrl,
      typeformAccessToken,
      typeformWorkspace,
      Http()
    )

    val result = createForm.execute
    assert(result.isRight)
    assert(Try(verify(postRequestedFor(urlEqualTo("/forms"))
      .withHeader("Authorization", equalTo(s"Bearer $typeformAccessToken"))
      .withRequestBody(equalToJson(requestBody)))).isSuccess)
  }

  "CreateForm" should "return an error when the request fails" in {
    val typeformBaseUrl = TestData.typeformBaseUrl
    val typeformAccessToken = TestData.typeformAccessToken
    val typeformWorkspace = TestData.typeformWorkspace

    stubFor(post(urlEqualTo("/forms"))
      .willReturn(aResponse()
        .withStatus(401)
        .withBody("Internal Server Error")
      )
    )

    val createForm = CreateForm(
      typeformBaseUrl,
      typeformAccessToken,
      typeformWorkspace,
      Http()
    )

    val result = createForm.execute
    assert(result.isLeft)
  }
}
