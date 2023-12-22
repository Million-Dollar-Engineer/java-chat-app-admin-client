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
public class User {
    public String id;
    public String username, password, fullname,address,dateOfBirth,role,sex,email,status;
    public String deleted;
    
    @SerializedName("last_active")
    public String lastActive;
    
    @SerializedName("updated_at")
    public String updatedAt;
    
    @SerializedName("create_at")
    public String createAt;
    
    
}
