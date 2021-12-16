import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class CompressionGui extends Application {

    Button compress, decompress;
    File originalFile, newFile;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("compressor based on huffman-coding");

        compress = new Button();
        decompress = new Button();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("src\\sample"));

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        compress.setText("compress file");
        decompress.setText("decompress file");
        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setPrefSize(300, 7);

        HBox h2 = new HBox();
        h2.getChildren().add(compress);
        h2.getChildren().add(decompress);

        HBox h3= new HBox();
        h3.getChildren().add(textArea);


        h2.setAlignment(Pos.BASELINE_CENTER);
        h2.setSpacing(5);
        h3.setAlignment(Pos.BASELINE_CENTER);
        h3.setSpacing(0);


        VBox vBox = new VBox();
        vBox.getChildren().add(h2);
        vBox.getChildren().add(h3);

        vBox.setAlignment(Pos.BASELINE_CENTER);
        vBox.setSpacing(5);

        Scene scene = new Scene(vBox, 550, 200);

        primaryStage.setScene(scene);
        primaryStage.show();

        compress.setOnAction(e -> {

            originalFile = fileChooser.showOpenDialog(new Stage());
            newFile = fileChooser.showSaveDialog(new Stage());

            if (originalFile != null && newFile != null){
                HuffmanCompression.compress(originalFile, newFile);
                textArea.setText("compressed successfully");
            }
            else {
                textArea.setText("something went wrong");
            }
        });

        decompress.setOnAction(e -> {

            originalFile = fileChooser.showOpenDialog(new Stage());
            newFile = fileChooser.showSaveDialog(new Stage());

            if (originalFile != null && newFile != null){
                HuffmanCompression.expand(originalFile, newFile);
                textArea.setText("decompressed successfully");
            }
            else {
                textArea.setText("something went wrong");
            }
        });
    }
}
