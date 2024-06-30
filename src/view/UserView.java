package view;

import business.UserManager;
import core.Helper;
import entity.User;

import javax.swing.*;

/**
 * UserView sınıfı, kullanıcı bilgilerini düzenlemek veya yeni kullanıcı eklemek için kullanıcı arayüzü sağlar.
 */
public class UserView extends Layout {
    private JPanel container;
    private JLabel lbl_brand;
    private JTextField txt_username;
    private JTextField txt_password;
    private JTextField txt_first_name;
    private JTextField txt_last_name;
    private JComboBox cmb_role;
    private JButton btn_save;

    private final User user;
    private final UserManager userManager;

    /**
     * UserView sınıfının yapıcı metodu.
     * @param user Kullanıcı nesnesi (varsa düzenleme, yoksa yeni kullanıcı ekleme)
     */
    public UserView(User user) {
        this.user = user;
        this.userManager = new UserManager();

        this.add(container); // Konteyneri ekrana ekle
        this.guiInitilize(300, 400); // Arayüzü belirli boyutlarda başlat

        // Eğer kullanıcı nesnesi varsa, mevcut kullanıcı bilgilerini arayüzde göster
        if (user != null) {
            this.txt_username.setText(user.getUsername());
            this.txt_password.setText(user.getPassword());
            this.txt_first_name.setText(user.getFirst_name());
            this.txt_last_name.setText(user.getLast_name());
        }

        // ComboBox'a rolleri ekle
        this.cmbRoleAdd();

        // "btn_save" butonuna ActionListener ekle
        this.btn_save.addActionListener(e -> {
            // Eğer rol seçilmemişse uyarı ver
            if (cmb_role.getSelectedIndex() == -1) {
                Helper.showMsg("fill");
                return;
            }

            // Kullanıcı adı, şifre, ad ve soyadı alanlarını al
            String username = txt_username.getText().trim();
            String password = txt_password.getText().trim();
            String firstName = txt_first_name.getText().trim();
            String lastName = txt_last_name.getText().trim();
            User.Role role = (User.Role) cmb_role.getSelectedItem();

            // Alanların boş olup olmadığını kontrol et
            if (Helper.isFieldListEmpty(new JTextField[]{txt_username, txt_password, txt_first_name, txt_last_name})) {
                Helper.showMsg("fill"); // Uyarı mesajı
            } else {
                // Yeni bir kullanıcı nesnesi oluştur
                User newUser = new User();
                newUser.setUsername(username);
                newUser.setPassword(password);
                newUser.setFirst_name(firstName);
                newUser.setLast_name(lastName);
                newUser.setRole(role);

                // Eğer kullanıcı varsa güncelle, yoksa kaydet
                boolean result;
                if (user != null) {
                    newUser.setId(user.getId());
                    result = userManager.update(newUser);
                } else {
                    result = userManager.save(newUser);
                }

                // Sonucu göster
                if (result) {
                    Helper.showMsg("done"); // İşlem başarılı mesajı
                    dispose(); // Pencereyi kapat
                } else {
                    Helper.showMsg("error"); // Hata mesajı
                }
            }
        });
    }

    /**
     * ComboBox'a rolleri ekler.
     */
    public void cmbRoleAdd() {
        this.cmb_role.setModel(new DefaultComboBoxModel<>(User.Role.values())); // ComboBox'a rolleri ekle

        // Eğer kullanıcı varsa, mevcut kullanıcı rolünü seçili hale getir
        if (user != null) {
            this.cmb_role.setSelectedItem(user.getRole());
        } else {
            this.cmb_role.setSelectedItem(null);
        }
    }
}
