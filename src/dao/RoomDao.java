package dao;

import core.Db;
import entity.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class RoomDao {
    private final Connection connection;

    public RoomDao() {
        this.connection = Db.getInstance();
    }

    public ArrayList<Room> findAll() {
        ArrayList<Room> roomList = new ArrayList<>();
        String sql = "SELECT * FROM public.room ORDER BY room_id";
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

    public ArrayList<Room> searchForTable(String hotelName, String cityName, String startDate, String endDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        ArrayList<Room> roomList = new ArrayList<>();
        try {

            LocalDate parsedStartDate = (startDate != null && !startDate.isEmpty()) ? LocalDate.parse(startDate, formatter) : null;
            LocalDate parsedEndDate = (endDate != null && !endDate.isEmpty()) ? LocalDate.parse(endDate, formatter) : null;

            StringBuilder queryBuilder = new StringBuilder("SELECT * FROM public.room AS r ")
                    .append("LEFT JOIN public.hotel AS h ON r.hotel_id = h.hotel_id ")
                    .append("LEFT JOIN public.pension AS p ON r.pension_id = p.pension_id ")
                    .append("LEFT JOIN public.season AS s ON r.season_id = s.season_id WHERE 1=1");

            if (hotelName != null && !hotelName.isEmpty()) {
                queryBuilder.append(" AND h.hotel_name = '").append(hotelName).append("'");
            }
            if (cityName != null && !cityName.isEmpty()) {
                queryBuilder.append(" AND h.hotel_address = '").append(cityName).append("'");
            }
            if (parsedStartDate != null) {
                queryBuilder.append(" AND s.start_date >= '").append(parsedStartDate.toString()).append("'");
            }
            if (parsedEndDate != null) {
                queryBuilder.append(" AND s.end_date <= '").append(parsedEndDate.toString()).append("'");
            }

            String query = queryBuilder.toString();
            ResultSet rs = this.connection.createStatement().executeQuery(query);
            while (rs.next()) {
                roomList.add(this.match(rs));
            }
        } catch (SQLException | DateTimeParseException e) {
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

    public boolean updateStock(Room room) {
        String query = "UPDATE public.room SET stock = ? WHERE room_id = ? ";
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
                    " WHERE room_id = ?";

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
