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

    public AddRoomView() {
        this.comboItem = new ComboItem();
        this.hotel = new Hotel();
        this.room = new Room();
        this.season = new Season();
        this.pensionManager = new PensionManager();
        this.seasonManager = new SeasonManager();
        this.hotelManager = new HotelManager();
        this.roomManager = new RoomManager();
        this.add(container);
        this.guiInitilize(500, 500);
        this.addCombobox();

        this.cmb_hotel.addActionListener(e -> {
            ComboItem selectedOtelItem = (ComboItem) this.cmb_hotel.getSelectedItem();
            int selectedOtelId = selectedOtelItem.getKey();
            ArrayList<Pension> pensions = this.pensionManager.getPensionByOtelId(((ComboItem) this.cmb_hotel.getSelectedItem()).getKey());
            this.cmb_pension.removeAllItems();

            for (Pension pension : pensions) {
                this.cmb_pension.addItem(pension.getComboItem());
            }

            ArrayList<Season> seasons = this.seasonManager.getSeasonsByOtelId(((ComboItem) this.cmb_hotel.getSelectedItem()).getKey());
            this.cmb_season.removeAllItems();

            for (Season season : seasons) {
                this.cmb_season.addItem(season.getComboItem());

            }

        });



        for (Room.RoomType type : Room.RoomType.values()) {
            ComboItem item = new ComboItem(type.toString(), type);
            this.cmb_type.addItem(item);
        }
        this.cmb_type.setSelectedIndex(-1);


        this.btn_save.addActionListener(e -> {

            ComboItem selectedTypeItem = (ComboItem) this.cmb_type.getSelectedItem();
            Room.RoomType selectedRoomType = (Room.RoomType) selectedTypeItem.getValue();

            // Room nesnesine atanması
            this.room.setRoom_type(selectedRoomType);

            JTextField[] selectedRoomList = new JTextField[]{this.txt_adult, this.txt_child, this.txt_capacity, this.txt_m2, this.txt_stock};

            if (Helper.isFieldListEmpty(selectedRoomList) || this.cmb_hotel.getSelectedIndex() == -1 || this.cmb_season.getSelectedIndex() == -1 || this.cmb_pension.getSelectedIndex() == -1 || this.cmb_type.getSelectedIndex() == -1) {
                Helper.showMsg("fill");
            } else {
                boolean result = false;
                ComboItem selectedHotel = (ComboItem) cmb_hotel.getSelectedItem();
                ComboItem selectedPension = (ComboItem) cmb_pension.getSelectedItem();
                ComboItem selectedSeason = (ComboItem) this.cmb_season.getSelectedItem();//: TODO hata veriyor
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

                if (room.getRoom_id() != 0) {
                    result = this.roomManager.update(this.room);
                } else {
                    result = this.roomManager.save(this.room);
                }
                if (result) {
                    Helper.showMsg("done");
                    dispose();
                } else {
                    Helper.showMsg("error");
                }
            }

        });
    }

    public void addCombobox() {
        for (Hotel hotel : this.hotelManager.findAll()) {
            this.cmb_hotel.addItem(hotel.getComboItem());
        }
        this.cmb_hotel.setSelectedIndex(-1);
    }

}