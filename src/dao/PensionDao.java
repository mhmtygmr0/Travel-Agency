package dao;

import core.Db;
import entity.Pension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PensionDao {
    private final Connection connection;

    public PensionDao() {
        this.connection = Db.getInstance();
    }

    public ArrayList<Pension> findAll() {
        ArrayList<Pension> pensionList = new ArrayList<>();
        String sql = "SELECT * FROM public.pension";
        try {
            ResultSet rs = this.connection.createStatement().executeQuery(sql);
            while (rs.next()) {

                pensionList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pensionList;
    }

    public ArrayList<Pension> getPensionByOtelId(int id) {
        ArrayList<Pension> pensions = new ArrayList<>();
        String query = "SELECT * FROM public.pension WHERE hotel_id = ?";

        try (PreparedStatement pr = connection.prepareStatement(query)) {
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();

            while (rs.next()) {
                Pension pension = match(rs);
                pensions.add(pension);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pensions;
    }

    public Pension getByID(int id) {
        Pension obj = null;
        String query = "SELECT * FROM public.pension WHERE pension_id = ? ";
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

    public boolean save(Pension pension) {
        String query = "INSERT INTO public.pension (hotel_id, pension_type) VALUES (?, ?)";
        try {
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setInt(1, pension.getHotel_id());  // hotel_id tamsayı olduğundan setInt() kullanıyoruz
            pr.setString(2, pension.getPension_type().toString());  // pension_type string olarak ekleniyor

            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean update(Pension pension) {
        try {
            String query = "UPDATE public.pension SET " +
                    "hotel_id = ?," +
                    "pension_type = ?" +
                    "WHERE pension_id = ?";

            PreparedStatement pr = connection.prepareStatement(query);
            pr.setInt(1, pension.getHotel_id());
            pr.setString(2, pension.getPension_type().toString());
            return pr.executeUpdate() != -1;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public boolean delete(int pension_id) {
        try {
            String query = "DELETE FROM public.pension WHERE pension_id = ?";
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setInt(1, pension_id);
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public Pension match(ResultSet rs) throws SQLException {
        Pension pension = new Pension();
        pension.setPension_id(rs.getInt("pension_id"));
        pension.setHotel_id(rs.getInt("hotel_id"));
        pension.setPension_type(Pension.PensionType.valueOf(rs.getString("pension_type")));


        return pension;
    }
}
