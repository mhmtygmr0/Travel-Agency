package view;

import business.HotelManager;
import business.SeasonManager;
import core.ComboItem;
import core.Helper;
import entity.Hotel;
import entity.Room;
import entity.Season;

import javax.swing.*;

public class UpdateHotelView extends Layout {
    private JButton btn_hotel_save;
    private JPanel pnl_hotel;
    private JTextField txt_hotel_name;
    private JTextField txt_hotel_email;
    private JTextField txt_hotel_phone;
    private JComboBox cmb_hotel_star;
    private JRadioButton btn_car_park;
    private JRadioButton btn_fitness;
    private JRadioButton btn_concierge;
    private JRadioButton btn_wifi;
    private JRadioButton btn_swimming;
    private JRadioButton btn_spa;
    private JRadioButton btn_room_service;
    private JTextField txt_hotel_address;
    private JPanel container;
    private HotelManager hotelManager;
    private SeasonManager seasonManager;
    private Hotel hotel;
    private Room room;
    private Season season;
    private int hotelId;

    // Constructor
    public UpdateHotelView(int hotelId) {
        this.add(container);  // Arayüz bileşenini konteynere ekler
        this.guiInitilize(500, 500);  // Arayüzün boyutunu ve diğer ayarlarını yapar

        this.hotelManager = new HotelManager();  // HotelManager örneği oluşturur
        this.seasonManager = new SeasonManager();  // SeasonManager örneği oluşturur
        this.hotelId = hotelId;  // Güncellenecek otelin ID'sini alır

        // Otel bilgilerini ve combobox'ı doldurur
        this.populateFields();

        // Save butonuna tıklandığında yapılacak işlemler
        this.btn_hotel_save.addActionListener(e -> {
            // Gerekli alanların dolu olup olmadığını kontrol eder
            JTextField[] selectedHotelList = {this.txt_hotel_name, this.txt_hotel_email, this.txt_hotel_phone};
            if (Helper.isFieldListEmpty(selectedHotelList) || this.cmb_hotel_star.getSelectedIndex() == -1) {
                Helper.showMsg("fill");  // Boş alan uyarısı gösterir
                return;
            }

            boolean result;
            // Güncellenen oteli alır
            this.hotel = this.hotelManager.getById(hotelId);

            // Kullanıcı arayüzündeki bilgilerle otel nesnesini günceller
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

            // Otel bilgilerini günceller ve sonucu kullanıcıya bildirir
            result = this.hotelManager.update(this.hotel);
            if (result) {
                Helper.showMsg("done");  // Başarılı mesajı gösterir
                dispose();  // Pencereyi kapatır
            } else {
                Helper.showMsg("error");  // Hata mesajı gösterir
            }
        });
    }

    // Combobox'a yıldız seçeneklerini ekler
    public void cmbstarAdd() {
        String[] stars = {"*", "**", "***", "****", "*****"};
        this.cmb_hotel_star.setModel(new DefaultComboBoxModel<>(stars));

        // Eğer otel varsa, mevcut yıldız seçeneğini seçer
        if (this.hotel != null) {
            this.cmb_hotel_star.setSelectedItem(this.hotel.getHotel_star());
        } else {
            this.cmb_hotel_star.setSelectedItem(null);
        }
    }

    // Otel bilgilerini ve combobox'ı dolduran metod
    public void populateFields() {
        this.hotel = hotelManager.getById(hotelId);  // Verilen ID'ye göre otel bilgilerini alır

        // Otel bilgilerini kullanıcı arayüzünde ilgili alanlara yerleştirir
        this.txt_hotel_name.setText(this.hotel.getHotel_name());
        this.txt_hotel_address.setText(this.hotel.getHotel_address());
        this.txt_hotel_email.setText(this.hotel.getHotel_email());
        this.txt_hotel_phone.setText(this.hotel.getHotel_phone());
        this.cmbstarAdd();  // Combobox'a yıldız seçeneklerini ekler

        // Otelin sahip olduğu özellikleri kullanıcı arayüzünde işaretler
        this.btn_car_park.setSelected(this.hotel.isHotel_car_park());
        this.btn_wifi.setSelected(this.hotel.isHotel_wifi());
        this.btn_swimming.setSelected(this.hotel.isHotel_pool());
        this.btn_fitness.setSelected(this.hotel.isHotel_fitness());
        this.btn_concierge.setSelected(this.hotel.isHotel_concierge());
        this.btn_spa.setSelected(this.hotel.isHotel_spa());
        this.btn_room_service.setSelected(this.hotel.isHotel_room_service());
    }
}
