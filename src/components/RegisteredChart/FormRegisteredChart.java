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

        setBackground(new java.awt.Color(250, 250, 250));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chatRegisteredLine, javax.swing.GroupLayout.PREFERRED_SIZE, 993, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(80, Short.MAX_VALUE)
                .addComponent(chatRegisteredLine, javax.swing.GroupLayout.PREFERRED_SIZE, 642, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private components.RegisteredChart.ChatRegisteredLine chatRegisteredLine;
    // End of variables declaration//GEN-END:variables
}
