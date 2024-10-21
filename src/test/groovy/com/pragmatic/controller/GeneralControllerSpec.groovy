package com.pragmatic.controller

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.json.JsonMapper
import com.google.gson.Gson
import com.pragmatic.controller.exception.ErrorMessage
import com.pragmatic.dto.UserDto
import io.restassured.RestAssured

import org.jsoup.Jsoup
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.SqlGroup
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.w3c.dom.NameList
import spock.lang.Specification
import io.restassured.http.ContentType
import spock.lang.Unroll

import static io.restassured.RestAssured.given
import org.springframework.http.HttpStatus

import static io.restassured.RestAssured.reset
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GeneralControllerSpec extends Specification {

    @LocalServerPort
    int randomPort;

    def setup() {
        // Налаштовуємо базову адресу для RestAssured
        RestAssured.baseURI = "http://localhost"
        RestAssured.port = randomPort
    }

    def "hello: successful flow: string return name"(){
        when:
        def response = given()
        .params(Map.of(
                "name", "testName"
        ))
        .log().all()
        .get("/hello")
        then:
        response.statusCode() == HttpStatus.OK.value()
        response.body().asString() == "Hello testName!"
    }

    def "helloWorld"() {
        when:
        def response = given()
        .log().all()
        .get("/")
        then:
        response.body().asString() == 'Hello World'
    }


    @SqlGroup([
            @Sql(executionPhase = AFTER_TEST_METHOD, value = "/com/pragmatic/dao/users/clean.sql")
    ])
    def "create user: : success flow"() {

        setup:
        def url = "/createUser" // Відносний шлях до контроллера

        def params = [
                "email"    : "blabla@bla.com",
                "password" : "test1test1",
                "name"     : "bla_name"
        ]
        when: "Відправляємо POST запит"
        def response = given()
                .contentType("application/json") // Вказуємо, що відправляємо JSON
                .body(params)
                .log().all() //TODO Питання. файнолл питання.
                .post(url)
        then: "Перевіряємо статус відповіді"
        response.statusCode() == HttpStatus.OK.value()
    }

    def "getUserById: error flow"() {
        when:
        def response = given()
                .params("id", testValue)
                .log().all()
                .get("/getUserById")

        then:
        response.body().as(ErrorMessage.class)
        response.statusCode() == HttpStatus.BAD_REQUEST.value()

        where:
        testValue  | errorCode
        0| HttpStatus.BAD_REQUEST
        ""  | HttpStatus.BAD_REQUEST
    }

    @SqlGroup([
            @Sql(executionPhase = BEFORE_TEST_METHOD, value = "/com/pragmatic/dao/users/create-users.sql"),
            @Sql(executionPhase = AFTER_TEST_METHOD, value = "/com/pragmatic/dao/users/clean.sql")
    ])
    def "getAllUsers: success flow"(){
        //parse json and each element try to convert to needed
        when:
        def response = given()
        .contentType("application/json")
        .get("/getAllUsers")

        def body = response.body().asString()
        println(body)

        ObjectMapper mapper = new ObjectMapper();

        List<UserDto> studentList = new ArrayList<>();

        studentList = Arrays.asList(mapper.readValue(body, UserDto[].class))
        println(studentList)

        then:
        response.statusCode() == HttpStatus.OK.value()


    }




    @Unroll
    def "createUser: error flow"() {
        given: "an invalid user request"
        def userRequest = [
                email   : email,
                password: password,
                name    : name
        ]

        when: "POST request is sent to /createUser with invalid data"
        def response = given()
                .contentType(ContentType.JSON)
                .body(userRequest)
                .when()
                .log().all()
                .post("/createUser")

        then: "Response should be #expectedStatus"
        response.statusCode() == expectedStatus.value()


        where:
        email                | password          | name            || expectedStatus
        null                 | "ValidPass123"    | "ValidName"     || HttpStatus.BAD_REQUEST  // email = null
        ""                   | "ValidPass123"    | "ValidName"     || HttpStatus.BAD_REQUEST  // email = порожній
        "t@e"                | "ValidPass123"    | "ValidName"     || HttpStatus.BAD_REQUEST  // email занадто короткий
        "valid.email@test.com" | null             | "ValidName"     || HttpStatus.BAD_REQUEST  // password = null
        "valid.email@test.com" | ""               | "ValidName"     || HttpStatus.BAD_REQUEST  // password = порожній
        "valid.email@test.com" | "short"          | "ValidName"     || HttpStatus.BAD_REQUEST  // password занадто короткий
        "valid.email@test.com" | "ValidPass123"   | null            || HttpStatus.BAD_REQUEST  // name = null
        "valid.email@test.com" | "ValidPass123"   | ""              || HttpStatus.BAD_REQUEST  // name = порожній
        "valid.email@test.com" | "ValidPass123"   | "Jo"            || HttpStatus.BAD_REQUEST  // name занадто короткий
        "valid.email@test.com" | "ValidPass123"   | "VeryLongNameThatExceedsLimit" || HttpStatus.BAD_REQUEST  // name занадто довгий
        "valid.email@test.com" | "ValidPass123"   | "ValidName"     || HttpStatus.OK           // Успішний випадок
    }



}


