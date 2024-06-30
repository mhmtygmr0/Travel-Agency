package entity;

public class Room {
    private int room_id;
    private int hotel_id;
    private int pension_id;
    private int season_id;
    private RoomType room_type;
    private int stock;
    private double adult_price;
    private double child_price;
    private int bed_capacity;
    private int square_meter;
    private boolean television;
    private boolean minibar;
    private boolean game_console;
    private boolean cash_box;
    private boolean projection;
    private boolean gym;

    public enum RoomType {
        SINGLE_ROOM,
        DOUBLE_ROOM,
        JUNIOR_SUITE_ROOM,
        SUITE_ROOM
    }

    public Room(int room_id, int hotel_id, int pension_id, int season_id, RoomType room_type, int stock, double adult_price, double child_price, int bed_capacity, int square_meter, boolean television, boolean minibar, boolean game_console, boolean cash_box, boolean projection, boolean gym) {
        this.room_id = room_id;
        this.hotel_id = hotel_id;
        this.pension_id = pension_id;
        this.season_id = season_id;
        this.room_type = room_type;
        this.stock = stock;
        this.adult_price = adult_price;
        this.child_price = child_price;
        this.bed_capacity = bed_capacity;
        this.square_meter = square_meter;
        this.television = television;
        this.minibar = minibar;
        this.game_console = game_console;
        this.cash_box = cash_box;
        this.projection = projection;
        this.gym = gym;
    }

    public Room(){

    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public int getPension_id() {
        return pension_id;
    }

    public void setPension_id(int pension_id) {
        this.pension_id = pension_id;
    }

    public int getSeason_id() {
        return season_id;
    }

    public void setSeason_id(int season_id) {
        this.season_id = season_id;
    }

    public RoomType getRoom_type() {
        return room_type;
    }

    public void setRoom_type(RoomType room_type) {
        this.room_type = room_type;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getAdult_price() {
        return adult_price;
    }

    public void setAdult_price(double adult_price) {
        this.adult_price = adult_price;
    }

    public double getChild_price() {
        return child_price;
    }

    public void setChild_price(double child_price) {
        this.child_price = child_price;
    }

    public int getBed_capacity() {
        return bed_capacity;
    }

    public void setBed_capacity(int bed_capacity) {
        this.bed_capacity = bed_capacity;
    }

    public int getSquare_meter() {
        return square_meter;
    }

    public void setSquare_meter(int square_meter) {
        this.square_meter = square_meter;
    }

    public boolean isTelevision() {
        return television;
    }

    public void setTelevision(boolean television) {
        this.television = television;
    }

    public boolean isMinibar() {
        return minibar;
    }

    public void setMinibar(boolean minibar) {
        this.minibar = minibar;
    }

    public boolean isGame_console() {
        return game_console;
    }

    public void setGame_console(boolean game_console) {
        this.game_console = game_console;
    }

    public boolean isCash_box() {
        return cash_box;
    }

    public void setCash_box(boolean cash_box) {
        this.cash_box = cash_box;
    }

    public boolean isProjection() {
        return projection;
    }

    public void setProjection(boolean projection) {
        this.projection = projection;
    }

    public boolean isGym() {
        return gym;
    }

    public void setGym(boolean gym) {
        this.gym = gym;
    }

    @Override
    public String toString() {
        return "Room{" +
                "room_id=" + room_id +
                ", hotel_id=" + hotel_id +
                ", pension_id=" + pension_id +
                ", season_id=" + season_id +
                ", room_type=" + room_type +
                ", stock=" + stock +
                ", adult_price=" + adult_price +
                ", child_price=" + child_price +
                ", bed_capacity=" + bed_capacity +
                ", square_meter=" + square_meter +
                ", television=" + television +
                ", minibar=" + minibar +
                ", game_console=" + game_console +
                ", cash_box=" + cash_box +
                ", projection=" + projection +
                ", gym=" + gym +
                '}';
    }
}
