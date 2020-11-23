import java.util.Random;

public class DataPacket {
	// If you make a DataPacket... PLEASE use the constructor with multiple
	// arguments

	private String header; // Basically a name for the packet
	private String data; // Simply the data it carries (bits) - Consists of 20 bytes of data in real life
	private int packetNumber;

	DataPacket() {
		// Constructor - This code runs upon construction
		header = this.createHeader();
		data = this.createData();
		packetNumber = this.generatePacketNumber();
	}

	DataPacket(String desiredHeader, String desiredData, int desiredPacketNumber) {
		header = desiredHeader;
		data = desiredData;
		packetNumber = desiredPacketNumber;
	}
	
	public boolean rollForError()
	{
		return false;
	}

	private String createHeader() {
		return "DataPacket" + packetNumber;
	}
	
	public String getHeader()
	{
		return header;
	}

	private String createData() {
		// The following bits are 160 bits long (aka 20 bytes)
		return "0101011100101011110001010111001010111100010101110010101111000101011100101011110001010111001010111100010101110010101111000101011100101011110001010111001010111100";
	}
	
	public String getData()
	{
		return data;
	}

	private int generatePacketNumber() {
		Random rollPacketNumber = new Random();
		return rollPacketNumber.nextInt(6000000);
	}

	// We should have a 1/1000 chance that ONE of the following methods happens
	public void simulateDataLoss() {
		// Simulate data loss
		data = null;
	}

	public void simulateIncorrectOrder() {
		// Simulate incorrect order
		data = "Out of order";
	}

	public void simulateCorruptData() {
		// Simulate corrupt data - 160 zeroes to simulate corrupt data
		data = "Corrupt";
	}

	public void simulatePhantomPackets() {
		// Simulate phantom packets
		data = "Phantom";
	}
}