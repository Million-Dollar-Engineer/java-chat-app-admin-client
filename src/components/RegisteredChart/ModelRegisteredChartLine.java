/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package components.RegisteredChart;

import java.awt.Color;

public class ModelRegisteredChartLine {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public double getValue() {
        return value;
    }

    public ModelRegisteredChartLine(String name, double vaule) {
        this.name = name;
        this.value = vaule;
    }

    public void setVaule(double vaule) {
        this.value = vaule;
    }
    private String name;
    private double value;
    private Color color;
    
}
