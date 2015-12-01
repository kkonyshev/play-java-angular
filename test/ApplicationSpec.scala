import java.util.List

import models.UserAccount
import org.junit.Ignore
import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._
import play.api.http.HeaderNames

import play.api.test._
import play.api.test.Helpers._
import play.libs.Json

@RunWith(classOf[JUnitRunner])
@Ignore
class ApplicationSpec extends Specification {

  "Application" should {

    "render index template" in new WithApplication {
      val html = views.html.index("Coco")

      contentAsString(html) must contain("Coco")
    }

    "redirect to login page" in new WithApplication{
      val home = route(FakeRequest(GET, "/")).get

      status(home) must equalTo(SEE_OTHER)
      //contentType(home) must beSome.which(_ == "text/html")
      //contentAsString(home) must contain ("TweetMap")
    }

    "login page" in new WithApplication{
      val home = route(FakeRequest(GET, "/admin/login")).get

      status(home) must equalTo(OK)
      contentType(home) must beSome.which(_ == "text/html")
      contentAsString(home) must contain ("Sign In")
    }

    "user list" in new WithApplication{
      val userList = route(FakeRequest(GET, "/user/")).get

      status(userList) must equalTo(OK)
      contentType(userList) must beSome.which(_ == "application/json")

      //val responseNode = Json.parse(contentAsString(userList))
      //val newUserList: Seq[UserAccount] = Json.fromJson(responseNode, classOf[Seq[UserAccount]])
      //newUserList must empty
      //val newUser: UserAccount = Json.fromJson(responseNode, classOf[UserAccount])
      //(responseNode "success").as[Boolean] must equalTo(true)
    }

    "user not found" in new WithApplication{
      val userGetUnknown = route(FakeRequest(GET, "/user/hs@ngs.ru")).get
      status(userGetUnknown) must equalTo(NOT_FOUND)

      val userAccount = new UserAccount("hs@ngs.ru", "Name", "secret")
      val jsonObject = Json.toJson(userAccount)

      val userJson = Json.stringify(jsonObject)
      println("userJsonString: " + userJson)
      val jsonObject2 = Json.toJson(
        Map(
          "email" -> Json.toJson("hs@ngs.ru"),
          "name" -> Json.toJson("Name"),
          "empasswordail" -> Json.toJson("secret111")
        )
      )

      //val result = controllers.UserAccountController.createGroup()(fakeRequest).result.value.get

      //status(fakeRequest) must equalTo(CREATED)
      //val user = route(FakeRequest(POST, "/user/")).post(userJson).value.get
      //val user = route(FakeRequest(POST, "/user/").withRawBody(Nil)).withJsonBody(jsonObject2)
      //status(user) must equalTo(CREATED)

      //val userGet = route(FakeRequest(GET, "/user/hs%40ngs.ru")).get
      //status(userGet) must equalTo(OK)
    }


    "seller list" in new WithApplication{
      val userList = route(FakeRequest(GET, "/seller/")).get

      status(userList) must equalTo(OK)
      contentType(userList) must beSome.which(_ == "application/json")
    }

    "seller not found" in new WithApplication{
      val userGetUnknown = route(FakeRequest(GET, "/seller/1")).get
      status(userGetUnknown) must equalTo(NOT_FOUND)
    }

    "search for tweets" in new WithApplication {
      //val search = controllers.Admin.search("typesafe")(FakeRequest())

      //status(search) must equalTo(OK)
      //contentType(search) must beSome("application/json")
      //(contentAsJson(search) \ "statuses").as[Seq[JsValue]].length must beGreaterThan(0)
    }
  }
}
