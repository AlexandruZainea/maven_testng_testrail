import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class SampleTest {

    @TestCase(id = "C1")
    @Test(groups = { "NewTag"})
    public void createIssue() {

        assertTrue(1 == 1);

    }

}


