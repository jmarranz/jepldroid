package com.innowhere.jepldroidtest;

import android.app.Application;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.github.amlcurran.showcaseview.ShowcaseView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        //ShowcaseView.class.getName();

        TextView tv = new TextView(this);
        tv.setText("App Started ");
        setContentView(tv);

        StringBuilder output = new StringBuilder();
        output.append("Appstart: \n");

        //String url = "jdbc:sqlite://data/data/example.testjdbc/example.db";
        String url = "jdbc:sqlite:" + getApplication().getDir("test", Application.MODE_PRIVATE) + "/test.db";
        Connection con;
        String dropString = "drop table if exists COFFEES";
        String createString;
        createString = "create table COFFEES "
                + "(COF_NAME varchar(32), "
                + "SUP_ID int, " + "PRICE float, " + "SALES int, "
                + "TOTAL int)";
        String insertString = "drop table COFFEES if exisits";
        String query = "select COF_NAME, PRICE from COFFEES";
        Statement stmt;

/*
        try {
            Class.forName("org.sqldroid.SQLDroidDriver");

        } catch (java.lang.ClassNotFoundException e) {
        	throw new RuntimeException(e);
        }
*/
        try {

            DriverManager.registerDriver(new org.sqldroid.SQLDroidDriver());

            con =
                    DriverManager.getConnection(url, "myLogin", "myPW");

            stmt = con.createStatement();
            stmt.executeUpdate(dropString);
            stmt.executeUpdate(createString);
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

            ResultSet rs = stmt.executeQuery(query);

            output.append("Coffee Break Coffees and Prices:\n");
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
}
