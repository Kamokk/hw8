package yarieva;

import com.codeborne.selenide.CollectionCondition;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;


import java.util.stream.Stream;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;


public class IkeaTest extends TestBase  {
    @ValueSource (strings = {"Гардина", "Лампа"})
    @Disabled
    @Tag("blocker")

    @ParameterizedTest(name="Поиск в Ikea слова {0}")
            void commonIkeaTest(String searchQuery) {
        open("https://www.ikea.com/ru/ru/");
        $("input[type='search']").setValue(searchQuery);
        $("input[type='search']").pressEnter();

        $$(withText(searchQuery)).shouldHave(CollectionCondition.sizeGreaterThan(0));

    }
    @EnumSource(SearchQuery.class)
    @Disabled
    @ParameterizedTest(name="Поиск в Ikea слова {0}")
    void searchIkeaWithEnum(SearchQuery searchQuery) {

        open("https://www.ikea.com/ru/ru/");
        $("input[type='search']").setValue(searchQuery.getSearchQuery());
        $("input[type='search']").pressEnter();

        $$(withText(searchQuery.getSearchQuery())).shouldHave(CollectionCondition.sizeGreaterThan(0));
    }
    
    @CsvFileSource(resources = "/source.csv")
    @Disabled
    @ParameterizedTest(name="Поиск в Ikea слова {0}")
            void searchIkeaWithCsvSource(String searchQueryName) {
        open("https://www.ikea.com/ru/ru/");

        $("input[type='search']").setValue(searchQueryName);
        $("input[type='search']").pressEnter();

        $$(withText(searchQueryName)).shouldHave(CollectionCondition.sizeGreaterThan(0));
        
            }

            @ParameterizedTest(name="Категории.Category {0}")
    @MethodSource("dataCategories")
    void searchIkeaWithMethodSource(String searchCategoryName) {
                open("https://www.ikea.com/ru/ru/");

                $("input[type='search']").setValue(searchCategoryName);
                $("input[type='search']").pressEnter();

                $$(withText(searchCategoryName)).shouldHave(CollectionCondition.sizeGreaterThan(0));
            }

    public static Stream<Arguments> dataCategories() {
        return Stream.of(
                Arguments.of("светильник"),
                Arguments.of("скатерть"),
                Arguments.of("картина")
        );
    }

}
