/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interface;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author lnt09
 */
public class LoginHistoryUser {
    @SerializedName("login_time")
    public String loginTime;
    public String ip,fullname,username;
}
