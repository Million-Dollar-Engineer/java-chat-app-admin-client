/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package components.userManager.ListFriend;

import components.userManager.LoginHistory.*;
import components.GroupChatList.ViewAllMember.*;
import components.GroupChatList.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author lnt09
 */
public class UserManagerListFriendTableHeader extends JLabel {
    
    public UserManagerListFriendTableHeader(String text)
    {
        super(text);
        setOpaque(true);
        setBackground(Color.WHITE);
        setFont(new Font("sansserif",1,12));
        setForeground(new Color(102,102,102));
        setBorder(new EmptyBorder(10,7,10,7));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        g.setColor(new Color(230,230,230));
        g.drawLine(0,getHeight()-1,getWidth(),getHeight()-1);
    }
    
}
