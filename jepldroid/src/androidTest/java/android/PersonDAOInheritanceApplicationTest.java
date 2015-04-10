package android;

import android.app.Application;
import android.test.ApplicationTestCase;

import unittest.TestPersonDAOInheritance;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class PersonDAOInheritanceApplicationTest extends ApplicationTestCase<Application> {
    public PersonDAOInheritanceApplicationTest() {
        super(Application.class);


    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        createApplication();
    }

    public void test()
    {
        new TestPersonDAOInheritance().someTest();
    }
}
