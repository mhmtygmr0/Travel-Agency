package core;

public class ComboItem {
    private int key;
    private String value;


    /**
     * Belirtilen anahtar ve değer ile ComboItem nesnesi oluşturur.
     *
     * @param key   öğenin anahtarı
     * @param value öğenin değeri
     */
    public ComboItem(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public ComboItem() {

    }

    /**
     * Anahtarı döndürür.
     *
     * @return öğenin anahtarı
     */
    public int getKey() {
        return key;
    }


    /**
     * Anahtarı ayarlar.
     *
     * @param key yeni anahtar değeri
     */
    public void setKey(int key) {
        this.key = key;
    }


    /**
     * Değeri döndürür.
     *
     * @return öğenin değeri
     */
    public String getValue() {
        return value;
    }


    /**
     * Değeri ayarlar.
     *
     * @param value yeni değer
     */
    public void setValue(String value) {
        this.value = value;
    }


    /**
     * Nesneyi temsil eden String değeri döndürür.
     *
     * @return öğenin değeri
     */
    @Override
    public String toString() {
        return value;
    }
}
