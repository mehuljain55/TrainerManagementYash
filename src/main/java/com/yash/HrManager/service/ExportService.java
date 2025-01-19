package com.yash.HrManager.service;

import com.yash.HrManager.Entity.DailySchedule;
import com.yash.HrManager.Entity.Training;
import com.yash.HrManager.Entity.User;
import com.yash.HrManager.repository.DailyScheduleRepo;
import com.yash.HrManager.repository.TraniningRepo;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ExportService {

    @Autowired
    private DailyScheduleRepo dailyScheduleRepo;

    @Autowired
    private TraniningRepo traniningRepo;

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

    public byte[] exportTrainingDailyScheduleListSingleWeek(List<DailySchedule> dailyScheduleList, User user) throws IOException {

        String email = user.getEmailId();
        String sname = user.getName();
        int trainingId = dailyScheduleList.get(0).getTrainingId();
        int weekId = dailyScheduleList.get(0).getWeekScheduleId();

        System.out.println(dailyScheduleList.size());

        for(DailySchedule schedule:dailyScheduleList)
        {
            System.out.println(schedule);
        }

        System.out.println(email+" "+trainingId);
        Training training = traniningRepo.findTrainingsByEmailAndTrainingId(email, trainingId);
        String trainingName = training.getTrainingName();
        String startDate = findDateRange(dailyScheduleList).get("startDate");
        String endDate = findDateRange(dailyScheduleList).get("endDate");

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Training Data");

        // Set up header rows for email, name, training, etc.
        Row row1 = sheet.createRow(0);
        row1.createCell(0).setCellValue("Email: " + email);
        row1.createCell(1).setCellValue("Training Id: " + trainingId);
        row1.createCell(2).setCellValue("Week Start Date");
        row1.createCell(3).setCellValue(startDate);

        Row row2 = sheet.createRow(1);
        row2.createCell(0).setCellValue("Name: " + sname);
        row2.createCell(1).setCellValue("Training Name: " + trainingName);
        row2.createCell(2).setCellValue("Week End Date");
        row2.createCell(3).setCellValue(endDate);

        // Add a row for Week Id
        Row row3 = sheet.createRow(2);
        row3.createCell(0).setCellValue("Week Id: " + weekId);

        // Add a blank row for spacing
        sheet.createRow(3);

        // Create column headers (Sno, Date, Description, Attendance)
        Row header = sheet.createRow(4);
        header.createCell(0).setCellValue("Sno");
        header.createCell(1).setCellValue("Date");
        header.createCell(2).setCellValue("Description");
        header.createCell(3).setCellValue("Attendance");

        // Apply bold style for header
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 12);
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        for (int i = 0; i < 4; i++) {
            header.getCell(i).setCellStyle(headerCellStyle);
        }

        // Populate rows with data
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        int rowNum = 5;
        int sno = 1; // To add serial number

        for (DailySchedule schedule : dailyScheduleList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(sno++);
            row.createCell(1).setCellValue(dateFormat.format(schedule.getDate()));
            row.createCell(2).setCellValue(schedule.getDescription());
            row.createCell(3).setCellValue(schedule.getTrainerAttendance().toString());
        }

        // Adjust column widths
        sheet.setColumnWidth(0, 4000);
        sheet.setColumnWidth(1, 8000);
        sheet.setColumnWidth(2, 10000);
        sheet.setColumnWidth(3, 8000);

        // Write to byte array output stream
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            workbook.write(byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }
    }


    public  Map<String, String> findDateRange(List<DailySchedule> dailyScheduleList) {

        if (dailyScheduleList == null || dailyScheduleList.isEmpty()) {
            return Collections.emptyMap();
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date startDate = dailyScheduleList.stream()
                .map(DailySchedule::getDate)
                .filter(Objects::nonNull)
                .min(Date::compareTo)
                .orElse(null);

        Date endDate = dailyScheduleList.stream()
                .map(DailySchedule::getDate)
                .filter(Objects::nonNull)
                .max(Date::compareTo)
                .orElse(null);

        Map<String, String> dateRange = new HashMap<>();
        dateRange.put("startDate", startDate != null ? dateFormat.format(startDate) : null);
        dateRange.put("endDate", endDate != null ? dateFormat.format(endDate) : null);
        return dateRange;
    }
}
