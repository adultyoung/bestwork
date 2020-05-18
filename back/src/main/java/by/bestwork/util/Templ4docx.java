package by.bestwork.util;

import by.bestwork.domain.ContractRequest;
import by.bestwork.domain.Docs;
import by.bestwork.domain.PereRaspredRequest;
import by.bestwork.domain.RaspredRequest;
import by.bestwork.dto.CompanyInfo;
import by.bestwork.service.ServiceDriveService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import pl.jsolve.templ4docx.core.Docx;
import pl.jsolve.templ4docx.core.VariablePattern;
import pl.jsolve.templ4docx.variable.TextVariable;
import pl.jsolve.templ4docx.variable.Variables;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Templ4docx {

    private final CreateTable createTable;
    private final ServiceDriveService driveService;

    public Templ4docx(CreateTable createTable, ServiceDriveService driveService) {
        this.createTable = createTable;
        this.driveService = driveService;
    }


    public String generateDocx(ContractRequest request) throws IOException {
        Docx docx = new Docx(
                new ClassPathResource("exampleDoc/example.docx"
                )
                        .getInputStream());
        docx.setVariablePattern(new VariablePattern("${", "}"));

        CompanyInfo companyInfo = new CompanyInfo(request.getCompanyType());

// preparing variables
        Variables variables = new Variables();
        variables.addTextVariable(new TextVariable("${companyName}", companyInfo.getName()));
        variables.addTextVariable(new TextVariable("${directorJob}", companyInfo.getDirectorJob()));
        variables.addTextVariable(new TextVariable("${directorName}", companyInfo.getDirectorName()));
        variables.addTextVariable(new TextVariable("${osnovanie}", companyInfo.getOsnovanie()));
        variables.addTextVariable(new TextVariable("${fio}", request.getRequest().getLastName() + " " + request.getRequest().getFirstName() + " " + request.getRequest().getOtchestvo()));
        variables.addTextVariable(new TextVariable("${fioRod}", request.getFioRod()));
        variables.addTextVariable(new TextVariable("${fioDat}", request.getFioDat()));
        variables.addTextVariable(new TextVariable("${fioTvor}", request.getFioTvor()));
        variables.addTextVariable(new TextVariable("${job}", request.getJob()));
        variables.addTextVariable(new TextVariable("${jobTvor}", request.getJobTvor()));
        variables.addTextVariable(new TextVariable("${jobRod}", request.getJobRod()));
        switch (request.getPeriod().intValue()) {
            case 1:
                variables.addTextVariable(new TextVariable("${per}", request.getPeriod() + " год"));
                break;
            case 2 - 4:
                variables.addTextVariable(new TextVariable("${per}", request.getPeriod() + " года"));
                break;
            default:
                variables.addTextVariable(new TextVariable("${per}", request.getPeriod() + " лет"));
        }
        variables.addTextVariable(new TextVariable("${startOfContract}", new SimpleDateFormat("dd.MM.yyyy").format(request.getStartOfContract())));
        variables.addTextVariable(new TextVariable("${endOfContract}", new SimpleDateFormat("dd.MM.yyyy").format(request.getEndOfContract())));
        variables.addTextVariable(new TextVariable("${startDateOfPassport}", new SimpleDateFormat("dd.MM.yyyy").format(request.getStartDateOfPassport())));
        variables.addTextVariable(new TextVariable("${endDateOfPassport}", new SimpleDateFormat("dd.MM.yyyy").format(request.getEndDateOfPassport())));
        variables.addTextVariable(new TextVariable("${dateOfBirth}", new SimpleDateFormat("dd.MM.yyyy").format(request.getDateOfBirth())));
        variables.addTextVariable(new TextVariable("${tel}", request.getRequest().getTel()));
        variables.addTextVariable(new TextVariable("${homeTel}", request.getHomeTel()));
        variables.addTextVariable(new TextVariable("${factAddress}", request.getFactAddress()));
        variables.addTextVariable(new TextVariable("${officialAddress}", request.getOfficialAddress()));
        variables.addTextVariable(new TextVariable("${passportNumber}", request.getPassportNumber()));
        variables.addTextVariable(new TextVariable("${whoIsGiven}", request.getWhoIsGiven()));
        variables.addTextVariable(new TextVariable("${uniqPassportNumber}", request.getUniqPassportNumber()));

// fill template
        docx.fillTemplate(variables);

// save filled .docx file
        String filename = "contract" + request.getId() + ".docx";

        File dir = new File("contracts");
        if (!dir.exists()) dir.mkdirs();
        BufferedOutputStream stream =
                new BufferedOutputStream(new FileOutputStream(new File("contracts/" + filename)));
        docx.save(stream);
        stream.flush();
        stream.close();
        try {
            createTable.generateDocxFileFromTemplate(request, filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "contracts/" + filename;
    }

    public String generateRaspred(RaspredRequest request) throws IOException {
        Docx docx = new Docx(
                new ClassPathResource("exampleDoc/example.docx"
                )
                        .getInputStream());
        docx.setVariablePattern(new VariablePattern("${", "}"));

        CompanyInfo companyInfo = new CompanyInfo(request.getCompanyType());
        Variables variables = new Variables();
        variables.addTextVariable(new TextVariable("${firmBlank}", companyInfo.getFirmBlank()));
        variables.addTextVariable(
                new TextVariable(
                        "${prikazNumber}", driveService.savePrikaz(
                        "Письмо на распределение " +
                                request.getRequest().getLastName() + " " +
                                request.getRequest().getFirstName() + " " +
                                request.getRequest().getOtchestvo() + " " +
                                request.getRequest().getYoName(), request.getCompanyType())));
        variables.addTextVariable(new TextVariable("${date}", new SimpleDateFormat("dd.MM.yyyy").format(new Date(System.currentTimeMillis()))));
        variables.addTextVariable(new TextVariable("${rukovoditelYO}", request.getRequest().getRukovoditelYO()));
        variables.addTextVariable(new TextVariable("${yo}", request.getRequest().getYoName()));
        variables.addTextVariable(new TextVariable("${rukovoditelYOFio}", request.getRequest().getRectorName()));
        variables.addTextVariable(new TextVariable("${yearOfGraduation}", request.getRequest().getYearOfGraduation()));
        variables.addTextVariable(new TextVariable("${facultet}", request.getRequest().getFacultet()));
        variables.addTextVariable(new TextVariable("${speciality}", request.getRequest().getSpecialization()));
        variables.addTextVariable(new TextVariable("${fioTvor}", request.getRequest().getFioTvor()));


        variables.addTextVariable(new TextVariable("${rukovoditelInitials}", companyInfo.getDirectorName()));
        variables.addTextVariable(new TextVariable("${firmBlank}", companyInfo.getFirmBlank()));

        docx.fillTemplate(variables);

        String filename = "raspred_" + request.getId() + ".docx";

        File dir = new File("raspred");
        if (!dir.exists()) dir.mkdirs();
        BufferedOutputStream stream =
                new BufferedOutputStream(new FileOutputStream(new File("raspred/" + filename)));
        docx.save(stream);
        stream.flush();
        stream.close();
        return filename;
    }

    public String generatePereRaspred(PereRaspredRequest request) throws IOException {
        Docx docx = new Docx(
                new ClassPathResource("exampleDoc/pereRaspred_YO.docx"
                )
                        .getInputStream());
        docx.setVariablePattern(new VariablePattern("${", "}"));

        CompanyInfo companyInfo = new CompanyInfo(request.getCompanyType());
        Variables variables = new Variables();

        variables.addTextVariable(new TextVariable("${firmBlank}", companyInfo.getFirmBlank()));
        variables.addTextVariable(
                new TextVariable("${prikazNumber}", driveService.savePrikaz(
                        "Письмо на перераспределение" +
                                request.getRequest().getLastName() + " " +
                                request.getRequest().getFirstName() + " " +
                                request.getRequest().getOtchestvo() + " " +
                                request.getRequest().getYoName(), request.getCompanyType())));
        variables.addTextVariable(new TextVariable("${date}", new SimpleDateFormat("dd.MM.yyyy").format(new Date(System.currentTimeMillis())) + " г."));
        variables.addTextVariable(new TextVariable("${rukovoditelYO}", request.getRequest().getRukovoditelYO()));
        variables.addTextVariable(new TextVariable("${yo}", request.getRequest().getYoName()));
        variables.addTextVariable(new TextVariable("${rukovoditelYOFio}", request.getRequest().getRectorName()));
        variables.addTextVariable(new TextVariable("${yearOfGraduation}", request.getRequest().getYearOfGraduation()));
        variables.addTextVariable(new TextVariable("${facultet}", request.getRequest().getFacultet()));
        variables.addTextVariable(new TextVariable("${speciality}", request.getRequest().getSpecialization()));
        variables.addTextVariable(new TextVariable("${fioTvor}", request.getRequest().getFioTvor()));
        variables.addTextVariable(new TextVariable("${firma}", companyInfo.getName()));
        variables.addTextVariable(new TextVariable("${rukovoditelInitials}", companyInfo.getDirectorName()));
        docx.fillTemplate(variables);

        String filename = "pereRaspredYO_" + request.getId() + ".docx";

        File dir = new File("pereRaspred");
        if (!dir.exists()) dir.mkdirs();
        BufferedOutputStream stream =
                new BufferedOutputStream(new FileOutputStream(new File("pereRaspred/" + filename)));
        docx.save(stream);
        stream.flush();
        stream.close();

        docx = new Docx(
                new ClassPathResource("exampleDoc/pereRaspred_base.docx"
                )
                        .getInputStream());
        docx.setVariablePattern(new VariablePattern("${", "}"));
        variables = new Variables();
        variables.addTextVariable(new TextVariable("${firmBlank}", companyInfo.getFirmBlank()));
        variables.addTextVariable(
                new TextVariable("${prikazNumber}", driveService.savePrikaz(
                        "Письмо на перераспределение" +
                                request.getRequest().getLastName() + " " +
                                request.getRequest().getFirstName() + " " +
                                request.getRequest().getOtchestvo() + " " +
                                request.getBaseOfRaspred(), request.getCompanyType())));

        variables.addTextVariable(new TextVariable("${firma}", companyInfo.getName()));
        variables.addTextVariable(new TextVariable("${job}", request.getJob()));
        variables.addTextVariable(new TextVariable("${fioRod}", request.getRequest().getFioRod()));
        variables.addTextVariable(new TextVariable("${yearOfGraduation}", request.getRequest().getYearOfGraduation()));
        variables.addTextVariable(new TextVariable("${yo}", request.getRequest().getYoName()));
        variables.addTextVariable(new TextVariable("${rukovoditelInitials}", companyInfo.getDirectorName()));
        docx.fillTemplate(variables);

        String filename2 = "pereRaspred_Base_" + request.getId() + ".docx";

        dir = new File("pereRaspred");
        if (!dir.exists()) dir.mkdirs();
        stream =
                new BufferedOutputStream(new FileOutputStream(new File("pereRaspred/" + filename2)));
        docx.save(stream);
        stream.flush();
        stream.close();

        return filename + " " + filename2;
    }

    public String generateSpravkaSMestaRaboty(Docs docs, ContractRequest userContract) throws IOException {
        Docx docx = new Docx(
                new ClassPathResource("exampleDoc/Spravka_s_mesta_raboty.docx"
                )
                        .getInputStream());
        docx.setVariablePattern(new VariablePattern("${", "}"));
        Variables variables = new Variables();
        CompanyInfo companyInfo = new CompanyInfo(userContract.getCompanyType());
        variables.addTextVariable(new TextVariable("${firmBlank}", companyInfo.getFirmBlank()));
        variables.addTextVariable(
                new TextVariable("${prikazNumber}", driveService.savePrikaz(
                        "Справка с места работы" +
                                userContract.getRequest().getLastName() + " " +
                                userContract.getRequest().getFirstName() + " " +
                                userContract.getRequest().getOtchestvo(), userContract.getCompanyType())));
        variables.addTextVariable(new TextVariable("${date}", new SimpleDateFormat("dd.MM.yyyy").format(new Date(System.currentTimeMillis())) + " г."));
        variables.addTextVariable(new TextVariable("${fioDat}", userContract.getFioDat()));
        variables.addTextVariable(new TextVariable("${firmName}", companyInfo.getName()));
        variables.addTextVariable(new TextVariable("${startDateOfContract}", new SimpleDateFormat("dd.MM.yyyy").format(userContract.getStartOfContract())));
        variables.addTextVariable(new TextVariable("${prinyatPrikaz}", userContract.getPrinyatPrikaz()));
        variables.addTextVariable(new TextVariable("${firmDirector}", companyInfo.getDirectorJob()));
        variables.addTextVariable(new TextVariable("${firmDirectorName}", companyInfo.getDirectorName()));

        docx.fillTemplate(variables);

        String filename = "spravka_s_mesta_raboty_" + docs.getId() + ".docx";

        File dir = new File("spravka");
        if (!dir.exists()) dir.mkdirs();
        BufferedOutputStream stream =
                new BufferedOutputStream(new FileOutputStream(new File("spravka/" + filename)));
        docx.save(stream);
        stream.flush();
        stream.close();

        return filename;

    }

    public String generateSpravkaOPeriodeRaboty(Docs docs, ContractRequest userContract) throws IOException {
        if (userContract.getYvolenPrikaz() == null) {
            return null;
        }
        Docx docx = new Docx(
                new ClassPathResource("exampleDoc/Spravka_o_periode_raboty.docx"
                )
                        .getInputStream());
        docx.setVariablePattern(new VariablePattern("${", "}"));
        Variables variables = new Variables();
        CompanyInfo companyInfo = new CompanyInfo(userContract.getCompanyType());
        variables.addTextVariable(new TextVariable("${firmBlank}", companyInfo.getFirmBlank()));
        variables.addTextVariable(
                new TextVariable("${prikazNumber}", driveService.savePrikaz(
                        "Справка о периоде работы" +
                                userContract.getRequest().getLastName() + " " +
                                userContract.getRequest().getFirstName() + " " +
                                userContract.getRequest().getOtchestvo(), userContract.getCompanyType())));
        variables.addTextVariable(new TextVariable("${date}", new SimpleDateFormat("dd.MM.yyyy").format(new Date(System.currentTimeMillis()))));
        variables.addTextVariable(new TextVariable("${adresat}", docs.getAdresat()));
        variables.addTextVariable(new TextVariable("${firmName}", companyInfo.getName()));
        variables.addTextVariable(new TextVariable("${fio}", userContract.getRequest().getLastName() + " " + userContract.getRequest().getFirstName() + " " + userContract.getRequest().getOtchestvo()));
        variables.addTextVariable(new TextVariable("${startDateOfContract}", new SimpleDateFormat("dd.MM.yyyy").format(userContract.getStartOfContract())));
        variables.addTextVariable(new TextVariable("${prinyatPrikaz}", userContract.getPrinyatPrikaz()));
        variables.addTextVariable(new TextVariable("${yvolenPrikaz}", userContract.getYvolenPrikaz()));
        variables.addTextVariable(new TextVariable("${yvolenDate}", new SimpleDateFormat("dd.MM.yyyy").format(userContract.getYvolenDate())));
        variables.addTextVariable(new TextVariable("${firmDirector}", companyInfo.getDirectorJob()));
        variables.addTextVariable(new TextVariable("${firmDirectorName}", companyInfo.getDirectorName()));

        docx.fillTemplate(variables);

        String filename = "spravka_o_periode_raboty_" + docs.getId() + ".docx";

        File dir = new File("spravka");
        if (!dir.exists()) dir.mkdirs();
        BufferedOutputStream stream =
                new BufferedOutputStream(new FileOutputStream(new File("spravka/" + filename)));
        docx.save(stream);
        stream.flush();
        stream.close();

        return filename;

    }

    public String generateSpravkaODohodah(Docs docs, ContractRequest userContract) throws IOException {
        Docx docx = new Docx(
                new ClassPathResource("exampleDoc/spravka_dohodi.docx"
                )
                        .getInputStream());
        docx.setVariablePattern(new VariablePattern("${", "}"));
        Variables variables = new Variables();
        CompanyInfo companyInfo = new CompanyInfo(userContract.getCompanyType());
        variables.addTextVariable(new TextVariable("${firmBlank}", companyInfo.getFirmBlank()));
        variables.addTextVariable(
                new TextVariable("${prikazNumber}", driveService.savePrikaz(
                        "Справка о доходах" +
                                userContract.getRequest().getLastName() + " " +
                                userContract.getRequest().getFirstName() + " " +
                                userContract.getRequest().getOtchestvo(), userContract.getCompanyType())));
        variables.addTextVariable(new TextVariable("${date}", new SimpleDateFormat("dd.MM.yyyy").format(new Date(System.currentTimeMillis()))));
        variables.addTextVariable(new TextVariable("${fioRod}", userContract.getFioRod()));
        variables.addTextVariable(new TextVariable("${firmName}", companyInfo.getName()));
        variables.addTextVariable(new TextVariable("${firmAddress}", companyInfo.getAddress()));
        variables.addTextVariable(new TextVariable("${firmYNP}", companyInfo.getYNP()));
        variables.addTextVariable(new TextVariable("${job}", userContract.getJob()));
        variables.addTextVariable(new TextVariable("${startDateOfContract}", new SimpleDateFormat("dd.MM.yyyy").format(userContract.getStartOfContract())));
        variables.addTextVariable(new TextVariable("${endDateOfContract}", new SimpleDateFormat("dd.MM.yyyy").format(userContract.getEndOfContract())));
        variables.addTextVariable(new TextVariable("${firmDirectorName}", companyInfo.getDirectorName()));
        variables.addTextVariable(new TextVariable("${firmDirector}", companyInfo.getDirectorJob()));

        docx.fillTemplate(variables);

        String filename = "spravka_o_dohodah_" + docs.getId() + ".docx";

        File dir = new File("spravka");
        if (!dir.exists()) dir.mkdirs();
        BufferedOutputStream stream =
                new BufferedOutputStream(new FileOutputStream(new File("spravka/" + filename)));
        docx.save(stream);
        stream.flush();
        stream.close();

        return filename;
    }

    public String getHarakteristika(Docs docs, ContractRequest userContract) throws IOException {
        Docx docx = new Docx(
                new ClassPathResource("exampleDoc/harakteristika.docx"
                )
                        .getInputStream());
        docx.setVariablePattern(new VariablePattern("${", "}"));
        Variables variables = new Variables();
        CompanyInfo companyInfo = new CompanyInfo(userContract.getCompanyType());
        variables.addTextVariable(new TextVariable("${firmBlank}", companyInfo.getFirmBlank()));
        variables.addTextVariable(
                new TextVariable("${prikazNumber}", driveService.savePrikaz(
                        "Характеристика" +
                                userContract.getRequest().getLastName() + " " +
                                userContract.getRequest().getFirstName() + " " +
                                userContract.getRequest().getOtchestvo(), userContract.getCompanyType())));
        variables.addTextVariable(new TextVariable("${date}", new SimpleDateFormat("dd.MM.yyyy").format(new Date(System.currentTimeMillis()))));
        variables.addTextVariable(new TextVariable("${fio}", userContract.getRequest().getLastName() + " " + userContract.getRequest().getFirstName() + " " + userContract.getRequest().getOtchestvo()));
        variables.addTextVariable(new TextVariable("${birthYear}", new SimpleDateFormat("yyyy").format(userContract.getDateOfBirth())));
        variables.addTextVariable(new TextVariable("${job}", userContract.getJob()));
        variables.addTextVariable(new TextVariable("${startDateOfContract}", new SimpleDateFormat("dd.MM.yyyy").format(userContract.getStartOfContract())));
        variables.addTextVariable(new TextVariable("${firmDirectorName}", companyInfo.getDirectorName()));
        variables.addTextVariable(new TextVariable("${firmDirector}", companyInfo.getDirectorJob()));
        variables.addTextVariable(new TextVariable("${firmName}", companyInfo.getName()));

        docx.fillTemplate(variables);

        String filename = "harakteristika_" + docs.getId() + ".docx";

        File dir = new File("spravka");
        if (!dir.exists()) dir.mkdirs();
        BufferedOutputStream stream =
                new BufferedOutputStream(new FileOutputStream(new File("spravka/" + filename)));
        docx.save(stream);
        stream.flush();
        stream.close();

        return filename;
    }
}
