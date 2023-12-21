/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package components.UserOnlineChart;

import components.RegisteredChart.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import static java.util.Collections.list;
import java.util.List;

public class UserOnlineChartLine extends javax.swing.JPanel {

    private List<ModelRegisteredChartLine> model;

    public UserOnlineChartLine() {
        System.out.println("ChatRegisteredLine");
        initComponents();
        setOpaque(false);
//        setBackground(Color.WHITE);
    }

    public void initData() {
        panelUserOnlineChart.removeAllData();
        if (model != null) {
            for (ModelRegisteredChartLine data : model) {
                System.out.println(data.getName()+"  "+data.getValue());
                panelUserOnlineChart.addItem(data);
            }
        }
    }

    public List<ModelRegisteredChartLine> getModel()
    {
        return model;
    }
    
    public void setModel(List<ModelRegisteredChartLine> model) {
        this.model = model;
        initData();
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        System.out.println("ben chat ");
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        super.paintComponent(grphcs);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelUserOnlineChart = new components.UserOnlineChart.PanelUserOnlineChart();

        setBackground(new java.awt.Color(250, 250, 250));

        javax.swing.GroupLayout panelUserOnlineChartLayout = new javax.swing.GroupLayout(panelUserOnlineChart);
        panelUserOnlineChart.setLayout(panelUserOnlineChartLayout);
        panelUserOnlineChartLayout.setHorizontalGroup(
            panelUserOnlineChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 995, Short.MAX_VALUE)
        );
        panelUserOnlineChartLayout.setVerticalGroup(
            panelUserOnlineChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 623, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelUserOnlineChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelUserOnlineChart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 48, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private components.UserOnlineChart.PanelUserOnlineChart panelUserOnlineChart;
    // End of variables declaration//GEN-END:variables
}
