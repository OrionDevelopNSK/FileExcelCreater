package MonitoringOfLocomotiveMileages.View;

import MonitoringOfLocomotiveMileages.Controller.UIControllerBinding;
import javafx.application.*;
import javafx.stage.Stage;


public class UserInterface extends Application {


    @Override
    public void start(Stage stage) throws Exception {

        ApplicationWindow applicationWindow = new ApplicationWindow();
        applicationWindow.CreateApplicationWindow(stage);


        UIControllerBinding uiController = new UIControllerBinding();
        uiController.BindingOfControls(stage, applicationWindow);


    }








}
