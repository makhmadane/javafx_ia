package sn.data_ia_fx.repository;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {

    private final String server= "localhost";
    private final String username = "postgres";
    private final String password = "passer";
    private final String bd = "data_ia_assurance";
    private final String url = ""
            + "jdbc:postgresql://"+server+":5432/"+bd;
    private Connection conn = null;

    public Connection getConnection(){
        try {
            Class.forName("org.postgresql.Driver");
            if(conn == null){ //Singleton
                conn= DriverManager.getConnection(url, username, password);
                System.out.println("Connecté");
            }
        } catch (Exception ex) {
            conn=null;
            System.out.print("Erreur");
        }
        return conn;
    }
}
