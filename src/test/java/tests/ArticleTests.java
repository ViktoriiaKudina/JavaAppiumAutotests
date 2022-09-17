package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class ArticleTests extends CoreTestCase {

    @Test
    @DisplayName("Compare article title with expected one")
    @Step("Start the test CompareArticleTitle")
    public void testCompareArticleTitle() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String articleTitle = ArticlePageObject.getArticleTitle();

        Assert.assertEquals(
                "UNEXPECTED TITLE!",
                "Java (programming language)",
                articleTitle
        );
    }

    @Test
    @DisplayName("Check the article title present")
    @Step("Start test ArticleElementTitlePresent")
    public void testArticleElementTitlePresent() {

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("The");
        searchPageObject.clickByArticleWithSubstring("The");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        WebElement titleElement = articlePageObject.waitForTitleElement();
        Assert.assertNotNull("Can't find article title", titleElement);
    }

    @Test
    @DisplayName("Swipe article to the footer")
    @Step("Start test SwipeArticle")
    public void testSwipeArticle() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.swipeToFooter();
    }

}
