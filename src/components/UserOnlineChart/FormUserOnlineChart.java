/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package components.UserOnlineChart;

import Interface.OnlineChart;
import com.google.gson.Gson;
import components.RegisteredChart.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.net.http.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FormUserOnlineChart extends javax.swing.JPanel {

    public FormUserOnlineChart() {
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
//        userOnlineChartLine.setModel(list);

        new CallAPIUserOnlineChart().execute();
        userOnlineChartSearching.addListenerSearchButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CallAPIUserOnlineChart().execute();
            }
        });
    }

    private class CallAPIUserOnlineChart extends SwingWorker<String, OnlineChart[]> {

        @Override
        protected String doInBackground() {
            try {
                String year = userOnlineChartSearching.getYear();
                String api = "http://13.215.176.178:8881/admin/users-active-each-month" + "?year=" + year;

                HttpClient client = HttpClient.newHttpClient();

                HttpRequest req = HttpRequest.newBuilder()
                        .uri(new URI(api))
                        .GET()
                        .build();

                HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());

                int resCode = res.statusCode();
                String body = res.body();

                if (resCode == HttpURLConnection.HTTP_OK) {
                    System.out.println("Call API thanh cong");

                    JSONParser par = new JSONParser();
                    JSONObject data = (JSONObject) par.parse(body);
                    JSONArray data1 = (JSONArray) data.get("data");

                    String json = data1.toString();

                    Gson gson = new Gson();

                    OnlineChart[] list = gson.fromJson(json, OnlineChart[].class);

                    publish(list);
                } else {
                    System.out.println("Call API that bai");

                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException ex) {
                Logger.getLogger(FormUserOnlineChart.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(FormUserOnlineChart.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(FormUserOnlineChart.class.getName()).log(Level.SEVERE, null, ex);
            }
            return "Failed";
        }

        @Override
        protected void process(List<OnlineChart[]> chunks) {

            List<ModelRegisteredChartLine> list = new ArrayList<>();

            int[] month = new int[13];

            for (int i = 1; i <= 12; i++) {
                month[i] = 0;
            }

            OnlineChart[] data = chunks.get(chunks.size() - 1);

            for (OnlineChart chart : data) {
                System.out.println("Month: " + chart.month + " User: " + chart.number);
                month[chart.month] = chart.number;
            }

            for (int i = 1; i <= 12; i++) {
                list.add(new ModelRegisteredChartLine("Nothing", month[i]));
            }
            userOnlineChartLine.setModel(list);

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        userOnlineChartSearching = new components.UserOnlineChart.UserOnlineChartSearching();
        userOnlineChartLine = new components.UserOnlineChart.UserOnlineChartLine();

        setBackground(new java.awt.Color(250, 250, 250));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));
        jLabel1.setText("User Online Chart");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(userOnlineChartLine, javax.swing.GroupLayout.PREFERRED_SIZE, 987, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(userOnlineChartSearching, javax.swing.GroupLayout.PREFERRED_SIZE, 978, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(823, 823, 823)))))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(userOnlineChartSearching, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userOnlineChartLine, javax.swing.GroupLayout.PREFERRED_SIZE, 662, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private components.UserOnlineChart.UserOnlineChartLine userOnlineChartLine;
    private components.UserOnlineChart.UserOnlineChartSearching userOnlineChartSearching;
    // End of variables declaration//GEN-END:variables
}
