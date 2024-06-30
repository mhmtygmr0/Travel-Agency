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

    public PensionView(int hotel_id) {
        this.hotel = new Hotel();
        this.hotelManager = new HotelManager();
        this.pensionManager = new PensionManager();
        this.pension = new Pension();
        this.add(container);
        this.guiInitilize(300, 300);
        this.addCombobox();

        this.btn_save.addActionListener(e -> {
            if (this.cmb_hotel.getSelectedIndex() == -1 || this.cmb_pension.getSelectedIndex() == -1) {
                Helper.showMsg("fill");

            } else {
                boolean result = false;
                ComboItem selectedHotel = (ComboItem) cmb_hotel.getSelectedItem();
                this.pension.setHotel_id(selectedHotel.getKey());
                this.pension.setPension_type(Pension.PensionType.valueOf(cmb_pension.getSelectedItem().toString()));

                if (this.pension.getPension_id() != 0) {
                    result = this.pensionManager.update(this.pension);

                } else {
                    result = this.pensionManager.save(this.pension);
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

        for (Pension.PensionType type : Pension.PensionType.values()) {
            this.cmb_pension.addItem(type.toString());
        }
        this.cmb_pension.setSelectedIndex(-1);
    }

}