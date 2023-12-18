/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package components.RegisteredChart;

import java.util.ArrayList;
import java.util.List;

public class FormRegisteredChart extends javax.swing.JPanel {

    public FormRegisteredChart() {
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
        chatRegisteredLine.setModel(list);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chatRegisteredLine = new components.RegisteredChart.ChatRegisteredLine();
        jLabel1 = new javax.swing.JLabel();
        registeredChartSearching2 = new components.RegisteredChart.RegisteredChartSearching();

        setBackground(new java.awt.Color(250, 250, 250));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));
        jLabel1.setText("User Manager");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(registeredChartSearching2, javax.swing.GroupLayout.PREFERRED_SIZE, 976, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(chatRegisteredLine, javax.swing.GroupLayout.PREFERRED_SIZE, 993, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(23, 23, 23)
                            .addComponent(jLabel1))))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(registeredChartSearching2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chatRegisteredLine, javax.swing.GroupLayout.PREFERRED_SIZE, 625, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private components.RegisteredChart.ChatRegisteredLine chatRegisteredLine;
    private javax.swing.JLabel jLabel1;
    private components.RegisteredChart.RegisteredChartSearching registeredChartSearching2;
    // End of variables declaration//GEN-END:variables
}
