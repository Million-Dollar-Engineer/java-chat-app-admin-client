/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package components.userManager.LoginHistory;

import Interface.LoginHistoryUser;
import Interface.User;
import com.google.gson.Gson;
import components.userManager.LoginHistory.*;
import components.GroupChatList.ViewAllMember.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableCellRenderer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import project.DataBase;

/**
 *
 * @author lnt09
 */
public class UserManagerLoginHistory extends javax.swing.JPanel {

    /**
     * Creates new form GroupChatListAllMember
     */
    private String username;

    public UserManagerLoginHistory(String name) {
        initComponents();
        username = name;
        // Add fake data
        try {
//            for (int i = 0; i < 50; i++) {
////                userManagerLoginHistoryTable.addRow(new Object[]{"2023/12/6"});
//            }
            usernameText.setText(name);
        } catch (Exception err) {
            System.out.println("Loi trong khoi tao");
            err.printStackTrace();
        }

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        userManagerLoginHistoryTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

        loadData();
    }

    public void loadData() {
        userManagerLoginHistoryTable.clearData();
        new CallAPILoginHistoryOfAnUser().execute();
    }

    private class CallAPILoginHistoryOfAnUser extends SwingWorker<String, LoginHistoryUser[]> {

        @Override
        protected String doInBackground() {
            try {

//                String api = "http://13.215.176.178:8881/admin/login-histories?username=" + username + "&orderBy=desc";

                String api = DataBase.serverUrl + "/admin/login-histories?username=" + username + "&orderBy=desc";

                HttpClient client = HttpClient.newHttpClient();

                HttpRequest req = HttpRequest.newBuilder()
                        .uri(new URI(api))
                        .GET()
                        .header("Content-Type", "application/json")
                        .build();

                HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());

                int resCode = res.statusCode();
                String body = res.body();

                JSONParser par = new JSONParser();
                JSONObject json = (JSONObject) par.parse(body);

                if (resCode == 200) {
                    JSONArray data = (JSONArray) json.get("loginData");
                    String json1 = data.toString();

                    Gson gson = new Gson();
                    LoginHistoryUser[] users = gson.fromJson(json1, LoginHistoryUser[].class);
                    publish(users);

                    return "Done";
                } else {
                    JOptionPane.showMessageDialog(null, String.valueOf(json.get("error")), "ERROR", JOptionPane.ERROR_MESSAGE);
                    return "Failed";
                }

            } catch (URISyntaxException ex) {
                Logger.getLogger(UserManagerLoginHistory.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(UserManagerLoginHistory.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(UserManagerLoginHistory.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(UserManagerLoginHistory.class.getName()).log(Level.SEVERE, null, ex);
            }
            return "Done";
        }

        @Override
        protected void process(List<LoginHistoryUser[]> chunks) {
            LoginHistoryUser[] data = chunks.get(chunks.size() - 1);
            userManagerLoginHistoryTable.clearData();
            for (LoginHistoryUser user : data) {
                userManagerLoginHistoryTable.addLoginHistoryUserRow(user);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        previousPageUserManager = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        usernameText = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        userManagerLoginHistoryTable = new components.userManager.LoginHistory.UserManagerLoginHistoryTable();

        setBackground(new java.awt.Color(255, 255, 255));

        previousPageUserManager.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        previousPageUserManager.setForeground(new java.awt.Color(127, 127, 127));
        previousPageUserManager.setText("User Manager");
        previousPageUserManager.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));
        jLabel1.setText("/ Login history of");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(127, 127, 127));

        usernameText.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        usernameText.setForeground(new java.awt.Color(51, 51, 51));

        jScrollPane2.setBorder(null);

        userManagerLoginHistoryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Login Time", "User Name", "Full Name", "IP"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(userManagerLoginHistoryTable);
        if (userManagerLoginHistoryTable.getColumnModel().getColumnCount() > 0) {
            userManagerLoginHistoryTable.getColumnModel().getColumn(2).setMinWidth(250);
            userManagerLoginHistoryTable.getColumnModel().getColumn(2).setPreferredWidth(250);
            userManagerLoginHistoryTable.getColumnModel().getColumn(2).setMaxWidth(250);
            userManagerLoginHistoryTable.getColumnModel().getColumn(3).setMinWidth(250);
            userManagerLoginHistoryTable.getColumnModel().getColumn(3).setPreferredWidth(250);
            userManagerLoginHistoryTable.getColumnModel().getColumn(3).setMaxWidth(250);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(previousPageUserManager)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(usernameText, javax.swing.GroupLayout.PREFERRED_SIZE, 568, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(usernameText, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(previousPageUserManager, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        super.paintComponent(grphcs);
    }

    public void addListenerPreviousPageUserManager(MouseAdapter e) {
        previousPageUserManager.addMouseListener(e);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel previousPageUserManager;
    private components.userManager.LoginHistory.UserManagerLoginHistoryTable userManagerLoginHistoryTable;
    private javax.swing.JLabel usernameText;
    // End of variables declaration//GEN-END:variables
}
