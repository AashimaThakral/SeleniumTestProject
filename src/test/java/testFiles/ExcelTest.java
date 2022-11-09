package testFiles;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.annotations.Test;

import resources.ExcelUtil;

public class ExcelTest{

    @Test
    public void ExcelTest() throws IOException{
        ExcelUtil excel = new ExcelUtil();
   ArrayList<String> list =  excel.getCell("ExcelSheet", "TestCase", "GmailLogin");
   System.out.println(list.get(1));
    }
    
}
