package view;

import business.HotelManager;
import core.Helper;
import entity.Hotel;

import javax.swing.*;

public class AddHotelView extends Layout {
    private JPanel container;
    private JPanel pnl_hotel;
    private JTextField txt_hotel_name;
    private JTextField txt_hotel_address;
    private JTextField txt_hotel_email;
    private JTextField txt_hotel_phone;
    private JComboBox<String> cmb_hotel_star;
    private JRadioButton btn_car_park;
    private JRadioButton btn_wifi;
    private JRadioButton btn_fitness;
    private JRadioButton btn_spa;
    private JRadioButton btn_swimming;
    private JRadioButton btn_concierge;
    private JRadioButton btn_room_service;
    private JButton btn_hotel_save;
    private Hotel hotel;
    private HotelManager hotelManager;

    // Yapıcı metod
    public AddHotelView() {
        this.hotelManager = new HotelManager();  // HotelManager sınıfı üzerinden işlemleri yönetir
        this.hotel = new Hotel();  // Yeni bir Hotel nesnesi oluşturur
        this.add(container);  // Arayüz bileşenlerini konteynere ekler
        this.guiInitilize(500, 500);  // Arayüzün boyutunu ve diğer ayarlarını yapar
        this.cmbstarAdd();  // Yıldız seçim ComboBox'ını doldurur

        // Eğer mevcut bir otel varsa, bilgileri giriş alanlarına yerleştirir
        if (this.hotel.getHotel_id() != 0) {
            this.txt_hotel_name.setText(this.hotel.getHotel_name());
            this.txt_hotel_address.setText(this.hotel.getHotel_address());
            this.txt_hotel_email.setText(this.hotel.getHotel_email());
            this.txt_hotel_phone.setText(this.hotel.getHotel_phone());
            this.cmb_hotel_star.setSelectedItem(this.hotel.getHotel_star());
        }

        // Kaydet butonuna tıklanınca yapılacak işlemler
        btn_hotel_save.addActionListener(e -> {
            JTextField[] checkFieldList = {this.txt_hotel_name, this.txt_hotel_email, this.txt_hotel_phone, this.txt_hotel_address};

            // Gerekli alanların boş olup olmadığını kontrol eder
            if (Helper.isFieldListEmpty(checkFieldList) || this.cmb_hotel_star.getSelectedIndex() == -1) {
                Helper.showMsg("fill");  // Yardımcı metinle uygun bir mesaj gösterir
            } else {
                boolean result = true;

                // Otel nesnesine kullanıcının girdiği bilgileri atar
                this.hotel.setHotel_name(this.txt_hotel_name.getText());
                this.hotel.setHotel_address(this.txt_hotel_address.getText());
                this.hotel.setHotel_email(this.txt_hotel_email.getText());
                this.hotel.setHotel_phone(this.txt_hotel_phone.getText());
                this.hotel.setHotel_star((String) this.cmb_hotel_star.getSelectedItem());
                this.hotel.setHotel_car_park(this.btn_car_park.isSelected());
                this.hotel.setHotel_wifi(this.btn_wifi.isSelected());
                this.hotel.setHotel_pool(this.btn_swimming.isSelected());
                this.hotel.setHotel_fitness(this.btn_fitness.isSelected());
                this.hotel.setHotel_concierge(this.btn_concierge.isSelected());
                this.hotel.setHotel_spa(this.btn_spa.isSelected());
                this.hotel.setHotel_room_service(this.btn_room_service.isSelected());

                // HotelManager üzerinden oteli kaydeder
                result = this.hotelManager.save(hotel);
                if (result) {
                    Helper.showMsg("done");  // Başarılı mesajını gösterir
                    dispose();  // Pencereyi kapatır
                } else {
                    Helper.showMsg("error");  // Hata mesajını gösterir
                }
            }
        });
    }

    // Yıldız seçim ComboBox'ını dolduran metod
    public void cmbstarAdd() {
        String[] stars = {"*", "**", "***", "****", "*****"};
        this.cmb_hotel_star.setModel(new DefaultComboBoxModel<>(stars));
        this.cmb_hotel_star.setSelectedIndex(-1);  // Varsayılan olarak seçili olacak öğeyi belirler
    }
}
