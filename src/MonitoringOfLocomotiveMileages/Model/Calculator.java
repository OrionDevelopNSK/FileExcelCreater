package MonitoringOfLocomotiveMileages.Model;

import MonitoringOfLocomotiveMileages.Controller.CreatorExcelFile;
import MonitoringOfLocomotiveMileages.View.ApplicationWindow;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Calculator {

    static List<Locomotive> oldListLocomotives;
    static List<Locomotive> newListLocomotives;

    final static int maximumPermissibleMileage = 50000;

    public static List<GetterLocomotivesForTable> tableOfLocomotives;



    public void GetListLocomotives(){


        GetterLocomotive getterLocomotive =  new GetterLocomotive();
        oldListLocomotives = getterLocomotive.GetLocomotives(ApplicationWindow.applicationWindow.pathOfOldFile.getText());
        newListLocomotives = getterLocomotive.GetLocomotives(ApplicationWindow.applicationWindow.pathOfNewFile.getText());

        tableOfLocomotives = new ArrayList<>();

        SearchForTwoIdenticalLocomotives();

        new CreatorExcelFile().CreateFile(GetNonRepeatingLocomotives(tableOfLocomotives));

    }


    public void SearchForTwoIdenticalLocomotives(){

        for (int i = 0; i < newListLocomotives.size(); i++) {
            Locomotive currentLocomotives = newListLocomotives.get(i);

            for (int j = 0; j < oldListLocomotives.size(); j++) {

                if (currentLocomotives.getNumber().equals(oldListLocomotives.get(j).getNumber())){

                    SetTableLocomotives(currentLocomotives, oldListLocomotives.get(j));

                }
            }
        }


    }

    void SetTableLocomotives(Locomotive currentLocomotive, Locomotive oldLocomotive){
        int differenceOfMileage = CalculateDifferenceOfMileage(currentLocomotive, oldLocomotive);
        int remainingMileage = CalculateRemainingMileage(currentLocomotive.getMileage());
        int averageMileage = CalculationTheAverageMileageForTenDay(differenceOfMileage);
        int remainingMileageDays = CalculationEndOfTheMileage(currentLocomotive.getMileage(), averageMileage);
        String endDateOfTheMileage = CalculationEndDateOfTheMileage(remainingMileageDays);

        //убираем последний символ буквы в номере локомотива
        String numberWithoutLastChar = (currentLocomotive.getNumber()).substring(0,currentLocomotive.getNumber().length()-1);


        GetterLocomotivesForTable locomotiveForTable = new GetterLocomotivesForTable.Builder().
                setSeries(currentLocomotive.getSeries()).
                setNumber(numberWithoutLastChar).
                setStatus(currentLocomotive.getStatus()).
                setOldMileage(oldLocomotive.getMileage()).
                setNewMileage(currentLocomotive.getMileage()).
                setRemainingMileage(remainingMileage).
                setAverageMileage(averageMileage).
                setRemainingMileageDays(remainingMileageDays).
                setEndDateOfTheMileage(endDateOfTheMileage).
                build();

        tableOfLocomotives.add(locomotiveForTable);
    }


    public List<GetterLocomotivesForTable> GetNonRepeatingLocomotives(List<GetterLocomotivesForTable> locomotives) {


        List<GetterLocomotivesForTable> currentListLocomotives = new ArrayList<>();

        GetterLocomotivesForTable currentLocomotive;

        for (int i = 0; i < locomotives.size(); i++) {
            if (locomotives.get(i) != null) {
                currentLocomotive = locomotives.get(i);
                for (int j = 0; j < locomotives.size(); j++) {
                    if (locomotives.get(j) != null) {
                        if (currentLocomotive.getNumber() == locomotives.get(j).getNumber()) {

                            locomotives.remove(i);

                        }
                    }
                }
                currentListLocomotives.add(currentLocomotive);
            }
        }

        return currentListLocomotives;
    }





    int CalculateDifferenceOfMileage(Locomotive newLocomotive, Locomotive oldLocomotive) {

        if (newLocomotive.getMileage() < oldLocomotive.getMileage()){
            return (maximumPermissibleMileage - oldLocomotive.getMileage()) + newLocomotive.getMileage();
        }else{
            return newLocomotive.getMileage() - oldLocomotive.getMileage();
        }


    }

    int CalculateRemainingMileage(int mileage){
        return maximumPermissibleMileage - mileage;
    }

    int CalculationTheAverageMileageForTenDay(int differenceOfMileage) {
        return differenceOfMileage / 10;
    }

    int CalculationEndOfTheMileage(int mileage, int averageMileage){
        float mileageFloat = ((float) mileage);
        float averageMileageFloat = ((float) averageMileage);

        //возвращение числа округленного до ближайшего целого
        try{
            return (int)Math.floor((maximumPermissibleMileage - mileage) / averageMileage);
        }
        catch (ArithmeticException e){
            System.out.println("деление на нуль");
            return -1;
        }


    }

    String CalculationEndDateOfTheMileage(int remainingMileageDays){

        if (remainingMileageDays < 0) return "ОТЦЕПЛЕН";

        Calendar currentData = Calendar.getInstance();
        currentData.add(Calendar.DAY_OF_MONTH, remainingMileageDays);
        Date date = currentData.getTime();

        //форматирование строки времени
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        return formatter.format(date);
    }







}
