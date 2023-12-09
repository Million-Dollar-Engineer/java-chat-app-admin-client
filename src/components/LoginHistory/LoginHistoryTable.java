package components.LoginHistory;

import components.userManager.*;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class LoginHistoryTable extends JTable {

    ListSelectionModel selectionModel;

    public LoginHistoryTable() {
        
        setShowHorizontalLines(true);
        setGridColor(new Color(230, 230, 230));
        setBackground(Color.WHITE);
        setRowHeight(40);
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                UserManagerTableHeader header = new UserManagerTableHeader(o + "");
                if (i1 == 1) {
                    header.setHorizontalAlignment(JLabel.CENTER);
                }
                return header;
            }
        });
        setBorder(null);
        initComponents();
    }

    public void initComponents() {
        selectionModel = getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public void addListener(ListSelectionListener listener) {
        selectionModel.addListSelectionListener(listener);
    }

    public void addRow(Object[] row) {
        DefaultTableModel model = (DefaultTableModel) getModel();
        model.addRow(row);
    }
}
