import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.io.*;

public class Controller {

    String xml, xmlOut;

    File input, output;

    @FXML
    TextArea originalTA = new TextArea();

    @FXML
    TextArea resultTA = new TextArea();

    public void onLoadXMLFile(ActionEvent e) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("src\\sample"));

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        FileChooser.ExtensionFilter extFilter2 = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.getExtensionFilters().add(extFilter2);

        input = fileChooser.showOpenDialog(new Stage());

        InputStream orgInStream = System.in;

        try {
            System.setIn(new FileInputStream(input));
        } catch (FileNotFoundException ee) {
            ee.printStackTrace();
        }
        xml = CustomStdIn.readString();

        CustomStdIn.close();
        System.setIn(orgInStream);

        originalTA.setText(xml);
        xml = originalTA.getText();
    }

    public void onSaveXMLFile(ActionEvent e) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("src\\sample"));

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        FileChooser.ExtensionFilter extFilter2 = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.getExtensionFilters().add(extFilter2);

        output = fileChooser.showSaveDialog(new Stage());

        PrintStream orgOutStream = System.out;

        try {
            System.setOut(new PrintStream(output));
        } catch (FileNotFoundException ee) {
            ee.printStackTrace();
        }
        CustomStdOut.write(xmlOut);

        CustomStdOut.close();
        System.setOut(orgOutStream);
    }

    public void onJSON(ActionEvent e) {
        xml = originalTA.getText();
        xmlOut = JSON.XMLToJSON(xml);
        resultTA.setText(xmlOut);
    }

    public void OnConsistency(ActionEvent e) {
        xml = originalTA.getText();
        ConsistencyCheck checker = new ConsistencyCheck(xml);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Consistency check");
        alert.setHeaderText("Consistency check");

        if (checker.checkBalancedTags() && checker.checkValidDataPositions()) {
            alert.setContentText("XML file is consistent");
        } else {

            alert.setContentText("XML file is NOT consistent");
        }
        alert.showAndWait();
    }

    public void onFormatting(ActionEvent e){
        xml = originalTA.getText();
        xmlOut = Formatting.format(xml);
        resultTA.setText(xmlOut);
    }

    public void onMinifying(ActionEvent e){
        xml = originalTA.getText();
        xmlOut = Minifying.minify(xml);
        resultTA.setText(xmlOut);
    }

    public void onCompress(ActionEvent e) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("src\\sample"));

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        FileChooser.ExtensionFilter extFilter2 = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.getExtensionFilters().add(extFilter2);

        output = fileChooser.showSaveDialog(new Stage());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("compression status");
        alert.setHeaderText("compression status");

        if (input != null && output != null) {
            HuffmanCompression.compress(input, output);
            alert.setContentText("compression completed");

            InputStream orgInStream = System.in;

            try {
                System.setIn(new FileInputStream(output));
            } catch (FileNotFoundException ee) {
                ee.printStackTrace();
            }
            xmlOut = CustomStdIn.readString();

            CustomStdIn.close();
            System.setIn(orgInStream);

            resultTA.setText(xmlOut);
        } else {
            alert.setContentText("something went wrong");
        }
        alert.showAndWait();
    }

    public void onDecompress(ActionEvent e) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("src\\sample"));

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        FileChooser.ExtensionFilter extFilter2 = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.getExtensionFilters().add(extFilter2);

        File originalFile, newFile;

        originalFile = fileChooser.showOpenDialog(new Stage());
        newFile = fileChooser.showSaveDialog(new Stage());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("decompression status");
        alert.setHeaderText("decompression status");
        if (originalFile != null && newFile != null) {
            HuffmanCompression.expand(originalFile, newFile);
            alert.setContentText("decompression completed");

            InputStream orgInStream = System.in;

            try {
                System.setIn(new FileInputStream(originalFile));
            } catch (FileNotFoundException ee) {
                ee.printStackTrace();
            }
            xmlOut = CustomStdIn.readString();

            CustomStdIn.close();

            originalTA.setText(xmlOut);

            try {
                System.setIn(new FileInputStream(newFile));
            } catch (FileNotFoundException ee) {
                ee.printStackTrace();
            }
            xmlOut = CustomStdIn.readString();

            CustomStdIn.close();
            System.setIn(orgInStream);

            resultTA.setText(xmlOut);
        } else {
            alert.setContentText("something went wrong");
        }
        alert.showAndWait();
    }
}
