package praktikum.stellarburgers;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import praktikum.stellarburgers.pom.page.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginPageParamTest.class,
        MainPageBurgerAssemblyParamTest.class,
        MainPageConstructorSectionsParamTest.class,
        OrderHistoryPageParamTest.class,
        ProfilePageParamTest.class,
        RegisterPageParamTest.class
})
public class WebUiParamTestLauncher {
}
