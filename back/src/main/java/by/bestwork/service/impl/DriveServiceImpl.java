package by.bestwork.service.impl;

import by.bestwork.config.constant.ApplicationConstant;
import by.bestwork.domain.ContractRequest;
import by.bestwork.dto.HelpBody;
import by.bestwork.service.AuthorizationService;
import by.bestwork.service.DriveService;
import by.bestwork.util.ApplicationConfig;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.AppendValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static by.bestwork.config.constant.ApplicationConstant.APPLICATION_NAME;
import static by.bestwork.config.constant.ApplicationConstant.JSON_FACTORY;

@Service
public class DriveServiceImpl implements DriveService {

    private Logger logger = LoggerFactory.getLogger(DriveServiceImpl.class);

    private Sheets sheetsService;
    private Drive driveService;

    @Autowired
    AuthorizationService authorizationService;

    @Autowired
    ApplicationConfig applicationConfig;

    @PostConstruct
    public void init() throws Exception {
        Credential credential = authorizationService.getCredentials();
        driveService = new Drive.Builder(ApplicationConstant.HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME).build();
        sheetsService = new Sheets.Builder(ApplicationConstant.HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME).build();
    }

    @Override
    public void uploadFile(MultipartFile multipartFile) throws Exception {
        logger.debug("Inside Upload Service...");

        String path = applicationConfig.getTemporaryFolder();
        String fileName = multipartFile.getOriginalFilename();
        String contentType = multipartFile.getContentType();

        java.io.File transferedFile = new java.io.File(path, fileName);
        multipartFile.transferTo(transferedFile);

        File fileMetadata = new File();
        fileMetadata.setName(fileName);

        FileContent mediaContent = new FileContent(contentType, transferedFile);
        File file = driveService.files().create(fileMetadata, mediaContent).setFields("id").execute();

        logger.debug("File ID: " + file.getName() + ", " + file.getId());
    }

    @Override
    public String saveHelp(HelpBody help) throws IOException {
        List<List<Object>> values = Arrays.asList(
                Arrays.asList("", help.getName(), help.getTel(), help.getQuestion())
        );
        ValueRange body = new ValueRange()
                .setValues(values);
        AppendValuesResponse result =
                sheetsService.spreadsheets().values().append("1an0GuYSdz-RloOO8Lxpbuw94d55sGxGGnj8oCmJ6rrU", "Sheet1", body)
                        .setValueInputOption("USER_ENTERED")
                        .execute();
        System.out.printf("%d cells appended.", result.getUpdates().getUpdatedCells());
        return "";
    }

    @Override
    public ContractRequest saveContractRequest(ContractRequest contractRequest) throws IOException, GeneralSecurityException {
        logger.debug("Inside Save Service...");

        String path = applicationConfig.getTemporaryFolder();
        String fileName = "contract" + contractRequest.getId() + ".docx";
        File fileMetaData = new File();
        fileMetaData.setName(contractRequest.getRequest().getLastName() + " " + contractRequest.getRequest().getFirstName() + " " + contractRequest.getRequest().getOtchestvo() + " " + contractRequest.getCreatedAt());
        fileMetaData.setParents(Collections.singletonList("1ayVvuKevF9J0vbRtgmW5eFu4NIRle82e"));

        java.io.File filePath = new java.io.File(path, fileName);
        FileContent fileContent = new FileContent("application/vnd.openxmlformats-officedocument.wordprocessingml.document", filePath);
        File file = driveService.files().create(fileMetaData, fileContent)
                .setFields("id, parents")
                .execute();
        logger.debug("File ID: " + file.getId());
        contractRequest.setSaved(true);

        savePrikazCode(contractRequest);
        saveContractToBase(contractRequest, file);

        return contractRequest;

    }

    private String getPrikazCode(ContractRequest contractRequest) throws IOException {
        String prikazCodeEx = new SimpleDateFormat("yy/MM-dd").format(contractRequest.getStartOfContract());
        ValueRange result = sheetsService.spreadsheets().values().get("1H01LyU9tVPUx3tviPi9cT_SANif88GHBzMKQSKWWORU", getCompany(contractRequest) + "!A3:A").execute();
        List<List<Object>> rows = result.getValues();
        String prikazCode = null;
        for (int j = 1; j < 99; j++) {
            if (j < 10) {
                prikazCode = prikazCodeEx + "0" + j;
            } else {
                prikazCode = prikazCodeEx + j;
            }
            if (getIteration(rows, prikazCode)) {
                break;
            }
        }
        return prikazCode;
    }

    private void savePrikazCode(ContractRequest contractRequest) throws IOException {
        String prikazCode = getPrikazCode(contractRequest);
        List<List<Object>> values = Arrays.asList(
                Arrays.asList(prikazCode, new SimpleDateFormat("dd.MM.yyyy").format(contractRequest.getStartOfContract()), "Приказ на принятие " + contractRequest.getWhoAreYou() + " " + contractRequest.getRequest().getLastName() + " " + contractRequest.getRequest().getFirstName() + " " + contractRequest.getRequest().getOtchestvo() + " " + new SimpleDateFormat("dd.MM.yyyy").format(contractRequest.getStartOfContract()))
        );
        ValueRange body = new ValueRange()
                .setValues(values);
        AppendValuesResponse result =
                sheetsService.spreadsheets().values().append("1H01LyU9tVPUx3tviPi9cT_SANif88GHBzMKQSKWWORU", getCompany(contractRequest), body)
                        .setValueInputOption("USER_ENTERED")
                        .execute();
        System.out.printf("%d cells appended.", result.getUpdates().getUpdatedCells());
    }

    private boolean getIteration(List<List<Object>> rows, String prikazCode) {
        for (int i = 0; i < rows.size(); i++) {
            if (rows.get(i).get(0).toString().contains(prikazCode)) {
                return false;
            }
        }
        return true;
    }

    private String getCompany(ContractRequest contractRequest) {
        switch (contractRequest.getCompanyType()) {
            case TD:
                return "ТД";
            case FK:
                return "ФК";
            case KB:
                return "КБ";
            case TI:
                return "ТИ";
            default:
                return "";
        }
    }


    private void saveContractToBase(ContractRequest contractRequest, File file) throws IOException {
        List<Object> row = new ArrayList<>();
        row.add(contractRequest.getCompanyType() + " - " + contractRequest.getRequest().getLastName() + " " + contractRequest.getRequest().getFirstName() + " " + contractRequest.getRequest().getOtchestvo());
        row.add("ИП");
        row.add(contractRequest.getUniqPassportNumber());
        row.add(contractRequest.getStartOfContract().toString());
        row.add(file.getId());
        List<List<Object>> values = new ArrayList<>();
        values.add(row);
        ValueRange body = new ValueRange()
                .setValues(values);
        AppendValuesResponse result =
                sheetsService.spreadsheets().values().append("1UHh5BklSjVqmyThao2_weHdbnrNTgXAGEg_dedcbg3w", "Sheet1", body)
                        .setValueInputOption("USER_ENTERED")
                        .execute();
        System.out.printf("%d cells appended.", result.getUpdates().getUpdatedCells());
    }

}
