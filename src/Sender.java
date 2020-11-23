
public class Sender {
	private Receiver rc;
	private boolean handshakeEstablished;

	public Sender() {
		handshakeEstablished = false;
		rc = null;
	}

	public boolean handshake(Receiver receiver) {
		if (receiver == null) {
			return false;
		} else {
			rc = receiver;
		}
		return true;
	}

	public DataPacket sendACK(Sender snd, Receiver rc2) {
		if (handshake(rc2)) {
			DataPacket ACK = new DataPacket("ACK", "data", 0);
			handshakeEstablished = true;
			return ACK;
		} else {
			return null;
		}

	}

	public DataPacket sendACK() {
		if (handshakeEstablished)
		{
			DataPacket ACK = new DataPacket("ACK", "data", 0);
			return ACK;
		}
		else
		{
			return null;
		}
	}
	
	public void sendDataTCP()
	{
		
	}

	public DataPacket retransmitPacket(DataPacket dp) {
		DataPacket dp2 = new DataPacket();
		return dp2;
	}

}
