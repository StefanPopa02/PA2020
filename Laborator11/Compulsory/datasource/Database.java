package com.popastefan.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    protected Connection con;
    private static Database myDatabase = null;

    private Database() {

    }

    public Connection getCon() {
        return con;
    }

    //am rezolvat singleton-ul
    public static Database getMyDatabase() {
        if (myDatabase == null) {
            myDatabase = new Database();
        }
        return myDatabase;
    }

    public void openDb() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myrestapi","admin","admin");
        } catch (SQLException e) {
            System.out.println("Eroare la deschiderea db: " + e.getMessage());
        }
    }

    public void closeDb() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Eroare la inchiderea db: " + e.getMessage());
        }
    }
}
