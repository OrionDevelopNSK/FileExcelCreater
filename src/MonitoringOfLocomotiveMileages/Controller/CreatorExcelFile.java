package MonitoringOfLocomotiveMileages.Controller;

import MonitoringOfLocomotiveMileages.Model.GetterLocomotivesForTable;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class CreatorExcelFile {


    final String sheetName = "Пробеги";


    public void CreateFile(List<GetterLocomotivesForTable> finalTableOfLocomotives) {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet(sheetName);

        List<Row> rowList = new ArrayList<>();
        List<Cell> cellList = new ArrayList<>();

        CreationHelper createHelper = workbook.getCreationHelper();

        int countRow = finalTableOfLocomotives.size();


        var methods = GetterLocomotivesForTable.class.getDeclaredMethods();
        int countCell = methods.length;


        for (int i = 0; i < countRow; i++) {
            Row currentRow = sheet.createRow(i);

            currentRow.createCell(0).setCellValue(finalTableOfLocomotives.get(i).getSeries());
            currentRow.createCell(1).setCellValue(finalTableOfLocomotives.get(i).getNumber());
            currentRow.createCell(2).setCellValue(finalTableOfLocomotives.get(i).getStatus());
            currentRow.createCell(3).setCellValue(finalTableOfLocomotives.get(i).getOldMileage());
            currentRow.createCell(4).setCellValue(finalTableOfLocomotives.get(i).getNewMileage());
            currentRow.createCell(5).setCellValue(finalTableOfLocomotives.get(i).getRemainingMileage());
            currentRow.createCell(6).setCellValue(finalTableOfLocomotives.get(i).getAverageMileage());
            currentRow.createCell(7).setCellValue(finalTableOfLocomotives.get(i).getRemainingMileageDays());
            currentRow.createCell(8).setCellValue(finalTableOfLocomotives.get(i).getEndDateOfTheMileage());

        }

        ExporterExcelFile.ExportExcelFile(workbook);
    }



}
