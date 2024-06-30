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

    public AddSeasonView() {
        this.hotelManager = new HotelManager();
        this.hotel = new Hotel();
        this.seasonManager = new SeasonManager();
        this.season = new Season();
        this.cmb_hotel.getSelectedItem();
        this.add(container);
        this.guiInitilize(300, 400);


        for (Hotel hotel : this.hotelManager.findAll()) {
            this.cmb_hotel.addItem(hotel.getComboItem());
        }

        this.btn_save.addActionListener(e -> {
            boolean result = false;
            ComboItem selectSeason = (ComboItem) this.cmb_hotel.getSelectedItem();
            this.season.setHotel_id(selectSeason.getKey());
            this.season.setSeason_type(this.cmb_hotel.getSelectedItem().toString());
            this.season.setPrice_parameter(Double.parseDouble(this.txt_price_parameter.getText()));
            JFormattedTextField[] checkDateList = {this.txt_start_date, this.txt_end_date, this.txt_price_parameter};

            if (Helper.isFieldListEmpty(checkDateList)) {
                Helper.showMsg("fill");
                return;
            } else {
                try {

                    this.season.setStart_date(LocalDate.parse(this.txt_start_date.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    this.season.setEnd_date(LocalDate.parse(this.txt_end_date.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));

                    result = this.seasonManager.save(this.season);

                } catch (DateTimeException ex) {
                    Helper.showMsg("Date Format is Wrong !");
                    return;
                }
            }
            if (result) {
                Helper.showMsg("done");

                dispose();
            } else {
                Helper.showMsg("error");
            }
        });
    }


    private void createUIComponents() throws ParseException {
        this.txt_start_date = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.txt_start_date.setText("01/06/2024");
        this.txt_end_date = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.txt_end_date.setText("01/12/2024");
    }


}
