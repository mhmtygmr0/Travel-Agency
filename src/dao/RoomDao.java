package dao;

import core.Db;
import entity.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomDao {
    private final Connection connection;

    public RoomDao() {
        this.connection = Db.getInstance();
    }

    public ArrayList<Room> findAll() {
        ArrayList<Room> roomList = new ArrayList<>();
        String sql = "SELECT * FROM public.room";
        try {
            ResultSet rs = this.connection.createStatement().executeQuery(sql);
            while (rs.next()) {

                roomList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomList;
    }

    public ArrayList<Room> getRoomByOtelId(int id) {
        ArrayList<Room> rooms = new ArrayList<>();
        String query = "SELECT * FROM public.room WHERE hotel_id = ?";

        try (PreparedStatement pr = connection.prepareStatement(query)) {
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();

            while (rs.next()) {
                Room room = match(rs);
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rooms;
    }

    public Room getByID(int id) {
        Room obj = null;
        String query = "SELECT * FROM public.room WHERE room_id = ? ";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public Room match(ResultSet rs) throws SQLException {
        Room obj = new Room();
        obj.setRoom_id(rs.getInt("room_id"));
        obj.setHotel_id(rs.getInt("hotel_id"));
        obj.setPension_id(rs.getInt("pension_id"));
        obj.setSeason_id(rs.getInt("season_id"));
        obj.setRoom_type(Room.RoomType.valueOf(rs.getString("room_type")));
        obj.setStock(rs.getInt("stock"));
        obj.setAdult_price(rs.getDouble("adult_price"));
        obj.setChild_price(rs.getDouble("child_price"));
        obj.setBed_capacity(rs.getInt("bed_capacity"));
        obj.setSquare_meter(rs.getInt("square_meter"));
        obj.setTelevision(rs.getBoolean("television"));
        obj.setMinibar(rs.getBoolean("minibar"));
        obj.setGame_console(rs.getBoolean("game_console"));
        obj.setCash_box(rs.getBoolean("cash_box"));
        obj.setProjection(rs.getBoolean("projection"));
        obj.setGym(rs.getBoolean("gym"));
        return obj;
    }

    public boolean save(Room room) {
        String query = "INSERT INTO public.room" +
                "(" +
                "hotel_id," +
                "pension_id," +
                "season_id," +
                "room_type," +
                "stock," +
                "adult_price," +
                "child_price," +
                "bed_capacity," +
                "square_meter," +
                "television," +
                "minibar," +
                "game_console," +
                "cash_box," +
                "projection, " +
                "gym" +
                ")" +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setInt(1, room.getHotel_id());
            pr.setInt(2, room.getPension_id());
            pr.setInt(3, room.getSeason_id());
            pr.setString(4, room.getRoom_type().toString());
            pr.setInt(5, room.getStock());
            pr.setDouble(6, room.getAdult_price());
            pr.setDouble(7, room.getChild_price());
            pr.setInt(8, room.getBed_capacity());
            pr.setInt(9, room.getSquare_meter());
            pr.setBoolean(10, room.isTelevision());
            pr.setBoolean(11, room.isMinibar());
            pr.setBoolean(12, room.isGame_console());
            pr.setBoolean(13, room.isCash_box());
            pr.setBoolean(14, room.isProjection());
            pr.setBoolean(15, room.isGym());
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public ArrayList<Room> selectByQuery(String query) {
        ArrayList<Room> roomList = new ArrayList<>();
        try {
            ResultSet rs = this.connection.createStatement().executeQuery(query);
            while (rs.next()) {
                roomList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomList;
    }


    public boolean updateStock(Room room) {
        String query = "UPDATE public.room SET stock = ? WHERE id = ? ";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1, room.getStock());
            pr.setInt(2, room.getRoom_id());

            pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }


    public boolean delete(int room_id) {
        try {
            String query = "DELETE FROM public.room WHERE room_id = ?";
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setInt(1, room_id);
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }


    // Oda bilgilerini güncelleyen metot
    public boolean update(Room room) {
        try {
            String query = "UPDATE public.room SET " +
                    "hotel_id = ?," +
                    "pension_id = ?," +
                    "season_id= ?," +
                    "room_type= ?," +
                    "stock= ?," +
                    "adult_price = ?," +
                    "child_price = ?," +
                    "bed_capacity = ?," +
                    "square_meter = ?," +
                    "television = ?," +
                    "minibar = ?," +
                    "game_console = ?," +
                    "cash_box = ?," +
                    "projection = ?," +
                    "gym = ?" +
                    " WHERE id = ?";

            PreparedStatement pr = connection.prepareStatement(query);
            pr.setInt(1, room.getHotel_id());
            pr.setInt(2, room.getPension_id());
            pr.setInt(3, room.getSeason_id());
            pr.setString(4, room.getRoom_type().toString());
            pr.setInt(5, room.getStock());
            pr.setDouble(6, room.getAdult_price());
            pr.setDouble(7, room.getChild_price());
            pr.setInt(8, room.getBed_capacity());
            pr.setInt(9, room.getSquare_meter());
            pr.setBoolean(10, room.isTelevision());
            pr.setBoolean(11, room.isMinibar());
            pr.setBoolean(12, room.isGame_console());
            pr.setBoolean(13, room.isCash_box());
            pr.setBoolean(14, room.isProjection());
            pr.setBoolean(15, room.isGym());
            pr.setInt(16, room.getRoom_id());
            return pr.executeUpdate() != -1;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }
}