package by.bestwork.controller;

import by.bestwork.dao.DocsDao;
import by.bestwork.domain.ContractRequest;
import by.bestwork.domain.Docs;
import by.bestwork.service.ContractRequestService;
import by.bestwork.service.PereRaspredRequestService;
import by.bestwork.service.RaspredRequestService;
import by.bestwork.util.Templ4docx;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Set;

@Controller
@RestController
public class PrintRequestController {
    private final ContractRequestService contractRequestService;
    private final RaspredRequestService raspredRequestService;
    private final PereRaspredRequestService pereRaspredRequestService;
    private final DocsDao docsDao;
    private final Templ4docx docx;

    public PrintRequestController(ContractRequestService contractRequestService, RaspredRequestService raspredRequestService, PereRaspredRequestService pereRaspredRequestService, DocsDao docsDao, Templ4docx docx) {
        this.contractRequestService = contractRequestService;
        this.raspredRequestService = raspredRequestService;
        this.pereRaspredRequestService = pereRaspredRequestService;
        this.docsDao = docsDao;
        this.docx = docx;
    }

    @RequestMapping(value = "/print-request-contract/{id}", method = RequestMethod.GET)
    public String printContract(@PathVariable("id") Long contractId) throws IOException {
        ContractRequest request = contractRequestService.findById(contractId);
        return docx.generateDocx(request);
    }

    @RequestMapping(value = "/print-raspred/{id}", method = RequestMethod.GET)
    public String printRaspred(@PathVariable("id") String id) throws IOException {
        return docx.generateRaspred(raspredRequestService.findById(id));
    }

    @RequestMapping(value = "/print-pereraspred/{id}", method = RequestMethod.GET)
    public String printPereRaspred(@PathVariable("id") String id) throws IOException {
        return docx.generatePereRaspred(pereRaspredRequestService.findById(id));
    }

    @RequestMapping(value = "/print-spravka/{id}", method = RequestMethod.GET)
    public String printPereRaspred(@PathVariable("id") Long id) throws IOException {
        Docs doc = docsDao.findById(id).get();
        Set<ContractRequest> request = contractRequestService.getContractRequestsForUser(doc.getUserId());
        switch (doc.getDocsType()) {
            case SMESTARABOTI:
                return docx.generateSpravkaSMestaRaboty(doc, request.iterator().next());
            case OPERIODE:
                return docx.generateSpravkaOPeriodeRaboty(doc, request.iterator().next());
            case ODOHODAH:
                return docx.generateSpravkaODohodah(doc, request.iterator().next());
            case HARAKTERISTIKA:
                return docx.getHarakteristika(doc, request.iterator().next());
            default:
                return null;
        }
    }
}
