package MonitoringOfLocomotiveMileages.Controller;



import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import java.io.FileInputStream;



public class ImporterExcelFile {

    //чтение книги из файла
    public HSSFWorkbook readWorkBook(String filename){

        //чтение книги из файла
        try{

            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(filename));
            HSSFWorkbook wb = new HSSFWorkbook(fs);

            return wb;
        }
        catch (Exception e){
            System.out.println("ERROR, FILE NOT OPENED");
            return null;
        }
    }



}
