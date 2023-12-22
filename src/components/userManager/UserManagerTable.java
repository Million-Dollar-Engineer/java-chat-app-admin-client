
package components.userManager;

import Interface.User;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class UserManagerTable extends JTable {
    
    ListSelectionModel selectionModel;
    public UserManagerTable()
    {
        setShowHorizontalLines(true);
        setGridColor(new Color(230,230,230));
        setBackground(Color.WHITE);
        setRowHeight(40);
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent (JTable jtable, Object o, boolean bln, boolean bln1,int i,int i1){
                 UserManagerTableHeader header = new UserManagerTableHeader(o + "");
                 if(i1==1)
                 {
                     header.setHorizontalAlignment(JLabel.CENTER);
                 }
                 return header;
            }
        });
        setBorder(null);
        initComponents();
//        setDefaultRenderer(Object.class,new DefaultTableCellRenderer(){
//            @Override
//            public Component getTableCellRendererComponent (JTable jtable, Object o, boolean bln, boolean bln1,int i,int i1){
//                 if(i1!=4)
//                 {
//                    return super.getTableCellRendererComponent(jtable, o, bln1, bln1, i, i1);
//                 }
//                 return new JLabel("Testing");
//            }
//        });
    }
    
    public void clearData()
    {
        DefaultTableModel model = (DefaultTableModel) getModel();
        model.setRowCount(0);
    }
    public void addUserRow(User user)
    {
        addRow(new Object[]{user.username,user.password,user.fullname, user.address, user.dateOfBirth,user.sex,user.email,user.lastActive,user.status});
    }
    
    public void initComponents(){
        selectionModel = getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    public void addListener(ListSelectionListener listener)
    {
        selectionModel.addListSelectionListener(listener);
    }

    public void addRow(Object[] row)
    {
        DefaultTableModel model = (DefaultTableModel) getModel();
        model.addRow(row);
    }
}
