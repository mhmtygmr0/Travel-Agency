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

public class UpdateRoomView extends Layout {
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
    private JPanel container;
    private HotelManager hotelManager;
    private SeasonManager seasonManager;
    private PensionManager pensionManager;
    private RoomManager roomManager;
    private ComboItem comboItem;
    private PersonelView personelView = new PersonelView();
    private Hotel hotel;
    private Room room;
    private Season season;
    private int roomId;

    public UpdateRoomView(int roomId) {
        this.add(container);
        this.guiInitilize(700, 500);
        this.cmb_hotel.setEnabled(false);
        this.comboItem = new ComboItem();
        this.hotel = new Hotel();
        this.room = new Room();
        this.season = new Season();
        this.roomId = roomId;
        this.pensionManager = new PensionManager();
        this.seasonManager = new SeasonManager();
        this.hotelManager = new HotelManager();
        this.roomManager = new RoomManager();
        Room currentRoom = roomManager.getById(roomId);

        for (Hotel hotel : this.hotelManager.findAll()) {
            this.cmb_hotel.addItem(hotel.getHotel_name());
        }
        this.cmb_hotel.setSelectedItem(currentRoom.getHotel().getHotel_name());

        if (currentRoom.isCash_box()) {
            this.btn_moneybox.setSelected(true);
        }
        if (currentRoom.isGame_console()) {
            this.btn_console.setSelected(true);
        }
        if (currentRoom.isMinibar()) {
            this.btn_minibar.setSelected(true);
        }
        if (currentRoom.isProjection()) {
            this.btn_projection.setSelected(true);
        }
        if (currentRoom.isTelevision()) {
            this.btn_tv.setSelected(true);
        }

        ArrayList<Pension> pensions = this.pensionManager.getPensionByOtelId(currentRoom.getHotel_id());
        for (Pension pension : pensions) {
            this.cmb_pension.addItem(pension.getComboItem());
        }

        ArrayList<Season> seasons = this.seasonManager.getSeasonsByOtelId(currentRoom.getHotel_id());
        for (Season season : seasons) {
            this.cmb_season.addItem(season.getComboItem());
        }

        for (Room.RoomType type : Room.RoomType.values()) {
            this.cmb_type.addItem(type);
        }
        this.cmb_type.setSelectedItem(currentRoom.getRoom_type());


        this.cmb_hotel.setSelectedItem(currentRoom.getHotel().getHotel_name());
        this.cmb_pension.setSelectedItem(this.pensionManager.getPensionByOtelId(currentRoom.getHotel_id()));
        this.cmb_season.setSelectedItem(this.seasonManager.getById(currentRoom.getSeason_id()));
        this.cmb_type.setSelectedItem(currentRoom.getRoom_type());
        this.txt_stock.setText(String.valueOf(currentRoom.getStock()));
        this.txt_adult.setText(String.valueOf(currentRoom.getAdult_price()));
        this.txt_child.setText(String.valueOf(currentRoom.getChild_price()));
        this.txt_capacity.setText(String.valueOf(currentRoom.getBed_capacity()));
        this.txt_m2.setText(String.valueOf(currentRoom.getSquare_meter()));

        this.btn_save.addActionListener(e -> {

            JTextField[] selectedRoomList = new JTextField[]{this.txt_adult, this.txt_child, this.txt_capacity, this.txt_capacity, this.txt_m2};

            if (Helper.isFieldListEmpty(selectedRoomList) || this.cmb_type.getSelectedIndex() == -1 || this.cmb_pension.getSelectedIndex() == -1 || this.cmb_season.getSelectedIndex() == -1 || this.cmb_hotel.getSelectedIndex() == -1) {
                Helper.showMsg("fill");
            } else {
                boolean result;

                this.room = this.roomManager.getById(currentRoom.getRoom_id());

                ComboItem selectedPension = (ComboItem) this.cmb_pension.getSelectedItem();
                ComboItem selectedSeason = (ComboItem) this.cmb_season.getSelectedItem();

                this.room.setHotel_id(currentRoom.getHotel_id());
                this.room.setSeason_id(selectedSeason.getKey());
                this.room.setPension_id(selectedPension.getKey());
                Room.RoomType selectedRoomType = (Room.RoomType) this.cmb_type.getSelectedItem();
                this.room.setRoom_type(selectedRoomType);
                this.room.setStock(Integer.parseInt(this.txt_stock.getText()));
                this.room.setAdult_price(Double.parseDouble(txt_adult.getText()));
                this.room.setChild_price(Double.parseDouble(this.txt_child.getText()));
                this.room.setBed_capacity(Integer.parseInt(this.txt_capacity.getText()));
                this.room.setSquare_meter(Integer.parseInt(this.txt_m2.getText()));
                this.room.setTelevision(this.btn_tv.isSelected());
                this.room.setMinibar(this.btn_minibar.isSelected());
                this.room.setGame_console(this.btn_console.isSelected());
                this.room.setCash_box(this.btn_moneybox.isSelected());
                this.room.setProjection(this.btn_projection.isSelected());

                result = this.roomManager.update(this.room);

                if (result) {
                    dispose();
                    Helper.showMsg("done");
                } else {
                    Helper.showMsg("error");
                }
            }

        });
    }
}
