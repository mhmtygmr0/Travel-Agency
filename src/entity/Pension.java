package entity;

import core.ComboItem;

public class Pension {
    private int pension_id;
    private PensionType pension_type;
    private int hotel_id;

    public enum PensionType {
        ONLY_BED,
        ROOM_BREAKFAST,
        HALF_PENSION,
        FULL_PENSION,
        ULTRA_ALL_IN_ON,
        ALL_IN_ON,
        WITHOUT_ALCOHOL_FULL_CREDIT
    }

    public Pension() {
    }

    public Pension(int pension_id, PensionType pension_type, int hotel_id) {
        this.pension_id = pension_id;
        this.pension_type = pension_type;
        this.hotel_id = hotel_id;
    }

    public int getPension_id() {
        return pension_id;
    }

    public void setPension_id(int pension_id) {
        this.pension_id = pension_id;
    }

    public PensionType getPension_type() {
        return pension_type;
    }

    public void setPension_type(PensionType pension_type) {
        this.pension_type = pension_type;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public ComboItem getComboItem(){
        return new ComboItem(this.getPension_id(), "Pension ID : "+ this.getPension_id() + " Hotel ID : " + this.getHotel_id() + " Pension Type : " + this.getPension_type());
    }

    @Override
    public String toString() {
        return "Pension{" +
                "pension_id=" + pension_id +
                ", pension_type=" + pension_type +
                ", hotel_id=" + hotel_id +
                '}';
    }
}
