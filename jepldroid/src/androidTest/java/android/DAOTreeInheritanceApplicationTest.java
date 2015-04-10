package android;

import android.app.Application;
import android.test.ApplicationTestCase;

import unittest.TestDAOTreeInheritance;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class DAOTreeInheritanceApplicationTest extends ApplicationTestCase<Application> {
    public DAOTreeInheritanceApplicationTest() {
        super(Application.class);


    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        createApplication();
    }

    public void test()
    {
        new TestDAOTreeInheritance().someTest();
    }
}
