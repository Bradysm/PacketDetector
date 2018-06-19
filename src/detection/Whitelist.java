/**
 * 
 */
package detection;

import java.util.HashMap;

/**
 * This class is used to create a white list of valid data
 * commands and headers
 * 
 * @author Brady Murphy (bradysm)
 * @version Jun 19, 2018
 */
public class Whitelist {
    /**
     * hash map is used to provided constant time
     */
    private HashMap<String, Packet> wl;


    /**
     * constructor used to create a white list
     */
    public Whitelist() {
        wl = new HashMap<String, Packet>();
    }


    /**
     * adds a packet to the white list
     * 
     * @param packet
     *            CCSDS packet
     */
    public void addToList(Packet packet) {
        wl.put(packet.getPacketID(), packet);
    }


    /**
     * this method is used to determine if a packet is valid on the white list
     * A boolean value will be returned representing its validity
     * 
     * @param packet
     *            CCSDS command packet
     * @return boolean value, true if valid
     */
    public boolean determineValidity(Packet packet) {
        Packet result = wl.get(packet.getPacketID());
        return (result != null) ? true : false;

    }

}
