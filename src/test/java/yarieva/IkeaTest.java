package yarieva;

import com.codeborne.selenide.CollectionCondition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;


public class IkeaTest extends TestBase {
    public static Stream<Arguments> dataCategories() {
        return Stream.of(
                Arguments.of("светильник"),
                Arguments.of("скатерть"),
                Arguments.of("картина")
        );
    }

    @ValueSource(strings = {"Гардина", "Лампа"})
    @DisplayName("Поиск в Икеа с аннотацией ValueSource")
    @Tag("blocker")

    @ParameterizedTest(name = "Поиск в Ikea слова {0}")
    void commonIkeaTest(String searchQueryValueSource) {
        open("https://www.ikea.com/ru/ru/");
        $("input[type='search']").setValue(searchQueryValueSource);
        $("input[type='search']").pressEnter();

        $$(withText(searchQueryValueSource)).shouldHave(CollectionCondition.sizeGreaterThan(0));

    }

    @EnumSource(SearchQuery.class)
    @DisplayName("Поиск в Икеа с аннотацией EnumSource")
    @ParameterizedTest(name = "Поиск в Ikea слова {0}")
    void searchIkeaWithEnum(SearchQuery searchQueryEnumSource) {

        open("https://www.ikea.com/ru/ru/");
        $("input[type='search']").setValue(searchQueryEnumSource.getSearchQuery());
        $("input[type='search']").pressEnter();

        $$(withText(searchQueryEnumSource.getSearchQuery())).shouldHave(CollectionCondition.sizeGreaterThan(0));
    }

    @CsvFileSource(resources = "/source.csv")
    @DisplayName("Поиск в Икеа с аннотацией CsvFileSource")
    @ParameterizedTest(name = "Поиск в Ikea слова {0}")
    void searchIkeaWithCsvSource(String searchQueryCsvFileSource) {
        open("https://www.ikea.com/ru/ru/");

        $("input[type='search']").setValue(searchQueryCsvFileSource);
        $("input[type='search']").pressEnter();

        $$(withText(searchQueryCsvFileSource)).shouldHave(CollectionCondition.sizeGreaterThan(0));

    }

    @ParameterizedTest(name = "Категории.Category {0}")
    @MethodSource("dataCategories")
    @DisplayName("Поиск в Икеа с аннотацией MethodSource")
    void searchIkeaWithMethodSource(String searchCategoryMethodSource) {
        open("https://www.ikea.com/ru/ru/");
        $("input[type='search']").setValue(searchCategoryMethodSource);
        $("input[type='search']").pressEnter();

        $$(withText(searchCategoryMethodSource)).shouldHave(CollectionCondition.sizeGreaterThan(0));
    }

}
