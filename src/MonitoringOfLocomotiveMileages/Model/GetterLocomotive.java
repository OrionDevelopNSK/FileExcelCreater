package MonitoringOfLocomotiveMileages.Model;

import MonitoringOfLocomotiveMileages.Controller.ImporterExcelFile;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import java.util.*;


public class GetterLocomotive {

    private final int columnNumberOfseries = 3;
    private final int columnNumberOfNumber = 4;
    private final int columnNumberOfLocation = 9;
    private final int columnNumberOfStatus = 11;
    private final int columnNumberOfMileage = 28;

    private final int startRowWithInformation = 5;
    private final int finishRowWithInformation = 417;



    String GetCellSeriesInformation(HSSFSheet mainSheet, int numberRow) {

        return mainSheet.getRow(numberRow).getCell(columnNumberOfseries).getRichStringCellValue().getString();
    }

    String GetCellNumberInformation(HSSFSheet mainSheet, int numberRow) {
        return mainSheet.getRow(numberRow).getCell(columnNumberOfNumber).getRichStringCellValue().getString();
    }

    String GetCellLocationInformation(HSSFSheet mainSheet, int numberRow) {
        return mainSheet.getRow(numberRow).getCell(columnNumberOfLocation).getRichStringCellValue().getString();
    }

    String GetCellStatusInformation(HSSFSheet mainSheet, int numberRow) {
        return mainSheet.getRow(numberRow).getCell(columnNumberOfStatus).getRichStringCellValue().getString();
    }

    int GetCellMileageInformation(HSSFSheet mainSheet, int numberRow) {
        return Integer.parseInt((mainSheet.getRow(numberRow).getCell(columnNumberOfMileage).getRichStringCellValue().getString()));
    }


    public List<Locomotive> GetLocomotives(String path) {

        ImporterExcelFile excelFile = new ImporterExcelFile();

        HSSFSheet mainSheep = excelFile.readWorkBook(path).getSheetAt(MainClass.sheetIndex);
        List<Locomotive> locomotives = new ArrayList<>();



        for (int i = startRowWithInformation; i <= finishRowWithInformation; i++) {

            Locomotive locomotive = new Locomotive.Builder()
                    .withSeries(GetCellSeriesInformation(mainSheep, i))
                    .withNumber(GetCellNumberInformation(mainSheep, i))
                    .withLocation(GetCellLocationInformation(mainSheep, i))
                    .withStatus(GetCellStatusInformation(mainSheep, i))
                    .withMileage(GetCellMileageInformation(mainSheep, i))
                    .build();


            locomotives.add(locomotive);

        }
        return locomotives;
    }



}

