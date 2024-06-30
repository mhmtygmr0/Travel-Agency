package view;

import business.HotelManager;
import business.PensionManager;
import business.RoomManager;
import business.SeasonManager;
import core.Helper;
import entity.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.event.*;
import java.text.ParseException;
import java.util.ArrayList;

public class PersonelView extends Layout {
    private JPanel container;
    private JTabbedPane tab_menu;
    private JTable tbl_hotel;
    private JTable tbl_pension;
    private JTable tbl_season;
    private JTable tbl_room;
    private JTextField txt_adults;
    private JTextField txt_children;
    private JButton btn_room_search;
    private JTextField txt_hotel_name;
    private JTextField txt_hotel_city;
    private JFormattedTextField txt_hotel_strt_date;
    private JFormattedTextField txt_hotel_fnsh_date;
    private JButton btn_room_reset;
    private JTable tbl_reservation;
    private JButton btn_logout;
    private JLabel lbl_welcome;
    private JPanel pnl_personel;
    private JPanel pnl_hotels;
    private JPanel pnl_room;
    private JPanel pnl_booking;
    private JScrollPane scrl_hostel;
    private JScrollPane scrl_season;
    private JPanel pnl_season;
    private JPanel pnl_pension;
    private JScrollPane scrl_room;
    private JPanel pnl_menu_room;
    private JPanel pnl_menu2_room;
    private JScrollPane scrl_booking;
    private JPanel pnl_manu_booking;
    private DefaultTableModel tmdl_hotel = new DefaultTableModel();
    private DefaultTableModel tmdl_season = new DefaultTableModel();
    private DefaultTableModel tmdl_pension = new DefaultTableModel();
    private DefaultTableModel tmdl_room = new DefaultTableModel();
    private JPopupMenu hotel_menu;
    private JPopupMenu season_menu;
    private JPopupMenu pension_menu;
    private JPopupMenu room_menu;
    private Object[] col_hotel;
    private Object[] col_room;
    private Object[] col_res_room;
    private User user;
    private Hotel hotel;
    private Season season;
    private Pension pension;
    private Room room;
    private HotelManager hotelManager;
    private SeasonManager seasonManager;
    private PensionManager pensionManager;
    private RoomManager roomManager;
    private int selectedHotelID;
    private int selectedRoomID;

    public PersonelView(User user) {
        this.user = user;
        this.hotelManager = new HotelManager();
        this.seasonManager = new SeasonManager();
        this.pensionManager = new PensionManager();
        this.roomManager = new RoomManager();
        this.add(container);
        this.guiInitilize(1400, 750);
        this.lbl_welcome.setText("Welcome : " + this.user.getFirst_name() + " " + this.user.getLast_name());

        this.loadHotelTable();
        this.loadHotelComponent();

        this.loadSeasonTable();
        this.loadSeasonComponent();

        this.loadPensionTable();
        this.loadPensionComponent();

        this.loadRoomTable(null);
        this.loadRoomComponent();
    }

    public void buttons() {
        btn_room_reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //loadReservationRoomTable(null);
            }
        });


        btn_room_search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField[] roomJTextField = new JTextField[]{txt_adults, txt_children};
                if (Helper.isFieldListEmpty(roomJTextField)) {// Filtreleme aşamasında çocuk veya yetişkin sayısını girmezse hata mesajı ver
                    Helper.showMsg("Please enter the number of children and adults");
                } else {
                    int selectedAdult = Integer.parseInt(txt_adults.getText());
                    int selectedChild = Integer.parseInt(txt_children.getText());

                    if (selectedAdult < 0 || selectedChild < 0) {
                        Helper.showMsg("Please enter the number of children and adults greater than 0");
                    }
                    ArrayList<Room> roomList = roomManager.searchForTable(
                            txt_hotel_name.getText(),
                            txt_hotel_city.getText(),
                            txt_hotel_strt_date.getText(),
                            txt_hotel_fnsh_date.getText(),
                            txt_adults.getText(),
                            txt_children.getText()
                    );

                    ArrayList<Object[]> searchResult = roomManager.getForTable(col_res_room.length, roomList);
                    //loadReservationRoomTable(searchResult);

                }
            }

        });

        btn_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginView loginView = new LoginView();
                loadHotelTable();
                dispose();
            }
        });

        tbl_hotel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tbl_hotel.getSelectedRow();
                if (selectedRow != -1) {

                    selectedHotelID = (int) tmdl_hotel.getValueAt(selectedRow, 0);
                    loadSeasonTable();
                    loadPensionTable();
                    loadRoomTable(null);
                }
            }
        });

    }

    public void loadHotelTable() {
        Object[] col_hotel = {"ID", "Name", "Adress", "Mail", "Telefon", "Star", "Car Park", "Wifi", "Pool", "Fitness", "Convention", "Spa", "Room Services"};
        ArrayList<Object[]> hotelList = this.hotelManager.getForTable(col_hotel.length, this.hotelManager.findAll());
        createTable(this.tmdl_hotel, this.tbl_hotel, col_hotel, hotelList);
    }

    public void loadSeasonTable() {
        Object[] col_season = {"ID", "Hotel ID", "Start Date", "Finish Date", "Price Parameter"};
        ArrayList<Object[]> seasonList = this.seasonManager.getForTable(col_season.length, this.seasonManager.findAll());
        createTable(this.tmdl_season, tbl_season, col_season, seasonList);
    }

    public void loadPensionTable() {
        Object[] col_pension = {"ID", "Hotel ID", "Pension Type"};
        ArrayList<Object[]> pensionList = this.pensionManager.getForTable(col_pension.length, this.pensionManager.findAll());
        createTable(this.tmdl_pension, tbl_pension, col_pension, pensionList);
    }

    public void loadRoomTable(ArrayList<Object[]> roomList) {
        col_room = new Object[]{"ID", "Hotel ID", "Pension ID", "Season ID", "Type", "Stock", "Adult Price", "Child Price", "Bed Capacity", "Square Meter", "Television", "Minibar", "Game Console", "Cash BOX", "Projection", "GYM"};
        if (roomList == null) {
            roomList = this.roomManager.getForTable(this.col_room.length, this.roomManager.findAll());
        }
        createTable(this.tmdl_room, this.tbl_room, this.col_room, roomList);
    }

    public void loadHotelComponent() {
        tableRowSelect(this.tbl_hotel);
        tableRowSelect(this.tbl_pension);
        tableRowSelect(this.tbl_room);
        tableRowSelect(this.tbl_season);

        this.hotel_menu = new JPopupMenu();

        this.hotel_menu.add("Add Hotel").addActionListener(e -> {
            AddHotelView addHotelView = new AddHotelView();
            addHotelView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable();
                }
            });
        });

        this.hotel_menu.add("Add Season").addActionListener(e -> {
            AddSeasonView addSeasonView = new AddSeasonView();
            addSeasonView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadSeasonTable();
                }
            });
        });

        this.hotel_menu.add("Add Room").addActionListener(e -> {
            AddRoomView addRoomView = new AddRoomView();
            addRoomView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadRoomTable(null);
                }
            });
        });

        this.hotel_menu.add("Add Pension").addActionListener(e -> {
            int selectId = this.getTableSelectedRow(tbl_hotel, 0);
            PensionView pensionView = new PensionView(selectId);
            pensionView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadPensionTable();
                }
            });
        });

        this.hotel_menu.addSeparator();

        this.hotel_menu.add("Update Hotel").addActionListener(e -> {
            int selectedRow = this.tbl_hotel.getSelectedRow();
            if (selectedRow != -1) {
                int selectedHotelId = (int) tmdl_hotel.getValueAt(selectedRow, 0);
                loadHotelTable();
                UpdateHotelView updateHotelView = new UpdateHotelView(selectedHotelId);
                updateHotelView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadHotelTable();
                    }
                });
            }
        });

        this.hotel_menu.addSeparator();

        this.hotel_menu.add("Delete Hotel").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectBrandId = this.getTableSelectedRow(tbl_hotel, 0);
                if (this.hotelManager.delete(selectBrandId)) {
                    Helper.showMsg("done");
                    loadHotelTable();
                    loadPensionTable();
                    loadRoomTable(null);
                    loadSeasonTable();
                } else {
                    Helper.showMsg("error");
                }
            }
        });

        this.tbl_hotel.setComponentPopupMenu(hotel_menu);
    }

    public void loadSeasonComponent() {
        tableRowSelect(this.tbl_season);
        this.season_menu = new JPopupMenu();

        this.season_menu.add("Delete Season").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectBrandId = this.getTableSelectedRow(this.tbl_season, 0);
                if (this.seasonManager.delete(selectBrandId)) {
                    Helper.showMsg("done");
                    loadSeasonTable();

                } else {
                    Helper.showMsg("error");
                }
            }
        });

        this.tbl_season.setComponentPopupMenu(this.season_menu);
    }

    public void loadPensionComponent() {
        tableRowSelect(tbl_pension);

        this.pension_menu = new JPopupMenu();

        this.pension_menu.add("Delete Pension").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectBrandId = this.getTableSelectedRow(tbl_pension, 0);
                if (this.pensionManager.delete(selectBrandId)) {
                    Helper.showMsg("done");
                    loadPensionTable();


                } else {
                    Helper.showMsg("error");
                }
            }
        });

        this.tbl_pension.setComponentPopupMenu(pension_menu);
    }

    public void loadRoomComponent() {
        tableRowSelect(tbl_room);
        this.room_menu = new JPopupMenu();

        this.room_menu.add("Update Room").addActionListener(e -> {
            int selectedRow = tbl_room.getSelectedRow();
            if (selectedRow != -1) {
                int selectedRoomID = (int) tmdl_room.getValueAt(selectedRow, 0);
                loadRoomTable(null);
                UpdateRoomView updateRoomView = new UpdateRoomView(selectedRoomID);
                updateRoomView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadRoomTable(null);
                    }
                });
            }
        });

        this.room_menu.addSeparator();

        this.room_menu.add("Delete Room").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectBrandId = this.getTableSelectedRow(tbl_room, 0);
                if (this.roomManager.delete(selectBrandId)) {
                    Helper.showMsg("done");
                    loadRoomTable(null);
                    loadHotelTable();
                    loadPensionTable();
                } else {
                    Helper.showMsg("error");
                }
            }
        });
        this.tbl_room.setComponentPopupMenu(room_menu);
    }

    private void createUIComponents() throws ParseException {
        this.txt_hotel_strt_date = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.txt_hotel_strt_date.setText("01/01/2024");
        this.txt_hotel_fnsh_date = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.txt_hotel_fnsh_date.setText("01/05/2024");
    }
}