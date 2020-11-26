
public class Receiver {
	public Sender sender;
	public int packetsToBeReceived;
	public int errorCount;
	public int packetsReceived;
	public DataPacket[] dataPacketsCollected;
	
	Receiver()
	{
		sender = null;
		packetsToBeReceived = 100;
		errorCount = 0;
		packetsReceived = 0;
		dataPacketsCollected = new DataPacket[packetsToBeReceived];
	}
	
	Receiver(Sender snd, int packets)
	{
		sender = snd;
		packetsToBeReceived = packets;
		errorCount = 0;
		packetsReceived = 0;
		dataPacketsCollected = new DataPacket[packetsToBeReceived];
	}
	
	public int getPacketsToBeReceived()
	{
		return packetsToBeReceived;
	}

	public void beginHandshakeProcess() {
		if (initiateHandshake(sender, this)) {
			errorCount = 0;
			packetsReceived = 0;
			System.out.println("Handshake between Sender and Receiver complete.");
			sender.sendDataTCP();

		} else {
			System.out.println("Handshake between Sender and Receiver has FAILED.");
		}
	}

	private boolean initiateHandshake(Sender snd, Receiver rc) {
		if (snd.sendACK(snd, rc).getHeader().equals("ACK")) {
			return true;
		} else {
			return false;
		}
	}

	public void collectPacketTCP(DataPacket dp) {
		if (checkForError(dp))
		{
			dp = fixError(dp);
			collectPacketTCP(dp);
		}
		else
		{
			dataPacketsCollected[packetsReceived] = dp;
			packetsReceived++;
		}
	}
	
	private DataPacket fixError(DataPacket dp) { // This is essentially checkSum in TCP
		this.errorCount++;
		if(dp.getData().equals("Out of order"))
		{
			// Packet is out of order. Fix it.
			dp = sender.retransmitPacket(dp);
		}
		else if (dp.getData().equals("Phantom"))
		{
			// Packet is a phantom packet. Fix it.
			dp = sender.retransmitPacket(dp);
		}
		else if (dp.getData() == "Empty")
		{
			// Packet was lost. Fix it.
			dp = sender.retransmitPacket(dp);
		}
		else if (dp.getData().equals("Corrupt"))
		{
			// Packet was corrupted. Fix it.
			dp = sender.retransmitPacket(dp);
		}
		System.out.println(dp.getHeader() + " has been fixed.");
		System.out.println("Error count = " + errorCount);
		return dp;
	}

	private boolean checkForError(DataPacket dp)
	{
		if (dp.getData().equals("Out of order") || dp.getData().equals("Phantom") || dp.getData().equals("Empty") || dp.getData().equals("Corrupt"))
		{
			return true;
		}
		return false;
	}

	public void beginUDP(Sender send) {
		send.sendDataUDP();
	}

	public void collectPacketUDP(DataPacket dp) {
		dataPacketsCollected[packetsReceived] = dp;
		packetsReceived++;
	}

}
