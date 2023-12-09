
package components.userManager.ListFriend;

import components.userManager.LoginHistory.*;
import components.GroupChatList.ViewAllMember.*;
import components.GroupChatList.*;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class UserManagerListFriendTable extends JTable {
    
    ListSelectionModel selectionModel;
    public UserManagerListFriendTable()
    {
        setShowHorizontalLines(true);
        setGridColor(new Color(230,230,230));
        setBackground(Color.WHITE);
        setRowHeight(40);
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent (JTable jtable, Object o, boolean bln, boolean bln1,int i,int i1){
                 UserManagerListFriendTableHeader header = new UserManagerListFriendTableHeader(o + "");
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
