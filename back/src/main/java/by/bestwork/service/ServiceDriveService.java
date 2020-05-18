package by.bestwork.service;

import by.bestwork.config.constant.ApplicationConstant;
import by.bestwork.domain.Check;
import by.bestwork.domain.ContractRequest;
import by.bestwork.domain.RaspredRequest;
import by.bestwork.domain.User;
import by.bestwork.dto.CompanyType;
import by.bestwork.dto.HelpBody;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.security.interfaces.RSAPrivateKey;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.*;

import static by.bestwork.config.constant.ApplicationConstant.JSON_FACTORY;

@Service
public class ServiceDriveService {

    private GoogleCredential credential;
    private Sheets sheetService;
    private Drive driveService;
    private final UserService userService;
    private final CheckService checkService;

    @Value("google.account.service.issuer")
    private String issuer;

    @Value("google.account.service.subject")
    private String subject;

    @Value("google.account.service.audience")
    private String audience;

    public ServiceDriveService(UserService userService, CheckService checkService) {
        this.userService = userService;
        this.checkService = checkService;
    }

    @PostConstruct
    public void init() throws IOException {
        InputStream in = new ClassPathResource("service.json").getInputStream();
        credential = GoogleCredential.fromStream(in).createScoped(Arrays.asList(DriveScopes.DRIVE, SheetsScopes.SPREADSHEETS));
        sheetService = new Sheets.Builder(ApplicationConstant.HTTP_TRANSPORT, ApplicationConstant.JSON_FACTORY, null).setHttpRequestInitializer(credential).build();
        driveService = new Drive.Builder(ApplicationConstant.HTTP_TRANSPORT, JSON_FACTORY, null).setHttpRequestInitializer(credential).build();
    }

    public void saveHelp(HelpBody help) throws IOException {
        List<List<Object>> values = Arrays.asList(
                Arrays.asList("", help.getName(), help.getTel(), help.getQuestion())
        );
        ValueRange body = new ValueRange()
                .setValues(values);
        long now = System.currentTimeMillis();

        String signedJwt = null;

        try {
            Algorithm algorithm = Algorithm.RSA256(null, (RSAPrivateKey) credential.getServiceAccountPrivateKey());
            signedJwt = JWT.create()
                    .withKeyId(credential.getServiceAccountPrivateKeyId())
                    .withIssuer(issuer)
                    .withSubject(subject)
                    .withAudience(audience)
                    .withIssuedAt(new Date(now))
                    .withExpiresAt(new Date(now + 3600 * 1000L))
                    .sign(algorithm);
        } catch (Exception e) {
            System.out.println(e);
        }
        AppendValuesResponse result =
                sheetService.spreadsheets().values().append("1an0GuYSdz-RloOO8Lxpbuw94d55sGxGGnj8oCmJ6rrU", "Sheet1", body)
                        .setValueInputOption("USER_ENTERED").setBearerToken(signedJwt)
                        .execute();
        System.out.printf("%d cells appended.", result.getUpdates().getUpdatedCells());
    }

    @Scheduled(cron = "0 0 0 1 1/1 ? *")
    public void updateClientsDolgs() throws IOException {
        synchronized (sheetService) {
            String signedJwt = null;
            long now = System.currentTimeMillis();

            try {
                Algorithm algorithm = Algorithm.RSA256(null, (RSAPrivateKey) credential.getServiceAccountPrivateKey());
                signedJwt = JWT.create()
                        .withKeyId(credential.getServiceAccountPrivateKeyId())
                        .withIssuer(issuer)
                        .withSubject(subject)
                        .withAudience(audience)
                        .withIssuedAt(new Date(now))
                        .withExpiresAt(new Date(now + 3600 * 1000L))
                        .sign(algorithm);
            } catch (Exception e) {
                System.out.println(e);
            }
            List<List<Object>> clients = sheetService.spreadsheets().values().get("1CaFl8F3nn_Kuf7XJB64Zw7csM16VauCNsQ7y4ySgMj0", "Sheet1!F1:F").setBearerToken(signedJwt).execute().getValues();
            for (List<Object> row : clients) {
                row.set(0, 77 + (double) row.get(0));
            }
            ValueRange body = new ValueRange()
                    .setValues(clients);
            UpdateValuesResponse result =
                    sheetService.spreadsheets().values().update("1CaFl8F3nn_Kuf7XJB64Zw7csM16VauCNsQ7y4ySgMj0", "Sheet1!F1:F", body)
                            .setValueInputOption("USER_ENTERED").setBearerToken(signedJwt)
                            .execute();
        }
    }


    @Scheduled(fixedRate = 900000)
    public void saveCheck() throws IOException, ParseException {
        synchronized (sheetService) {
            String signedJwt = null;
            long now = System.currentTimeMillis();

            try {
                Algorithm algorithm = Algorithm.RSA256(null, (RSAPrivateKey) credential.getServiceAccountPrivateKey());
                signedJwt = JWT.create()
                        .withKeyId(credential.getServiceAccountPrivateKeyId())
                        .withIssuer(issuer)
                        .withSubject(subject)
                        .withAudience(audience)
                        .withIssuedAt(new Date(now))
                        .withExpiresAt(new Date(now + 3600 * 1000L))
                        .sign(algorithm);
            } catch (Exception e) {
                System.out.println(e);
            }

            List<Check> checks = checkService.findAllbyUploadedIsFalse();
            List<List<Object>> clients = sheetService.spreadsheets().values().get("1CaFl8F3nn_Kuf7XJB64Zw7csM16VauCNsQ7y4ySgMj0", "Sheet1!A1:O").setBearerToken(signedJwt).execute().getValues();


            for (Check check : checks) {
                Optional<String> extension = getExtensionByStringHandling(check.getFile());
                String fileName = new SimpleDateFormat("dd.MM.yyyy").format(new Date(System.currentTimeMillis())) + "_" + check.getSum() + "_р." + extension.get();

                File fileMetaData = new File();
                fileMetaData.setName(fileName);
                fileMetaData.setParents(Collections.singletonList("1snucRcuz-qBaDDgHPmtXAA198L5W0Qwk"));

                FileContent fileContent = new FileContent(getMIMETypeOfExtension(extension.get()), new java.io.File(""));
                File file = driveService.files().create(fileMetaData, fileContent).setOauthToken(signedJwt)
                        .setFields("id, parents")
                        .execute();
                check.setUploaded(true);
                checkService.saveCheck(check);
                Date dateNow = new Date(now);
                for (List<Object> row : clients) {
                    if (row.get(0).equals(check.getUserId())) {
                        List<Object> cells = clients.get(0);
                        int index = 6;
                        for (int cellNumber = 6; cellNumber < cells.size(); cellNumber++) {
                            Date date = new SimpleDateFormat("MM.yy").parse(cells.get(cellNumber).toString());
                            if (dateNow.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue() == date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue()) {
                                index = cellNumber;
                                break;
                            }
                        }
                        row.set(index, check.getSum());
                        double dolg = (double) row.get(5);
                        dolg -= check.getSum();
                        row.set(5, dolg);
                        User user = userService.getUser(check.getUserId());
                        user.setDolg(dolg);
                        userService.save(user);
                        break;
                    }
                }
            }
            ValueRange body = new ValueRange()
                    .setValues(clients);
            UpdateValuesResponse result =
                    sheetService.spreadsheets().values().update("1CaFl8F3nn_Kuf7XJB64Zw7csM16VauCNsQ7y4ySgMj0", "Sheet1", body)
                            .setValueInputOption("USER_ENTERED").setBearerToken(signedJwt)
                            .execute();
            System.out.printf("%d cells appended.", result.getUpdatedCells());
        }
    }

    private String getMIMETypeOfExtension(String extension) {
        switch (extension) {
            case "jpg":
                return "image/jpg";
            case "png":
                return "image/png";
            case "txt":
                return "txt/plain";
            case "pdf":
                return "application/pdf";
        }
        return null;
    }

    public void saveStudentRequest(RaspredRequest request) throws IOException {
        List<List<Object>> values = Arrays.asList(
                Arrays.asList(request.getUserId(), request.getFio(), request.getRequest().getYoName(), request.getRequest().getRectorName(), request.getRequest().getFacultet(), request.getRequest().getSpecialization(), request.getTel(), request.getEmail())
        );
        ValueRange body = new ValueRange()
                .setValues(values);
        long now = System.currentTimeMillis();

        String signedJwt = null;

        try {
            Algorithm algorithm = Algorithm.RSA256(null, (RSAPrivateKey) credential.getServiceAccountPrivateKey());
            signedJwt = JWT.create()
                    .withKeyId(credential.getServiceAccountPrivateKeyId())
                    .withIssuer(issuer)
                    .withSubject(subject)
                    .withAudience(audience)
                    .withIssuedAt(new Date(now))
                    .withExpiresAt(new Date(now + 3600 * 1000L))
                    .sign(algorithm);
        } catch (Exception e) {
            System.out.println(e);
        }
        AppendValuesResponse result =
                sheetService.spreadsheets().values().append("1Dr99hbWCYvbKBou7hAr_wQGGUy5qv84os_MdsiuH_wE", "Sheet1", body)
                        .setValueInputOption("USER_ENTERED").setBearerToken(signedJwt)
                        .execute();
        System.out.printf("%d cells appended.", result.getUpdates().getUpdatedCells());
        values = Arrays.asList(
                Arrays.asList(getPrikazCode(request.getDate(), "ФК", signedJwt), request.getDate(), "Письмо на распределение " + request.getFio() + " " + request.getRequest().getYoName())
        );
        savePrikaz(values, signedJwt, "ФК");
    }

    private String getPrikazCode(Date date, String predpriatie, String signedJwt) throws IOException {
        String prikazCodeEx = new SimpleDateFormat("yy/MM-dd").format(date);
        ValueRange result = sheetService.spreadsheets().values().get("1xPP36LVGEVVNhrXdZSo9338VIi6Yg64lDWBb16xc3E4", predpriatie + "!A1:A").setBearerToken(signedJwt).execute();
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

    private boolean getIteration(List<List<Object>> rows, String prikazCode) {
        for (int i = 0; i < rows.size(); i++) {
            if (rows.get(i).get(0).toString().contains(prikazCode)) {
                return false;
            }
        }
        return true;
    }

    private void savePrikaz(List<List<Object>> values, String signedJwt, String predpriatie) throws IOException {
        ValueRange body = new ValueRange()
                .setValues(values);
        AppendValuesResponse result =
                sheetService.spreadsheets().values().append("1xPP36LVGEVVNhrXdZSo9338VIi6Yg64lDWBb16xc3E4", predpriatie, body)
                        .setValueInputOption("USER_ENTERED").setBearerToken(signedJwt)
                        .execute();
        System.out.printf("%d cells appended.", result.getUpdates().getUpdatedCells());
    }

    public void saveContractRequest(ContractRequest request) throws IOException {
        long now = System.currentTimeMillis();
        User user = userService.loadUserById(request.getUserId());
        String signedJwt = null;

        try {
            Algorithm algorithm = Algorithm.RSA256(null, (RSAPrivateKey) credential.getServiceAccountPrivateKey());
            signedJwt = JWT.create()
                    .withKeyId(credential.getServiceAccountPrivateKeyId())
                    .withIssuer(issuer)
                    .withSubject(subject)
                    .withAudience(audience)
                    .withIssuedAt(new Date(now))
                    .withExpiresAt(new Date(now + 3600 * 1000L))
                    .sign(algorithm);
        } catch (Exception e) {
            System.out.println(e);
        }
        request.setPrinyatPrikaz(getPrikazCode(request.getStartOfContract(), getCompany(request.getCompanyType()), signedJwt));
        List<List<Object>> values = Arrays.asList(
                Arrays.asList(request.getPrinyatPrikaz(), request.getStartOfContract(), "Приказ на принятие " + request.getFioRod())
        );
        savePrikaz(values, signedJwt, getCompany(request.getCompanyType()));
        String[] trustee = {""};
        request.getTrustees().iterator().forEachRemaining(trust -> trustee[0] += trust.getComment() + " " + trust.getName() + " " + trust.getTel() + ", ");
        String[] networks = {""};
        request.getSocialNetworks().iterator().forEachRemaining(social -> networks[0] += social.getTypeOfSocial() + " " + social.getUrl() + ", ");
        values = Arrays.asList(
                Arrays.asList(request.getReferal(), user.getId(), request.getRequest().getLastName() + " " + request.getRequest().getFirstName()
                                + " " + request.getRequest().getOtchestvo(), getCompany(request.getCompanyType()), request.getWhoAreYou(),
                        request.getJob(), request.getStartOfContract(), request.getPrinyatPrikaz(), "", "", request.getEndOfContract(),
                        request.getRequest().getTel(), request.getRequest().getTel(), request.getFactAddress(), request.getPassportNumber(),
                        request.getWhoIsGiven(), request.getStartDateOfPassport(), request.getEndDateOfPassport(), request.getDateOfBirth(),
                        request.getUniqPassportNumber(), request.getOfficialAddress(), trustee[0], networks[0], request.getRequest().getEmail())
        );
        ValueRange body = new ValueRange()
                .setValues(values);

        AppendValuesResponse result =
                sheetService.spreadsheets().values().append("1IzSn0IDVRIKryaBmPWJbZo5FLDea16IBjWOjMFxgQKU", "Sheet1", body)
                        .setValueInputOption("USER_ENTERED").setBearerToken(signedJwt)
                        .execute();
        int dolg = (Calendar.getInstance().getActualMaximum(Calendar.DATE) - Calendar.getInstance().get(Calendar.DAY_OF_MONTH)) * 77;
        values = Arrays.asList(
                Arrays.asList(user.getId(), request.getRequest().getLastName() + " " + request.getRequest().getFirstName() + " " + request.getRequest().getOtchestvo(), user.getOplata(), request.getPredoplata(), user.getDolgPredPeriod(), dolg
                ));
        result =
                sheetService.spreadsheets().values().append("1CaFl8F3nn_Kuf7XJB64Zw7csM16VauCNsQ7y4ySgMj0", "Sheet1", body)
                        .setValueInputOption("USER_ENTERED").setBearerToken(signedJwt)
                        .execute();
    }

    private int getIdFromBase(String signedJwt) throws IOException {
        ValueRange result = sheetService.spreadsheets().values().get("1IzSn0IDVRIKryaBmPWJbZo5FLDea16IBjWOjMFxgQKU", "Sheet1!A1:A").setBearerToken(signedJwt).execute();
        List<List<Object>> rows = result.getValues();
        if (!rows.get(0).isEmpty()) {
            return 1 + (int) rows.get(rows.size() - 1).get(0);
        }
        return 1;
    }

    private String getCompany(CompanyType type) {
        switch (type) {
            case TI:
                return "ТИ";
            case KB:
                return "КБ";
            case FK:
                return "ФК";
            case TD:
                return "ТД";
        }
        return null;
    }

    private Optional<String> getExtensionByStringHandling(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));

    }

    public void yvolitNah(long userId) throws IOException {
        long now = System.currentTimeMillis();
        User user = userService.loadUserById(userId);
        String signedJwt = null;
        try {
            Algorithm algorithm = Algorithm.RSA256(null, (RSAPrivateKey) credential.getServiceAccountPrivateKey());
            signedJwt = JWT.create()
                    .withKeyId(credential.getServiceAccountPrivateKeyId())
                    .withIssuer(issuer)
                    .withSubject(subject)
                    .withAudience(audience)
                    .withIssuedAt(new Date(now))
                    .withExpiresAt(new Date(now + 3600 * 1000L))
                    .sign(algorithm);
        } catch (Exception e) {
            System.out.println(e);
        }
        List<List<Object>> clients = sheetService.spreadsheets().values().get("1CaFl8F3nn_Kuf7XJB64Zw7csM16VauCNsQ7y4ySgMj0", "Sheet1!A1:O").execute().getValues();
        for (int i = 1; i < clients.size(); i++) {
            if (clients.get(i).get(0).equals(user.getId())) {
                if (0 == (int) clients.get(i).get(5)) {
                    List<Request> requests = new ArrayList<>();
                    requests.add(new Request()
                            .setDeleteDimension(new DeleteDimensionRequest()
                                    .setRange(new DimensionRange()
                                            .setSheetId(0)
                                            .setDimension("ROWS")
                                            .setStartIndex(i)
                                            .setEndIndex(i)
                                    )
                            ));
                    BatchUpdateSpreadsheetRequest body =
                            new BatchUpdateSpreadsheetRequest().setRequests(requests);
                    BatchUpdateSpreadsheetResponse response =
                            sheetService.spreadsheets().batchUpdate("1xPP36LVGEVVNhrXdZSo9338VIi6Yg64lDWBb16xc3E4", body).execute();

                }
            }
        }
    }


    public String savePrikaz(String bodyOfPrikaz, CompanyType type) throws IOException {
        String predpriatie = getCompany(type);
        long now = System.currentTimeMillis();
        String signedJwt = null;
        try {
            Algorithm algorithm = Algorithm.RSA256(null, (RSAPrivateKey) credential.getServiceAccountPrivateKey());
            signedJwt = JWT.create()
                    .withKeyId(credential.getServiceAccountPrivateKeyId())
                    .withIssuer(issuer)
                    .withSubject(subject)
                    .withAudience(audience)
                    .withIssuedAt(new Date(now))
                    .withExpiresAt(new Date(now + 3600 * 1000L))
                    .sign(algorithm);
        } catch (Exception e) {
            System.out.println(e);
        }
        String prikaz = getPrikazCode(new Date(now), predpriatie, signedJwt);
        List<List<Object>> values = Arrays.asList(
                Arrays.asList(prikaz, new SimpleDateFormat("dd.MM.yyyy").format(new Date(now)), bodyOfPrikaz)
        );
        ValueRange body = new ValueRange()
                .setValues(values);
        AppendValuesResponse result =
                sheetService.spreadsheets().values().append("1xPP36LVGEVVNhrXdZSo9338VIi6Yg64lDWBb16xc3E4", predpriatie, body)
                        .setValueInputOption("USER_ENTERED").setBearerToken(signedJwt)
                        .execute();
        System.out.printf("%d cells appended.", result.getUpdates().getUpdatedCells());
        return prikaz;
    }
}
