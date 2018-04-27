import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({
        InputFileTest.class,
        OutputFileTest.class,
        PilotTest.class,
        PilotSortTests.class
})

public final class AllModuleTest {
}
