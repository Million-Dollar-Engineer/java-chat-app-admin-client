/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package components.SpamAndReport;

import Interface.Report;
import com.google.gson.Gson;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author lnt09
 */
public class SpamAndReportBan extends javax.swing.JPanel {

    /**
     * Creates new form SpamAndReportBan
     */
    public SpamAndReportBan() {
        initComponents();

        banButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CallAPIBanUser().execute();
            }
        });
    }

    private class CallAPIBanUser extends SwingWorker<String, Object> {

        @Override
        public String doInBackground() {
            try {

                String id = reportedUserText.getText();
                JSONObject o = new JSONObject();
                o.put("id", id);
                String json = o.toString();

                String api = "http://13.215.176.178:8881/admin/ban-account";

                HttpClient client = HttpClient.newHttpClient();
                HttpRequest req = HttpRequest.newBuilder()
                        .uri(new URI(api))
                        .header("Content-Type", "application/json")
                        .method("POST", HttpRequest.BodyPublishers.ofString(json))
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
                    String msg = String.valueOf(data.get("message"));
                    return msg;

                } else {
                    System.out.println("Call API Fail");
                    return "Failed ban user";
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
            return "Failed ban user";
        }

        @Override
        protected void done() {
            try {
                String msg = get();
                SwingUtilities.invokeLater(new Runnable(){
                    @Override
                    public void run()
                    {
                        JOptionPane.showMessageDialog(null,msg,"Info",JOptionPane.INFORMATION_MESSAGE);
                    }
                });
                
                
            } catch (InterruptedException ex) {
                Logger.getLogger(SpamAndReportBan.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ExecutionException ex) {
                Logger.getLogger(SpamAndReportBan.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
                   
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        reportedUserText = new javax.swing.JLabel();
        banButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        contentText = new javax.swing.JTextArea();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));
        jLabel1.setText("Reported User:");

        reportedUserText.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        reportedUserText.setForeground(new java.awt.Color(51, 51, 51));

        banButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        banButton.setForeground(new java.awt.Color(127, 127, 127));
        banButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/6.png"))); // NOI18N
        banButton.setText("Ban User");
        banButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                banButtonActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(127, 127, 127));
        jLabel3.setText("Content:");

        contentText.setEditable(false);
        contentText.setBackground(new java.awt.Color(255, 255, 255));
        contentText.setColumns(20);
        contentText.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        contentText.setForeground(new java.awt.Color(51, 51, 51));
        contentText.setLineWrap(true);
        contentText.setRows(5);
        contentText.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jScrollPane1.setViewportView(contentText);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(139, 139, 139)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 593, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(reportedUserText, javax.swing.GroupLayout.PREFERRED_SIZE, 593, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(banButton, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(195, 195, 195)))
                .addContainerGap(234, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(reportedUserText, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(banButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 9, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        super.paintComponent(grphcs);
    }

    public void setReportedUser(String name) {
        reportedUserText.setText(name);
    }

    public void setContent(String content) {
        contentText.setText(content);
    }

    private void banButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_banButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_banButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton banButton;
    private javax.swing.JTextArea contentText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel reportedUserText;
    // End of variables declaration//GEN-END:variables
}
