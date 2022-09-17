package tests;

import io.qameta.allure.Step;
import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

public class SearchTests extends CoreTestCase {

    @Test
    @Step("Start search test")
    public void testSearch() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    @Step("Start test search by title and description")
    public void testSearchByTitleAndDescription() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        searchPageObject.waitForElementByTitleAndDescription("Java","Object-oriented programming language");
    }

    @Test
    @Step("Start test cansel search")
    public void testCancelSearch() {

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.clickCanselSearch();
        searchPageObject.waitForCancelButtonToDisappear();

    }

    @Test
    @Step("Start test amount of not empty search")
    public void testAmountOfNotEmptySearch() {

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        String searchLine = "Linkin Park Discography";
        searchPageObject.typeSearchLine(searchLine);

        int amountOfSearchResults = searchPageObject.getAmountOfFoundArticles();

        Assert.assertTrue(
                "We found few results",
                amountOfSearchResults > 0);
    }

    @Test
    @Step("Start test amount of empty search")
    public void testAmountOfEmptySearch() {

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        String searchLine = "ghjghfjghfjghfj";
        searchPageObject.typeSearchLine(searchLine);
        searchPageObject.waitForEmptyResultsLabel();
        searchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    @Step("Start test cancelling search")
    public void testCancelingASearch() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        String searchLine = "Linkin Park Discography";
        searchPageObject.typeSearchLine(searchLine);

        int amountOfSearchResults = searchPageObject.getAmountOfFoundArticles();

        Assert.assertTrue(
                "We found few results",
                amountOfSearchResults > 0);

        searchPageObject.clickCanselSearch();
        searchPageObject.waitForEmptyResultsLabel();
        searchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    @Step("Start test check valid search")
    public void testCheckValidSearch() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Horse");
        searchPageObject.getAmountOfFoundArticles();

        int amountOfSearchResults = searchPageObject.getAmountOfFoundArticles();

        assertNotEquals("NOT EQUALS WITH SEARCH", 0, amountOfSearchResults);

        driver.navigate().back();

        searchPageObject.clickCanselSearch();
        searchPageObject.waitForCancelButtonToDisappear();

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.getAmountOfFoundArticles();

        amountOfSearchResults = searchPageObject.getAmountOfFoundArticles();

        assertNotEquals("NOT EQUALS WITH SEARCH", 0, amountOfSearchResults);

        driver.navigate().back();

        searchPageObject.clickCanselSearch();
        searchPageObject.waitForCancelButtonToDisappear();

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("GG");
        searchPageObject.getAmountOfFoundArticles();

        amountOfSearchResults = searchPageObject.getAmountOfFoundArticles();

        assertNotEquals("NOT EQUALS WITH SEARCH", 0, amountOfSearchResults);

    }
}
