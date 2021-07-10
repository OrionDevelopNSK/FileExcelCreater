package MonitoringOfLocomotiveMileages.View;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ApplicationWindow {


    Label labelPathOfOldFile;
    Label labelPathOfNewFile;

    public Button buttonBrowseOldFile;
    public Button buttonBrowseNewFile;
    public  Button runButton;

    public TextField pathOfOldFile;
    public TextField pathOfNewFile;

    String labelPathOfOldFileDescription = "Укажите путь к \"старому\" файлу";
    String labelPathOfNewFileDescription = "Укажите путь к \"новому\" файлу";

    String header = "Мониторинг пробега локомотивов";

    public static ApplicationWindow applicationWindow;


    public void CreateApplicationWindow(Stage stage){



        applicationWindow = this;

        stage.setTitle(header);


        labelPathOfOldFile = new Label(labelPathOfOldFileDescription);
        pathOfOldFile = new TextField();
        buttonBrowseOldFile = new Button("Выбрать");

        labelPathOfNewFile = new Label(labelPathOfNewFileDescription);
        pathOfNewFile = new TextField();
        buttonBrowseNewFile = new Button("Выбрать");

        runButton = new Button("Запуск");

        pathOfOldFile.setMinSize(300, 25);
        pathOfOldFile.setMaxSize(300, 25);

        pathOfNewFile.setMinSize(300, 25);
        pathOfNewFile.setMaxSize(300, 25);

        pathOfOldFile.setEditable(false);
        pathOfNewFile.setEditable(false);

        labelPathOfOldFile.setStyle("-fx-font: 15 arial;");
        labelPathOfNewFile.setStyle("-fx-font: 15 arial;");
        buttonBrowseOldFile.setStyle("-fx-font: 15 arial;");
        buttonBrowseNewFile.setStyle("-fx-font: 15 arial;");

        runButton.setStyle("-fx-font: 15 arial;");


        FlowPane root1 = new FlowPane(20, 20, pathOfOldFile, buttonBrowseOldFile);
        FlowPane root2 = new FlowPane(20, 20, pathOfNewFile, buttonBrowseNewFile);

        VBox vBox = new VBox(10,
                labelPathOfOldFile,
                root1,
                labelPathOfNewFile,
                root2,
                runButton);



        vBox.setAlignment(Pos.CENTER);
        root1.setAlignment(Pos.BASELINE_CENTER);
        root2.setAlignment(Pos.BASELINE_CENTER);

        Scene mainScene = new Scene(vBox, 500, 200);
        stage.setScene(mainScene);

        stage.show();


    }




}
