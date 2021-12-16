import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;

public class Controller {


    @FXML
    Button button = new Button();

    @FXML
    TextArea mainTA = new TextArea();

    @FXML
    TextArea resultTA = new TextArea();

    public void JSONButtonHandler(ActionEvent e) {
        String xml = mainTA.getText();
        resultTA.setText(JSON.XMLToJSON(xml));
    }

    public void onCompress(ActionEvent e) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("src\\sample"));

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        File originalFile, newFile;

        originalFile = fileChooser.showOpenDialog(new Stage());
        newFile = fileChooser.showSaveDialog(new Stage());

        if (originalFile != null && newFile != null) {
            HuffmanCompression.compress(originalFile, newFile);
            mainTA.setText("compressed successfully");
        } else {
            mainTA.setText("something went wrong");
        }
    }

    public void onDecompress(ActionEvent e) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("src\\sample"));

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        File originalFile, newFile;

        originalFile = fileChooser.showOpenDialog(new Stage());
        newFile = fileChooser.showSaveDialog(new Stage());

        if (originalFile != null && newFile != null) {
            HuffmanCompression.expand(originalFile, newFile);
            mainTA.setText("decompressed successfully");
        } else {
            mainTA.setText("something went wrong");
        }
    }
}
