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
public class Related {
        public String id, username, friend, friendOfFriend;
        
    @SerializedName("created_at")
    public String createdAt;
}
