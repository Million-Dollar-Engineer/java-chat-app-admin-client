/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package components.GroupChatList;

import java.awt.event.ActionListener;

/**
 *
 * @author lnt09
 */
public class GroupChatListForward extends javax.swing.JPanel {

    /**
     * Creates new form GroupChatListForward
     */
    public GroupChatListForward() {
        initComponents();
        groupIdText.setVisible(false);
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
        groupNameText = new javax.swing.JLabel();
        viewListOfAllAdmin = new javax.swing.JButton();
        viewListOfAllMemberButton = new javax.swing.JButton();
        groupIdText = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));
        jLabel1.setText("Group Name:");

        groupNameText.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        groupNameText.setForeground(new java.awt.Color(127, 127, 127));
        groupNameText.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        viewListOfAllAdmin.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        viewListOfAllAdmin.setForeground(new java.awt.Color(127, 127, 127));
        viewListOfAllAdmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/8.png"))); // NOI18N
        viewListOfAllAdmin.setText("View List of All Admin");

        viewListOfAllMemberButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        viewListOfAllMemberButton.setForeground(new java.awt.Color(127, 127, 127));
        viewListOfAllMemberButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/10.png"))); // NOI18N
        viewListOfAllMemberButton.setText("View List of All Member");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(137, 137, 137)
                        .addComponent(groupIdText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(112, 112, 112)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(groupNameText, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(244, 244, 244)
                        .addComponent(viewListOfAllMemberButton, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(viewListOfAllAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(162, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(groupIdText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(groupNameText, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(viewListOfAllMemberButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewListOfAllAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public void addListenerViewListOfAllAdmin(ActionListener listener) {
        viewListOfAllAdmin.addActionListener(listener);
    }

    public void addListenerViewListOfAllMember(ActionListener listener) {
        viewListOfAllMemberButton.addActionListener(listener);
    }

    public void setGroupNameText(String name) {
        groupNameText.setText(name);
    }

    public void setGroupIdText(String id) {
        groupIdText.setText(id);
    }

    public String getGroupNameText() {
        return groupNameText.getText();
    }

    public String getGroupIdText() {
        return groupIdText.getText();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField groupIdText;
    private javax.swing.JLabel groupNameText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton viewListOfAllAdmin;
    private javax.swing.JButton viewListOfAllMemberButton;
    // End of variables declaration//GEN-END:variables
}
