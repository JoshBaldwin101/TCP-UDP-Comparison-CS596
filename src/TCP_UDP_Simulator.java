/*  ---------------------------------------------------------------
*   CS 596 - Wireless Networks - TCP vs UDP
*   Contributors:
*           Josh Baldwin
*           Antonino Brunelle
*           MatthEW Magoon
*           Francisco Melendez Hernandez
*   --------------------------------------------------------------- 
*/

// Imports
import java.util.*;
import java.io.*;

/*  ---------------------------------------------------------------
*   Purpose:
*   To simulate the differences between TCP and UDP.and
*
*   What we're (hoping to find/prove):
*
*   UDP Pros:
*    - UDP is faster.
*    - Does not require a handshake. 
*
*   UDP Cons:
*    - UDP is subject to more errors. 
*
*   TCP Pros:
*    - TCP has no errors.
*
*   TCP Cons:
*    - TCP is slower.
*    - Requires a handshake. 
*   ---------------------------------------------------------------
*/  

/*  ---------------------------------------------------------------
*   THE PLAN:
*   ---------------------------------------------------------------
*   We should simulate the transfer of data and use both UDP and TCP (in the form of methods) to do it. 
*
*   I'm thinking we should make a .txt file, then populate the .txt file with basically random characters and numbers aka hashcode. 
*   I'm talking hundreds of thousands maybe even millions of characters. That way it actually takes a MEASURABLE amount of time to complete the program. 
*   Then, we'll use UDP and TCP to copy the characters from one .txt file to another. 
*   Then, we'll compare how many errors are in UDP and how much time it took UDP and TCP to complete this process (we don't have to error-check TCP since we'll have error checking).
*
*   Simluating Errors:
*   For UDP - We should just copy each character and during every character copy theres a 1/1000 (subject to change) that a character will be miscopied. 
*             This is intended to simulate data loss.
*   For TCP - We should transfer each character and simulate (1/1000) an error rate same as UDP then have a correction system which essentially starts over and checks what is done
*             and checks from index 0 to make "find" where the error is and then correctly copy it. Keep in mind, there should be the risk of data loss happening again in this step.
*
*   TCP Error Checking Expanded:
*   So what we should do is copy each character and if that 1/1000 chance happens then we stop, go back to index 0, check each and every character making sure it's right,
*   then once we find a character that was incorrectly copied, we replace the character with the correct one. Then it continues and repeats this process.
*   ---------------------------------------------------------------
*/

public class TCP_UDP_Simulator
{
    public int errorChance = 1000;  // (1 / errorChance) chance of hitting an error

    public static void main(String[]args) throws IOException
    {
    	
    }

    public boolean isThereError()
    {
        // This method will be called for each character. 
        // Use the public double at the top of this class "pctOfError" which contains the error rate. This var should determine if there's an error or not. 
        // Returns true if 1/1000 chance happens. 
        // Returns false 999/1000 times. 

        int errorNum = 420;         // If the random number is this, than there is an error

        Random rollForError = new Random();  
        int randNum = rollForError.nextInt(errorChance);        // generates 0 - 999
        
        if (randNum == errorNum)
        {
            System.out.print("Error number " + errorNum + " was rolled and an error occured");
            return true;
        }
        return false;
    }
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    
