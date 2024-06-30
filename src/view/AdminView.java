package view;

import business.UserManager;
import core.Helper;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class AdminView extends Layout {
    private JButton btn_logout; // Çıkış butonu
    private JPanel pnl_top; // Üst panel
    private JLabel lbl_welcome; // Hoş geldiniz etiketi
    private JTabbedPane tab_menu; // Sekmeli menü
    private JTable tbl_employee; // Çalışan tablosu
    private JPanel pnl_employee; // Çalışan paneli
    private JScrollPane scl_employee; // Çalışan tablosu kaydırıcı
    private JPanel container; // Ana konteyner panel
    private JComboBox<User.Role> cmb_role; // Rol seçim kutusu
    private JButton btn_employee_search; // Çalışan arama butonu
    private JButton btn_employee_reset; // Çalışan sıfırlama butonu
    private Object[] col_employee; // Çalışan tablosu sütunları
    private User user; // Kullanıcı nesnesi
    private UserManager userManager; // Kullanıcı yönetici sınıfı
    private DefaultTableModel tmdl_employee = new DefaultTableModel(); // Çalışan tablosu modeli
    private JPopupMenu employee_menu; // Çalışan sağ tıklama menüsü

    // AdminView sınıfının yapıcı metodu
    public AdminView(User user) {
        this.user = user;
        this.userManager = new UserManager();
        this.add(container); // Konteyneri ekrana ekle
        this.guiInitilize(600, 500); // GUI'yi belirtilen boyutlarla başlat
        this.lbl_welcome.setText("Welcome " + this.user.getFirst_name() + " " + this.user.getLast_name()); // Kullanıcının adıyla hoş geldiniz mesajı ayarla


        this.loadComponent();

        // Çalışan tablosunu ve bileşenlerini yükle
        this.loadEmployeeTable();
        this.loadEmployeeComponent();
        this.loadEmployeeFilters();
    }

    public void loadComponent(){
        // login ekranına dönmek için butonumuza görev veriyoruz
        btn_logout.addActionListener(e -> {
            dispose();
            LoginView loginView = new LoginView();
        });
    }

    // Çalışan tablosunu yükleme metodu
    public void loadEmployeeTable() {
        this.col_employee = new Object[]{"ID", "Kullanıcı Adı", "Şifre", "Ad", "Soyad", "Rol"};
        ArrayList<Object[]> employeeList = this.userManager.getForTable(this.col_employee.length);
        this.createTable(this.tmdl_employee, this.tbl_employee, this.col_employee, employeeList);
    }

    // Çalışan bileşenlerini yükleme metodu
    public void loadEmployeeComponent() {
        this.tableRowSelect(this.tbl_employee); // Çalışan tablosunda satır seçme işlevi
        this.employee_menu = new JPopupMenu();

        // Çalışan sağ tıklama menüsü öğeleri ve eylemleri
        this.employee_menu.add("New").addActionListener(e -> {
            UserView userView = new UserView(null); // Yeni kullanıcı görünümü aç
            userView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadEmployeeTable(); // Kullanıcı ekledikten sonra çalışan tablosunu yenile
                }
            });
        });

        this.employee_menu.add("Update").addActionListener(e -> {
            int selectUserId = this.getTableSelectedRow(tbl_employee, 0); // Seçilen kullanıcının ID'sini al
            UserView userView = new UserView(this.userManager.getById(selectUserId)); // Seçilen kullanıcıyı güncelleme görünümü aç
            userView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadEmployeeTable(); // Kullanıcı güncelledikten sonra çalışan tablosunu yenile
                }
            });
        });

        this.employee_menu.add("Delete").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectUserId = this.getTableSelectedRow(tbl_employee, 0); // Seçilen kullanıcının ID'sini al
                if (this.userManager.delete(selectUserId)) {
                    Helper.showMsg("done"); // Başarılı mesajı göster
                    loadEmployeeTable(); // Kullanıcı sildikten sonra çalışan tablosunu yenile
                } else {
                    Helper.showMsg("error"); // Hata mesajı göster
                }
            }
        });

        this.tbl_employee.setComponentPopupMenu(employee_menu); // Çalışan tablosuna sağ tıklama menüsünü ayarla

        // Çalışan arama butonu için eylem dinleyici
        btn_employee_search.addActionListener(e -> {
            User.Role selectedRole = (User.Role) cmb_role.getSelectedItem(); // Seçilen rolü al

            if (selectedRole == null) {
                loadEmployeeTable(); // Rol seçilmemişse tüm çalışanları yeniden yükle
            } else {
                ArrayList<Object[]> filteredEmployees = userManager.getEmployeesByRole(selectedRole); // Seçilen role göre filtreleme yap
                createTable(tmdl_employee, tbl_employee, col_employee, filteredEmployees); // Filtrelenmiş çalışanları tabloya yansıt
            }
        });

        // Çalışan sıfırlama butonu için eylem dinleyici
        btn_employee_reset.addActionListener(e -> {
            loadEmployeeTable(); // Çalışan tablosunu sıfırla
            this.cmb_role.setSelectedItem(null); // Rol seçimini temizle
        });
    }

    // Çalışan filtrelerini yükleme metodu
    public void loadEmployeeFilters() {
        this.cmb_role.setModel(new DefaultComboBoxModel<>(User.Role.values())); // Kullanıcı rollerini combo box modeline ekle
        this.cmb_role.setSelectedItem(null); // Başlangıçta hiçbir öğe seçili değil
    }
}
