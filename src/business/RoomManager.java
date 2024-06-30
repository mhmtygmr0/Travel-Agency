package business;

import core.Helper;
import dao.RoomDao;
import entity.Room;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class RoomManager {
    RoomDao roomDao = new RoomDao();


    public Room getById(int id) {
        return this.roomDao.getByID(id);
    }


    public ArrayList<Room> findAll() {
        return this.roomDao.findAll();
    }

    public ArrayList<Room> getRoomByOtelId(int id) {
        return this.roomDao.getRoomByOtelId(id);
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Room> rooms) {
        ArrayList<Object[]> roomList = new ArrayList<>();
        for (Room obj : rooms) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getRoom_id();
            rowObject[i++] = obj.getHotel_id();
            rowObject[i++] = obj.getPension_id();
            rowObject[i++] = obj.getSeason_id();
            rowObject[i++] = obj.getRoom_type();
            rowObject[i++] = obj.getStock();
            rowObject[i++] = obj.getAdult_price();
            rowObject[i++] = obj.getChild_price();
            rowObject[i++] = obj.getBed_capacity();
            rowObject[i++] = obj.getSquare_meter();
            rowObject[i++] = obj.isTelevision();
            rowObject[i++] = obj.isMinibar();
            rowObject[i++] = obj.isGame_console();
            rowObject[i++] = obj.isCash_box();
            rowObject[i++] = obj.isProjection();
            roomList.add(rowObject);
        }
        return roomList;
    }


    public boolean save(Room room) {
        if (room.getRoom_id() != 0) {
            Helper.showMsg("error");
        }
        return this.roomDao.save(room);
    }


    public boolean updateStock(Room room) {
        if (this.getById(room.getRoom_id()) == null) {
            return false;
        }
        return this.roomDao.updateStock(room);
    }


    public boolean delete(int id) {
        if (this.getById(id) == null) {
            Helper.showMsg(id + " No registered hotel with ID found!");
            return false;
        }
        return this.roomDao.delete(id);
    }


    public boolean update(Room room) {
        if (this.getById(room.getRoom_id()) == null) {
            Helper.showMsg(room.getRoom_id() + " No registered hotel with ID found!");
            return false;
        }
        return this.roomDao.update(room);
    }


    public ArrayList<Room> searchForTable(String hotelName, String cityName, String checkinDate, String checkoutDate, String adultNum, String childNum) {
        String query = "SELECT * from public.room r " +
                "LEFT JOIN public.hotel h ON r.hotel_id = h.id " +
                "LEFT JOIN public.hotel_season s ON r.season_id = s.id WHERE";

        ArrayList<String> whereList = new ArrayList<>();

        whereList.add(" r.stock > " + 0);

        checkinDate = LocalDate.parse(checkinDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();
        checkoutDate = LocalDate.parse(checkoutDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();

        whereList.add(" AND s.start_date <= '" + checkinDate + "'");
        whereList.add(" AND s.end_date >='" + checkoutDate + "'");

        if (hotelName != null) {
            whereList.add(" AND h.name ILIKE '%" + hotelName + "%'");
        }
        if (cityName != null) {

            whereList.add(" AND h.city ILIKE '%" + cityName + "%'");

        }


        if (adultNum != null && !adultNum.isEmpty() && childNum != null && !childNum.isEmpty()) {
            try {
                int adultNumber = Integer.parseInt(adultNum);
                int childNumber = Integer.parseInt(childNum);
                int totalNumber = adultNumber + childNumber;
                whereList.add(" AND r.bed_capacity >= '" + (totalNumber) + "'");


            } catch (NumberFormatException e) {
                e.printStackTrace();

            }
            query += String.join("", whereList);
            System.out.println(query);
        }

        ArrayList<Room> queryResult = this.roomDao.selectByQuery(query);
        return queryResult;
    }
}