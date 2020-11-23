public class TCPSimulator {
	private int packetsToBeSent;

	TCPSimulator() {
		packetsToBeSent = 10;
		DataPacket[] dataPacketArray = new DataPacket[packetsToBeSent];
	}

	TCPSimulator(int packets) {
		packetsToBeSent = packets;
	}

	public void populateArray(DataPacket[] arr) {
		String standardData = "0101011100101011110001010111001010111100010101110010101111000101011100101011110001010111001010111100010101110010101111000101011100101011110001010111001010111100";
		String headerLead = "Packet #";

		for (int i = 0; i < arr.length; i++) {
			arr[i] = new DataPacket(headerLead + i, standardData, i);
		}
	}
}