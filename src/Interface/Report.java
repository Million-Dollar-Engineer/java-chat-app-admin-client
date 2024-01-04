/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interface;

import com.google.gson.annotations.SerializedName;
import java.util.Date;

/**
 *
 * @author lnt09
 */
public class Report {

    public String id;

    @SerializedName("reporter_id")
    public String reporter;

    @SerializedName("accused_id")
    public String accused;

    public String reason;

    @SerializedName("created_at")
    public String createAt;

    @SerializedName("username")
    public String accusedUsername;
}
