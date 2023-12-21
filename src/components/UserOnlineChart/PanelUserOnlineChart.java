/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package components.UserOnlineChart;

import components.RegisteredChart.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;


public class PanelUserOnlineChart extends javax.swing.JPanel {


    private List<ModelRegisteredChartLine> list;



    public PanelUserOnlineChart() {

        System.out.println("PanelRegisteredChartLine");

        list = new ArrayList<>();

        initComponents();

        setOpaque(false);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 914, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 541, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private double getMax() {

        double max = 0;

        for (ModelRegisteredChartLine data : list) {

            if (data.getValue() > max) {

                max = data.getValue();

            }

        }

        return max;

    }



    @Override

    protected void paintComponent(Graphics grphcs) {

        System.out.println("Vao paintComponent");

        if (list.size() > 1) {

            System.out.println("Doing");

            Graphics2D g2 = (Graphics2D) grphcs;

            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int margin = 60;

            int marginWidth = 50;

            int width = getWidth() - marginWidth - margin * 2;

            int height = getHeight() - margin * 2;

            double max = getMax();

            double space = width / (list.size() - 1);

            Path2D.Float p = new Path2D.Float();

            p.moveTo(margin, getHeight() - margin);

            int x = margin;

            Font font = new Font("Arial", Font.PLAIN, 14); // Thay đổi font và kích thước theo ý muốn

            g2.setFont(font);
            g2.setColor(Color.GRAY);
            
            // Xác định số lượng khoảng mỗi tháng

            // Trục tung
            g2.drawLine(margin, getHeight() - margin, margin, 0);

            x = margin;
            
            g2.drawString("(user)", x-40, 25);
            
// Trục hoành
            g2.drawLine(margin, getHeight() - margin, getWidth() - marginWidth, getHeight() - margin);

            
            x = margin + 30;


            int y = getHeight() - margin;



            for (int i = 1; i <= 12; i++) {

                g2.fillRect(x - 1, (int) y - 5, 3, 10);

                g2.drawString(String.valueOf(i), x - 3, y + 25);

                x += space;

            }

            x-=space;

            g2.drawString("(month)", x + 20, y + 25);



            // In tháng lên các trục hoành

            for (int i = 1; i <= 12; i++) {



            }



            int numberOfMonths = 12;

            int numberOfSpaces = list.size() / numberOfMonths;



            g2.setPaint(Color.BLUE);



            x = margin + 30;

            for (ModelRegisteredChartLine data : list) {

                double location = data.getValue() * height / max;

                location = getHeight() - margin - location;

                p.lineTo(x, location);

                x += space;

            }

            p.lineTo(x - space, getHeight() - margin);

            GradientPaint gra = new GradientPaint(0, margin, new Color(6, 131, 212), 0, getHeight(), new Color(255, 255, 255, 0));

            g2.setPaint(gra);

            g2.fill(p);

            drawPoint(g2);

        }

        super.paintComponent(grphcs);

    }



    private void drawPoint(Graphics2D g2) {

        g2.setColor(new Color(6, 131, 212));



        int size = 6;

        int margin = 60;

        int marginWidth = 50;

        int width = getWidth() - marginWidth - margin * 2;

        int height = getHeight() - margin * 2;

        double max = getMax();

        double space = width / (list.size() - 1);

        int x = margin + 30;



        for (ModelRegisteredChartLine data : list) {

            double location = data.getValue() * height / max;

            location = getHeight() - margin - location;



            g2.setColor(Color.BLUE);

            g2.fillOval(x - 3, (int) location - 3, size, size);



            g2.setColor(Color.GRAY);

            g2.drawString(String.valueOf((int) data.getValue()), x - 10, (int) location - 20);

            x += space;

        }

    }



    public void addItem(ModelRegisteredChartLine item) {

        list.add(item);

        repaint();

        System.out.println("Done add item");

    }



    public void removeAllData() {

        list.clear();

        repaint();

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
