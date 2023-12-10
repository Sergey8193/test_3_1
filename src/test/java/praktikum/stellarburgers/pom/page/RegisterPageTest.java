package praktikum.stellarburgers.pom.page;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import praktikum.stellarburgers.pom.base.BaseTest;

import static praktikum.stellarburgers.browser.BrowserType.BY_BROWSER_SYSTEM_PROPERTY;
import static praktikum.stellarburgers.constants.UserState.NO_NAME_USER;
import static praktikum.stellarburgers.constants.UserStatus.AUTHORIZED_USER;
import static praktikum.stellarburgers.user.UserDataGenerator.getRandomEmail;
import static praktikum.stellarburgers.user.UserDataGenerator.getRandomName;
import static praktikum.stellarburgers.user.UserDataGenerator.getRandomPassword;

public class RegisterPageTest extends BaseTest {
    public RegisterPageTest() { super(BY_BROWSER_SYSTEM_PROPERTY, AUTHORIZED_USER, NO_NAME_USER);  }

    @Before
    public void startUp() { getBrowserAndPrepareUserData(); }

    @After
    public void cleanUp() { closeBrowserAndCleanUpUserData();  }

    @Test
    @DisplayName("User registration")
    @Description("Check that user registration via GUI is possible")
    public void checkThatUserCanBeRegisteredWithUserInterface() {
        mainPage.openMainPage();
        String email = getRandomEmail();
        String password = getRandomPassword();
        String name = getRandomName();
        boolean isUserRegistrationCompleted =
                mainPage.clickLoginToAccountButton()
                        .clickRegisterPageLink()
                        .fillOutRegisterForm(name, email, password)
                        .clickRegisterButton()
                        .waitUntilLoginPageToBeDisplayed()
                        .checkThatLoginButtonIsEnabled()
                ;
        Assert.assertTrue(isUserRegistrationCompleted);
    }

    @Test
    @DisplayName("Registration password validation")
    @Description("Check that registration is possible with correct password only")
    public void checkThatIfEnterIncorrectPasswordThenCorrespondingErrorMessageToBeDisplayed() {
        String name = getRandomName();
        String email = getRandomEmail();
        String password = getRandomPassword();
        boolean isPasswordErrorMessageDisplayed = mainPage
                .clickLoginToAccountButton()
                .clickRegisterPageLink()
                .fillOutRegisterForm(name, email, password.substring(0, 4))
                .clickNameInput()
                .checkThatPasswordErrorMessageIsDisplayed()
                ;
        Assert.assertTrue(isPasswordErrorMessageDisplayed);
    }

    @Test
    @DisplayName("Registration email validation")
    @Description("Check that registration with existent user email is impossible")
    public void checkThatIfEnterExistentUserEmailThenCorrespondingErrorMessageToBeDisplayed() {
        String email = userRegistrationData.getEmail();
        String password = getRandomPassword();
        String name = getRandomName();
        boolean isEmailErrorMessageDisplayed =
                mainPage.clickLoginToAccountButton()
                        .clickRegisterPageLink()
                        .fillOutRegisterForm(name, email, password)
                        .clickRegisterButtonAfterEnteringIncorrectData()
                        .checkThatEmailErrorMessageIsDisplayed()
                ;
        Assert.assertTrue(isEmailErrorMessageDisplayed);
    }
}
