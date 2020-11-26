
public class Sender {
	private Receiver rc;
	private boolean handshakeEstablished;
	private int errorChance = 1000;

	public Sender() {
		handshakeEstablished = false;
		rc = null;
	}
	
	public void setReceiver(Receiver receiver)
	{
		rc = receiver;
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
		if (handshakeEstablished) {
			DataPacket ACK = new DataPacket("ACK", "data", 0);
			return ACK;
		} else {
			return null;
		}
	}

	public void sendDataTCP() {
		DataPacket dp;
		ErrorSimulator es = new ErrorSimulator(errorChance);
		for (int i = 0; i < rc.getPacketsToBeReceived(); i++) {
			dp = new DataPacket();
			es.deliverPacketToReceiverTCP(dp, rc);
		}
	}
	
	public void sendDataUDP()
	{
		DataPacket dp;
		ErrorSimulator es = new ErrorSimulator(errorChance);
		for (int i = 0; i < rc.getPacketsToBeReceived(); i++) {
			dp = new DataPacket();
			es.deliverPacketToReceiverUDP(dp, rc);
			
		}
	}

	public DataPacket retransmitPacket(DataPacket dp) {
		DataPacket dp2 = new DataPacket();
		return dp2;
	}

}
