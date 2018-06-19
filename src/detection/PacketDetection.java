package detection;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main application class that loads and displays the packet validator
 * application
 * 
 * @author Brady Murphy (bradysm)
 * @version Jun 15, 2018
 */
public class PacketDetection extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(
                "PacketDetection.fxml"));

            Scene scene = new Scene(root); // attach scene graph to scene
            stage.setTitle("Brady Murphy's Packet Validator");
            stage.setScene(scene); // attach the scene to the stage
            stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * used as the runner method to create a Packet validator application
     * 
     * @param args
     *            command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
