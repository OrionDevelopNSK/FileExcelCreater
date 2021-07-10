package MonitoringOfLocomotiveMileages.Model;

import MonitoringOfLocomotiveMileages.Controller.CreatorExcelFile;
import MonitoringOfLocomotiveMileages.View.ApplicationWindow;



import java.text.SimpleDateFormat;
import java.util.*;

public class Calculator {


    //предельный пробег локомотива
    final static int maximumPermissibleMileage = 50000;
    //список состояний локомотива находящегося в депо
    final List<String> statesOff = List.of("ОЖ.ПЕР.РЕМ", "TP1+TO4", "TP-1","TP-2", "TP-3", "КОНСЕРВАЦИЯ");


    static List<Locomotive> oldListLocomotives;
    static List<Locomotive> newListLocomotives;
    public static List<TableLocomotives> tableOfLocomotives;

    public void GetListLocomotives(){


        GetterLocomotive getterLocomotive =  new GetterLocomotive();




        oldListLocomotives = getterLocomotive.GetLocomotives(ApplicationWindow.applicationWindow.pathOfOldFile.getText());
        newListLocomotives = getterLocomotive.GetLocomotives(ApplicationWindow.applicationWindow.pathOfNewFile.getText());

        tableOfLocomotives = new ArrayList<>();

        SearchForTwoIdenticalLocomotives();

        new CreatorExcelFile().CreateFile(GetNonRepeatingLocomotives(GetAllLocomotivesWithoutInDepot(tableOfLocomotives)));

    }

    List<TableLocomotives> GetAllLocomotivesWithoutInDepot(List<TableLocomotives> tableOfLocomotives){

        List<TableLocomotives> list = new ArrayList<>();
        for (int i = 0; i < tableOfLocomotives.size(); i++) {
            if (tableOfLocomotives.get(i).getEndDateOfTheMileage() != "ОТЦЕПЛЕН"){
                list.add(tableOfLocomotives.get(i));
            }
        }

        //сортировка по количеству дней до отцепки
        list.sort(new Comparator<TableLocomotives>() {
            @Override
            public int compare(TableLocomotives o1, TableLocomotives o2) {
                if (o1.getRemainingMileageDays() == o2.getRemainingMileageDays()) return 0;
                else if(o1.getRemainingMileageDays() > o2.getRemainingMileageDays()) return 1;
                else return -1;
            }
        });

        return list;
    }




    //сравниваем одинаковые локомотивы из разных таблиц для дальнейших расчетов
    void SearchForTwoIdenticalLocomotives(){

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

        //разница пробегов локомотивов
        int differenceOfMileage = CalculateDifferenceOfMileage(currentLocomotive, oldLocomotive);
        //остаток пробега до отцепки
        int remainingMileage = CalculateRemainingMileage(currentLocomotive.getMileage());
        //средний пробег за 10 дней
        int averageMileage = CalculationTheAverageMileageForTenDay(differenceOfMileage);
        //расчетное количество дней до отцепки
        int remainingMileageDays = CalculationEndOfTheMileage(currentLocomotive.getMileage(), averageMileage);
        //дата отцепки, при условии что локомотив еще не отцеплен
        String endDateOfTheMileage = CalculationEndDateOfTheMileage(remainingMileageDays, currentLocomotive);

        //убираем последний символ буквы в номере локомотива
        String numberWithoutLastChar = (currentLocomotive.getNumber()).substring(0,currentLocomotive.getNumber().length()-1);


        TableLocomotives locomotiveForTable = new TableLocomotives.Builder().
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

    //возвращаем список локомотивов без дупликатов
    List<TableLocomotives> GetNonRepeatingLocomotives(List<TableLocomotives> locomotives) {


        List<TableLocomotives> currentListLocomotives = new ArrayList<>();

        TableLocomotives currentLocomotive;

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
            return -1;
        }


    }

    String CalculationEndDateOfTheMileage(int remainingMileageDays, Locomotive locomotive){

        if (remainingMileageDays < 0 || statesOff.contains(locomotive.getStatus())) return "ОТЦЕПЛЕН";

        Calendar currentData = Calendar.getInstance();
        currentData.add(Calendar.DAY_OF_MONTH, remainingMileageDays);
        Date date = currentData.getTime();

        //форматирование строки даты
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        return formatter.format(date);
    }









}
