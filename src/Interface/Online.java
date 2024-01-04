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
public class Online {

    public String id, username;

    @SerializedName("access_times")
    public String accessTime;

    @SerializedName("chatted_people")
    public String chattedPeople;

    @SerializedName("chatted_group")
    public String chattedGroup;
    
    
    @SerializedName("created_at")
    public String createdAt;
}
