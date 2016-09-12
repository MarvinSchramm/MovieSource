package com.kreait;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.ResponseSpecBuilder;
import com.jayway.restassured.specification.ResponseSpecification;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieTest {

    @LocalServerPort
    private int port;

    final ResponseSpecification spec = new ResponseSpecBuilder()
            .expectHeader(HttpHeaders.ETAG, not(isEmptyOrNullString()))
            .expectHeader(HttpHeaders.CACHE_CONTROL, containsString("max-age"))
            .build();

    @Test
    public void test() {
        RestAssured.given()
                .port(port)
                .when().get("/movies")
                .then()
                .assertThat()
                .statusCode(200)
                .spec(spec);

    }
}
