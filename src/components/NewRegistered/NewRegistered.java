/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package components.NewRegistered;

import Interface.Online;
import Interface.User;
import com.google.gson.Gson;
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
public class NewRegistered extends javax.swing.JPanel {

    /**
     * Creates new form GroupChatListAllMember
     */
    public NewRegistered() {
        initComponents();

        // Add fake data
//        for (int i = 0; i < 50; i++) {
//            newRegisteredTable.addRow(new Object[]{"lenguyenthai123", "Lê Nguyên Thái", "lnt0995449235@gmail.com", "2023/11/02", "Active"});
//        }
//        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
//        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
//        newRegisteredTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
//
//        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
//        leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);
//        newRegisteredTable.getColumnModel().getColumn(1).setCellRenderer(leftRenderer);
        new CallAPIUserOnline().execute();
        newRegisteredSearching.addListenerSearchButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CallAPIUserOnline().execute();

            }
        });
    }

    private class CallAPIUserOnline extends SwingWorker<String, User[]> {

        @Override
        public String doInBackground() {

            String sortBy = newRegisteredSearching.getSortBy();
            if (sortBy == "creation date") {
                sortBy = "created_at";
            } else {
                sortBy = "username";
            }

            String order = newRegisteredSearching.getOrder();
            String startTime = newRegisteredSearching.getStartTime();
            String endTime = newRegisteredSearching.getEndTime();
            String fullname = newRegisteredSearching.getSearchText();

//            String api = "http://13.215.176.178:8881/admin/all-user" + "?sortBy=" + sortBy + "&order=" + order + "&fullname=" + fullname;
            String api = DataBase.serverUrl + "/admin/all-user" + "?sortBy=" + sortBy + "&order=" + order + "&fullname=" + fullname;

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
                    JSONArray list = (JSONArray) o.get("userData");

                    String json = list.toString();

                    Gson gson = new Gson();
                    User[] data = gson.fromJson(json, User[].class);
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
        public void process(List<User[]> result) {
            User[] data = result.get(result.size() - 1);
            newRegisteredTable.clearData();
            for (User user : data) {
                newRegisteredTable.addUserRow(user);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        newRegisteredTable = new components.NewRegistered.NewRegisteredTable();
        newRegisteredSearching = new components.NewRegistered.NewRegisteredSearching();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));
        jLabel1.setText("New Registered");

        jScrollPane1.setBorder(null);

        newRegisteredTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "User Name", "Full Name", "Address", "Date of Birth", "Sex", "Email", "Last Login", "Create At", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, true, true, true, false, false, true, false
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
            newRegisteredTable.getColumnModel().getColumn(7).setMinWidth(150);
            newRegisteredTable.getColumnModel().getColumn(7).setPreferredWidth(150);
            newRegisteredTable.getColumnModel().getColumn(7).setMaxWidth(150);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(newRegisteredSearching, javax.swing.GroupLayout.PREFERRED_SIZE, 953, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(192, 192, 192))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(newRegisteredSearching, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
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
    private components.NewRegistered.NewRegisteredSearching newRegisteredSearching;
    private components.NewRegistered.NewRegisteredTable newRegisteredTable;
    // End of variables declaration//GEN-END:variables
}
