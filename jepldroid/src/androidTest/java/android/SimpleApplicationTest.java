package android;

import android.app.Application;
import android.test.ApplicationTestCase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class SimpleApplicationTest extends ApplicationTestCase<Application> {
    public SimpleApplicationTest() {
        super(Application.class);


    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        createApplication();
    }

    public void test()
    {
        /*
        if (true)
        {
            return;
        }
        */

        // CreateDBModel.main(null);

        // JUnitCore.main(new String[]{TestAll.class.getName()});

        //String url = "jdbc:sqlite://data/data/example.testjdbc/example.db";
        String url = "jdbc:sqlite:" + getApplication().getDir("test", Application.MODE_PRIVATE) + "/test.db";

/*
        try {
            Class.forName("org.sqldroid.SQLDroidDriver");

        } catch (java.lang.ClassNotFoundException e) {
        	throw new RuntimeException(e);
        }
*/
        Connection con;
        Statement stmt;

        try {

            DriverManager.registerDriver(new org.sqldroid.SQLDroidDriver());

            con = DriverManager.getConnection(url, "myLogin", "myPW");

            stmt = con.createStatement();
            stmt.executeUpdate("drop table if exists COFFEES");
            stmt.executeUpdate("create table COFFEES "
                    + "(COF_NAME varchar(32), "
                    + "SUP_ID int, " + "PRICE float, " + "SALES int, "
                    + "TOTAL int)");
            stmt.close();

            stmt = con.createStatement();
            stmt.executeUpdate("insert into COFFEES "
                    + "values('Colombian', 00101, 7.99, 0, 0)");

            stmt.executeUpdate("insert into COFFEES "
                    + "values('French_Roast', 00049, 8.99, 0, 0)");

            stmt.executeUpdate("insert into COFFEES "
                    + "values('Espresso', 00150, 9.99, 0, 0)");

            stmt.executeUpdate("insert into COFFEES "
                    + "values('Colombian_Decaf', 00101, 8.99, 0, 0)");

            stmt.executeUpdate("insert into COFFEES "
                    + "values('French_Roast_Decaf', 00049, 9.99, 0, 0)");

            ResultSet rs = stmt.executeQuery("select COF_NAME, PRICE from COFFEES");

            // Coffee Break Coffees and Prices:
            int count = 0;
            while (rs.next()) {
                String s = rs.getString("COF_NAME");
                float f = rs.getFloat("PRICE");

                if (s.equals("Colombian"))
                {
                    assertEquals(f,7.99f);
                }
                else if (s.equals("French_Roast"))
                {
                    assertEquals(f,8.99f);
                }
                else if (s.equals("Espresso"))
                {
                    assertEquals(f,9.99f);
                }
                else if (s.equals("Colombian_Decaf"))
                {
                    assertEquals(f,8.99f);
                }
                else if (s.equals("French_Roast_Decaf"))
                {
                    assertEquals(f,9.99f);
                }
                else
                {
                    assertTrue("Not found", false);
                }

                count++;
            }
            stmt.close();
            con.close();

            assertEquals(count,5);

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        System.out.println("SUCCESS");
    }
}
