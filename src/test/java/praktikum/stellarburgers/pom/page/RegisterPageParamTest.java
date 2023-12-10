package praktikum.stellarburgers.pom.page;

import io.qameta.allure.Description;
import praktikum.stellarburgers.browser.BrowserType;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.stellarburgers.pom.base.BaseTest;
import praktikum.stellarburgers.constants.UserState;
import praktikum.stellarburgers.constants.UserStatus;

import static praktikum.stellarburgers.browser.BrowserType.*;
import static praktikum.stellarburgers.constants.UserState.NO_NAME_USER;
import static praktikum.stellarburgers.constants.UserStatus.AUTHORIZED_USER;
import static praktikum.stellarburgers.user.UserDataGenerator.*;

@RunWith(Parameterized.class)
public class RegisterPageParamTest extends BaseTest {

    public RegisterPageParamTest(BrowserType browserType, UserStatus userStatus, UserState userState) {
        super(browserType, userStatus, userState);
    }

    @Before
    public void startUp() { getBrowserAndPrepareUserData(); }

    @After
    public void cleanUp() { closeBrowserAndCleanUpUserData();  }

    @Parameterized.Parameters(name = "'Register' Page test: (browser: {0})")
    public static Object[][] getTestData() {
        return new Object[][]{
                { CHROME, AUTHORIZED_USER, NO_NAME_USER },
                { FIREFOX, AUTHORIZED_USER, NO_NAME_USER },
                { YANDEX, AUTHORIZED_USER, NO_NAME_USER },
                { EDGE, AUTHORIZED_USER, NO_NAME_USER },
        };
    }

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
                        .waitUntilRegisterPageToBeDisplayed()
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
        String name = userRegistrationData.getName();
        String email = userRegistrationData.getEmail();
        String password = userRegistrationData.getPassword();
        boolean isPasswordErrorMessageDisplayed = mainPage
                .clickLoginToAccountButton()
                .clickRegisterPageLink()
                .waitUntilRegisterPageToBeDisplayed()
                .fillOutRegisterForm(name, email, password.substring(0, 4))
                .clickEmailInput()
                .checkThatPasswordErrorMessageIsDisplayed()
                ;
        Assert.assertTrue(isPasswordErrorMessageDisplayed);
    }

    @Test
    @DisplayName("Registration email validation")
    @Description("Check that registration with existent user email is impossible")
    public void checkThatIfEnterExistentUserEmailThenCorrespondingErrorMessageToBeDisplayed() {
        String email = userRegistrationData.getEmail();
        String password = userRegistrationData.getPassword();
        String name = userRegistrationData.getName();
        boolean isEmailErrorMessageDisplayed = mainPage
                .clickLoginToAccountButton()
                .clickRegisterPageLink()
                .fillOutRegisterForm(name, email, password)
                .clickRegisterButtonAfterEnteringIncorrectData()
                .checkThatEmailErrorMessageIsDisplayed()
        ;
        Assert.assertTrue(isEmailErrorMessageDisplayed);
    }
}
