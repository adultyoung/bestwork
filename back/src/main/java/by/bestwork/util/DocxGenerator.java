//package by.bestwork.util;
//
//import by.bestwork.domain.ContractRequest;
//import by.bestwork.dto.PersonInfo;
//import by.bestwork.dto.SocialNetwork;
//import org.docx4j.jaxb.Context;
//import org.docx4j.model.table.TblFactory;
//import org.docx4j.openpackaging.exceptions.Docx4JException;
//import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
//import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
//import org.docx4j.wml.*;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.InputStream;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//public class DocxGenerator {
//
//    public static void main(String[] args) throws Exception {
//        ContractRequest request = new ContractRequest();
//        Set<PersonInfo> personInfos = new HashSet<>();
//        PersonInfo info = new PersonInfo();
//        info.setName("Владислав");
//        info.setTel("+375333333333");
//        info.setComment("Коммент");
//        personInfos.add(info);
//        request.setTrustees(personInfos);
//        request.setRecommendation(personInfos);
//        generateDocxFileFromTemplate(request, "word.docx");
//    }
//
//    public boolean generateDocxFileFromTemplate(ContractRequest request, String filename) throws Exception {
//
//        InputStream templateInputStream = new FileInputStream("C:/Users/mrchk/Desktop/bestwork/word.docx");
//
//        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(templateInputStream);
//        MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();
//
//        documentPart.addStyledParagraphOfText("Intense Quote", "Доверенные лица");
//        int writableWidthTwips = wordMLPackage.getDocumentModel()
//                .getSections().get(0).getPageDimensions().getWritableWidthTwips();
//        int columnNumber = 3;
//
//
//
//
//        ObjectFactory factory = Context.getWmlObjectFactory();
//        P p = factory.createP();
//        R r = factory.createR();
//        Text t = factory.createText();
//        t.setValue("Welcome To Baeldung");
//        r.getContent().add(t);
//        p.getContent().add(r);
//        RPr rpr = factory.createRPr();
//        BooleanDefaultTrue b = new BooleanDefaultTrue();
//        rpr.setB(b);
//        rpr.setI(b);
//        rpr.setCaps(b);
//        Color green = factory.createColor();
//        green.setVal("green");
//        rpr.setColor(green);
//        r.setRPr(rpr);
//
//
//        Tbl tbl = TblFactory.createTable(3, 3, writableWidthTwips/columnNumber);
//        List<Object> rows = tbl.getContent();
//        for (Object row : rows) {
//            Tr tr = (Tr) row;
//            List<Object> cells = tr.getContent();
//            for(Object cell : cells) {
//                Tc td = (Tc) cell;
//                td.getContent().add(p);
//            }
//        }
//
//
//
//
//
//
////        Set<PersonInfo> trustees = request.getTrustees();
////        Tbl tbl = TblFactory.createTable(trustees.size(), 3, writableWidthTwips / columnNumber);
////        List<Object> rows = tbl.getContent();
////        int i = 0;
////        for (PersonInfo personInfo : trustees) {
////            Tr tr = (Tr) rows.get(i);
////            List<Object> cells = tr.getContent();
////            for (int j = 0; j < cells.size(); j++) {
////                Tc td = (Tc) cells.get(j);
////                ObjectFactory factory = Context.getWmlObjectFactory();
////                P p = factory.createP();
////                R r = factory.createR();
////                Text t = factory.createText();
////                switch (j) {
////                    case 0:
////                        t.setValue(personInfo.getName());
////                    case 1:
////                        t.setValue(personInfo.getTel());
////                    case 2:
////                        t.setValue(personInfo.getComment());
////                }
////                r.getContent().add(t);
////                p.getContent().add(r);
////                td.getContent().add(p);
////            }
////        }
////        documentPart.addStyledParagraphOfText("Intense Quote", "Куратор от учреждения образования");
////        writableWidthTwips = wordMLPackage.getDocumentModel()
////                .getSections().get(0).getPageDimensions().getWritableWidthTwips();
////        columnNumber = 3;
////
////        PersonInfo kurator = request.getKurator();
////        if (kurator != null) {
////            tbl = TblFactory.createTable(1, 3, writableWidthTwips / columnNumber);
////            rows = tbl.getContent();
////                Tr tr = (Tr) rows.get(0);
////                List<Object> cells = tr.getContent();
////                for (int j = 0; j < cells.size(); j++) {
////                    Tc td = (Tc) cells.get(j);
////                    ObjectFactory factory = Context.getWmlObjectFactory();
////                    P p = factory.createP();
////                    R r = factory.createR();
////                    Text t = factory.createText();
////                    switch (j) {
////                        case 0:
////                            t.setValue(kurator.getName());
////                        case 1:
////                            t.setValue(kurator.getTel());
////                        case 2:
////                            t.setValue(kurator.getComment());
////                    }
////                    r.getContent().add(t);
////                    p.getContent().add(r);
////                    td.getContent().add(p);
////                }
////            }
////
////
////        writableWidthTwips = wordMLPackage.getDocumentModel()
////                .getSections().get(0).getPageDimensions().getWritableWidthTwips();
////        columnNumber = 3;
////
////        Set<PersonInfo> recommendation = request.getRecommendation();
////        tbl = TblFactory.createTable(recommendation.size(), 3, writableWidthTwips / columnNumber);
////        rows = tbl.getContent();
////        i = 0;
////        for (PersonInfo personInfo : recommendation) {
////            Tr tr = (Tr) rows.get(i);
////            List<Object> cells = tr.getContent();
////            for (int j = 0; j < cells.size(); j++) {
////                Tc td = (Tc) cells.get(j);
////                ObjectFactory factory = Context.getWmlObjectFactory();
////                P p = factory.createP();
////                R r = factory.createR();
////                Text t = factory.createText();
////                switch (j) {
////                    case 0:
////                        t.setValue(personInfo.getName());
////                    case 1:
////                        t.setValue(personInfo.getTel());
////                    case 2:
////                        t.setValue(personInfo.getComment());
////                }
////                r.getContent().add(t);
////                p.getContent().add(r);
////                td.getContent().add(p);
////            }
////        }
////
////
////        writableWidthTwips = wordMLPackage.getDocumentModel()
////                .getSections().get(0).getPageDimensions().getWritableWidthTwips();
////        columnNumber = 2;
////
////        Set<SocialNetwork> socialNetworks = request.getSocialNetworks();
////        tbl = TblFactory.createTable(socialNetworks.size(), 2, writableWidthTwips / columnNumber);
////        rows = tbl.getContent();
////        i = 0;
////        for (SocialNetwork socialNetwork : socialNetworks) {
////            Tr tr = (Tr) rows.get(i);
////            List<Object> cells = tr.getContent();
////            for (int j = 0; j < cells.size(); j++) {
////                Tc td = (Tc) cells.get(j);
////                ObjectFactory factory = Context.getWmlObjectFactory();
////                P p = factory.createP();
////                R r = factory.createR();
////                Text t = factory.createText();
////                switch (j) {
////                    case 0:
////                        t.setValue(socialNetwork.getTypeOfSocial());
////                    case 1:
////                        t.setValue(socialNetwork.getUrl());
////                }
////                r.getContent().add(t);
////                p.getContent().add(r);
////                td.getContent().add(p);
////            }
////        }
//
//        File exportFile = new File(filename);
//        try {
//            wordMLPackage.save(exportFile);
//        } catch (Docx4JException e) {
//            return false;
//        }
//        return true;
//    }
//}