package view;

import javax.swing.*;

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

    public UpdateRoomView(int roomId) {
        this.add(container);
        this.guiInitilize(500, 500);
    }
}
