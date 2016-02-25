/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginAuthentication;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;




public class Authentication {
    
    
    

    public static boolean login(String username, String password) {
        Connection con = null;
        PreparedStatement ps = null;
        //System.out.print(password);
        try {
            con = Database.getConnection();
            //System.out.println("authentication "+ con.getCatalog());
            ps = con.prepareStatement(
                    "SELECT email, password FROM staffaccount WHERE email= ? and password= ? ");
            ps.setString(1, username);
            
            //Uncomment line below for cleartext password
            //ps.setString(2, password);
            
            String passwordMD5 = "";
                try { 
                    MessageDigest md5 = MessageDigest.getInstance("MD5");
                    byte[] tmp = password.getBytes();
                    md5.update(tmp);
                    passwordMD5 = byteArrToString(md5.digest());
                }
                catch (NoSuchAlgorithmException ex) {  
                }
                
            //Comment line below for cleartext password
            ps.setString(2, passwordMD5);
            
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) { //found
                System.out.println(rs.getString("email"));
                return true;
            }
            else {
                return false;
            }
        }
        catch (Exception ex) {
            System.out.println("Error in login() -->" + ex.getMessage());
            ex.printStackTrace();
        }
        return false;
    }

    private static String byteArrToString(byte[] b) {
       String res = null; 
       StringBuffer sb = new StringBuffer(b.length * 2); 
       for (int i = 0; i < b.length; i++){
           int j = b[i] & 0xff; 
           if (j < 16) { 
               sb.append('0'); 
           } 
           sb.append(Integer.toHexString(j)); 
       } 
       res = sb.toString();
       return res.toUpperCase(); 
    }

    
}