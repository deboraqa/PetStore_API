
import org.junit.Test;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.Is.is;


public class PetStore {
    @Test
    public void cadastrar(){

        String pedido = "{\r\n    \"id\": 10,\r\n    \"petId\": 50,\r\n    \"quantity\": 7,\r\n    \"shipDate\": \"2024-02-27T17:50:28.803+00:00\",\r\n    \"status\": \"approved\",\r\n    \"complete\": true\r\n}";

        given() //Dado que
                .contentType(ContentType.JSON)// Tipo de conteudo da requisição
                .body(pedido) //Envio do body
                .when()// Quando
                .post("https://petstore.swagger.io/v2/store/order")// pedido na rota
                .then() //entao
                .log().all() //mostrar tudo que foi realizado
                .statusCode(200) // valida que a operação teve status 200
                .body(
                        "id",is(10),
                        "quantity",is(7)
                );
    }
    @Test
    public void pesquisar(){

        String petinexistente = "-4444444";
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("https://petstore.swagger.io/v2/pet/"+petinexistente)
                .then()
                .log().all()
                .statusCode(404);


    }
    @Test
    public void atualizar(){

        String pet= ("{\r\n  \"id\": 0,\r\n  \"category\": {\r\n    \"id\": 0,\r\n    \"name\": \"string\"\r\n  },\r\n  \"name\": \"doggie\",\r\n  \"photoUrls\": [\r\n    \"string\"\r\n  ],\r\n  \"tags\": [\r\n    {\r\n      \"id\": 0,\r\n      \"name\": \"string\"\r\n    }\r\n  ],\r\n  \"status\": \"sold\"\r\n}");
                given()
                        .contentType(ContentType.JSON)
                        .body(pet)
                        .when()
                        .put("https://petstore.swagger.io/v2/pet")
                        .then()
                        .log().all()
                        .statusCode(200);
    }
    @Test
    public void statuspending(){

        String pending= "sold";
            given()
                    .contentType(ContentType.JSON)
                    .body(pending)
                    .when()
                    .get("https://petstore3.swagger.io/api/v3/pet/findByStatus?status=" + pending)
                    .then()
                    .log().all()
                    .statusCode(200);


    }

}


