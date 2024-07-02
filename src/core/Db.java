package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db {


    // Veritabanı bağlantı bilgileri
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/travel_agency";
    public static final String DB_USERNAME = "postgres";
    public static final String DB_PASSWORD = "mehmet";

    private Connection connection = null;
    private static Db instance = null;


    /**
     * Db sınıfı kurucusu, veritabanı bağlantısını oluşturur.
     */
    public Db() {
        try {
            this.connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * Veritabanı bağlantısını döndürür.
     *
     * @return Connection nesnesi
     */
    public Connection getConnection() {
        return connection;
    }


    /**
     * Singleton tasarım deseni ile Db sınıfı için tekil örnek alır ve
     * veritabanı bağlantısını sağlar.
     *
     * @return Db sınıfının tek örneği üzerinden veritabanı bağlantısı
     */
    public static Connection getInstance() {
        try {
            // instance henüz oluşturulmamış veya bağlantı kapatılmışsa
            if (instance == null || instance.getConnection().isClosed()) {
                instance = new Db();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return instance.getConnection();
    }
}