package view;

import business.*;
import core.ComboItem;
import core.Helper;
import dao.RoomDao;
import entity.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.event.*;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class PersonelView extends Layout {
    private JPanel container;
    private JPanel pnl_personel;
    private JPanel pnl_hotels;
    private JPanel pnl_room;
    private JPanel pnl_season;
    private JPanel pnl_pension;
    private JPanel pnl_menu_room;
    private JPanel pnl_menu2_room;
    private JTabbedPane tab_menu;
    private JLabel lbl_welcome;
    private JTable tbl_hotel;
    private JTable tbl_pension;
    private JTable tbl_season;
    private JTable tbl_room;
    private JTable tbl_reservation;
    private JTextField txt_adults;
    private JTextField txt_children;
    private JFormattedTextField txt_season_strt_date;
    private JFormattedTextField txt_season_fnsh_date;
    private JButton btn_room_search;
    private JButton btn_room_reset;
    private JButton btn_logout;
    private JButton btn_add_hotel;
    private JButton btn_add_pension;
    private JButton btn_add_season;
    private JButton btn_add_room;
    private JScrollPane scrl_hostel;
    private JScrollPane scrl_season;
    private JScrollPane scrl_room;
    private JScrollPane scrl_reservation;
    private JComboBox cmb_hotel_name;
    private JComboBox cmb_hotel_city;
    private DefaultTableModel tmdl_hotel = new DefaultTableModel();
    private DefaultTableModel tmdl_season = new DefaultTableModel();
    private DefaultTableModel tmdl_pension = new DefaultTableModel();
    private DefaultTableModel tmdl_room = new DefaultTableModel();
    private DefaultTableModel tmdl_reservation = new DefaultTableModel();
    private DefaultTableModel tmdl_room_reservation = new DefaultTableModel();
    private JPopupMenu hotel_menu;
    private JPopupMenu season_menu;
    private JPopupMenu pension_menu;
    private JPopupMenu room_menu;
    private JPopupMenu reservation_menu;
    private Object[] col_hotel;
    private Object[] col_room;
    private Object[] col_res_room;
    private User user;
    private Hotel hotel;
    private Season season;
    private Pension pension;
    private Room room;
    private Reservation reservation;
    private HotelManager hotelManager;
    private SeasonManager seasonManager;
    private PensionManager pensionManager;
    private RoomManager roomManager;
    private ReservationManager reservationManager;
    private int selectedHotelID;

    /**
     * PersonelView sınıfı için parametresiz kurucu metod.
     */
    public PersonelView() {
    }

    /**
     * PersonelView sınıfı için parametreli kurucu metod.
     *
     * @param user Kullanıcı nesnesi
     */
    public PersonelView(User user) {
        this.user = user;
        this.hotelManager = new HotelManager();
        this.seasonManager = new SeasonManager();
        this.pensionManager = new PensionManager();
        this.roomManager = new RoomManager();
        this.reservationManager = new ReservationManager();
        this.add(container);
        this.guiInitilize(1500, 750);
        this.lbl_welcome.setText("Welcome : " + this.user.getFirst_name() + " " + this.user.getLast_name());

        // Arayüz bileşenlerini yükle
        this.loadComponent();

        // Otel tablosunu yükle
        this.loadHotelTable();
        this.loadHotelComponent();

        // Sezon tablosunu yükle
        this.loadSeasonTable();
        this.loadSeasonComponent();

        // Pansiyon tablosunu yükle
        this.loadPensionTable();
        this.loadPensionComponent();

        // Oda tablosunu yükle
        this.loadRoomTable(null);
        this.loadRoomComponent();
        this.loadRoomFilter();

        // Rezervasyon tablolarını yükle
        this.loadReservationRoomTable(null);
        this.loadReservationTable(null);
        this.loadReservationTableComponent();

    }


    /**
     * Arayüz bileşenlerini yükler
     */
    public void loadComponent() {

        // Otelleri ve şehirleri yükle
        for (Hotel hotel : this.hotelManager.findAll()) {
            this.cmb_hotel_name.addItem(hotel.getComboItem());
            this.cmb_hotel_city.addItem(hotel.getHotel_address());
        }
        this.cmb_hotel_name.setSelectedIndex(-1);
        this.cmb_hotel_city.setSelectedIndex(-1);

        // Çıkış yap butonu
        this.btn_logout.addActionListener(e -> {
            dispose();
            LoginView loginView = new LoginView();
            loadHotelTable();
        });

        // Oda filtre sıfırlama butonu
        this.btn_room_reset.addActionListener(e -> {
            this.cmb_hotel_name.setSelectedIndex(-1);
            this.cmb_hotel_city.setSelectedIndex(-1);
            this.txt_adults.setText("");
            this.txt_children.setText("");
            this.loadRoomTable(null);
        });

    }

    /**
     * Oda filtresini yükler
     */
    public void loadRoomFilter() {
        this.btn_room_search.addActionListener(e -> {

            ComboItem selectedHotelItem = (ComboItem) cmb_hotel_name.getSelectedItem();
            String hotelName = (selectedHotelItem != null) ? selectedHotelItem.getValue() : null;
            Object selectedCityItem = cmb_hotel_city.getSelectedItem();
            String cityName = (selectedCityItem != null) ? selectedCityItem.toString() : null;
            String startDate = txt_season_strt_date.getText();
            String endDate = txt_season_fnsh_date.getText();

            // Oda listesini ara
            ArrayList<Room> rooms = this.roomManager.searchForTable(
                    hotelName,
                    cityName,
                    startDate,
                    endDate
            );

            ArrayList<Object[]> roomRow = this.roomManager.getForTable(this.col_room.length, rooms);
            loadRoomTable(roomRow);

        });
    }

    /**
     * Otel tablosunu yükler
     */
    public void loadHotelTable() {
        Object[] col_hotel = {"ID", "Name", "Adress", "Mail", "Telefon", "Star", "Car Park", "Wifi", "Pool", "Fitness", "Convention", "Spa", "Room Services"};
        ArrayList<Object[]> hotelList = this.hotelManager.getForTable(col_hotel.length, this.hotelManager.findAll());
        this.createTable(this.tmdl_hotel, this.tbl_hotel, col_hotel, hotelList);
    }

    /**
     * Sezon tablosunu yükler
     */
    public void loadSeasonTable() {
        Object[] col_season = {"ID", "Hotel ID", "Start Date", "Finish Date", "Price Parameter"};
        ArrayList<Object[]> seasonList = this.seasonManager.getForTable(col_season.length, this.seasonManager.findAll());
        this.createTable(this.tmdl_season, tbl_season, col_season, seasonList);
    }

    /**
     * Pansiyon tablosunu yükler
     */
    public void loadPensionTable() {
        Object[] col_pension = {"ID", "Hotel ID", "Pension Type"};
        ArrayList<Object[]> pensionList = this.pensionManager.getForTable(col_pension.length, this.pensionManager.findAll());
        this.createTable(this.tmdl_pension, this.tbl_pension, col_pension, pensionList);
    }

    /**
     * Oda tablosunu yükler
     *
     * @param roomList Oda listesi
     */
    public void loadRoomTable(ArrayList<Object[]> roomList) {
        col_room = new Object[]{"ID", "Hotel ID", "Pension ID", "Season ID", "Type", "Stock", "Adult Price", "Child Price", "Bed Capacity", "Square Meter", "Television", "Minibar", "Game Console", "Cash BOX", "Projection", "GYM"};
        if (roomList == null) {
            roomList = this.roomManager.getForTable(this.col_room.length, this.roomManager.findAll());
        }
        this.createTable(this.tmdl_room, this.tbl_room, this.col_room, roomList);
    }

    /**
     * Rezervasyon tablosunu yükler
     *
     * @param reservation Rezervasyon nesnesi
     */
    public void loadReservationTable(Reservation reservation) {
        Object[] col_res = {"ID", "Room ID", "Entry Date", "Finish Date", "Total Amount", "Guest Number", "Guest Name ", "Guest TC Id Number", "Mail", "Phone"};
        ArrayList<Object[]> resList = this.reservationManager.getForTable(col_res.length, this.reservationManager.findAll());
        createTable(this.tmdl_reservation, this.tbl_reservation, col_res, resList);
    }

    /**
     * Rezervasyonlu Oda tablosunu yükler
     *
     * @param roomList Oda listesi
     */
    public void loadReservationRoomTable(ArrayList<Object[]> roomList) {
        col_res_room = new Object[]{"ID", "Hotel ID", "Pension ID", "Season ID", "Type", "Stock", "Adult Price", "Child Price", "Bed Capacity", "Square Meter", "Television", "Minibar", "Game Console", "Cash BOX", "Projection"};
        if (roomList == null) {
            roomList = this.roomManager.getForTable(this.col_res_room.length, this.roomManager.getRoomByOtelId(this.selectedHotelID));
        }
        createTable(this.tmdl_room_reservation, this.tbl_reservation, this.col_res_room, roomList);
    }

    /**
     * Oteli yükler
     */
    public void loadHotelComponent() {
        this.tableRowSelect(this.tbl_hotel);

        this.btn_add_hotel.addActionListener(e -> {
            AddHotelView addHotelView = new AddHotelView();
            addHotelView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable();
                }
            });
        });

        this.hotel_menu = new JPopupMenu();

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
                    loadReservationTable(null);
                } else {
                    Helper.showMsg("error");
                }
            }
        });

        this.tbl_hotel.setComponentPopupMenu(hotel_menu);
    }

    /**
     * Sezonları yükler
     */
    public void loadSeasonComponent() {
        this.tableRowSelect(this.tbl_season);
        this.season_menu = new JPopupMenu();

        this.btn_add_season.addActionListener(e -> {
            AddSeasonView addSeasonView = new AddSeasonView();
            addSeasonView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadSeasonTable();
                }
            });
        });

        this.season_menu.add("Delete Season").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectBrandId = this.getTableSelectedRow(this.tbl_season, 0);
                if (this.seasonManager.delete(selectBrandId)) {
                    Helper.showMsg("done");
                    loadSeasonTable();
                    loadRoomTable(null);
                    loadReservationTable(null);

                } else {
                    Helper.showMsg("error");
                }
            }
        });

        this.tbl_season.setComponentPopupMenu(this.season_menu);
    }

    /**
     * Pansiyon yükler
     */
    public void loadPensionComponent() {
        this.tableRowSelect(tbl_pension);

        this.pension_menu = new JPopupMenu();

        this.btn_add_pension.addActionListener(e -> {
            PensionView pensionView = new PensionView();
            pensionView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadPensionTable();
                }
            });
        });

        this.pension_menu.add("Delete Pension").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectBrandId = this.getTableSelectedRow(tbl_pension, 0);
                if (this.pensionManager.delete(selectBrandId)) {
                    Helper.showMsg("done");
                    loadPensionTable();
                    loadRoomTable(null);
                    loadReservationTable(null);

                } else {
                    Helper.showMsg("error");
                }
            }
        });

        this.tbl_pension.setComponentPopupMenu(pension_menu);
    }

    /**
     * Oda yükler
     */
    public void loadRoomComponent() {
        this.tableRowSelect(tbl_room);
        this.room_menu = new JPopupMenu();

        this.txt_adults.setText("1");
        this.txt_children.setText("1");

        this.btn_add_room.addActionListener(e -> {
            AddRoomView addRoomView = new AddRoomView();
            addRoomView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadRoomTable(null);
                }
            });
        });

        this.room_menu.add("Add Reservation").addActionListener(e -> {
            int selectId = this.getTableSelectedRow(this.tbl_room, 0);

            JTextField[] roomJTextField = new JTextField[]{this.txt_season_strt_date, this.txt_season_fnsh_date, this.txt_adults, this.txt_children};

            if (Helper.isFieldListEmpty(roomJTextField)) {
                Helper.showMsg("fill");
            } else {
                int adult_numb = Integer.parseInt(this.txt_adults.getText());
                int child_numb = Integer.parseInt(this.txt_children.getText());
                AddReservationView reservationView = new AddReservationView(this.roomManager.getById(selectId), this.txt_season_strt_date.getText(), this.txt_season_fnsh_date.getText(), adult_numb, child_numb, null);
                reservationView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadRoomTable(null);
                        loadReservationTable(null);
                    }
                });
            }
        });

        this.room_menu.addSeparator();

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
                        loadReservationTable(null);
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
                    loadReservationTable(null);
                } else {
                    Helper.showMsg("error");
                }
            }
        });

        this.tbl_room.setComponentPopupMenu(this.room_menu);
    }

    /**
     * Rezervasyon yükler
     */
    public void loadReservationTableComponent() {
        tableRowSelect(this.tbl_reservation);
        this.reservation_menu = new JPopupMenu();

        this.reservation_menu.add("Update Reservation").addActionListener(e -> {
            int selectId = this.getTableSelectedRow(this.tbl_reservation, 0);
            Reservation selectReservation = this.reservationManager.getById(selectId);
            int selectRoomId = selectReservation.getRoom_id();
            Room selectRoom = this.roomManager.getById(selectRoomId);
            UpdateReservationView reservationView = new UpdateReservationView(selectRoom, selectReservation.getCheck_in_date().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), selectReservation.getCheck_out_date().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), selectReservation.getAdult_count(), selectReservation.getChild_count(), selectReservation);
            reservationView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadReservationTable(null);
                }
            });
        });

        this.reservation_menu.addSeparator();

        this.reservation_menu.add("Delete Reservation").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectResId = this.getTableSelectedRow(this.tbl_reservation, 0);
                int selectRoomId = this.reservationManager.getById(selectResId).getRoom_id();
                Room selectedRoom = this.roomManager.getById(selectRoomId);
                selectedRoom.setStock(selectedRoom.getStock() + 1);
                this.roomManager.updateStock(selectedRoom);
                if (this.reservationManager.delete(selectResId)) {
                    Helper.showMsg("done");
                    loadRoomTable(null);
                    loadReservationTable(null);
                    loadHotelTable();
                    loadPensionTable();
                } else {
                    Helper.showMsg("error");
                }
            }
        });

        this.tbl_reservation.setComponentPopupMenu(this.reservation_menu);
    }

    private void createUIComponents() throws ParseException {
        this.txt_season_strt_date = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.txt_season_strt_date.setText("01/07/2024");
        this.txt_season_fnsh_date = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.txt_season_fnsh_date.setText("01/08/2024");
    }
}