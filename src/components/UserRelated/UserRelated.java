/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package components.UserRelated;

import Interface.Related;
import com.google.gson.Gson;
import components.NewRegistered.*;
import components.SpamAndReport.*;
import components.GroupChatList.ViewAllMember.*;
import components.RegisteredChart.FormRegisteredChart;
import components.RegisteredChart.ModelRegisteredChartLine;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
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
public class UserRelated extends javax.swing.JPanel {

    /**
     * Creates new form GroupChatListAllMember
     */
    public UserRelated() {
        initComponents();

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        userRelatedTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
//
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);

        userRelatedTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        userRelatedTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

        new CallAPIUserRelated().execute();
        userRelatedSearching.addListenerSearchButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CallAPIUserRelated().execute();
            }
        });

    }

    private class CallAPIUserRelated extends SwingWorker<String, Related[]> {

        @Override
        public String doInBackground() {

            String sortBy = userRelatedSearching.getSortBy();
            if (sortBy == "name") {
                sortBy = "full_name";
            } else {
                sortBy = "created_at";
            }

            String fullname = userRelatedSearching.getSearchText();
            String option = userRelatedSearching.getOption();
            if (option.equals("greater")) {
                option = "greaterThan";
            }
            if (option.equals("less")) {
                option = "lowerThan";
            }

            String number = userRelatedSearching.getNumber();

//            String api = "http://13.215.176.178:8881/admin/friend-and-fof" + "?sortBy=" + sortBy +"&order=asc"+ "&" + option + "=" + number + "&username=" + username;
//                    + "&username=" + username;
            String api="";
            try {
                api = DataBase.serverUrl + "/admin/friend-and-fof" + "?sortBy=" + sortBy + "&order=asc" + "&" + option + "=" + number + "&username=" +  URLEncoder.encode(fullname, "UTF-8");
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(UserRelated.class.getName()).log(Level.SEVERE, null, ex);
            }

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
                JSONParser par = new JSONParser();
                JSONObject o = (JSONObject) par.parse(body);
                if (resCode == 200) {
                    System.out.println("Call API thanh cong");

                    System.out.println("Data: " + body);

                    JSONArray list = (JSONArray) o.get("data");

                    String json = list.toString();

                    Gson gson = new Gson();
                    Related[] data = gson.fromJson(json, Related[].class);
                    publish(data);

                    return "Done";
                } else {
                    System.out.println("Call API that bai");
                    JOptionPane.showMessageDialog(null, String.valueOf(o.get("error")), "ERROR", JOptionPane.ERROR_MESSAGE);
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
        public void process(List<Related[]> result) {
            Related[] data = result.get(result.size() - 1);
            userRelatedTable.clearData();
            for (Related related : data) {
                userRelatedTable.addRelatedRow(related);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        userRelatedSearching = new components.UserRelated.UserRelatedSearching();
        jScrollPane2 = new javax.swing.JScrollPane();
        userRelatedTable = new components.UserRelated.UserRelatedTable();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));
        jLabel1.setText("User Related");

        jScrollPane2.setBorder(null);

        userRelatedTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "User Name ", "Number Direct Friends", "Number Friend of friend", "Created At"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        userRelatedTable.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jScrollPane2.setViewportView(userRelatedTable);
        if (userRelatedTable.getColumnModel().getColumnCount() > 0) {
            userRelatedTable.getColumnModel().getColumn(1).setMinWidth(300);
            userRelatedTable.getColumnModel().getColumn(1).setPreferredWidth(300);
            userRelatedTable.getColumnModel().getColumn(1).setMaxWidth(300);
            userRelatedTable.getColumnModel().getColumn(2).setMinWidth(250);
            userRelatedTable.getColumnModel().getColumn(2).setPreferredWidth(250);
            userRelatedTable.getColumnModel().getColumn(2).setMaxWidth(250);
            userRelatedTable.getColumnModel().getColumn(3).setMinWidth(250);
            userRelatedTable.getColumnModel().getColumn(3).setPreferredWidth(250);
            userRelatedTable.getColumnModel().getColumn(3).setMaxWidth(250);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1128, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(userRelatedSearching, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(userRelatedSearching, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    private javax.swing.JScrollPane jScrollPane2;
    private components.UserRelated.UserRelatedSearching userRelatedSearching;
    private components.UserRelated.UserRelatedTable userRelatedTable;
    // End of variables declaration//GEN-END:variables
}
