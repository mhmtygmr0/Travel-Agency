package dao;

import core.Db;
import entity.Reservation;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReservationDao {

    private final Connection connection;

    public ReservationDao() {
        this.connection = Db.getInstance();
    }

    public Reservation getByID(int reservation_id) {
        Reservation obj = null;
        String query = "SELECT * FROM public.reservation WHERE reservation_id = ?";

        try (PreparedStatement pr = this.connection.prepareStatement(query)) {
            pr.setInt(1, reservation_id);
            try (ResultSet rs = pr.executeQuery()) {
                if (rs.next()) {
                    obj = this.match(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public Reservation match(ResultSet rs) throws SQLException {
        Reservation obj = new Reservation();
        obj.setReservation_id(rs.getInt("reservation_id"));
        obj.setRoom_id(rs.getInt("room_id"));
        obj.setCheck_in_date(LocalDate.parse(rs.getString("check_in_date")));
        obj.setCheck_out_date(LocalDate.parse(rs.getString("check_out_date")));
        obj.setTotal_price(rs.getDouble("total_price"));
        obj.setGuest_count(rs.getInt("guest_count"));
        obj.setGuest_name(rs.getString("guest_name"));
        obj.setGuest_citizen_id(rs.getString("guest_citizen_id"));
        obj.setGuest_phone(rs.getString("guest_phone"));
        obj.setGuest_email(rs.getString("guest_email"));
        return obj;
    }

    public ArrayList<Reservation> findAll() {
        ArrayList<Reservation> resList = new ArrayList<>();
        String sql = "SELECT * FROM public.reservation";
        try (Statement st = this.connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                resList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resList;
    }

    public boolean save(Reservation reservation) {
        String query = "INSERT INTO public.reservation" +
                "(" +
                "room_id," +
                "check_in_date," +
                "check_out_date," +
                "total_price," +
                "guest_count," +
                "guest_name," +
                "guest_citizen_id," +
                "guest_phone," +
                "guest_email" +
                ")" +
                "VALUES (?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement pr = connection.prepareStatement(query)) {
            pr.setInt(1, reservation.getRoom_id());
            pr.setDate(2, Date.valueOf(reservation.getCheck_in_date()));
            pr.setDate(3, Date.valueOf(reservation.getCheck_out_date()));
            pr.setDouble(4, reservation.getTotal_price());
            pr.setInt(5, reservation.getGuest_count());
            pr.setString(6, reservation.getGuest_name());
            pr.setString(7, reservation.getGuest_citizen_id());
            pr.setString(8, reservation.getGuest_phone());
            pr.setString(9, reservation.getGuest_email());

            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean delete(int reservation_id) {
        String query = "DELETE FROM public.reservation WHERE reservation_id = ?";
        try (PreparedStatement pr = connection.prepareStatement(query)) {
            pr.setInt(1, reservation_id);
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public ArrayList<Reservation> getByListReservationId(int id) {
        return this.selectByQuery("SELECT * FROM public.reservation WHERE reservation_id=" + id);
    }

    public ArrayList<Reservation> selectByQuery(String query) {
        ArrayList<Reservation> resList = new ArrayList<>();
        try (Statement st = this.connection.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                resList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resList;
    }

    public boolean update(Reservation reservation) {
        String query = "UPDATE public.reservation SET " +
                "room_id = ?," +
                "check_in_date = ?," +
                "check_out_date = ?," +
                "total_price = ?," +
                "guest_count = ?," +
                "guest_name = ?," +
                "guest_citizen_id = ?," +
                "guest_phone = ?," +
                "guest_email = ?" +
                " WHERE reservation_id = ?";
        try (PreparedStatement pr = connection.prepareStatement(query)) {
            pr.setInt(1, reservation.getRoom_id());
            pr.setDate(2, Date.valueOf(reservation.getCheck_in_date()));
            pr.setDate(3, Date.valueOf(reservation.getCheck_out_date()));
            pr.setDouble(4, reservation.getTotal_price());
            pr.setInt(5, reservation.getGuest_count());
            pr.setString(6, reservation.getGuest_name());
            pr.setString(7, reservation.getGuest_citizen_id());
            pr.setString(8, reservation.getGuest_phone());
            pr.setString(9, reservation.getGuest_email());
            pr.setInt(10, reservation.getReservation_id());

            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

}
