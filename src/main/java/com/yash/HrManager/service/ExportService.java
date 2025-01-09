package com.yash.HrManager.service;

import com.yash.HrManager.Entity.DailySchedule;
import com.yash.HrManager.Entity.Training;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class ExportService {

    public byte[] exportTrainingList(List<Training> trainings) throws IOException {

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Training Data");

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 12);

        Row header = sheet.createRow(0);
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        header.createCell(0).setCellValue("Training ID");
        header.createCell(1).setCellValue("Email ID");
        header.createCell(2).setCellValue("Trainer Name");
        header.createCell(3).setCellValue("No. of Participants");
        header.createCell(4).setCellValue("Description");
        header.createCell(5).setCellValue("Start Date");
        header.createCell(6).setCellValue("End Date");
        header.createCell(7).setCellValue("Status");

        for (int i = 0; i < 8; i++) {
            header.getCell(i).setCellStyle(headerCellStyle);
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // Populating rows with data
        int rowNum = 1;
        for (Training training : trainings) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(training.getTrainingId());
            row.createCell(1).setCellValue(training.getEmailId());
            row.createCell(2).setCellValue(training.getTrainerName());
            row.createCell(3).setCellValue(training.getNoOfParticipant());
            row.createCell(4).setCellValue(training.getDescription());

            Cell startDateCell = row.createCell(5);
            startDateCell.setCellValue(dateFormat.format(training.getStartDate()));

            Cell endDateCell = row.createCell(6);
            endDateCell.setCellValue(dateFormat.format(training.getEndDate()));

            row.createCell(7).setCellValue(training.getStatus().toString());
        }

        sheet.setColumnWidth(0, 4000); // Training ID
        sheet.setColumnWidth(1, 8000); // Email ID
        sheet.setColumnWidth(2, 6000); // Trainer Name
        sheet.setColumnWidth(3, 4000); // No. of Participants
        sheet.setColumnWidth(4, 8000); // Description
        sheet.setColumnWidth(5, 4000); // Start Date
        sheet.setColumnWidth(6, 4000); // End Date
        sheet.setColumnWidth(7, 4000); // Status


        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            workbook.write(byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }
    }

    public byte[] exportTrainingDailyScheduleList(List<DailySchedule> dailyScheduleList, int weekScheduleId) throws IOException {

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Training Data");

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 12);

        Row header = sheet.createRow(0);
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        header.createCell(0).setCellValue("Training ID");
        header.createCell(1).setCellValue("Email ID");
        header.createCell(2).setCellValue("Trainer Name");
        header.createCell(3).setCellValue("No. of Participants");
        header.createCell(4).setCellValue("Description");
        header.createCell(5).setCellValue("Date");

        for (int i = 0; i < 8; i++) {
            header.getCell(i).setCellStyle(headerCellStyle);
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // Populating rows with data
        int rowNum = 1;
        for (DailySchedule schedule : dailyScheduleList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(schedule.getTrainingId());
            row.createCell(1).setCellValue(schedule.getEmailId());
            row.createCell(2).setCellValue(schedule.getDescription());
            row.createCell(3).setCellValue(schedule.getDate());
            row.createCell(4).setCellValue(schedule.getDescription());

            Cell startDateCell = row.createCell(5);
            startDateCell.setCellValue(dateFormat.format(schedule.getDate()));

        }

        sheet.setColumnWidth(0, 4000); // Training ID
        sheet.setColumnWidth(1, 8000); // Email ID
        sheet.setColumnWidth(2, 6000); // Trainer Name
        sheet.setColumnWidth(3, 8000); // Description
        sheet.setColumnWidth(4, 4000); // Start Date

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            workbook.write(byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }
    }
}
