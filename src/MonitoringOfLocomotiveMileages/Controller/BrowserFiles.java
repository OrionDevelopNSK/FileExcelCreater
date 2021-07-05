package MonitoringOfLocomotiveMileages.Controller;

import MonitoringOfLocomotiveMileages.Model.MainClass;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class BrowserFiles {



    public void GetFileChooser(Stage mainStage, TextField textField){

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("EXCELL FILES", "*.xls"));
        File selectedFile = fileChooser.showOpenDialog(mainStage);
        String str = selectedFile.getPath().toString().replace("\\", "\\\\");
        textField.setText(str);


    }

}
