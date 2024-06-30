package view;

import business.UserManager;
import core.Helper;
import entity.User;

import javax.swing.*;

/**
 * LoginView sınıfı, kullanıcı giriş ekranını temsil eder ve giriş işlemlerini gerçekleştirir.
 */
public class LoginView extends Layout {
    private JPanel container;
    private JTextField txt_username;
    private JPasswordField txt_password;
    private JButton btn_login;
    private final UserManager userManager;

    /**
     * LoginView sınıfının yapıcı metodu, UserManager örneği oluşturarak başlatır.
     */
    public LoginView() {
        this.userManager = new UserManager();
        this.add(container);  // Arayüzü konteynere ekle
        this.guiInitilize(300, 300);  // Arayüzü belirli boyutlarda başlat

        // Giriş butonuna tıklandığında yapılacak işlemler
        btn_login.addActionListener(e -> {
            JTextField[] checkFieldList = {this.txt_username, this.txt_password};
            // Kullanıcı adı ve şifre boşsa uyarı verir
            if (Helper.isFieldListEmpty(checkFieldList)) {
                Helper.showMsg("fill");
            } else {
                // Kullanıcı adı ve şifreye göre kullanıcıyı bulur
                User loginUser = this.userManager.findByLogin(this.txt_username.getText(), new String(this.txt_password.getPassword()));
                if (loginUser == null) {
                    Helper.showMsg("notFound");  // Kullanıcı bulunamazsa uyarı verir
                } else {
                    // Kullanıcı rolüne göre ilgili kullanıcı arayüzünü açar
                    if (loginUser.getRole() == User.Role.admin) {
                        AdminView adminView = new AdminView(loginUser); // Admin kullanıcı arayüzünü açar
                        dispose();  // Giriş ekranını kapatır
                    } else {
                        PersonelView personelView = new PersonelView(loginUser);  // Personel kullanıcı arayüzünü açar
                        dispose();  // Giriş ekranını kapatır
                    }
                }
            }
        });
    }
}
