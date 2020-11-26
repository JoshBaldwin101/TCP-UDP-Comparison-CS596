
public class UDPSimulator {
	private int packetsToBeSent;
	
	UDPSimulator()
	{
		packetsToBeSent = 10;
	}
	
	UDPSimulator(int packets)
	{
		packetsToBeSent = packets;
	}
	
	public void Begin() {
		Sender sender1 = new Sender();
		Receiver rc = new Receiver(sender1, packetsToBeSent);
		sender1.setReceiver(rc);
		
		rc.beginUDP(sender1);
	}
}
