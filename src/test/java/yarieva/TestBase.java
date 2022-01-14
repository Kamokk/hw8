package yarieva;


import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;


public class TestBase {


    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "2048x1153";


    }

}