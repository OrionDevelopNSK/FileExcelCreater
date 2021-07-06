package MonitoringOfLocomotiveMileages.Model;


import MonitoringOfLocomotiveMileages.View.UserInterface;




public class MainClass {


    public static String fileName = "C:\\Users\\Orion\\Desktop\\RESOURCES\\1111.xls";
    public static int sheetIndex = 0;




    public static void main(String[] args) {



        UserInterface userInterface = new UserInterface();
        userInterface.launch(UserInterface.class, args);







    }











}
