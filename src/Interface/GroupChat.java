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
public class GroupChat {
    public String id,name, deleted;
    
    @SerializedName("created_at")
    public String createAt;
    
    @SerializedName("updated_at")
    public String updateAt;
}
