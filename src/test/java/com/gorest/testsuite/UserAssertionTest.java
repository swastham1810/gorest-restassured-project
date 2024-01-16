package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * 1. Verify the if the total record is 20
 * 2. Verify the if the name of id = 5914197 is equal to ”Bhilangana Dhawan”
 * 3. Check the single ‘Name’ in the Array list (Dev Bhattacharya)
 * 4. Check the multiple ‘Names’ in the ArrayList (Usha Kaul Esq., Akshita Mishra, Chetanaanand Reddy )
 * 5. Verify the emai of userid = 5914185 is equal “tandon_iv_aanandinii@prosacco.example”
 * 6. Verify the status is “Active” of user name is “Amaresh Rana”
 * 7. Verify the Gender = male of user name is “Dhanalakshmi Pothuvaal”
 */
public class UserAssertionTest extends TestBase {
    static ValidatableResponse response;

    @BeforeClass
    public static void start() {
        response = given()
                .queryParam("page", 1)
                .queryParam("per_page", 20)
                .when()
                .get("/users")
                .then().statusCode(200);
    }

    //1. Verify the if the total record is 20
    @Test
    public void test001() {
        response.body("size()", equalTo(20));
    }

    //2. Verify the if the name of id = 5914139 is equal to ”Bilwa Embranthiri”
    @Test
    public void test002() {
        response.body("find{it.id == 5914139}.name", equalTo("Bilwa Embranthiri"));
    }

    //3. Check the single ‘Name’ in the Array list (Aanjaneya Iyer)
    @Test
    public void test003() {
        response.body("name", hasItem("Aanjaneya Iyer"));
    }

    //4. Check the multiple ‘Names’ in the ArrayList (Dinesh Mehrotra, Aanjaneya Iyer, Chandini Prajapat )
    @Test
    public void test004() {
        response.body("name", hasItems("Dinesh Mehrotra", "Aanjaneya Iyer", "Chandini Prajapat"));
    }

    //5. Verify the email of userid = 5914141 is equal “pilla_dandapaani_amb@goyette.test”
    @Test
    public void test005() {
        response.body("find{it.id == 5914141}.email", equalTo("pilla_dandapaani_amb@goyette.test"));
    }

    //6. Verify the status is “Active” of user name is “Dandapaani Agarwal”
    @Test
    public void test006() {
        response.body("find{it.status == 'active'}.name", equalTo("Yoginder Dhawan Esq."));
    }

    //7. Verify the Gender = male
    @Test
    public void test007() {
        response.body("find{it.name == 'Dinesh Mehrotra'}.gender", equalTo("male"));
    }


}
