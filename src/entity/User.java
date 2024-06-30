package entity;

public class User {
    // Kullanıcının benzersiz kimliği
    private int id;

    // Kullanıcı adı
    private String username;

    // Kullanıcı şifresi
    private String password;

    // Kullanıcının ilk adı
    private String first_name;

    // Kullanıcının soyadı
    private String last_name;

    // Kullanıcının rolü (admin veya personel)
    private Role role;

    // Kullanıcı rollerini tanımlayan enum
    public enum Role {
        admin,
        personel
    }

    // Varsayılan kurucu metot
    public User(){
    }

    // Parametreli kurucu metot
    public User(int id, String username, String password, String first_name, String last_name, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.role = role;
    }

    // Kullanıcının kimliğini döndürür
    public int getId() {
        return id;
    }

    // Kullanıcının kimliğini ayarlar
    public void setId(int id) {
        this.id = id;
    }

    // Kullanıcının kullanıcı adını döndürür
    public String getUsername() {
        return username;
    }

    // Kullanıcının kullanıcı adını ayarlar
    public void setUsername(String username) {
        this.username = username;
    }

    // Kullanıcının şifresini döndürür
    public String getPassword() {
        return password;
    }

    // Kullanıcının şifresini ayarlar
    public void setPassword(String password) {
        this.password = password;
    }

    // Kullanıcının ilk adını döndürür
    public String getFirst_name() {
        return first_name;
    }

    // Kullanıcının ilk adını ayarlar
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    // Kullanıcının soyadını döndürür
    public String getLast_name() {
        return last_name;
    }

    // Kullanıcının soyadını ayarlar
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    // Kullanıcının rolünü döndürür
    public Role getRole() {
        return role;
    }

    // Kullanıcının rolünü ayarlar
    public void setRole(Role role) {
        this.role = role;
    }

    // Kullanıcı nesnesini string olarak döndürür
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", role=" + role +
                '}';
    }
}
