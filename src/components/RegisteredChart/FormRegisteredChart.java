/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package components.RegisteredChart;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingWorker;
import java.net.http.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FormRegisteredChart extends javax.swing.JPanel {

    public FormRegisteredChart() {
        initComponents();
        System.out.println("FormRegisteredChart");
        initData();
        setVisible(true);
    }

    public void initData() {
//        List<ModelRegisteredChartLine> list = new ArrayList<>();
//        list.add(new ModelRegisteredChartLine("Friday", 125));
//
//        list.add(new ModelRegisteredChartLine("Monday", 10));
//        list.add(new ModelRegisteredChartLine("Tuesday", 150));
//        list.add(new ModelRegisteredChartLine("Wednesday", 80));
//        list.add(new ModelRegisteredChartLine("Thursday", 100));
//        list.add(new ModelRegisteredChartLine("Friday", 125));
//        list.add(new ModelRegisteredChartLine("Saturday", 80));
//        list.add(new ModelRegisteredChartLine("Sunday", 200));
//        list.add(new ModelRegisteredChartLine("Saturday", 80));
//        list.add(new ModelRegisteredChartLine("Sunday", 200));
//        list.add(new ModelRegisteredChartLine("Saturday", 80));
//        list.add(new ModelRegisteredChartLine("Sunday", 200));
//        chatRegisteredLine.setModel(list);
        new CallAPIRegisteredChart().execute();

        registeredChartSearching.addListenerSearchButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CallAPIRegisteredChart().execute();
            }
        });
    }

    private class CallAPIRegisteredChart extends SwingWorker<String, JSONArray> {

        @Override
        public String doInBackground() {

            String api = "http://13.215.176.178:8881/admin/users-each-month" + "?year=" + registeredChartSearching.getYear();

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

                    publish(list);

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
        public void process(List<JSONArray> result) {
            JSONArray data = result.get(result.size() - 1);
            int[] month = new int[13];

            // Initalize
            for (int i = 1; i <= 12; i++) {
                month[i] = 0;
            }

            for (int i = 0; i < data.size(); i++) {
                JSONObject chunk = (JSONObject) data.get(i);
                int m = Integer.valueOf((String) chunk.get("month"), 10);
                int numberUser = Integer.valueOf(String.valueOf(chunk.get("number_of_user")));
                System.out.println("Month: " + m + " User: " + numberUser);

                month[m] = numberUser;
            }

            List<ModelRegisteredChartLine> list = new ArrayList<>();
            for (int i = 1; i <= 12; i++) {
                list.add(new ModelRegisteredChartLine("Nothing", month[i]));
            }
            chatRegisteredLine.setModel(list);

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chatRegisteredLine = new components.RegisteredChart.ChatRegisteredLine();
        jLabel1 = new javax.swing.JLabel();
        registeredChartSearching = new components.RegisteredChart.RegisteredChartSearching();

        setBackground(new java.awt.Color(250, 250, 250));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));
        jLabel1.setText("Registered Chart");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(registeredChartSearching, javax.swing.GroupLayout.PREFERRED_SIZE, 976, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(23, 23, 23)
                            .addComponent(jLabel1)
                            .addGap(828, 828, 828)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(chatRegisteredLine, javax.swing.GroupLayout.PREFERRED_SIZE, 1134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(registeredChartSearching, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(chatRegisteredLine, javax.swing.GroupLayout.PREFERRED_SIZE, 625, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private components.RegisteredChart.ChatRegisteredLine chatRegisteredLine;
    private javax.swing.JLabel jLabel1;
    private components.RegisteredChart.RegisteredChartSearching registeredChartSearching;
    // End of variables declaration//GEN-END:variables
}
