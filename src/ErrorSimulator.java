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
	
	public void deliverPacketToReceiverTCP(DataPacket dp, Receiver receiver)
	{
		if (isThereError())
		{
			dp = inflictError(dp);
		}
		
		receiver.collectPacketTCP(dp);
	}

	public void deliverPacketToSender(DataPacket dp, Sender sender)
	{
		sender.sendACK();
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
        default:
        	dp.simulateCorruptData();
        	break;
        }
        
		return dp;
	}
	
	private boolean isThereError()
    {
        int errorNum = 5;         // If the random number is this, than there is an error

        Random rollForError = new Random();  
        int randNum = rollForError.nextInt(errorChance);        // generates 0 - 999
        
        if (randNum == errorNum)
        {
            return true;
        }
        return false;
    }

	public void deliverPacketToReceiverUDP(DataPacket dp, Receiver rc) {
		/*
		if (isThereError())
		{
			dp = inflictError(dp);
		}
		*/
		
		rc.collectPacketUDP(dp);
	}
}
