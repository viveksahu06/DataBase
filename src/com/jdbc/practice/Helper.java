package com.jdbc.practice;

import java.awt.Image;
import java.io.InputStream;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Blob;

public class Helper {

    public static ImageIcon getImageIconByID(int id, Connection con) {
        ImageIcon icon = null;
        try {
            String q = "select pic from images where id=?";
            PreparedStatement pstmt = con.prepareStatement(q);
            pstmt.setInt(1, id);
            ResultSet set = pstmt.executeQuery();

            if (set.next()) {
                Blob b = set.getBlob("pic");
                InputStream is = b.getBinaryStream();
                Image image = ImageIO.read(is);
                icon = new ImageIcon(image);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } 
        return icon;
    }
}
