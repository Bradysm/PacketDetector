package detection;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.io.File;
import javafx.event.ActionEvent;

/**
 * this class is used as the controller of the events being created
 * on the front end of the application and defines how to handle
 * each event
 * 
 * @author Brady Murphy (bradysm)
 * @version Jun 15, 2018
 */
public class ValidatorController {
    /**
     * List used to determine the safety of a the system
     */
    private LinkedList<Packet> unauthPackets;

    // GUI controls which are defined in FXML
    @FXML
    private Label notificationLabel;

    @FXML
    private Label readLabel;

    @FXML
    private Label commandLabel;

    @FXML
    private Button runButton;

    @FXML
    private Button quitButton;

    @FXML
    private Button yesButton;

    @FXML
    private Button noButton;


    /**
     * Quits the program and closes the window
     * 
     * @param event
     *            quit button is clicked
     */
    @FXML
    void quitButtonPressed(ActionEvent event) {
        System.exit(0);
    }


    /**
     * begins reading in the file to determine whether a packet command has
     * been sent which could be determined as unsafe to the user
     * 
     * @param event
     *            run button is clicked
     */
    @FXML
    void runButtonPressed(ActionEvent event) {
        // disable buttons and prep GUI
        runButton.setDisable(true);
        notificationLabel.setVisible(false);
        readLabel.setVisible(true);

        unauthPackets = FileReader.readFile(new File("test.txt"));

        // decide on questionable commands
        readLabel.setVisible(false);
        commandLabel.setVisible(true);
        displayUnauth();
        yesButton.setDisable(false);
        noButton.setDisable(false);
    }


    /**
     * will display on the screen the a potentially unauthorized command
     */
    public void displayUnauth() {
        // check to see if the lsit is empty
        if (unauthPackets == null || unauthPackets.isEmpty()) {
            setYesNoDisable();
            commandLabel.setText("No unauthorized commands were sent");
            runButton.setDisable(false);
            notificationLabel.setVisible(true);
        }
        else {
            String command = unauthPackets.get(0).getHex();
            String form = String.format("%s%s%n%s", "Was ", command,
                " authorized to be sent?");
            commandLabel.setText(form);
        }
    }


    /**
     * this button will remove front entry from the unauthorized list
     * and display the next command on the GUI
     * 
     * @param event
     *            clicked button
     */
    @FXML
    void yesButtonPressed(ActionEvent event) {
        unauthPackets.remove(0);
        displayUnauth();
    }


    /**
     * Disables the yes and no buttons, and then displays on
     * the GUI that the commands have been compromised
     * 
     * @param event
     *            clicked on no button
     */
    @FXML
    void noButtonPressed(ActionEvent event) {
        setYesNoDisable();
        commandLabel.setText("You've been compromised.");
        runButton.setDisable(false);
        setYesNoDisable();

    }


    /**
     * called by the FXMLLoader to initialize the controller object
     * and instance variables within it
     */
    public void initialize() {
        readLabel.setVisible(false);
        setYesNoDisable();
        unauthPackets = null;
        commandLabel.setVisible(false);
    }


    /**
     * disables the yes and no buttons
     */
    private void setYesNoDisable() {
        yesButton.setDisable(true);
        noButton.setDisable(true);
    }

}
