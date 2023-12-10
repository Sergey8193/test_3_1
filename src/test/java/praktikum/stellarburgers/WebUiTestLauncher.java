package praktikum.stellarburgers;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import praktikum.stellarburgers.pom.page.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginPageTest.class,
        MainPageBurgerAssemblyTest.class,
        MainPageConstructorSectionsTest.class,
        OrderHistoryPageTest.class,
        ProfilePageTest.class,
        RegisterPageTest.class
})
public class WebUiTestLauncher {
}
