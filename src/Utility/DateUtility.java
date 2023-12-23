/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utility;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author lnt09
 */
public class DateUtility {
    
    public static String format(String inputDateString)
    {
         // Chuyển đổi chuỗi thành đối tượng Date
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = inputFormat.parse(inputDateString);

            // Định dạng lại thành chuỗi mới
            SimpleDateFormat outputFormat = new SimpleDateFormat(outputDateFormat);
            String outputDateString = outputFormat.format(date);
    }
}
