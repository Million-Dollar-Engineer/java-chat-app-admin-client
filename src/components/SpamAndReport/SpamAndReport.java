/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package components.SpamAndReport;

import Interface.GroupChat;
import Interface.Report;
import com.google.gson.Gson;
import components.GroupChatList.ViewAllMember.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import project.DataBase;

/**
 *
 * @author lnt09
 */
public class SpamAndReport extends javax.swing.JPanel {

    /**
     * Creates new form GroupChatListAllMember
     */
    public SpamAndReport() {
        initComponents();

        spamAndReportTable.addListener(new ListenerTable());
        // Add fake data
//        for (int i = 0; i < 50; i++) {
//            spamAndReportTable.addRow(new Object[]{"lenguyenthai123", "Có lời lẽ không phù hợp i lẽ không phù hợp i lẽ không phù hợp i lẽ không phù hợp i lẽ không phù hợp i lẽ không phù hợp i lẽ không phù hợp i lẽ không phù hợp i lẽ không phù hợp i lẽ không phù hợp i lẽ không phù hợp i lẽ không phù hợp i lẽ không phù hợp i lẽ không phù hợp i lẽ không phù hợp i lẽ không phù hợp i lẽ không phù hợp i lẽ không phù hợp i lẽ không phù hợp i lẽ không phù hợp i lẽ không phù hợp i lẽ không phù hợp i lẽ không phù hợp i lẽ không phù hợp i lẽ không phù hợp i lẽ không phù hợp i lẽ không phù hợp i lẽ không phù hợp i lẽ không phù hợp i lẽ không phù hợp i lẽ không phù hợp cần phải được thanh lọc", "2023/12/6", "tranhuykhanh"});
//        }

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        spamAndReportTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);
        spamAndReportTable.getColumnModel().getColumn(1).setCellRenderer(leftRenderer);

        new CallAPISpamAndReportSearching().execute();

        spamAndReportSearching.addListenerSearchButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CallAPISpamAndReportSearching().execute();
            }
        });

    }

    private class CallAPISpamAndReportSearching extends SwingWorker<String, Report[]> {

        @Override
        public String doInBackground() {
            try {

                String sortBy = spamAndReportSearching.getSortBy();
                if (sortBy.equals("name")) {
                    sortBy = "username";
                }
                else{
                    sortBy = "time";
                }

                String orderBy = spamAndReportSearching.getOrderBy();

                String name = spamAndReportSearching.getSearchText();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                Date startTimeD = spamAndReportSearching.getDateStart();
                Date endTimeD = spamAndReportSearching.getDateEnd();

                String startTime = dateFormat.format(startTimeD);
                String endTime = dateFormat.format(endTimeD);

                System.out.println("Date: " + startTime + "   " + endTime);

//                String api = "http://13.215.176.178:8881/admin/spam-reports" + "?sortBy=" + sortBy + "&username=" + name + "&startTime=" + startTime + "&endTime=" + endTime;
                String api = DataBase.serverUrl + "/admin/spam-reports" + "?sortBy=" + sortBy + "&order=" + orderBy + "&username=" + name + "&startTime=" + startTime + "&endTime=" + endTime;
                System.out.println("API: " + api);
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest req = HttpRequest.newBuilder()
                        .uri(new URI(api))
                        .GET()
                        .build();

                HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());

                System.out.println("Response Code: " + res.statusCode());
//                System.out.println("Data: "+ res.body());
                String body = res.body();
                System.out.println("Body: " + body);
                if (res.statusCode() == 200) {
                    System.out.println("Call API thanh cong");

                    JSONParser par = new JSONParser();
                    JSONObject data = (JSONObject) par.parse(body);
                    JSONArray list = (JSONArray) data.get("spamReports");
                    String json = list.toString();

                    Gson gson = new Gson();
                    Report[] reports = gson.fromJson(json, Report[].class);

                    publish(reports);

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
        public void process(List<Report[]> chunks) {
            spamAndReportTable.clearData();
            Report[] data = chunks.get(chunks.size() - 1);
            for (Report group : data) {
                spamAndReportTable.addReportRow(group);
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
        spamAndReportTable = new components.SpamAndReport.SpamAndReportTable();
        spamAndReportSearching = new components.SpamAndReport.SpamAndReportSearching();
        spamAndReportBan = new components.SpamAndReport.SpamAndReportBan();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));
        jLabel1.setText("Spam and Report");

        jScrollPane2.setBorder(null);

        spamAndReportTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Reporter Id", "Accused Id", "User Name of Accused", "Reason", "Create At"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        spamAndReportTable.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        spamAndReportTable.setSelectionForeground(new java.awt.Color(127, 127, 127));
        jScrollPane2.setViewportView(spamAndReportTable);
        if (spamAndReportTable.getColumnModel().getColumnCount() > 0) {
            spamAndReportTable.getColumnModel().getColumn(0).setMinWidth(50);
            spamAndReportTable.getColumnModel().getColumn(0).setPreferredWidth(50);
            spamAndReportTable.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spamAndReportSearching, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(spamAndReportBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(spamAndReportSearching, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spamAndReportBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
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

    public class ListenerTable implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = spamAndReportTable.getSelectedRow();
                if (selectedRow != -1) {
                    // Lấy dữ liệu từ dòng được chọn
                    String reportedUser = getStringValue(spamAndReportTable.getValueAt(selectedRow, 2));
                    String username = getStringValue(spamAndReportTable.getValueAt(selectedRow, 3));
                    String content = getStringValue(spamAndReportTable.getValueAt(selectedRow, 4));

                    System.out.println(reportedUser + "  " + content);
                    spamAndReportBan.setReportedUser(username);
                    spamAndReportBan.setContent(content);
                    spamAndReportBan.setHidden(reportedUser);
                }
            }
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private components.SpamAndReport.SpamAndReportBan spamAndReportBan;
    private components.SpamAndReport.SpamAndReportSearching spamAndReportSearching;
    private components.SpamAndReport.SpamAndReportTable spamAndReportTable;
    // End of variables declaration//GEN-END:variables
}
