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
    private ComboItem comboItem;
    private HotelManager hotelManager;
    private SeasonManager seasonManager;
    //private PensionManager pensionManager;
    //private RoomManager roomManager;
    private Hotel hotel;
    private Room room;
    private Season season;
    private int hotelId;


    public UpdateHotelView(int hotelId) {
        this.add(container);
        this.guiInitilize(500, 500);
        this.comboItem = new ComboItem();
        this.hotel = new Hotel();
        this.room = new Room();
        this.season = new Season();
        this.hotelId = hotelId;
        this.seasonManager = new SeasonManager();
        this.hotelManager = new HotelManager();
        Hotel currentHotel = hotelManager.getById(hotelId);
        this.cmbstarAdd();

        //Açılan ekrandaki butonların, düzenlenen oteldeki verilerle eşleştirilmesi

        if(currentHotel.isHotel_car_park()){btn_car_park.setSelected(true);}
        if(currentHotel.isHotel_wifi()){btn_wifi.setSelected(true);}
        if(currentHotel.isHotel_pool()){btn_swimming.setSelected(true);}
        if(currentHotel.isHotel_fitness()){btn_fitness.setSelected(true);}
        if(currentHotel.isHotel_concierge()){btn_concierge.setSelected(true);}
        if(currentHotel.isHotel_spa()){btn_spa.setSelected(true);}
        if(currentHotel.isHotel_room_service()){btn_room_service.setSelected(true);}

        this.txt_hotel_name.setText(currentHotel.getHotel_name());
        this.txt_hotel_address.setText(currentHotel.getHotel_address());
        this.txt_hotel_email.setText(currentHotel.getHotel_email());
        this.txt_hotel_phone.setText(currentHotel.getHotel_phone());
        this.cmb_hotel_star.setSelectedItem(currentHotel.getHotel_star());

        //Save butonu işlemleri

        this.btn_hotel_save.addActionListener(e -> {

            JTextField[] selectedHotelList = new JTextField[]{this.txt_hotel_name, this.txt_hotel_email, this.txt_hotel_phone};


            if (Helper.isFieldListEmpty(selectedHotelList) || this.cmb_hotel_star.getSelectedIndex() == -1) {
                Helper.showMsg("fill");
                return;
            }
            else {}

            boolean result;

            this.hotel = this.hotelManager.getById(currentHotel.getHotel_id());

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

            result = this.hotelManager.update(this.hotel);

            if (result){
                Helper.showMsg("done");
                dispose();
            }
            else {
                Helper.showMsg("error");
            }
        });
    }

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
}