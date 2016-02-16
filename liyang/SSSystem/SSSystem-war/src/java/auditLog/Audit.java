/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package AuditLog;
import java.io.*;


/**
 *
 * @author Developer
 */
public class Audit{  // this is the start audit class

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
