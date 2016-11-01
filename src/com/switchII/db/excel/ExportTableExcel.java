package com.switchII.db.excel;

import java.awt.Font;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;

import com.switchII.db.datasource.DbConnect;
import com.switchII.db.handler.ColumnBean;
import com.switchII.db.handler.MySqlHandler;
import com.switchII.db.handler.TableBean;

/**
 * 导出数据库Excel
 * 
 * @author Anton
 * 
 */
public class ExportTableExcel {

	public static void main(String args[]) {
		
		String url_41_1 = "jdbc:mysql://localhost:3306/database?useUnicode=true&characterEncoding=UTF-8" ; 
		DbConnect.setURL(url_41_1) ; 
		
		MySqlHandler handler = new MySqlHandler();
		List<TableBean> tables = handler.queryTables();
		for (TableBean t : tables) {
			System.out.println("t = " + t.getTableName());
		}

		ExportTableExcel e = new ExportTableExcel();
		String path = "f:\\test.xml";
		e.exportExcel(e.buildHSSFWorkbook("yunpos_后台数据库_ " , tables),  path); 
		
	}

	/**
	 * 导出生成的Excel
	 * 
	 * @param wb
	 * @param fileTitle
	 * @return
	 */
	public String exportExcel(HSSFWorkbook wb,  String path) {
		
		FileOutputStream fileOut = null;
		try {
			fileOut = new FileOutputStream(path);
			wb.write(fileOut);
			System.out.println("导出数据成功..");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fileOut != null) {
				try {
					fileOut.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return path;
	}

	/**
	 * 根据一个表创建一个Excel
	 * 
	 * @return
	 */
	public HSSFWorkbook buildHSSFWorkbook(String title , List<TableBean> tables) {

		String[] excelHeader = { "字段", "类型", "是否为空", "是否为主键", "默认值", "注释 "  };
		 
		// 单元格列宽
		int[] excelHeaderWidth = { 200, 120, 80, 80, 100, 350  };

		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("数据库设计文档");
		
		HSSFRow rowHeader = sheet.createRow((int) 0);
		HSSFCell cellHeader = rowHeader.createCell(0);
		cellHeader.setCellValue(title+"_数据库设计文档 ") ; 
		
		HSSFCellStyle style = wb.createCellStyle();
		// 设置居中样式
		HSSFFont fonts = wb.createFont();
		fonts.setBoldweight((short) Font.BOLD); 
		style.setFont(fonts) ; 
		style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
//		fonts.setFontHeightInPoints((short) 12);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中

		// 设置合计样式
		HSSFCellStyle style1 = wb.createCellStyle();
		HSSFFont font = wb.createFont();
		font.setColor(HSSFColor.RED.index);
		font.setBoldweight((short) Font.BOLD); // 粗体
		font.setFontHeightInPoints((short) 12);
		style1.setFont(font);
		style1.setFillForegroundColor(IndexedColors.BLUE.getIndex());
		style1.setAlignment(HSSFCellStyle.ALIGN_LEFT); // 水平居中
		style1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
		
		HSSFCellStyle style2 = wb.createCellStyle();
		HSSFFont font2 = wb.createFont();
		font2.setColor(HSSFColor.BLACK.index);
		font2.setBoldweight((short) Font.BOLD); // 粗体
		style2.setFont(font2);
		style2.setAlignment(HSSFCellStyle.ALIGN_LEFT); // 水平居中
		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
		
		//设置背景色:  
		HSSFCellStyle cellStyle = wb.createCellStyle();    
		  
		//设置边框:  
		cellStyle.setFillForegroundColor((short) 1);// 设置背景色    
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);    
		  
		//设置居中:  
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框    
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框    
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框    
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框    
		
		//设置背景色:  
		HSSFCellStyle cellStyleHeader = wb.createCellStyle();    
		  
		HSSFFont fontHeader = wb.createFont();   
		fontHeader.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示    
		fontHeader.setFontHeightInPoints((short) 11);   
		fontHeader.setColor(HSSFColor.WHITE.index);
		//设置边框:  
		cellStyleHeader.setFillForegroundColor((short) 56);// 设置背景色    
		cellStyleHeader.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);    
		  
		//设置居中:  
		cellStyleHeader.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框    
//		cellStyleHeader.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框    
		cellStyleHeader.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框    
//		cellStyleHeader.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框    
		cellStyleHeader.setFont(fontHeader) ; 

		// 设置列宽度（像素）
		for (int i = 0; i < excelHeaderWidth.length; i++) {
			sheet.setColumnWidth(i, 32 * excelHeaderWidth[i]);
		}
 
		int rowPosition = 1 ; 
		
		for(int i = 0 ; i < tables.size() ; i ++){
			
			CellRangeAddress cra=new CellRangeAddress(rowPosition, rowPosition, 0, 5);         
	        sheet.addMergedRegion(cra);  
			
			TableBean t = tables.get(i) ; 
			HSSFRow r = sheet.createRow((int) rowPosition);
			HSSFCell c = r.createCell(0);
			c.setCellValue(t.getTableName()) ; 
			rowPosition += 1 ;  
			c.setCellStyle(cellStyleHeader) ; 
			HSSFRow columnRow = sheet.createRow((int) rowPosition);
			for (int ii = 0; ii < excelHeader.length; ii++) {
				HSSFCell cell = columnRow.createCell(ii);
				cell.setCellValue(excelHeader[ii]); 
				cell.setCellStyle(cellStyleHeader) ; 
			}
			
			List<ColumnBean> columns = t.getColumns() ; 
			
			for (int iii = 0; iii < columns.size(); iii++) {
				rowPosition += 1 ; 
				HSSFRow columnRows = sheet.createRow((int) rowPosition);
				
				ColumnBean cc = columns.get(iii) ; 
				
				HSSFCell cell = columnRows.createCell(0);
				cell.setCellValue(cc.getField());
				
				HSSFCell cell1 = columnRows.createCell(1);
				cell1.setCellValue(cc.getType());
				
				HSSFCell cell2 = columnRows.createCell(2);
				cell2.setCellValue("NO".equals(cc.getIsNull())?"不为空":"可为空");
				
				HSSFCell cell3 = columnRows.createCell(3);
				cell3.setCellValue("PRI".equals(cc.getIsKey())?"主键":"");
				
				HSSFCell cell4 = columnRows.createCell(4);
				cell4.setCellValue(cc.getDefaultVal());
				
				HSSFCell cell5 = columnRows.createCell(5);
				cell5.setCellValue(cc.getComments());
				 
				cell.setCellStyle(cellStyle) ; 
				cell1.setCellStyle(cellStyle) ; 
				cell2.setCellStyle(cellStyle) ; 
				cell3.setCellStyle(cellStyle) ; 
				cell4.setCellStyle(cellStyle) ; 
				cell5.setCellStyle(cellStyle) ; 
//				cell.setCellStyle(style2);
			}
			
			rowPosition += 3 ; 
			System.out.println("position = " + rowPosition) ; 
		}
 
		return wb;
	}

}
