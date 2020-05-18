package by.bestwork.util;

import by.bestwork.domain.ContractRequest;
import by.bestwork.dto.PersonInfo;
import by.bestwork.dto.SocialNetwork;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Set;

@Component
public class CreateTable {

    boolean generateDocxFileFromTemplate(ContractRequest request, String filename) {
        InputStream inputStream;
        XWPFDocument document;
        OutputStream out;
        try {
            inputStream = new FileInputStream("contracts/" + filename);
            document = new XWPFDocument(inputStream);
            out = new FileOutputStream(new File(new ClassPathResource("static/contracts/").getURI().toString() + filename));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = paragraph.createRun();
        run.setText("Доверенные лица");
        run.setBold(true);
        run.setFontSize(18);
        run.setCapitalized(true);

        XWPFTable table = document.createTable();
        XWPFTableRow tableRowOne = table.getRow(0);
        tableRowOne.getCell(0).setText("ФИО");
        tableRowOne.addNewTableCell().setText("Тел");
        tableRowOne.addNewTableCell().setText("Коммент");

        Set<PersonInfo> trustees = request.getTrustees();
        for (PersonInfo recommendation : trustees) {
            XWPFTableRow tableRow = table.createRow();
            tableRow.getCell(0).setText(recommendation.getName());
            tableRow.getCell(1).setText(recommendation.getTel());
            tableRow.getCell(2).setText(recommendation.getComment());
        }
        try {
            document.write(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        try {
            inputStream = new FileInputStream(new ClassPathResource("static/contracts/").getURI().toString() + filename);
            document = new XWPFDocument(inputStream);
            out = new FileOutputStream(new File(new ClassPathResource("static/contracts/").getURI().toString() + filename));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        run = paragraph.createRun();
        run.setText("Рекомендации");
        run.setBold(true);
        run.setFontSize(18);
        run.setCapitalized(true);

        XWPFTable recommendTable = document.createTable();
        tableRowOne = recommendTable.getRow(0);
        tableRowOne.getCell(0).setText("ФИО");
        tableRowOne.addNewTableCell().setText("Тел");
        tableRowOne.addNewTableCell().setText("Коммент");
        Set<PersonInfo> recommendations = request.getRecommendation();
        for (PersonInfo recommendation : recommendations) {
            XWPFTableRow tableRow = recommendTable.createRow();
            tableRow.getCell(0).setText(recommendation.getName());
            tableRow.getCell(1).setText(recommendation.getTel());
            tableRow.getCell(2).setText(recommendation.getComment());
        }

        try {
            document.write(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        try {
            inputStream = new FileInputStream(new ClassPathResource("static/contracts/").getURI().toString() + filename);
            document = new XWPFDocument(inputStream);
            out = new FileOutputStream(new File(new ClassPathResource("static/contracts/").getURI().toString() + filename));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        run = paragraph.createRun();
        run.setText("Социальные сети");
        run.setBold(true);
        run.setFontSize(18);
        run.setCapitalized(true);


        XWPFTable socialTable = document.createTable();
        tableRowOne = socialTable.getRow(0);
        tableRowOne.getCell(0).setText("Социальная сеть");
        tableRowOne.addNewTableCell().setText("Ссылка");
        Set<SocialNetwork> socialNetworks = request.getSocialNetworks();
        for (SocialNetwork socialNetwork : socialNetworks) {
            XWPFTableRow tableRow = socialTable.createRow();
            tableRow.getCell(0).setText(socialNetwork.getTypeOfSocial());
            tableRow.getCell(1).setText(socialNetwork.getUrl());
        }

        try {
            document.write(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        System.out.println(filename + " written successully");
        return true;
    }

    private void fillTable(XWPFTable table, Set<PersonInfo> recommendations) {
        for (PersonInfo recommendation : recommendations) {
            XWPFTableRow tableRow = table.createRow();
            tableRow.getCell(0).setText(recommendation.getName());
            tableRow.getCell(1).setText(recommendation.getTel());
            tableRow.getCell(2).setText(recommendation.getComment());
        }
    }

    private void createFirstRow(XWPFTable table) {
        XWPFTableRow tableRowOne = table.getRow(0);
        tableRowOne.getCell(0).setText("ФИО");
        tableRowOne.addNewTableCell().setText("Тел");
        tableRowOne.addNewTableCell().setText("Коммент");
    }
}