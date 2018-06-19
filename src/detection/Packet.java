package detection;

import java.math.BigInteger;

/**
 * This class abstracts a CCSDS data packet being sent to a satellite.
 * The Packet class provides operations to convert from the hex value to binary
 * and then determine the safety of the packet.
 * 
 * @author Brady Murphy (bradysm)
 * @version Jun 14, 2018
 */
public class Packet implements Comparable<Packet> {
    /**
     * packet data and safety variables
     */
    private final String hex;
    private final String binary;
    private final String packetId;


    /**
     * constructor to create a CCSDS packet object
     * 
     * @param hex
     *            data packet sent in binary
     */
    public Packet(String hex) {
        this.hex = hex;
        this.binary = hexToBinary(hex);
        this.packetId = binary.substring(3, 16); // bits 3-15 starting from 0
    }


    /**
     * getter function provided to retrieve the binary interpretation of the
     * data packet
     * 
     * @return String containing the binary packet
     */
    public String getBinary() {
        return this.binary;
    }


    /**
     * getter function provided to return the hex value of the CCSDS packet
     * 
     * @return String containing the hex packet value
     */
    public String getHex() {
        return this.hex;
    }


    /**
     * gets the ID of the packet
     * 
     * @return String containing the packet ID
     */
    public String getPacketID() {
        return this.packetId;
    }


    /**
     * Compares two packets together based on their commands
     * 
     *
     * @param packet
     *            is the packet to be compared to
     * @return 1 if this packet is greater, 0 if equal, and -1 if this packet is
     *         less than the parameter packet
     */
    @Override
    public int compareTo(Packet packet) {
        long thisID = Long.parseLong(packetId);
        long otherID = Long.parseLong(packet.packetId);
        return (int)(thisID - otherID);
    }


    /**
     * This method will return a String representing the binary version
     * 
     * @param hex
     * @return
     */
    private final String hexToBinary(String hex) {
        StringBuilder bin = new StringBuilder(new BigInteger(hex, 16).toString(
            2));
        // compensate to add zeros to the front of the conversion
        while (bin.length() % 4 != 0) {
            bin.insert(0, "0");
        }
        return bin.toString();
    }
}
