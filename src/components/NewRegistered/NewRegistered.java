/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package components.NewRegistered;

import components.SpamAndReport.*;
import components.GroupChatList.ViewAllMember.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author lnt09
 */
public class NewRegistered extends javax.swing.JPanel {

    /**
     * Creates new form GroupChatListAllMember
     */
    public NewRegistered() {
        initComponents();

        // Add fake data
        for (int i = 0; i < 50; i++) {
            newRegisteredTable.addRow(new Object[]{"lenguyenthai123", "Lê Nguyên Thái","lnt0995449235@gmail.com","2023/11/02","Active"});
        }

//        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
//        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
//        newRegisteredTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
//
//        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
//        leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);
//        newRegisteredTable.getColumnModel().getColumn(1).setCellRenderer(leftRenderer);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        newRegisteredSearching1 = new components.NewRegistered.NewRegisteredSearching();
        jScrollPane1 = new javax.swing.JScrollPane();
        newRegisteredTable = new components.NewRegistered.NewRegisteredTable();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));
        jLabel1.setText("New Registered");

        jScrollPane1.setBorder(null);

        newRegisteredTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User Name", "Full Name", "Email", "Registration Date", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(newRegisteredTable);
        if (newRegisteredTable.getColumnModel().getColumnCount() > 0) {
            newRegisteredTable.getColumnModel().getColumn(1).setMinWidth(230);
            newRegisteredTable.getColumnModel().getColumn(1).setPreferredWidth(230);
            newRegisteredTable.getColumnModel().getColumn(1).setMaxWidth(230);
            newRegisteredTable.getColumnModel().getColumn(2).setMinWidth(230);
            newRegisteredTable.getColumnModel().getColumn(2).setPreferredWidth(230);
            newRegisteredTable.getColumnModel().getColumn(2).setMaxWidth(230);
            newRegisteredTable.getColumnModel().getColumn(3).setMinWidth(150);
            newRegisteredTable.getColumnModel().getColumn(3).setPreferredWidth(150);
            newRegisteredTable.getColumnModel().getColumn(3).setMaxWidth(150);
            newRegisteredTable.getColumnModel().getColumn(4).setMinWidth(150);
            newRegisteredTable.getColumnModel().getColumn(4).setPreferredWidth(150);
            newRegisteredTable.getColumnModel().getColumn(4).setMaxWidth(150);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(newRegisteredSearching1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(newRegisteredSearching1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        super.paintComponent(grphcs);
    }

    private String getStringValue(Object value) {
        if (value instanceof String) {
            return (String) value;
        } else {
            return "";  // Hoặc xử lý giá trị không phải chuỗi theo cách khác nếu cần thiết
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private components.NewRegistered.NewRegisteredSearching newRegisteredSearching1;
    private components.NewRegistered.NewRegisteredTable newRegisteredTable;
    // End of variables declaration//GEN-END:variables
}
