/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package components.GroupChatList.ViewAllAdmin;

import Interface.User;
import com.google.gson.Gson;
import components.GroupChatList.ViewAllMember.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableCellRenderer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import project.DataBase;

/**
 *
 * @author lnt09
 */
public class GroupChatListAllAdmin extends javax.swing.JPanel {

    /**
     * Creates new form GroupChatListAllMember
     */
    private String groupName, id;

    public GroupChatListAllAdmin(String id, String name) {
        this.id = id;
        initComponents();
        groupNameText.setText(name);
        // Add fake data
//        for (int i = 0; i < 50; i++) {
//            groupChatListAllAdminTable.addRow(new Object[]{"lenguyenthai123", "Lê Nguyên Thái", "Admin", "2023/12/6"});
//        }

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        groupChatListAllAdminTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

        new CallAPIGroupChatAllAdmin().execute();
    }

    private class CallAPIGroupChatAllAdmin extends SwingWorker<String, User[]> {

        @Override
        protected String doInBackground() {
            try {
//                String api = "http://13.215.176.178:8881/admin/group-chat-member/" + id + "?admin=true";
                String api = DataBase.serverUrl + "/admin/group-chat-member/" + id + "?admin=true";

                System.out.println("API all member: " + api);

                HttpClient client = HttpClient.newHttpClient();

                HttpRequest req = HttpRequest.newBuilder()
                        .uri(new URI(api))
                        .GET()
                        .build();

                HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());

                int resCode = res.statusCode();
                String json = res.body();
                if (resCode == HttpURLConnection.HTTP_OK) {
                    System.out.println("Call API Thanh cong");

                    JSONParser par = new JSONParser();
                    JSONObject data = (JSONObject) par.parse(json);
                    JSONArray list = (JSONArray) data.get("members");
                    String json1 = list.toString();

                    Gson gson = new Gson();
                    User[] users = gson.fromJson(json1, User[].class);

                    publish(users);
                    System.out.println("Users: " + json1);

                    return "Done";
                } else {
                    return "Failed";
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
            return "Failed";
        }

        @Override
        protected void process(List<User[]> chunks) {
            User[] data = chunks.get(chunks.size() - 1);
            groupChatListAllAdminTable.clearData();
            for (User user : data) {
                groupChatListAllAdminTable.addUserRow(user);
            }
        }
    }

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
                "Id", "User Name", "Full Name", "Address", "Date of Birth", "Sex", "Email", "Last Login", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(groupChatListAllAdminTable);

        groupNameText.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        groupNameText.setForeground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1141, Short.MAX_VALUE)
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
