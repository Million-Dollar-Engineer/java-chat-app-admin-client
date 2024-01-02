/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package components.GroupChatList;

import Interface.GroupChat;
import com.google.gson.Gson;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import org.json.simple.JSONObject;
import java.net.*;
import java.net.http.*;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author lnt09
 */
public class GroupChatList extends javax.swing.JPanel {

    /**
     * Creates new form GroupChatList
     */
    public GroupChatList() {
        initComponents();

//        groupChatListTable.addRow(new Object[]{"2003/12/02", "Super Kamen Rider", "20", "3"});
//        groupChatListTable.addRow(new Object[]{"2003/12/02", "Super Kamen Rider", "20", "3"});
//        groupChatListTable.addRow(new Object[]{"2003/12/02", "Super Kamen Rider", "20", "3"});
//        groupChatListTable.addRow(new Object[]{"2003/12/02", "Super Kamen Rider", "20", "3"});
//        groupChatListTable.addRow(new Object[]{"2003/12/02", "Super Kamen Rider", "20", "3"});
//        groupChatListTable.addRow(new Object[]{"2003/12/02", "Super Kamen Rider", "20", "3"});
        groupChatListTable.addListener(new ListenerTable());

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        groupChatListTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

        for (int i = 2; i <= 3; i++) {
            DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
            leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);
            groupChatListTable.getColumnModel().getColumn(i).setCellRenderer(leftRenderer);
        }

        // Inital
        new CallAPIGroupChatSearching().execute();

        groupChatListSearching.addListenerSearchButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CallAPIGroupChatSearching().execute();
            }
        }
        );
        
        groupChatListSearching.addListenerRefreshButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CallAPIGroupChatSearching().execute();
            }
        }
        );
    }

    private class CallAPIGroupChatSearching extends SwingWorker<String, GroupChat[]> {

        @Override
        public String doInBackground() {
            try {

                String sortBy = groupChatListSearching.getSortBy();
                if (sortBy.equals("creation date")) {
                    sortBy = "time";
                }

                String name = groupChatListSearching.getSearchText();

                String api = "http://13.215.176.178:8881/admin/group-chat" + "?sortBy=" + sortBy + "&name=" + name;

                HttpClient client = HttpClient.newHttpClient();
                HttpRequest req = HttpRequest.newBuilder()
                        .uri(new URI(api))
                        .GET()
                        .build();

                HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());

                System.out.println("Response Code: " + res.statusCode());
//                System.out.println("Data: "+ res.body());
                String body = res.body();
                if (res.statusCode() == 200) {
                    System.out.println("Call API thanh cong");

                    JSONParser par = new JSONParser();
                    JSONObject data = (JSONObject) par.parse(body);
                    JSONArray list = (JSONArray) data.get("groupList");
                    String json = list.toString();

                    Gson gson = new Gson();
                    GroupChat[] groups = gson.fromJson(json, GroupChat[].class);

                    publish(groups);

                    System.out.println("Data: " + list);

                    return "Done";

                } else {
                    System.out.println("Call API Fail");
                    return "Failed";
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
            return "Failed";
        }

        @Override
        public void process(List<GroupChat[]> chunks) {
            groupChatListTable.clearData();
            GroupChat[] data = chunks.get(chunks.size() - 1);
            for (GroupChat group : data) {
                groupChatListTable.addGroupChatRow(group);
            }
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
        jScrollPane2 = new javax.swing.JScrollPane();
        groupChatListTable = new components.GroupChatList.GroupChatListTable();
        groupChatListSearching = new components.GroupChatList.GroupChatListSearching();
        groupChatListForward = new components.GroupChatList.GroupChatListForward();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));
        jLabel1.setText("Group Chat");

        jScrollPane2.setBorder(null);

        groupChatListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id ", "Group Name", "Created At", "Updated At"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        groupChatListTable.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jScrollPane2.setViewportView(groupChatListTable);
        if (groupChatListTable.getColumnModel().getColumnCount() > 0) {
            groupChatListTable.getColumnModel().getColumn(0).setMinWidth(50);
            groupChatListTable.getColumnModel().getColumn(0).setPreferredWidth(50);
            groupChatListTable.getColumnModel().getColumn(0).setMaxWidth(50);
            groupChatListTable.getColumnModel().getColumn(1).setMinWidth(450);
            groupChatListTable.getColumnModel().getColumn(1).setPreferredWidth(450);
            groupChatListTable.getColumnModel().getColumn(1).setMaxWidth(450);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(groupChatListSearching, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addComponent(groupChatListForward, javax.swing.GroupLayout.PREFERRED_SIZE, 1031, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(groupChatListSearching, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(groupChatListForward, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public void addListenerViewListOfAllAdminButton(ActionListener listener) {
        groupChatListForward.addListenerViewListOfAllAdmin(listener);
    }

    public void addListenerViewListOfAllMemberButton(ActionListener listener) {
        groupChatListForward.addListenerViewListOfAllMember(listener);

    }

    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        super.paintComponent(grphcs);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private components.GroupChatList.GroupChatListForward groupChatListForward;
    private components.GroupChatList.GroupChatListSearching groupChatListSearching;
    private components.GroupChatList.GroupChatListTable groupChatListTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables

    private String getStringValue(Object value) {
        if (value instanceof String) {
            return (String) value;
        } else {
            return "";  // Hoặc xử lý giá trị không phải chuỗi theo cách khác nếu cần thiết
        }
    }

    public String getGroupNametext() {
        return groupChatListForward.getGroupNameText();
    }

    public String getGroupIdText() {
        return groupChatListForward.getGroupIdText();
    }

    public class ListenerTable implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = groupChatListTable.getSelectedRow();
                if (selectedRow != -1) {
                    // Lấy dữ liệu từ dòng được chọn
                    String groupName = getStringValue(groupChatListTable.getValueAt(selectedRow, 1));
                    String groupId = getStringValue(groupChatListTable.getValueAt(selectedRow, 0));

                    System.out.println(groupName);
                    System.out.println(groupId);
                    // In thông tin của dòng được chọn
                    groupChatListForward.setGroupNameText(groupName);
                    groupChatListForward.setGroupIdText(groupId);

                }
            }
        }
    }

}
