
public class Receiver {
	public Sender sender;
	public int packetsToBeReceived;
	
	Receiver()
	{
		sender = null;
		packetsToBeReceived = 100;
	}
	
	Receiver(Sender snd, int packets)
	{
		sender = snd;
		packetsToBeReceived = packets;
	}

	public void beginHandshakeProcess() {
		if (initiateHandshake(sender, this)) {
			System.out.println("Handshake between Sender and Receiver complete.");

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

	public void collectPacket(DataPacket dp) {
		if (checkForError(dp))
		{
			// You left off here
		}
		else
		{
			fixError(dp);
			collectPacket(dp);
		}
	}
	
	private DataPacket fixError(DataPacket dp) {
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
		else if (dp.getData() == null)
		{
			// Packet was lost. Fix it.
			dp = sender.retransmitPacket(dp);
		}
		else if (dp.getData().equals("Corrupt"))
		{
			// Packet was corrupted. Fix it.
			dp = sender.retransmitPacket(dp);
		}
		return dp;
	}

	private boolean checkForError(DataPacket dp)
	{
		if (dp.getData().equals("Out of order") || dp.getData().equals("Phantom") || dp.getData() == null || dp.getData().equals("Corrupt"))
		{
			return true;
		}
		return false;
	}

}
