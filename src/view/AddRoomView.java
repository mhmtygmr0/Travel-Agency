package view;

import business.HotelManager;
import business.PensionManager;
import business.RoomManager;
import business.SeasonManager;
import core.ComboItem;
import core.Helper;
import entity.Hotel;
import entity.Pension;
import entity.Room;
import entity.Season;

import javax.swing.*;
import java.util.ArrayList;

public class AddRoomView extends Layout {
    private JPanel container;
    private JComboBox cmb_hotel;
    private JComboBox cmb_pension;
    private JComboBox cmb_season;
    private JTextField txt_adult;
    private JTextField txt_child;
    private JTextField txt_stock;
    private JTextField txt_capacity;
    private JTextField txt_m2;
    private JComboBox cmb_type;
    private JButton btn_save;
    private JRadioButton btn_console;
    private JRadioButton btn_tv;
    private JRadioButton btn_minibar;
    private JRadioButton btn_projection;
    private JRadioButton btn_gym;
    private JRadioButton btn_moneybox;
    private HotelManager hotelManager;
    private SeasonManager seasonManager;
    private PensionManager pensionManager;
    private ComboItem comboItem;
    private Hotel hotel;
    private Room room;
    private Season season;
    private RoomManager roomManager;

    // Yapıcı metod
    public AddRoomView() {
        this.comboItem = new ComboItem();
        this.hotel = new Hotel();
        this.room = new Room();
        this.season = new Season();
        this.pensionManager = new PensionManager();
        this.seasonManager = new SeasonManager();
        this.hotelManager = new HotelManager();
        this.roomManager = new RoomManager();
        this.add(container);  // Arayüz bileşenini konteynere ekler
        this.guiInitilize(700, 500);  // Arayüzün boyutunu ve diğer ayarlarını yapar
        this.addCombobox();  // Combobox'ları dolduran metod

        // Kaydet butonuna tıklandığında yapılacak işlemler
        this.btn_save.addActionListener(e -> {
            JTextField[] selectedRoomList = new JTextField[]{this.txt_adult, this.txt_child, this.txt_capacity, this.txt_m2, this.txt_stock};

            // Seçili alanların boş olup olmadığını kontrol eder
            if (Helper.isFieldListEmpty(selectedRoomList) || this.cmb_hotel.getSelectedIndex() == -1 || this.cmb_season.getSelectedIndex() == -1 || this.cmb_pension.getSelectedIndex() == -1 || this.cmb_type.getSelectedIndex() == -1) {
                Helper.showMsg("fill");  // Boş alan uyarısı gösterir
            } else {
                boolean result = false;
                ComboItem selectedHotel = (ComboItem) cmb_hotel.getSelectedItem();
                ComboItem selectedPension = (ComboItem) cmb_pension.getSelectedItem();
                ComboItem selectedSeason = (ComboItem) this.cmb_season.getSelectedItem();

                // Oda nesnesine seçili değerleri atar
                this.room.setSeason_id(selectedSeason.getKey());
                this.room.setPension_id(selectedPension.getKey());
                this.room.setHotel_id(selectedHotel.getKey());
                this.room.setRoom_type((Room.RoomType) this.cmb_type.getSelectedItem());
                this.room.setStock(Integer.parseInt(this.txt_stock.getText()));
                this.room.setAdult_price(Double.parseDouble(this.txt_adult.getText()));
                this.room.setChild_price(Double.parseDouble(this.txt_child.getText()));
                this.room.setBed_capacity(Integer.parseInt(this.txt_capacity.getText()));
                this.room.setSquare_meter(Integer.parseInt(this.txt_m2.getText()));
                this.room.setTelevision(this.btn_tv.isSelected());
                this.room.setMinibar(this.btn_minibar.isSelected());
                this.room.setGame_console(this.btn_console.isSelected());
                this.room.setCash_box(this.btn_moneybox.isSelected());
                this.room.setProjection(this.btn_projection.isSelected());
                this.room.setGym(this.btn_gym.isSelected());

                // Eğer oda id değeri 0'dan farklı ise oda güncellemesi yapar, değilse yeni oda kaydı yapar
                if (room.getRoom_id() != 0) {
                    result = this.roomManager.update(this.room);
                } else {
                    result = this.roomManager.save(this.room);
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
        // Otel combobox'ını doldurur
        for (Hotel hotel : this.hotelManager.findAll()) {
            this.cmb_hotel.addItem(hotel.getComboItem());
        }
        this.cmb_hotel.setSelectedIndex(-1);  // Varsayılan seçimi kaldırır

        // Oda tipi combobox'ını doldurur
        for (Room.RoomType type : Room.RoomType.values()) {
            this.cmb_type.addItem(type);
        }
        this.cmb_type.setSelectedIndex(-1);  // Varsayılan seçimi kaldırır

        // Otel seçildiğinde yapılacak işlemler
        this.cmb_hotel.addActionListener(e -> {
            ComboItem selectedHotel = (ComboItem) this.cmb_hotel.getSelectedItem();
            int selectedHotelId = selectedHotel.getKey();

            // Seçilen otele göre pansiyonları getirir
            ArrayList<Pension> pensions = this.pensionManager.getPensionByHotelId(selectedHotelId);
            this.cmb_pension.removeAllItems();  // Combobox'ı temizler

            for (Pension pension : pensions) {
                this.cmb_pension.addItem(pension.getComboItem());  // Pansiyonları combobox'a ekler
            }

            // Seçilen otele göre sezonları getirir
            ArrayList<Season> seasons = this.seasonManager.getSeasonsByHotelId(selectedHotelId);
            this.cmb_season.removeAllItems();  // Combobox'ı temizler

            for (Season season : seasons) {
                this.cmb_season.addItem(season.getComboItem());  // Sezonları combobox'a ekler
            }
        });
    }
}
