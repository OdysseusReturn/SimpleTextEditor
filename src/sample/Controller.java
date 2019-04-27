package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button newFileButton;

    @FXML
    private Button openFileButton;

    @FXML
    private Button saveFileButton;

    @FXML
    private Button closeFileButton;

    @FXML
    private TextArea textArea;

    @FXML
    private void createNewFile(ActionEvent event) {
        try {

            FileOutputStream fos = new FileOutputStream("/Users/admin/Desktop/note.txt", false); //change path
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void openFile(ActionEvent event) {
        StringBuffer stringBuffer = new StringBuffer();
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get("/Users/admin/Desktop/note.txt"), StandardCharsets.UTF_8); //add line wrapping
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(String line: lines){
            stringBuffer.append(line);
        }

        String inStr = stringBuffer.toString();
        textArea.setText(inStr);

    }
        @FXML
    private void saveFile(ActionEvent event) {
        String outText = textArea.getText();
        try {

            FileOutputStream fos = new FileOutputStream("/Users/admin/Desktop/note.txt", false); //change path
            byte[] buffer = outText.getBytes();

            fos.write(buffer, 0, buffer.length);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void closeFile(ActionEvent event) {
        Platform.exit();
    }
}