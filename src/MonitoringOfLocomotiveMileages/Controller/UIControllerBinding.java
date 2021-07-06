package MonitoringOfLocomotiveMileages.Controller;

import MonitoringOfLocomotiveMileages.Model.Calculator;
import MonitoringOfLocomotiveMileages.View.ApplicationWindow;
import MonitoringOfLocomotiveMileages.View.InputErrorHandler;
import javafx.stage.Stage;

public class UIControllerBinding {



    public void BindingOfControls(Stage stage, ApplicationWindow applicationWindow) {


        BrowserFiles browserFiles = new BrowserFiles();
        Calculator calculator = new Calculator();
        applicationWindow.buttonBrowseOldFile.setOnAction((ae) -> browserFiles.GetFileChooser(stage, applicationWindow.pathOfOldFile));
        applicationWindow.buttonBrowseNewFile.setOnAction((ae) -> browserFiles.GetFileChooser(stage, applicationWindow.pathOfNewFile));
        applicationWindow.runButton.setOnAction((ae) -> new InputErrorHandler().HandleErrors());

    }
}
