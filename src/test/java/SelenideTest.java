import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static org.testng.reporters.XMLConstants.TESTCASE;

public class SelenideTest {
    public final static By INPUT_EMAIL = By.id("inputEmail");
    public final static By INPUT_PASSWORD = By.id("inputPassword");
    public final static By LOGIN_BUTTON = By.id("btnLogin");
    public final static String EMAIL = "aleksvolsky@gmail.com";
    public final static String PASSWORD = "Alex1862123";
    public final static String DEFECT = "Defect";
    @BeforeMethod
    public void navigate() {
        open("https://app.qase.io/login");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        Selenide.closeWebDriver();
    }
    @Test
    public void projectTest(){
        $(INPUT_EMAIL).setValue(EMAIL);
        $(INPUT_PASSWORD).setValue(PASSWORD);
        $(LOGIN_BUTTON).click();
        $("#createButton").shouldBe(visible).click();
        $("#project-name").setValue("QWE");
        $("#project-code").setValue("12345678");
        $("#description-area").setValue("This is my first project");
        $(byXpath("//span[text()='Create project']//ancestor::button")).click();
        $(byXpath("//div[@class='na4G5n']/div")).shouldBe(visible);
    }
    @Test
    public void testcaseTest(){
        $(INPUT_EMAIL).setValue(EMAIL);
        $(INPUT_PASSWORD).setValue(PASSWORD);
        $(LOGIN_BUTTON).click();
        $("#createButton").shouldBe(visible).click();
        $("#project-name").setValue("QWE");
        $("#project-code").setValue("12345678");
        $("#description-area").setValue("This is my second project");
        $(byXpath("//span[text()='Create project']//ancestor::button")).click();
        $(byXpath("//div[@class='na4G5n']/div")).shouldBe(visible);
        $("#create-case-button").click();
        $("#title").setValue("Authorization");
        $("#save-case").click();
        $("#create-case-button").shouldBe(visible);
        Assert.assertTrue($("#create-case-button").isDisplayed());

    }
    @Test
    public void defectTest(){
        $(INPUT_EMAIL).setValue(EMAIL);
        $(INPUT_PASSWORD).setValue(PASSWORD);
        $(LOGIN_BUTTON).click();
        $("#createButton").shouldBe(visible).click();
        $("#project-name").setValue("QWE");
        $("#project-code").setValue("12345678");
        $("#description-area").setValue("This is my third project");
        $(byXpath("//span[text()='Create project']//ancestor::button")).click();
        $(byXpath("//div[@class='na4G5n']/div")).shouldBe(visible);
        $(byXpath("//span[text()='Defects']//ancestor::a")).click();
        $(byXpath("//a[@class='btn btn-primary' and text()='Create new defect']")).click();
        $("#title").setValue("Regression");
        $(byXpath("//p[@class='Q9IhIQ']")).setValue("Bad result");
        $(byXpath("//button[@type='submit']")).click();
        $(byXpath("//span[@class='OL6rtE']")).shouldHave(text("Defect was created successfully!"));
        Assert.assertTrue($(By.xpath("//a[text()='Export']")).isDisplayed());    }
}
