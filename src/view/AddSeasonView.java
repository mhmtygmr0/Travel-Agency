package view;

import business.HotelManager;
import business.SeasonManager;
import core.ComboItem;
import core.Helper;
import entity.Hotel;
import entity.Season;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddSeasonView extends Layout {
    private JPanel container;
    private JButton btn_save;
    private JFormattedTextField txt_start_date;
    private JFormattedTextField txt_end_date;
    private JFormattedTextField txt_price_parameter;
    private JComboBox cmb_hotel;
    private HotelManager hotelManager;
    private Hotel hotel;
    private SeasonManager seasonManager;
    private Season season;

    // Yapıcı metod
    public AddSeasonView() {
        this.hotelManager = new HotelManager();
        this.hotel = new Hotel();
        this.seasonManager = new SeasonManager();
        this.season = new Season();
        this.cmb_hotel.getSelectedItem();  // Combobox'tan seçilen öğeyi alır
        this.add(container);  // Arayüz bileşenini konteynere ekler
        this.guiInitilize(300, 400);  // Arayüzün boyutunu ve diğer ayarlarını yapar
        this.cmbAddHotel();  // Combobox'ı dolduran metod

        // Kaydet butonuna tıklandığında yapılacak işlemler
        this.btn_save.addActionListener(e -> {
            // Gerekli alanların dolu olup olmadığını kontrol eder
            JFormattedTextField[] checkDateList = {this.txt_start_date, this.txt_end_date, this.txt_price_parameter};

            if (Helper.isFieldListEmpty(checkDateList) || this.cmb_hotel.getSelectedIndex() == -1) {
                Helper.showMsg("fill");  // Boş alan uyarısı gösterir

            } else {
                boolean result = false;
                ComboItem selectedHotel = (ComboItem) this.cmb_hotel.getSelectedItem();
                this.season.setHotel_id(selectedHotel.getKey());  // Seçilen otelin ID'sini set eder
                this.season.setSeason_type(this.cmb_hotel.getSelectedItem().toString());  // Sezon tipini set eder
                this.season.setPrice_parameter(Double.parseDouble(this.txt_price_parameter.getText()));  // Fiyat parametresini set eder

                try {
                    // Başlangıç ve bitiş tarihlerini doğru formatta parse eder
                    this.season.setStart_date(LocalDate.parse(this.txt_start_date.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    this.season.setEnd_date(LocalDate.parse(this.txt_end_date.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));

                    result = this.seasonManager.save(this.season);  // Sezonu kaydeder

                } catch (DateTimeException ex) {
                    Helper.showMsg("Date Format is Wrong !");  // Tarih formatı hatası gösterir
                    return;
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

    // Combobox'a otelleri ekleyen metod
    private void cmbAddHotel() {
        for (Hotel hotel : this.hotelManager.findAll()) {
            this.cmb_hotel.addItem(hotel.getComboItem());  // Otelleri combobox'a ekler
        }
        this.cmb_hotel.setSelectedIndex(-1);  // Varsayılan seçimi kaldırır
    }

    // MaskFormatter kullanarak tarih alanlarını oluşturan metod
    private void createUIComponents() throws ParseException {
        this.txt_start_date = new JFormattedTextField(new MaskFormatter("##/##/####"));  // Başlangıç tarihi alanı
        this.txt_start_date.setText("01/06/2024");  // Varsayılan başlangıç tarihi

        this.txt_end_date = new JFormattedTextField(new MaskFormatter("##/##/####"));  // Bitiş tarihi alanı
        this.txt_end_date.setText("01/12/2024");  // Varsayılan bitiş tarihi
    }
}
