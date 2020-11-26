public class TCPSimulator {
	private int packetsToBeSent;

	TCPSimulator() {
		packetsToBeSent = 10;
		
	}

	TCPSimulator(int packets) {
		packetsToBeSent = packets;
	}

	public void Begin() {
		Sender sender1 = new Sender();
		Receiver rc = new Receiver(sender1, packetsToBeSent);
		
		rc.beginHandshakeProcess();
	}
}