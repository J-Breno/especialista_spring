package com.github.jbreno.algafood;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class KitchenRegistrationIT {
	
	@LocalServerPort
	private int port;
	
	@Test
	public void shouldReturnStatus200_WhenConsultKitchens() {
	given()
		.basePath("/kitchens")
		.port(port)
		.accept(ContentType.JSON)
	.when()
		.get()
	.then()
		.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void shouldContain2Kitchen_WhenConsultKitchens() {
		
	given()
		.basePath("/kitchens")
		.port(port)
		.accept(ContentType.JSON)
	.when()
		.get()
	.then()
		.body("", hasSize(2))
		.body("name", hasItems("Tailandesa", "Indiana"));
	}
	
}
