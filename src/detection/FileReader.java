/**
 * 
 */
package detection;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class is used to read in the data file and determine
 * whether all of the packets being sent are valid to the
 * users specification of valid
 * 
 * @author Brady Murphy (bradysm)
 * @version Jun 16, 2018
 */
public class FileReader {

    /**
     * this will read through the file given and determine if any of the
     * commands
     * don't meet the white list. If so it will return false and warn the user
     * that an invalid command was sent to the satellite. A pop will cup up
     * defining what line the command is on so the user can go to the file
     * and examine it
     * 
     * @param file
     *            that will be read in from the scanner. This file must meet the
     *            format defined as of 6/20/2018 by ASSIST
     */
    public final static LinkedList<Packet> readFile(File file) {
        Scanner in = null; // this will be used to read in the data
        boolean fileOpened = true;
        LinkedList<Packet> unauthPackets = new LinkedList<>();

        // try to open the file
        try {
            in = new Scanner(file);
        }
        catch (FileNotFoundException e) {
            fileOpened = false;
            System.out.println(e.getMessage());
        }

        if (fileOpened) {
            // read until there is no more telemetry commands left in the file
            Detector detector = new Detector();
            boolean valid = true;
            while (in.hasNext()) {
                // read in the data and split it at the spaces
                String[] data = in.nextLine().split(" ");

                // parse it to the correct expectations and validate
                String hex = data[1];
                Packet packet = new Packet(hex);
                valid = detector.determineValidity(packet);
                if (!valid) {
                    unauthPackets.add(packet);
                }
            }
            in.close();
        }
        // return potential unauthorized commands
        return unauthPackets;
    }

}
