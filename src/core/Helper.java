package core;

import javax.swing.*;

/**
 * Helper sınıfı, genel yardımcı işlevleri içerir.
 * GUI uygulamalarında kullanışlı metodlar sağlar.
 */
public class Helper {

    /**
     * Uygulama için Nimbus görünüm ve hissini ayarlar.
     * Eğer Nimbus mevcut değilse, varsayılan görünüm ve his kullanılır.
     */
    public static void setTheme() {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    /**
     * Belirtilen anahtara göre bir mesaj iletişim kutusu gösterir.
     * Eğer anahtar önceden tanımlanmış durumlardan biriyse, karşılık gelen mesaj gösterilir.
     * Aksi takdirde, girdi olarak verilen dize mesaj olarak gösterilir.
     *
     * @param str gösterilecek mesajı belirlemek için kullanılan anahtar
     */
    public static void showMsg(String str) {
        String msg;
        String title = switch (str) {
            case "fill" -> {
                // Tüm alanları doldurun mesajı
                msg = "Please fill in all fields.";
                yield "Error!";
            }
            case "done" -> {
                // Başarılı mesajı
                msg = "Successful";
                yield "Result";
            }
            case "notFound" -> {
                // Bulunamadı mesajı
                msg = str + " Not found!";
                yield "Not found.";
            }
            case "error" -> {

                msg = "You Made a Wrong Transaction!";
                yield "Error!";
            }
            default -> {
                msg = str;
                yield "Message";
            }
        };

        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);


    }

    /**
     * Kullanıcıdan onay almak için bir iletişim kutusu gösterir.
     *
     * @param str Onay mesajı veya anahtar
     * @return Kullanıcının seçimine göre true (Evet), false (Hayır)
     */

    public static boolean confirm(String str) {
        String msg;
        if (str.equals("sure")) {
            // İşlemi yapmak istediğinizden emin misiniz? mesajı
            msg = "Are you sure you want to do this action ?";
        } else {
            msg = str;
        }
        return JOptionPane.showConfirmDialog(null, msg, "Are you sure ?", JOptionPane.YES_NO_OPTION) == 0;
    }

    /**
     * Bir metin alanının boş olup olmadığını kontrol eder.
     *
     * @param field kontrol edilecek metin alanı
     * @return metin alanı boşsa true, değilse false döner
     */
    public static boolean isFieldEmpty(JTextField field) {
        return field.getText().trim().isEmpty();
    }

    /**
     * Bir dizi metin alanının boş olup olmadığını kontrol eder.
     *
     * @param fieldList kontrol edilecek metin alanı dizisi
     * @return herhangi bir metin alanı boşsa true, değilse false döner
     */
    public static boolean isFieldListEmpty(JTextField[] fieldList) {
        for (JTextField field : fieldList) {
            if (isFieldEmpty(field)) {
                return true; // Herhangi bir alan boşsa true döner
            }
        }
        return false; // Tüm alanlar doluysa veya boş değilse false döner
    }
}
