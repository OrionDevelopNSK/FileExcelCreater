package MonitoringOfLocomotiveMileages.View;

import MonitoringOfLocomotiveMileages.Model.Calculator;
import javafx.scene.control.Alert;


public class InputErrorHandler {

    public void HandleErrors(){

        Calculator calculator = new Calculator();


        if (ApplicationWindow.applicationWindow.pathOfOldFile.getText().isBlank()
                || ApplicationWindow.applicationWindow.pathOfNewFile.getText().isBlank()){

            Alert alert = new Alert(Alert.AlertType.ERROR, "Не выбраны файлы для обработки");
            alert.showAndWait();
            return;

        }
        else if ((ApplicationWindow.applicationWindow.pathOfOldFile.getText())
                .equals(ApplicationWindow.applicationWindow.pathOfNewFile.getText()))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Выбраны одинаковые файлы");
            alert.showAndWait();
            return;

        }
        calculator.GetListLocomotives();
    }

}
