/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package components.GroupChatList.ViewAllAdmin;

import components.GroupChatList.ViewAllMember.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author lnt09
 */
public class GroupChatListAllAdmin extends javax.swing.JPanel {

    /**
     * Creates new form GroupChatListAllMember
     */
    private String groupName;
    public GroupChatListAllAdmin(String name) {        
        initComponents();
        groupNameText.setText(name);
        // Add fake data
        for (int i = 0; i < 50; i++) {
            groupChatListAllAdminTable.addRow(new Object[]{"lenguyenthai123", "Lê Nguyên Thái", "Admin", "2023/12/6"});
        }

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        groupChatListAllAdminTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        previousPageGroupChat = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        groupChatListAllAdminTable = new components.GroupChatList.ViewAllAdmin.GroupChatListAllAdminTable();
        groupNameText = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        previousPageGroupChat.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        previousPageGroupChat.setForeground(new java.awt.Color(127, 127, 127));
        previousPageGroupChat.setText("Group Chat");
        previousPageGroupChat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        previousPageGroupChat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                previousPageGroupChatMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));
        jLabel1.setText("/ Admin List");

        jScrollPane2.setBorder(null);

        groupChatListAllAdminTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User Name", "Full Name", "Role", "Participate Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(groupChatListAllAdminTable);
        if (groupChatListAllAdminTable.getColumnModel().getColumnCount() > 0) {
            groupChatListAllAdminTable.getColumnModel().getColumn(0).setMinWidth(250);
            groupChatListAllAdminTable.getColumnModel().getColumn(0).setPreferredWidth(250);
            groupChatListAllAdminTable.getColumnModel().getColumn(0).setMaxWidth(250);
            groupChatListAllAdminTable.getColumnModel().getColumn(1).setMinWidth(250);
            groupChatListAllAdminTable.getColumnModel().getColumn(1).setPreferredWidth(250);
            groupChatListAllAdminTable.getColumnModel().getColumn(1).setMaxWidth(250);
        }

        groupNameText.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        groupNameText.setForeground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 833, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(previousPageGroupChat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(groupNameText)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(previousPageGroupChat)
                    .addComponent(jLabel1)
                    .addComponent(groupNameText))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void previousPageGroupChatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_previousPageGroupChatMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_previousPageGroupChatMouseClicked

    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        super.paintComponent(grphcs);
    }

    public void addListenerPreviousPageGroupChat(MouseAdapter e) {
        previousPageGroupChat.addMouseListener(e);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private components.GroupChatList.ViewAllAdmin.GroupChatListAllAdminTable groupChatListAllAdminTable;
    private javax.swing.JLabel groupNameText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel previousPageGroupChat;
    // End of variables declaration//GEN-END:variables
}