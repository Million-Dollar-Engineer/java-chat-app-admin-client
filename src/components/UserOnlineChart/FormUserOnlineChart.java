/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package components.UserOnlineChart;

import components.RegisteredChart.*;
import java.util.ArrayList;
import java.util.List;

public class FormUserOnlineChart extends javax.swing.JPanel {

    public FormUserOnlineChart() {
        initComponents();
        System.out.println("FormRegisteredChart");
        initData();
        setVisible(true);
    }

    public void initData() {
        List<ModelRegisteredChartLine> list = new ArrayList<>();
        list.add(new ModelRegisteredChartLine("Friday", 125));

        list.add(new ModelRegisteredChartLine("Monday", 10));
        list.add(new ModelRegisteredChartLine("Tuesday", 150));
        list.add(new ModelRegisteredChartLine("Wednesday", 80));
        list.add(new ModelRegisteredChartLine("Thursday", 100));
        list.add(new ModelRegisteredChartLine("Friday", 125));
        list.add(new ModelRegisteredChartLine("Saturday", 80));
        list.add(new ModelRegisteredChartLine("Sunday", 200));
        list.add(new ModelRegisteredChartLine("Saturday", 80));
        list.add(new ModelRegisteredChartLine("Sunday", 200));
        list.add(new ModelRegisteredChartLine("Saturday", 80));
        list.add(new ModelRegisteredChartLine("Sunday", 200));
        userOnlineChartLine.setModel(list);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        userOnlineChartSearching2 = new components.UserOnlineChart.UserOnlineChartSearching();
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
                            .addComponent(userOnlineChartSearching2, javax.swing.GroupLayout.PREFERRED_SIZE, 978, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(userOnlineChartSearching2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userOnlineChartLine, javax.swing.GroupLayout.PREFERRED_SIZE, 662, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private components.UserOnlineChart.UserOnlineChartLine userOnlineChartLine;
    private components.UserOnlineChart.UserOnlineChartSearching userOnlineChartSearching2;
    // End of variables declaration//GEN-END:variables
}
