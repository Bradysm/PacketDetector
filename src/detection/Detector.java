package detection;

import java.util.HashMap;

/**
 * Class used to detect whether a packet is valid and on the white list.
 * 
 * @author Brady Murphy (bradysm)
 * @version Jun 14, 2018
 */
public final class Detector {
    /**
     * White list containing valid packets
     */
    private Whitelist whitelist;


    /**
     * Constructor creating a Detector object users of this program
     * should insert valid commands into the constructor for the creation
     * of the white list
     */
    public Detector() {
        // add the rest of the white list commands here
        whitelist = new Whitelist();
        whitelist.addToList(new Packet("1706C00000010020"));

    }


    /**
     * Determines the validity of a data packet. This will return a boolean
     * value based on if a packet is a valid length and on the white list.
     * If either of these conditions is not true, false will be returned
     * 
     * @param packet
     *            data packet to determine validity
     * @return boolean value representing the validity of the packet
     */
    public boolean determineValidity(Packet packet) {
        // check length
        if (packet.getBinary().length() > 70) {
            return false;
        }
        // check if on white list
        return whitelist.determineValidity(packet);
    }
    
    
    
    /**
     * This class is used to create a white list of valid data
     * commands and headers. It is used as inner class for security
     * reasons so attackers cannot try to create a class to mimic 
     * a Detector object
     * 
     * @author Brady Murphy (bradysm)
     * @version Jun 19, 2018
     */
    private class Whitelist {
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

}
