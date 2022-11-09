package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

    
    public ArrayList<String> getCell(String sheetName, String headerValue, String rowValue) throws IOException {

        FileInputStream stream = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/testData/ExcelData.xlsx");
        XSSFWorkbook workBook = new XSSFWorkbook(stream);
        XSSFSheet sheet;
        ArrayList<String> list = new ArrayList<String>();
        
        int noOfSheets = workBook.getNumberOfSheets();
        for(int i=0; i<noOfSheets; i++){
            if(workBook.getSheetName(i).equalsIgnoreCase(sheetName)){
                sheet = workBook.getSheetAt(i);
                Iterator<Row> row= sheet.rowIterator();
                Row firstRow = row.next();
                Iterator<Cell> cell= firstRow.cellIterator();
                int k=0;
                int column=0;
                while(cell.hasNext()){
                    Cell value = cell.next();
                    if(value.getStringCellValue().equalsIgnoreCase(headerValue)){
                        column = k;
                    }
                    k++;
                }
                while(row.hasNext()){
                    Row r = row.next();
                    if(r.getCell(column).getStringCellValue().equalsIgnoreCase(rowValue)){
                        Iterator<Cell> c= r.cellIterator();
                        while(c.hasNext()){
                            {
                                Cell c1 = c.next();
                                if(c1.getCellType()== CellType.STRING){
                                    list.add(c1.getStringCellValue());
                                }
                                else{
                                    list.add(NumberToTextConverter.toText((c.next().getNumericCellValue())));
                                }

                            }
                            
                        } 
                    }
                    }
                }
            }
        return list;
}
}
