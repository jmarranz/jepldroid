package jepl.android;

import android.app.Application;
import android.test.ApplicationTestCase;

import jepl.unittest.TestNonJTA;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class NonJTAApplicationTest extends ApplicationTestCase<Application> {
    public NonJTAApplicationTest() {
        super(Application.class);


    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        createApplication();
    }

    public void test()
    {
        new TestNonJTA().someTest();
    }
}
