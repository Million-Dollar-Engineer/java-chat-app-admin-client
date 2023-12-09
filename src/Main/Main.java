/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Main;

import components.GroupChatList.GroupChatList;
import components.GroupChatList.ViewAllAdmin.GroupChatListAllAdmin;
import components.GroupChatList.ViewAllMember.GroupChatListAllMember;
import components.LoginHistory.LoginHistory;
import components.NewRegistered.NewRegistered;
import components.SpamAndReport.SpamAndReport;
import components.userManager.ListFriend.UserManagerListFriend;
import components.userManager.LoginHistory.UserManagerLoginHistory;
import java.awt.Color;
import javax.swing.JComponent;
import components.userManager.UserManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author lnt09
 */
public class Main extends javax.swing.JFrame {

    // Form 1
    private UserManager userManagerView = null;
    private UserManagerLoginHistory userManagerLoginHistoryView = null;
    private UserManagerListFriend userManagerListFriendView = null;

    // Form 2
    private LoginHistory loginHistoryView = null;

    //Form 3
    private GroupChatList groupChatListView = null;
    private GroupChatListAllMember groupChatListAllMemberView = null;
    private GroupChatListAllAdmin groupChatListAllAdminView = null;

    // Form 4
    private SpamAndReport spamAndReportView = null;

    private NewRegistered newRegisteredView = null;
//    private UserManager userManagerView = null;
//    private UserManager userManagerView = null;

    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
        initMoreComponent();
        setBackground(new Color(0, 0, 0, 0));
        menu.initMoving(Main.this);

        //init Layout User Manager 
        userManagerView = new UserManager();
        userManagerView.addListenerLoginHistory(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userManagerView.getUsername();
                if (!("".equals(username))) {
                    try {
                        userManagerLoginHistoryView = new UserManagerLoginHistory(username);
                        userManagerLoginHistoryView.addListenerPreviousPageUserManager(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                setForm(userManagerView);
                            }
                        });

                        setForm(userManagerLoginHistoryView);

                    } catch (Exception err) {
                        System.out.println("Loi trong nay");
                        err.printStackTrace();
                    }
                }
            }
        });
        userManagerView.addListenerListFriend(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userManagerView.getUsername();
                if (!("".equals(username))) {
                    try {
                        userManagerListFriendView = new UserManagerListFriend(username);
                        userManagerListFriendView.addListenerPreviousPageUserManager(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                setForm(userManagerView);
                            }
                        });

                        setForm(userManagerListFriendView);

                    } catch (Exception err) {
                        System.out.println("Loi trong nay");
                        err.printStackTrace();
                    }
                }
            }
        });
        setForm(userManagerView);

    }

    public void initMoreComponent() {

        // Menu
        menu.addListenerUserManager(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (userManagerView == null) {
                    userManagerView = new UserManager();

                }
                setForm(userManagerView);
            }
        });

        menu.addListenerLoginHistory(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (loginHistoryView == null) {
                    loginHistoryView = new LoginHistory();
                }
                setForm(loginHistoryView);
            }
        });

        menu.addListenerGroupChatList(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (groupChatListView == null) {
                    groupChatListView = new GroupChatList();

                    groupChatListView.addListenerViewListOfAllMemberButton(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String groupName = groupChatListView.getGroupNametext();

                            if (!("".equals(groupName))) {

                                groupChatListAllMemberView = new GroupChatListAllMember(groupName);

                                // Lấy ra username => call API => retreive data;
                                groupChatListAllMemberView.addListenerPreviousPageGroupChat(new MouseAdapter() {
                                    @Override
                                    public void mouseClicked(MouseEvent e) {
                                        setForm(groupChatListView);
                                    }
                                });
                                setForm(groupChatListAllMemberView);
                            }
                        }
                    }
                    );
                    groupChatListView.addListenerViewListOfAllAdminButton(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String groupName = groupChatListView.getGroupNametext();
                            if (!("".equals(groupName))) {

                                groupChatListAllAdminView = new GroupChatListAllAdmin(groupName);

                                // Lấy ra username => call API => retreive data;
                                groupChatListAllAdminView.addListenerPreviousPageGroupChat(new MouseAdapter() {
                                    @Override
                                    public void mouseClicked(MouseEvent e) {
                                        setForm(groupChatListView);
                                    }
                                });
                                setForm(groupChatListAllAdminView);
                            }
                        }
                    });

                }

                setForm(groupChatListView);
            }

        }
        );

        menu.addListenerSpamAndReportOption(
                new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e
            ) {
                if (spamAndReportView == null) {
                    spamAndReportView = new SpamAndReport();
                }
                setForm(spamAndReportView);
            }
        }
        );

        menu.addListenerNewRegisteredOption(
                new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e
            ) {
                if (newRegisteredView == null) {
                    newRegisteredView = new NewRegistered();
                }
                setForm(newRegisteredView);
            }
        }
        );
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new project.PanelBorder();
        panelBorder2 = new project.PanelBorder();
        menu = new components.Menu();
        header2 = new components.Header();
        mainPanel = new javax.swing.JPanel();

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        mainPanel.setOpaque(false);
        mainPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout panelBorder2Layout = new javax.swing.GroupLayout(panelBorder2);
        panelBorder2.setLayout(panelBorder2Layout);
        panelBorder2Layout.setHorizontalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder2Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0))
                    .addGroup(panelBorder2Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(header2, javax.swing.GroupLayout.PREFERRED_SIZE, 1004, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        panelBorder2Layout.setVerticalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, 784, Short.MAX_VALUE)
                    .addGroup(panelBorder2Layout.createSequentialGroup()
                        .addComponent(header2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelBorder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void setForm(JComponent com) {
        mainPanel.removeAll();
        mainPanel.add(com);
        mainPanel.repaint();
        mainPanel.revalidate();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private components.Header header2;
    private javax.swing.JPanel mainPanel;
    private components.Menu menu;
    private project.PanelBorder panelBorder1;
    private project.PanelBorder panelBorder2;
    // End of variables declaration//GEN-END:variables
}
