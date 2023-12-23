/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package components.LoginHistory;

import Interface.LoginHistoryUser;
import com.google.gson.Gson;
import components.userManager.LoginHistory.UserManagerLoginHistory;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableCellRenderer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author lnt09
 */
public class LoginHistory extends javax.swing.JPanel {

    /**
     * Creates new form LoginHistory
     */
    public LoginHistory() {
        initComponents();
        setOpaque(false);

        // Thiết lập renderer cho cột 1 (index 0) là căn lề trái
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        loginHistoryTable.getColumnModel().getColumn(1).setCellRenderer(leftRenderer);
        new CallAPILoginHistory().execute();
        loginHistoryTable.clearData();

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginHistoryTable.clearData();
                new CallAPILoginHistory().execute();
            }

        });
    }

    private class CallAPILoginHistory extends SwingWorker<String, LoginHistoryUser[]> {

        @Override
        protected String doInBackground() {
            try {

                String api = "http://13.215.176.178:8881/admin/login-histories";

                URL url = new URL(api);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();

                con.setRequestMethod("GET");
                con.setRequestProperty("Content-Type", "application/json");
                con.setDoOutput(true);

                int resCode = con.getResponseCode();
                System.out.println("Status code: " + resCode);
                if (resCode == HttpURLConnection.HTTP_OK) {
                    System.out.println("Call API thanh cong");

                    String resBuf = "", line = "";
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                        while ((line = reader.readLine()) != null) {
                            resBuf += line;
                        }
                    }
                    JSONParser par = new JSONParser();
                    JSONObject res = (JSONObject) par.parse(resBuf);
                    JSONArray list = (JSONArray) res.get("loginData");

                    System.out.println("Data " + list);

                    String json = list.toString();

                    Gson gson = new Gson();
                    LoginHistoryUser[] data = gson.fromJson(json, LoginHistoryUser[].class);

//                    for (LoginHistoryUser chunk : data) {
//                        publish(chunk);
//                    }
                    publish(data);
                    con.disconnect();
                    return "Done";

                }
                con.disconnect();
                return "failed";

            } catch (MalformedURLException ex) {
                Logger.getLogger(UserManagerLoginHistory.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(UserManagerLoginHistory.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(UserManagerLoginHistory.class.getName()).log(Level.SEVERE, null, ex);
            }
            return "Failed";
        }

        @Override
        protected void process(List<LoginHistoryUser[]> chunks) {
            LoginHistoryUser[] users = chunks.get(chunks.size() - 1);

            Thread th = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (LoginHistoryUser user : users) {
                        loginHistoryTable.addLoginHistoryUserRow(user);
                    }
                }
            });
            th.start();

        }

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
        jScrollPane1 = new javax.swing.JScrollPane();
        loginHistoryTable = new components.LoginHistory.LoginHistoryTable();
        refreshButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));
        jLabel1.setText("Login History");

        jScrollPane1.setBorder(null);

        loginHistoryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Login Time", "User Name", "Full Name", "Ip"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(loginHistoryTable);
        if (loginHistoryTable.getColumnModel().getColumnCount() > 0) {
            loginHistoryTable.getColumnModel().getColumn(2).setMinWidth(200);
            loginHistoryTable.getColumnModel().getColumn(2).setPreferredWidth(200);
            loginHistoryTable.getColumnModel().getColumn(2).setMaxWidth(200);
            loginHistoryTable.getColumnModel().getColumn(3).setMinWidth(200);
            loginHistoryTable.getColumnModel().getColumn(3).setPreferredWidth(200);
            loginHistoryTable.getColumnModel().getColumn(3).setMaxWidth(200);
        }

        refreshButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        refreshButton.setForeground(new java.awt.Color(127, 127, 127));
        refreshButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/4.png"))); // NOI18N
        refreshButton.setText("Refresh");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 883, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(refreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel1)
                        .addGap(33, 33, 33))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(refreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_refreshButtonActionPerformed

    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        super.paintComponent(grphcs);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private components.LoginHistory.LoginHistoryTable loginHistoryTable;
    private javax.swing.JButton refreshButton;
    // End of variables declaration//GEN-END:variables
}
