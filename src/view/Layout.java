package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Layout extends JFrame {


    /**
     * Belirtilen genişlik ve yükseklikle GUI penceresini başlatır.
     *
     * @param width  pencerenin genişliği
     * @param height pencerenin yüksekliği
     */
    public void guiInitilize(int width, int height) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Travel Agency");
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }


    /**
     * Bir tablo oluşturur ve verilen model, sütunlar ve satırlarla yapılandırır.
     *
     * @param model   tablo modelini temsil eden DefaultTableModel nesnesi
     * @param table   JTable nesnesi
     * @param columns sütun adlarını içeren Object dizisi
     * @param rows    satır verilerini içeren ArrayList<Object[]> nesnesi
     */
    public void createTable(DefaultTableModel model, JTable table, Object[] columns, ArrayList<Object[]> rows) {
        model.setColumnIdentifiers(columns);
        table.setModel(model);
        table.getTableHeader().setReorderingAllowed(false);
        table.getColumnModel().getColumn(0).setMaxWidth(75);

        DefaultTableModel clearModel = (DefaultTableModel) table.getModel();
        clearModel.setRowCount(0);

        if (rows == null) {
            rows = new ArrayList<>();
        }

        for (Object[] row : rows) {
            model.addRow(row);
        }
    }


    /**
     * Seçilen tablo satırını verilen sütun indeksine göre döndürür.
     *
     * @param table tablo nesnesi
     * @param index sütun indeksi
     * @return seçilen satırdaki belirtilen sütun değerini int olarak döner
     */
    public int getTableSelectedRow(JTable table, int index) {
        return Integer.parseInt(table.getValueAt(table.getSelectedRow(), index).toString());
    }


    /**
     * Tabloya fare tıklama dinleyicisi ekler ve tıklanan satırı seçili hale getirir.
     *
     * @param table tablo nesnesi
     */
    public void tableRowSelect(JTable table) {
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selected_row = table.rowAtPoint(e.getPoint());
                table.setRowSelectionInterval(selected_row, selected_row);
            }
        });
    }
}
