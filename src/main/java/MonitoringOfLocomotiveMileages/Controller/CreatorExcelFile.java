package MonitoringOfLocomotiveMileages.Controller;

import MonitoringOfLocomotiveMileages.Model.TableLocomotives;
import org.apache.poi.hssf.record.ExtendedFormatRecord;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;


import java.util.List;

public class CreatorExcelFile {


    final String sheetName = "Пробеги";


    public void CreateFile(List<TableLocomotives> finalTableOfLocomotives) {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet(sheetName);



        CreationHelper createHelper = workbook.getCreationHelper();

        int countRow = finalTableOfLocomotives.size();

        CellStyle cellStyle = SetBordersForCells(workbook);

        CreateHeader(sheet, cellStyle);

        for (int i = 1; i < countRow + 1; i++) {
            Row currentRow = sheet.createRow(i);


            Cell cell;

            cell = currentRow.createCell(0);
            cell.setCellValue(finalTableOfLocomotives.get(i-1).getSeries());
            cell.setCellStyle(cellStyle);

            cell = currentRow.createCell(1);
            cell.setCellValue(finalTableOfLocomotives.get(i-1).getNumber());
            cell.setCellStyle(cellStyle);

            cell = currentRow.createCell(2);
            cell.setCellValue(finalTableOfLocomotives.get(i-1).getStatus());
            cell.setCellStyle(cellStyle);

            cell = currentRow.createCell(3);
            cell.setCellValue(finalTableOfLocomotives.get(i-1).getOldMileage());
            cell.setCellStyle(cellStyle);

            cell = currentRow.createCell(4);
            cell.setCellValue(finalTableOfLocomotives.get(i-1).getNewMileage());
            cell.setCellStyle(cellStyle);

            cell = currentRow.createCell(5);
            cell.setCellValue(finalTableOfLocomotives.get(i-1).getRemainingMileage());
            cell.setCellStyle(cellStyle);

            cell = currentRow.createCell(6);
            cell.setCellValue(finalTableOfLocomotives.get(i-1).getAverageMileage());
            cell.setCellStyle(cellStyle);

            cell = currentRow.createCell(7);
            cell.setCellValue(finalTableOfLocomotives.get(i-1).getRemainingMileageDays());
            cell.setCellStyle(cellStyle);

            cell = currentRow.createCell(8);
            cell.setCellValue(finalTableOfLocomotives.get(i-1).getEndDateOfTheMileage());
            cell.setCellStyle(cellStyle);

        }


        ExporterExcelFile.ExportExcelFile(workbook);
    }


    void CreateHeader(Sheet sheet, CellStyle cellStyle){
        Row row = sheet.createRow(0);

        Cell cell;
        cell = row.createCell(0);
        cell.setCellValue("Серия");

        cell = row.createCell(1);
        cell.setCellValue("Номер");
        cell.setCellStyle(cellStyle);


        cell = row.createCell(2);
        cell.setCellValue("Состояние");
        cell.setCellStyle(cellStyle);


        cell = row.createCell(3);
        cell.setCellValue("Старый пробег");
        cell.setCellStyle(cellStyle);


        cell = row.createCell(4);
        cell.setCellValue("Текущий пробег");
        cell.setCellStyle(cellStyle);


        cell = row.createCell(5);
        cell.setCellValue("Остаток, км");
        cell.setCellStyle(cellStyle);


        cell = row.createCell(6);
        cell.setCellValue("Средний пробег");
        cell.setCellStyle(cellStyle);


        cell = row.createCell(7);
        cell.setCellValue("Дней до отцепки");
        cell.setCellStyle(cellStyle);


        cell = row.createCell(8);
        cell.setCellValue("Дата отцепки");
        cell.setCellStyle(cellStyle);
    }

    CellStyle SetBordersForCells(Workbook workbook){

        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setShrinkToFit(true);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);

        return cellStyle;
    }



}

