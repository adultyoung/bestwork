package by.bestwork.domain;

import by.bestwork.dto.CompanyType;
import by.bestwork.dto.PersonInfo;
import by.bestwork.dto.Request;
import by.bestwork.dto.SocialNetwork;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class ContractRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition = "int4")
    private int predoplata;
    private String prinyatPrikaz;
    private String yvolenPrikaz;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date yvolenDate;
    private String fioRod;
    private String fioDat;
    private String fioTvor;
    private String referal;
    private String job;
    private String jobTvor;
    private String jobRod;
    private Long period;
    private String officialAddress;
    private String passportNumber;
    private String uniqPassportNumber;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date startDateOfPassport;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date endDateOfPassport;
    private String whoIsGiven;
    private String viberNumber;
    private String homeTel;
    private String factAddress;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "contract_trustees", joinColumns = @JoinColumn(name = "contract_request_id"))
    private Set<PersonInfo> trustees = new HashSet<>(Arrays.asList(new PersonInfo()));
    private PersonInfo kurator;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "contract_recommendation", joinColumns = @JoinColumn(name = "contract_request_id"))
    private Set<PersonInfo> recommendation = new HashSet<>(Arrays.asList(new PersonInfo()));
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "contract_socials", joinColumns = @JoinColumn(name = "contract_request_id"))
    private Set<SocialNetwork> socialNetworks = new HashSet<>(Arrays.asList(new SocialNetwork()));
    private Long userId;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date startOfContract;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private Date endOfContract;
    private Integer periodOfContract;

    @Column(columnDefinition = "boolean default false")
    private boolean saved;

    private String whoAreYou;

    private String createdAt;

    private CompanyType companyType;


    @Embedded
    private Request request = new Request();
}