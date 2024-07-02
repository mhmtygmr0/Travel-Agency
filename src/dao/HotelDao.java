package dao;

import core.Db;
import entity.Hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HotelDao {
    private final Connection connection;

    public HotelDao() {
        this.connection = Db.getInstance();
    }

    public ArrayList<Hotel> findAll() {
        ArrayList<Hotel> hotelList = new ArrayList<>();
        String quary = "SELECT * FROM public.hotel ORDER BY hotel_id";

        try {
            ResultSet rs = this.connection.createStatement().executeQuery(quary);
            while (rs.next()) {
                hotelList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotelList;
    }

    // Belirli bir ID'ye sahip oteli getiren metot
    public Hotel getByID(int id) {
        Hotel obj = null;
        String query = "SELECT * FROM public.hotel WHERE hotel_id = ? ";
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

    // Otel ekleyen metot
    public boolean save(Hotel hotel) {
        String query = "INSERT INTO public.hotel " +
                "(hotel_name, hotel_address, hotel_email, hotel_phone, hotel_star, car_park, wifi, pool, fitness, concierge, spa, room_service) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setString(1, hotel.getHotel_name());
            pr.setString(2, hotel.getHotel_address());
            pr.setString(3, hotel.getHotel_email());
            pr.setString(4, hotel.getHotel_phone());
            pr.setString(5, hotel.getHotel_star());
            pr.setBoolean(6, hotel.isHotel_car_park());
            pr.setBoolean(7, hotel.isHotel_wifi());
            pr.setBoolean(8, hotel.isHotel_pool());
            pr.setBoolean(9, hotel.isHotel_fitness());
            pr.setBoolean(10, hotel.isHotel_concierge());
            pr.setBoolean(11, hotel.isHotel_spa());
            pr.setBoolean(12, hotel.isHotel_room_service());
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    // Otel silen metot
    public boolean delete(int id) {
        String query = "DELETE FROM public.hotel WHERE hotel_id = ?";
        try (PreparedStatement pr = connection.prepareStatement(query)) {
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Otel güncelleyen metot
    public boolean update(Hotel hotel) {
        try {
            String query = "UPDATE public.hotel SET " +
                    "hotel_name=?, hotel_address=?, hotel_email=?, hotel_phone=?, hotel_star=?, car_park=?, wifi=?, " +
                    "pool=?, fitness=?, concierge=?, spa=?, room_service=? " +
                    "WHERE hotel_id = ?";
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setString(1, hotel.getHotel_name());
            pr.setString(2, hotel.getHotel_address());
            pr.setString(3, hotel.getHotel_email());
            pr.setString(4, hotel.getHotel_phone());
            pr.setString(5, hotel.getHotel_star());
            pr.setBoolean(6, hotel.isHotel_car_park());
            pr.setBoolean(7, hotel.isHotel_wifi());
            pr.setBoolean(8, hotel.isHotel_pool());
            pr.setBoolean(9, hotel.isHotel_fitness());
            pr.setBoolean(10, hotel.isHotel_concierge());
            pr.setBoolean(11, hotel.isHotel_spa());
            pr.setBoolean(12, hotel.isHotel_room_service());
            pr.setInt(13, hotel.getHotel_id());
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public Hotel match(ResultSet rs) throws SQLException {
        Hotel hotel = new Hotel();
        hotel.setHotel_id(rs.getInt("hotel_id"));
        hotel.setHotel_name(rs.getString("hotel_name"));
        hotel.setHotel_address(rs.getString("hotel_address"));
        hotel.setHotel_email(rs.getString("hotel_email"));
        hotel.setHotel_phone(rs.getString("hotel_phone"));
        hotel.setHotel_star(rs.getString("hotel_star"));
        hotel.setHotel_car_park(rs.getBoolean("car_park"));
        hotel.setHotel_wifi(rs.getBoolean("wifi"));
        hotel.setHotel_pool(rs.getBoolean("pool"));
        hotel.setHotel_fitness(rs.getBoolean("fitness"));
        hotel.setHotel_concierge(rs.getBoolean("concierge"));
        hotel.setHotel_spa(rs.getBoolean("spa"));
        hotel.setHotel_room_service(rs.getBoolean("room_service"));
        return hotel;
    }
}
