package dao;

import core.Db;
import entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * UserDao sınıfı, kullanıcı veri erişimi işlemlerini sağlar.
 * Db sınıfından alınan veritabanı bağlantısını kullanır.
 */
public class UserDao {
    private final Connection connection; // Veritabanı bağlantısı

    /**
     * Yapıcı metot, Db sınıfından bir veritabanı bağlantısı alır.
     */
    public UserDao() {
        this.connection = Db.getInstance();
    }

    /**
     * Tüm kullanıcıları veritabanından getiren yöntem.
     *
     * @return Tüm kullanıcıları içeren liste
     */
    public ArrayList<User> findAll() {
        ArrayList<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM public.user ORDER BY user_id";
        try {
            ResultSet rs = this.connection.createStatement().executeQuery(sql);
            while (rs.next()) {
                userList.add(this.match(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    /**
     * Verilen kullanıcı adı ve şifreye göre kullanıcıyı bulan yöntem.
     *
     * @param username Kullanıcı adı
     * @param password Şifre
     * @return Eşleşen kullanıcı nesnesi veya null (eğer eşleşme yoksa)
     */
    public User findByLogin(String username, String password) {
        User user = null;
        String query = "SELECT * FROM public.user WHERE username = ? AND password = ?";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setString(1, username);
            pr.setString(2, password);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                user = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Verilen ID'ye sahip kullanıcıyı bulan yöntem.
     *
     * @param id Kullanıcı ID'si
     * @return Eşleşen kullanıcı nesnesi veya null (eğer ID'ye sahip kullanıcı yoksa)
     */
    public User getById(int id) {
        User user = null;
        String query = "SELECT * FROM public.user WHERE user_id = ?";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                user = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Veritabanından alınan ResultSet'i User nesnesine dönüştüren yöntem.
     *
     * @param rs ResultSet nesnesi
     * @return User nesnesi
     * @throws SQLException SQL istisnaları için
     */
    public User match(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setFirst_name(rs.getString("first_name"));
        user.setLast_name(rs.getString("last_name"));
        user.setRole(User.Role.valueOf(rs.getString("role")));
        return user;
    }

    /**
     * Yeni bir kullanıcı kaydeder.
     *
     * @param user Kaydedilecek kullanıcı nesnesi
     * @return Kayıt başarılıysa true, aksi halde false
     */
    public boolean save(User user) {
        String query = "INSERT INTO public.user (username, password, first_name, last_name, role) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setString(1, user.getUsername());
            pr.setString(2, user.getPassword());
            pr.setString(3, user.getFirst_name());
            pr.setString(4, user.getLast_name());
            pr.setString(5, user.getRole().toString());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Mevcut bir kullanıcıyı günceller.
     *
     * @param user Güncellenecek kullanıcı nesnesi
     * @return Güncelleme başarılıysa true, aksi halde false
     */
    public boolean update(User user) {
        String query = "UPDATE public.user SET username = ?, password = ?, first_name = ?, last_name = ?, role = ? WHERE user_id = ?";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setString(1, user.getUsername());
            pr.setString(2, user.getPassword());
            pr.setString(3, user.getFirst_name());
            pr.setString(4, user.getLast_name());
            pr.setString(5, user.getRole().toString());
            pr.setInt(6, user.getId());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Verilen ID'ye sahip kullanıcıyı siler.
     *
     * @param id Silinecek kullanıcının ID'si
     * @return Silme işlemi başarılıysa true, aksi halde false
     */
    public boolean delete(int id) {
        String query = "DELETE FROM public.user WHERE user_id = ?";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
