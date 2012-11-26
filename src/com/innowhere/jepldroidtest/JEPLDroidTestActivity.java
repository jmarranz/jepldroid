package com.innowhere.jepldroidtest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.runner.JUnitCore;

import unittest.TestAll;
import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.widget.TextView;
import example.CreateDBModel;

public class JEPLDroidTestActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);

        CreateDBModel.main(null);
        
        JUnitCore.main(new String[]{TestAll.class.getName()});
        if (true)
        {
        	finish();
         	return;
        }
        
        // http://docs.oracle.com/cd/E17076_02/html/installation/build_android_jdbc.html
        
        setContentView(R.layout.main);        
        TextView tv = new TextView(this);
        tv.setText("App Started");
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
}