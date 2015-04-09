package jepl.android;

import android.app.Application;
import android.test.ApplicationTestCase;

import jepl.unittest.TestDAOSelectors;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class DAOSelectorsApplicationTest extends ApplicationTestCase<Application> {
    public DAOSelectorsApplicationTest() {
        super(Application.class);


    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        createApplication();
    }

    public void test()
    {
        new TestDAOSelectors().someTest();
    }
}
