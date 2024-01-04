/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package components.UserOnline;

import Interface.Online;
import Interface.Related;
import com.google.gson.Gson;
import components.UserRelated.*;
import components.NewRegistered.*;
import components.SpamAndReport.*;
import components.GroupChatList.ViewAllMember.*;
import components.RegisteredChart.FormRegisteredChart;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
public class UserOnline extends javax.swing.JPanel {

    /**
     * Creates new form GroupChatListAllMember
     */
    public UserOnline() {
        initComponents();

//        // Add fake data
//        for (int i = 0; i < 50; i++) {
//            userOnlineTable.addRow(new Object[]{"lenguyenthai123", "Lê Nguyên Thái", "lnt0995449235@gmail.com", "2023/11/02", "Active"});
//        }
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        userOnlineTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
//
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);
        userOnlineTable.getColumnModel().getColumn(2).setCellRenderer(leftRenderer);

        new CallAPIUserOnline().execute();
        userOnlineSearching.addListenerSearchButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CallAPIUserOnline().execute();
            }
        });
    }

    private class CallAPIUserOnline extends SwingWorker<String, Online[]> {

        @Override
        public String doInBackground() {

            String sortBy = userOnlineSearching.getSortBy();
            System.out.println("Sort by: " + sortBy);
            if (sortBy.equals("name")) {
                sortBy = "username";
            } else {
                sortBy = "created_at";
            }

            String order = userOnlineSearching.getOrder();
            String startTime = userOnlineSearching.getStartTime();
            String endTime = userOnlineSearching.getEndTime();

            String username = userOnlineSearching.getSearchText();
            String option = userOnlineSearching.getOption();
            if (option.equals("greater")) {
                option = "greaterThan";
            }
            if (option.equals("less")) {
                option = "lowerThan";
            }

            String number = userOnlineSearching.getNumber();

//            String api = "http://13.215.176.178:8881/admin/active-user-and-relevant-info" + "?sortBy=" + sortBy + "&order=" + order + "&" + option + "=" + number + "&username=" + username;
            String api = DataBase.serverUrl + "/admin/active-user-and-relevant-info" + "?sortBy=" + sortBy + "&order=" + order + "&" + option + "=" + number + "&username=" + username;

            api = api + "&startTime=" + startTime + "&endTime=" + endTime;

//                    + "&username=" + username;
            System.out.println("Link :" + api);
            HttpClient client = HttpClient.newHttpClient();
            try {

                HttpRequest req;
                req = HttpRequest.newBuilder()
                        .uri(new URI(api))
                        .GET()
                        .build();

                HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());

                int resCode = res.statusCode();
                String body = res.body();
                if (resCode == 200) {
                    System.out.println("Call API thanh cong");

                    System.out.println("Data: " + body);

                    JSONParser par = new JSONParser();
                    JSONObject o = (JSONObject) par.parse(body);
                    JSONArray list = (JSONArray) o.get("data");

                    String json = list.toString();

                    Gson gson = new Gson();
                    Online[] data = gson.fromJson(json, Online[].class);
                    publish(data);

                    return "Done";
                } else {
                    System.out.println("Call API that bai");
                    return "Failed";
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException ex) {
                Logger.getLogger(FormRegisteredChart.class.getName()).log(Level.SEVERE, null, ex);
            } catch (URISyntaxException ex) {
                Logger.getLogger(FormRegisteredChart.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(FormRegisteredChart.class.getName()).log(Level.SEVERE, null, ex);
            }
            return "Failed";

        }

        @Override
        public void process(List<Online[]> result) {
            Online[] data = result.get(result.size() - 1);
            userOnlineTable.clearData();
            for (Online online : data) {
                userOnlineTable.addOnlineRow(online);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        userOnlineTable = new components.UserOnline.UserOnlineTable();
        userOnlineSearching = new components.UserOnline.UserOnlineSearching();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));
        jLabel1.setText("User Online");

        jScrollPane1.setBorder(null);

        userOnlineTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "User Name", "Number Access Times", "Number Chatted People", "Number Chatted Group"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(userOnlineTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(userOnlineSearching, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 9, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(10, 10, 10))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(userOnlineSearching, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    private components.UserOnline.UserOnlineSearching userOnlineSearching;
    private components.UserOnline.UserOnlineTable userOnlineTable;
    // End of variables declaration//GEN-END:variables
}
