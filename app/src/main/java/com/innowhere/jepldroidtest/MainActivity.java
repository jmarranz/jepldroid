package com.innowhere.jepldroidtest;

import android.app.Application;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

//import com.github.amlcurran.showcaseview.ShowcaseView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import jepl.JEPLBootNonJTA;
import jepl.JEPLBootRoot;
import jepl.JEPLCachedResultSet;
import jepl.JEPLDAL;
import jepl.JEPLNonJTADataSource;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        //ShowcaseView.class.getName();

        TextView tv = new TextView(this);
        setContentView(tv);

        StringBuilder output = new StringBuilder();

        //String url = "jdbc:sqlite://data/data/example.testjdbc/example.db";
        String url = "jdbc:sqlite:" + getApplication().getDir("test", Application.MODE_PRIVATE) + "/test.db";

        String login = "myLogin";
        String password = "myPW";

        output.append(executeJDBCRawTest(url,login,password));

        output.append("\n\n");

        output.append(executeJEPLDroidTest(url,login,password));

        tv.setText(output.toString());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private StringBuilder executeJEPLDroidTest(String url,String login,String password)
    {
        StringBuilder output = new StringBuilder();

        output.append("Coffee Break Coffees and Prices (JEPLDroid):\n\n");

        SimpleDataSource ds = new SimpleDataSource(org.sqldroid.SQLDroidDriver.class.getName(),url, login,password, 1);

        try {
            JEPLBootNonJTA boot = JEPLBootRoot.get().createJEPLBootNonJTA();
            JEPLNonJTADataSource jds = boot.createJEPLNonJTADataSource(ds);
            jds.setDefaultAutoCommit(false); // Not really needed, default value

            JEPLDAL dal = jds.createJEPLDAL();

            dal.createJEPLDALQuery("drop table if exists COFFEES").executeUpdate();

            dal.createJEPLDALQuery("create table COFFEES ("
                    + "COF_NAME varchar(32), "
                    + "SUP_ID int, " + "PRICE float, " + "SALES int, "
                    + "TOTAL int)").executeUpdate();


            dal.createJEPLDALQuery("insert into COFFEES "
                    + "values('Colombian', 00101, 7.99, 0, 0)").executeUpdate();

            dal.createJEPLDALQuery("insert into COFFEES "
                    + "values('French Roast', 00049, 8.99, 0, 0)").executeUpdate();

            dal.createJEPLDALQuery("insert into COFFEES "
                    + "values('Espresso', 00150, 9.99, 0, 0)").executeUpdate();

            dal.createJEPLDALQuery("insert into COFFEES "
                    + "values('Colombian Decaf', 00101, 8.99, 0, 0)").executeUpdate();

            dal.createJEPLDALQuery("insert into COFFEES "
                    + "values('French Roast Decaf', 00049, 9.99, 0, 0)").executeUpdate();

            JEPLCachedResultSet rs = dal.createJEPLDALQuery("select COF_NAME, PRICE from COFFEES").getJEPLCachedResultSet();
            int size = rs.size();
            for(int i = 1; i <= size ; i++)
            {
                String s = rs.getValue(i, "COF_NAME", String.class);
                float f = rs.getValue(i, "PRICE", float.class);
                output.append(s + "   " + f + "\n");
            }
        }
        finally {
            try {
                ds.destroy();
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


        return output;
    }

    private StringBuilder executeJDBCRawTest(String url,String login,String password)
    {
        StringBuilder output = new StringBuilder();

        output.append("Coffee Break Coffees and Prices (JDBC raw):\n\n");

/*
        try {
            Class.forName("org.sqldroid.SQLDroidDriver");

        } catch (java.lang.ClassNotFoundException e) {
        	throw new RuntimeException(e);
        }
*/
        try {

            DriverManager.registerDriver(new org.sqldroid.SQLDroidDriver());

            Connection con = DriverManager.getConnection(url, login, password);


            Statement stmt;
            stmt = con.createStatement();
            stmt.executeUpdate("drop table if exists COFFEES");

            stmt.executeUpdate("create table COFFEES ("
                    + "COF_NAME varchar(32), "
                    + "SUP_ID int, " + "PRICE float, " + "SALES int, "
                    + "TOTAL int)");
            stmt.close();

            stmt = con.createStatement();
            stmt.executeUpdate("insert into COFFEES "
                    + "values('Colombian', 00101, 7.99, 0, 0)");

            stmt.executeUpdate("insert into COFFEES "
                    + "values('French Roast', 00049, 8.99, 0, 0)");

            stmt.executeUpdate("insert into COFFEES "
                    + "values('Espresso', 00150, 9.99, 0, 0)");

            stmt.executeUpdate("insert into COFFEES "
                    + "values('Colombian Decaf', 00101, 8.99, 0, 0)");

            stmt.executeUpdate("insert into COFFEES "
                    + "values('French Roast Decaf', 00049, 9.99, 0, 0)");

            ResultSet rs = stmt.executeQuery("select COF_NAME, PRICE from COFFEES");

            while (rs.next()) {
                String s = rs.getString("COF_NAME");
                float f = rs.getFloat("PRICE");
                output.append(s + "   " + f + "\n");
            }
            stmt.close();
            con.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return output;
    }
}
