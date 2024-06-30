package view;

import javax.swing.*;

public class UpdateReservationView extends Layout {
    private JPanel container;
    private JRadioButton rb_car_park;
    private JRadioButton rb_spa;
    private JRadioButton rb_gym;
    private JRadioButton rb_fitness;
    private JRadioButton rb_tv;
    private JRadioButton rb_swimming;
    private JRadioButton rb_room_service;
    private JRadioButton rb_wifi;
    private JTextField txt_end_date;
    private JTextField txt_strt_date;
    private JTextField txt_bed_capacity;
    private JTextField txt_m2;
    private JRadioButton rb_game_console;
    private JRadioButton rb_projection;
    private JRadioButton rb_minibar;
    private JRadioButton rb_cash_safe;
    private JTextField txt_total_people;
    private JTextField txt_guest_name;
    private JTextField txt_guest_email;
    private JTextField txt_guest_id;
    private JTextField txt_guest_phone;
    private JTextField txt_guest_total_price;
    private JButton btn_save;
    private JTextField txt_hotel_name;
    private JTextField txt_hotel_adress;
    private JTextField txt_hotel_star;
    private JTextField txt_room_type;
    private JTextField txt_pension;

    public UpdateReservationView() {
        this.add(container);
        this.guiInitilize(800, 700);
    }
}