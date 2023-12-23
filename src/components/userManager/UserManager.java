/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package components.userManager;

import Interface.User;
import com.google.gson.Gson;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author lnt09
 */
public class UserManager extends javax.swing.JPanel {

    /**
     * Creates new form UserManager
     */
    private String id = null;

    public UserManager() {
        this.id = null;
        initComponents();
        setOpaque(false);

        //Fake data
//        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
//        leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);
//        userManagerTable.getColumnModel().getColumn(9).setCellRenderer(leftRenderer);
        // For Table    
        userManagerTable.addListener(new ListenerTable());

        // Searching 
        searching.addListenerSearchButton(new ListenerSearching());
        new APICallWorkerSearching().execute();
        setVisible(true);
    }

    @SuppressWarnings("unchecked")

// Handler button ========================    
    // Handler search => call API for search
    public class ListenerSearching implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            System.out.println(searching.getSearchText() + "     " + searching.getOptionSearch() + "        " + searching.getSortBy());
            new APICallWorkerSearching().execute();
//            JOptionPane.showMessageDialog(null, "Loading data...", "Notify", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public class APICallWorkerSearching extends SwingWorker<Boolean, User[]> {

        @Override
        protected Boolean doInBackground() {

//            Gson gson = new Gson();
//            String json = gson.toJson(search);
            HttpURLConnection con = null;
            try {
                //Call API in here
                String linkAPI = "http://13.215.176.178:8881/admin/all-user";

                String query1 = "";
                if ("name".equals(searching.getOptionSearch())) {
                    query1 += "?fullname=";
                }
                if ("user".equals(searching.getOptionSearch())) {
                    query1 += "?username=";
                }
                if ("status".equals(searching.getOptionSearch())) {
                    query1 += "?status=";
                }
                query1 += searching.getSearchText();

                String query2 = "";
                String sortBy = searching.getSortBy();
                if (sortBy == "name") {
                    query2 = "&sortBy=full_name";
                } else {
                    query2 = "&sortBy=created_at";
                }

                String query3 = "&order=" + searching.getOrder();

                String linkWithParameter = linkAPI + query1 + query2 + query3;
                System.out.println(linkWithParameter);
                URL url = new URL(linkWithParameter);

                con = (HttpURLConnection) url.openConnection();

                con.setRequestMethod("GET");
                con.setRequestProperty("Content-Type", "application/json");
                con.setDoOutput(true);

                int resCode = con.getResponseCode();
                System.out.println("Response code" + resCode);
                if (resCode == 200) {
                    String resBuf = "";
                    String line = "";
                    BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    while ((line = reader.readLine()) != null) {
                        resBuf += line;
                    }
                    System.out.println("res" + resBuf);

                    JSONParser par = new JSONParser();
                    JSONObject obj = (JSONObject) par.parse(resBuf);
                    JSONArray dataJson = (JSONArray) obj.get("userData");
                    String json = dataJson.toString();

                    Gson gson = new Gson();
                    User[] users = gson.fromJson(json, User[].class);

                    publish(users);
                    for (User user : users) {
                        System.out.println("User index" + user);
                    }

                    con.disconnect();
                    return true;
                }
                con.disconnect();
                return false;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (con != null) {
                    con.disconnect();
                }

            }
            return false;
        }

        @Override
        protected void process(List<User[]> chunks) {
            User[] users = chunks.get(chunks.size() - 1);
            userManagerTable.clearData();
            for (int i = 0; i < users.length; i++) {
                userManagerTable.addUserRow(users[i]);
            }// Doing update
        }

        @Override
        protected void done() {
            try {
                Boolean result = get();
                if (result) {
                    JOptionPane.showMessageDialog(null, "Load users successfully", "Successfully", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

//    // Handler refresh => Call api
//    public class ListenerRefresh implements ActionListener {
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//
//            new APICallWorkerRefresh().execute();
//        }
//    }
//    // Create new thread for solve the call api by Swing Worker
//
//    public class APICallWorkerRefresh extends SwingWorker<String, Object> {
//
//        @Override
//        protected String doInBackground() throws Exception {
//
//            return "Done";
//
//        }
//
//        @Override
//        protected void process(List<Object> chunks) {
//            Object latestValued = chunks.get(chunks.size() - 1);
//
//        }
//
//        @Override
//        protected void done() {
//            try {
//                String result = get();
//                System.out.println(result);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
    protected void paintComponent(Graphics grphcs) {
        System.out.println("User manager");
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

    private Boolean getBooleanValue(Object value) {
        if (value instanceof Boolean) {
            return (Boolean) value;
        } else {
            return false;  // Hoặc xử lý giá trị không phải chuỗi theo cách khác nếu cần thiết
        }
    }

    private int getIntValue(Object value) {
        if (value instanceof Integer) {
            return (Integer) value;
        } else {
            return 0;  // Hoặc xử lý giá trị không phải số nguyên theo cách khác nếu cần thiết
        }
    }

    public String getUsername() {
        return userCRUD.getUsernameText();
    }

    public void addListenerListFriend(ActionListener listener) {
        userCRUD.addListenerListFriendButton(listener);
    }

    public void addListenerLoginHistory(ActionListener listener) {
        userCRUD.addListenerLoginHistoryButton(listener);
    }

    public class ListenerTable implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = userManagerTable.getSelectedRow();
                if (selectedRow != -1) {
                    // Lấy dữ liệu từ dòng được chọn
                    String id = getStringValue(userManagerTable.getValueAt(selectedRow, 0));
                    String username = getStringValue(userManagerTable.getValueAt(selectedRow, 1));
                    String password = getStringValue(userManagerTable.getValueAt(selectedRow, 2));
                    String fullname = getStringValue(userManagerTable.getValueAt(selectedRow, 3));
                    String address = getStringValue(userManagerTable.getValueAt(selectedRow, 4));
                    String dateOfBirth = getStringValue(userManagerTable.getValueAt(selectedRow, 5));
                    String sex = getStringValue(userManagerTable.getValueAt(selectedRow, 6));
                    String email = getStringValue(userManagerTable.getValueAt(selectedRow, 7));
                    String status = getStringValue(userManagerTable.getValueAt(selectedRow, 9));
                    // In thông tin của dòng được chọn
                    userCRUD.setInfo(id,username, fullname, password, dateOfBirth, sex, email, address, status, true);
                }
            }
        }
    }
    public static void main(String[] args)
    {
        new UserManager().setVisible(true);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        searching = new components.userManager.searching();
        jScrollPane2 = new javax.swing.JScrollPane();
        userManagerTable = new components.userManager.UserManagerTable();
        userCRUD = new components.userManager.UserCRUD();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));
        jLabel1.setText("User Manager");

        jScrollPane2.setBorder(null);

        userManagerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "User", "Password", "Full Name", "Address", "Date of Birth", "Sex", "Email", "Last Login", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        userManagerTable.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jScrollPane2.setViewportView(userManagerTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel1)
                        .addGap(826, 977, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(userCRUD, javax.swing.GroupLayout.PREFERRED_SIZE, 941, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(searching, javax.swing.GroupLayout.PREFERRED_SIZE, 1073, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(searching, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(userCRUD, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private components.userManager.searching searching;
    private components.userManager.UserCRUD userCRUD;
    private components.userManager.UserManagerTable userManagerTable;
    // End of variables declaration//GEN-END:variables
}
