package ch.zli.m223;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.Test;

import ch.zli.m223.model.Entry;

//import com.google.errorprone.annotations.Var;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import java.time.LocalDateTime;

//import javax.swing.text.AbstractDocument.Content;

@QuarkusTest
public class EntryResourceTest {

    @Test
    public void testIndexEndpoint() {
        given()
          .when().get("/entries")
          .then()
             .statusCode(200)
             .body(is("[]"));
    }

    @Test
    public void testDeleteEndpoint() {
        var entry = new Entry();
        entry.setCheckIn(LocalDateTime.now());
        entry.setCheckOut(LocalDateTime.now());

        given().contentType(ContentType.JSON).body(entry).post("/entries");
        given().when().delete("/entries/1").then().statusCode(204);
    }

    @Test
    public void testUpdate() {
        var entry = new Entry();
        LocalDateTime now = LocalDateTime.now();
        entry.setCheckIn(now);
        entry.setCheckOut(now);

        given().contentType(ContentType.JSON).body(entry).post("/entries");
        var newEntry = new Entry();
        newEntry.setCheckIn(LocalDateTime.now());
        newEntry.setCheckOut(LocalDateTime.now());

        given().when().contentType(ContentType.JSON).body(newEntry).put("/entries").then().statusCode(200);
    }

}