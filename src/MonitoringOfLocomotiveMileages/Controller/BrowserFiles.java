package MonitoringOfLocomotiveMileages.Controller;

import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class BrowserFiles {



    public void GetFileChooser(Stage mainStage, TextField textField){

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("EXCEL FILES", "*.xls"));
        File selectedFile = fileChooser.showOpenDialog(mainStage);
        String str = selectedFile.getPath().replace("\\", "\\\\");
        textField.setText(str);


    }

}
