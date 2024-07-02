package view;

import business.HotelManager;
import business.PensionManager;
import core.ComboItem;
import core.Helper;
import entity.Hotel;
import entity.Pension;
import entity.User;

import javax.swing.*;

public class PensionView extends Layout {
    private JPanel container;
    private JComboBox cmb_hotel;
    private JComboBox cmb_pension;
    private JButton btn_save;
    private PensionManager pensionManager;
    private Pension pension;
    private HotelManager hotelManager;
    private Hotel hotel;
    private User user;

    // Yapıcı metod
    public PensionView() {
        this.hotel = new Hotel();
        this.hotelManager = new HotelManager();
        this.pensionManager = new PensionManager();
        this.pension = new Pension();
        this.add(container);  // Arayüz bileşenini konteynere ekler
        this.guiInitilize(300, 300);  // Arayüzün boyutunu ve diğer ayarlarını yapar
        this.addCombobox();  // Combobox'ları dolduran metod

        // Kaydet butonuna tıklandığında yapılacak işlemler
        this.btn_save.addActionListener(e -> {
            // Gerekli alanların dolu olup olmadığını kontrol eder
            if (this.cmb_hotel.getSelectedIndex() == -1 || this.cmb_pension.getSelectedIndex() == -1) {
                Helper.showMsg("fill");  // Boş alan uyarısı gösterir

            } else {
                boolean result = false;
                ComboItem selectedHotel = (ComboItem) cmb_hotel.getSelectedItem();
                this.pension.setHotel_id(selectedHotel.getKey());  // Seçilen otelin ID'sini set eder
                this.pension.setPension_type(Pension.PensionType.valueOf(cmb_pension.getSelectedItem().toString()));  // Seçilen pansiyon tipini set eder

                // Pansiyonun ID'si varsa güncelleme, yoksa kaydetme işlemi yapar
                if (this.pension.getPension_id() != 0) {
                    result = this.pensionManager.update(this.pension);  // Pansiyonu günceller

                } else {
                    result = this.pensionManager.save(this.pension);  // Pansiyonu kaydeder
                }

                // Sonucu kullanıcıya bildirir
                if (result) {
                    dispose();  // Pencereyi kapatır
                    Helper.showMsg("done");  // Başarılı mesajı gösterir
                } else {
                    Helper.showMsg("error");  // Hata mesajı gösterir
                }
            }
        });
    }

    // Combobox'ları dolduran metod
    public void addCombobox() {
        // Tüm otelleri combobox'a ekler
        for (Hotel hotel : this.hotelManager.findAll()) {
            this.cmb_hotel.addItem(hotel.getComboItem());
        }
        this.cmb_hotel.setSelectedIndex(-1);  // Varsayılan seçimi kaldırır

        // Tüm pansiyon tiplerini combobox'a ekler
        for (Pension.PensionType type : Pension.PensionType.values()) {
            this.cmb_pension.addItem(type.toString());
        }
        this.cmb_pension.setSelectedIndex(-1);  // Varsayılan seçimi kaldırır
    }

}
