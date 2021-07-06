package MonitoringOfLocomotiveMileages.Controller;


import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


public class ExporterExcelFile {

    static final String excelName = "Отцепка локомотивов.xls";

    //запись книги в файл
    public static void ExportExcelFile(Workbook workbook){
        try(OutputStream fileOutput = new FileOutputStream(excelName)){
            workbook.write(fileOutput);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
