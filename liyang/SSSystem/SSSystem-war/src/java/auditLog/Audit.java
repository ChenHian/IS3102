/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auditLog;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Chen Hian
 */
public class Audit {
    public void auditIt(String message) throws ClassNotFoundException // method declaration

    {
      
      BufferedWriter bw = null;

      try {
         bw = new BufferedWriter(new FileWriter("E:/IS3102/liyang/usageLog.txt", true)); // Change file directory according to your pc
	 bw.write(message); // passed the audit message
	 bw.newLine(); //print new line after each statement
	 bw.flush(); // flush the buffer after enter audit details.
      }

      catch (IOException ioe)
      {
	 
      }

      finally
      {                       
	 if (bw != null) try
         {
	    bw.close();
	 }

         catch (IOException ioe2)

         {
	    
	 }
      }



    
    }
    

}
