package org.gemini.com;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class excelWriteTransactionIds {

	//Header - thought of picking from the rest response but would need more control based on buy/sell
	private static int testResultColumn=19;

	 
    public static List<String> headerList()
    {	 List<String> headerList = new LinkedList<String>();
         headerList = Arrays.asList("Symbol", "Reason", "Side", "was_forced", "remaining_amount", "avg_execution_price","is_hidden","type","executed_amount","price","original_amount","is_live","options","exchange","id","order_id","is_cancelled","timestamp","timestampms","test_result");
		return headerList;
    	
    }

   //writeToExcel
    public static void writeToExecel(Map<String,JSONObject> orderJSONObjectMap,Map<String,String>transactionStatus,String resultFileName) 
    {
       // boolean header = false;
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("testResults");
   

       // System.out.println("Creating excel");
        int rowNum = 1;
        XSSFRow row1 = sheet.createRow(0); 
        List<String> headerList = new LinkedList<String>();
        headerList = excelWriteTransactionIds.headerList();
        Iterator<String> it = headerList.iterator();
        int headerColumn=0;

        while(it.hasNext())
        {
            
        	Cell cell1 = row1.createCell(headerColumn++);
            cell1.setCellValue(it.next().toString());           
        }

        for (String mapKey : orderJSONObjectMap.keySet()) 
        {
            int colNum = 0;
         

           
            //data row
            XSSFRow row = sheet.createRow(rowNum);            
           // XSSFCell cell= null;
            
            JSONObject jsonObj = orderJSONObjectMap.get(mapKey);
           // int headerCount=0;
            
            


            for (Object keyStr : jsonObj.keySet())
            {
            	if(keyStr.equals("client_order_id"))
            	{
            		continue;
            	}
                if((colNum==1) && !(keyStr.equals("reason")))
                {
                	colNum++;
                	Cell cell = row.createCell(colNum++);
                   	Object keyvalue = jsonObj.get(keyStr);
                     cell.setCellValue(keyvalue.toString());
                     sheet.autoSizeColumn(colNum);
                }
                else
                {
                	Cell cell = row.createCell(colNum++);
                   	Object keyvalue = jsonObj.get(keyStr);
                     cell.setCellValue(keyvalue.toString());
                     sheet.autoSizeColumn(colNum);
                }

            }
            
        	Cell transCell = row.createCell(testResultColumn); 
            sheet.autoSizeColumn(testResultColumn);            
        	transCell.setCellValue(transactionStatus.get(mapKey));

            rowNum++;
            }
        

        try {
            FileOutputStream outputStream = new FileOutputStream(resultFileName);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Done");
    }
    
    //writeToExcel
    public static void writeToExecelForOrder(Map<String,JSONObject> orderJSONObjectMap,Map<String,String>transactionStatus,String resultFileName) 
    {
       // boolean header = false;
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("testResults");
   

       // System.out.println("Creating excel");
        int rowNum = 1;
        XSSFRow row1 = sheet.createRow(0); 

        for (String mapKey : orderJSONObjectMap.keySet()) 
        {
            int colNum = 0;
            int headerColumn=0;
         

           
            //data row
            XSSFRow row = sheet.createRow(rowNum);            
           // XSSFCell cell= null;
            
            JSONObject jsonObj = orderJSONObjectMap.get(mapKey);
           // int headerCount=0;
            
            List<String> headerList = new LinkedList<String>();
            headerList = excelWriteTransactionIds.headerList();
            Iterator<String> it = headerList.iterator();
            
            while(it.hasNext())
            {
                
            	Cell cell1 = row1.createCell(headerColumn++);
                cell1.setCellValue(it.next().toString());           
            }


            for (Object keyStr : jsonObj.keySet())
            {
            	if(keyStr.equals("client_order_id"))
            	{
            		continue;
            	}
                if((colNum==1) && !(keyStr.equals("reason")))
                {
                	colNum++;
                	Cell cell = row.createCell(colNum++);
                   	Object keyvalue = jsonObj.get(keyStr);
                     cell.setCellValue(keyvalue.toString());
                     sheet.autoSizeColumn(colNum);
                }
                else
                {
                	Cell cell = row.createCell(colNum++);
                   	Object keyvalue = jsonObj.get(keyStr);
                     cell.setCellValue(keyvalue.toString());
                     sheet.autoSizeColumn(colNum);
                }

            }
            
        	Cell transCell = row.createCell(testResultColumn); 
            sheet.autoSizeColumn(testResultColumn);            
        	transCell.setCellValue(transactionStatus.get(mapKey));

            rowNum++;
            }
        

        try {
            FileOutputStream outputStream = new FileOutputStream(resultFileName);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Done");
    }

}