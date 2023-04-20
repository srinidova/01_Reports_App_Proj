package in.ashokit.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Component;

import in.ashokit.entity.CitizenPlan;

@Component
public class ExcelGenerator {

		public void generate (HttpServletResponse response, List<CitizenPlan> citizenPlans, File file) throws Exception{
			System.out.println("a2.----------ExcelGenerator----------------------citizenPlans.size"+citizenPlans.size());
			HSSFWorkbook workbook = new HSSFWorkbook();

			Sheet sheet = workbook.createSheet("Citizen Plans");
			Row rowhead = sheet.createRow(0);

			CellStyle style = workbook.createCellStyle();
			HSSFFont font = workbook.createFont();
			font.setBold(true);
			font.setFontHeight((short) 16);
			style.setFont(font);

			rowhead.createCell(0).setCellValue("S.No.");
			rowhead.createCell(1).setCellValue("Citizen Name");
			rowhead.createCell(2).setCellValue("Gender");
			rowhead.createCell(3).setCellValue("Plan Name");
			rowhead.createCell(4).setCellValue("Plan Status");
			rowhead.createCell(5).setCellValue("Plan Start Date");
			rowhead.createCell(6).setCellValue("Plan End Date");
			rowhead.createCell(7).setCellValue("Amount");

			int planIndex = 1;

			for (CitizenPlan plan : citizenPlans) {
				Row rowData = sheet.createRow(planIndex);

				rowData.createCell(0).setCellValue(plan.getCitizenId());
				rowData.createCell(1).setCellValue(plan.getCitizenName());
				rowData.createCell(2).setCellValue(plan.getGender());
				rowData.createCell(3).setCellValue(plan.getPlanName());
				rowData.createCell(4).setCellValue(plan.getPlanStatus());
				if (plan.getPlanStartDate() != null) {
					rowData.createCell(5).setCellValue(plan.getPlanStartDate() + "");
				} else {
					rowData.createCell(5).setCellValue("NA");
				}

				if (plan.getPlanEndDate() != null) {
					rowData.createCell(6).setCellValue(plan.getPlanEndDate() + "");
				} else {
					rowData.createCell(6).setCellValue("");
				}

				if (plan.getBenefitAmt() != null) {
					rowData.createCell(7).setCellValue(plan.getBenefitAmt());
				} else {
					rowData.createCell(7).setCellValue("NA");
				}
				planIndex++;
			}
			System.out.println("y2.----------file.getName----------------------"+file.getName());
			FileOutputStream fileOut = new FileOutputStream(new File(file.getName()));
			workbook.write(fileOut);
			fileOut.close();
			//file.delete();

			ServletOutputStream outputStream = response.getOutputStream();
			workbook.write(outputStream);
			outputStream.close();
			workbook.close();
			
			System.out.println("z2.----------ExcelGenerator----------------------");

		}
}