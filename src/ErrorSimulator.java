import java.util.Random;

public class ErrorSimulator {
	private int errorChance;
	
	ErrorSimulator()
	{
		errorChance = 1000;
	}
	
	ErrorSimulator(int error)
	{
		errorChance = error;
	}
	
	public void deliverPacketToReceiver(DataPacket dp, Receiver receiver)
	{
		if (isThereError())
		{
			dp = inflictError(dp);
		}
		
		receiver.collectPacket(dp);
	}

	public void deliverPacketToSender(DataPacket dp)
	{
		
	}
	
	private DataPacket inflictError(DataPacket dp) {
		Random rollForError = new Random();  
        int randNum = rollForError.nextInt(3);
        
        switch(randNum)
        {
        case 0:
        	dp.simulateCorruptData();
        	break;
        case 1:
        	dp.simulateDataLoss();
        	break;
        case 2:
        	dp.simulateIncorrectOrder();
        	break;
        case 3:
        	dp.simulatePhantomPackets();
        }
        
		return dp;
	}
	
	private boolean isThereError()
    {
        int errorNum = 1;         // If the random number is this, than there is an error

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
