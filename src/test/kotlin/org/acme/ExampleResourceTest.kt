package org.acme

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import jdk.nashorn.internal.ir.annotations.Ignore
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test

@QuarkusTest
class ExampleResourceTest {

    @Test
    @Ignore
    fun testHelloEndpoint() {
        given()
          .`when`().get("/fruits")
          .then()
             .statusCode(200)

    }

}