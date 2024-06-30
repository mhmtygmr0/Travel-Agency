package business;

import core.Helper;
import dao.UserDao;
import entity.User;
import java.util.ArrayList;

/**
 * UserManager sınıfı, kullanıcı işlemlerini yönetmek için kullanılır.
 * UserDao sınıfı üzerinden veri erişimi sağlar.
 */
public class UserManager {
    private final UserDao userDao; // Kullanıcı veri erişim işlemleri için UserDao nesnesi

    /**
     * Yapıcı metot, UserDao nesnesini başlatır.
     */
    public UserManager() {
        this.userDao = new UserDao();
    }

    /**
     * Verilen kullanıcı adı ve şifreyle eşleşen kullanıcıyı döndürür.
     *
     * @param username Kullanıcı adı
     * @param password Şifre
     * @return Eşleşen kullanıcı nesnesi veya null (eğer eşleşme yoksa)
     */
    public User findByLogin(String username, String password) {
        return this.userDao.findByLogin(username, password);
    }

    /**
     * Kullanıcı nesnelerini tablo için uygun formatta liste olarak döndürür.
     *
     * @param size Her kullanıcı için sütun sayısı
     * @return Tablo için uygun kullanıcı verilerini içeren liste
     */
    public ArrayList<Object[]> getForTable(int size) {
        ArrayList<Object[]> userRowList = new ArrayList<>();
        for (User user : this.findAll()) {
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = user.getId();
            rowObject[i++] = user.getUsername();
            rowObject[i++] = user.getPassword();
            rowObject[i++] = user.getFirst_name();
            rowObject[i++] = user.getLast_name();
            rowObject[i++] = user.getRole().toString(); // Rolü string olarak al
            userRowList.add(rowObject);
        }
        return userRowList;
    }

    /**
     * Verilen ID'ye sahip kullanıcı nesnesini döndürür.
     *
     * @param id Kullanıcı ID'si
     * @return Kullanıcı nesnesi veya null (eğer ID'ye sahip kullanıcı yoksa)
     */
    public User getById(int id) {
        return this.userDao.getById(id);
    }

    /**
     * Tüm kullanıcıları içeren bir liste döndürür.
     *
     * @return Kullanıcıları içeren liste
     */
    public ArrayList<User> findAll() {
        return this.userDao.findAll();
    }

    /**
     * Yeni bir kullanıcı kaydeder.
     *
     * @param user Kaydedilecek kullanıcı nesnesi
     * @return Kayıt başarılıysa true, aksi halde false
     */
    public boolean save(User user) {
        if (user.getId() != 0) {
            Helper.showMsg("error");
        }
        return this.userDao.save(user);
    }

    /**
     * Mevcut bir kullanıcıyı günceller.
     *
     * @param user Güncellenecek kullanıcı nesnesi
     * @return Güncelleme başarılıysa true, aksi halde false
     */
    public boolean update(User user) {
        if (this.getById(user.getId()) == null) {
            Helper.showMsg("notFound");
        }
        return this.userDao.update(user);
    }

    /**
     * Verilen ID'ye sahip kullanıcıyı siler.
     *
     * @param id Silinecek kullanıcının ID'si
     * @return Silme işlemi başarılıysa true, aksi halde false
     */
    public boolean delete(int id) {
        if (this.getById(id) == null) {
            Helper.showMsg(id + " No registered hotel with ID found!");
            return false;
        }
        return this.userDao.delete(id);
    }

    /**
     * Belirli bir role sahip kullanıcıları filtreleyerek döndürür.
     *
     * @param role Filtrelenecek rol
     * @return Filtrelenmiş kullanıcı listesi
     */
    public ArrayList<Object[]> getEmployeesByRole(User.Role role) {
        ArrayList<Object[]> filteredEmployees = new ArrayList<>();

        // Tüm kullanıcıları getir
        ArrayList<Object[]> allEmployees = this.getForTable(6); // col_employee dizisinin uzunluğunu geçirin

        // Seçilen role göre filtreleme yap
        for (Object[] employee : allEmployees) {
            // Her çalışanın rolünü kontrol et
            if (employee[5].equals(role.toString())) { // Rol bilgisi 6. sütunda (indeks 5) olduğunu varsayalım
                filteredEmployees.add(employee);
            }
        }

        return filteredEmployees;
    }
}
