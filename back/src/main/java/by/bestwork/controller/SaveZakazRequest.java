package by.bestwork.controller;

import by.bestwork.dao.DocsDao;
import by.bestwork.domain.Docs;
import by.bestwork.domain.User;
import by.bestwork.dto.DocsType;
import by.bestwork.util.Templ4docx;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/save-zakaz-request")
public class SaveZakazRequest {

    private final DocsDao docsDao;
    private final Templ4docx docx;

    public SaveZakazRequest(DocsDao docsDao, Templ4docx docx) {
        this.docsDao = docsDao;
        this.docx = docx;
    }

    @GetMapping
    public List<Docs> saveDocsRequest(@RequestParam("docs") List<String> docs, @AuthenticationPrincipal User user) {
        if (user == null) {
            return null;
        }
        List<Docs> retDocs = new ArrayList<>();
        docs.forEach(doc -> {
            Docs docRequest = new Docs();
            docRequest.setDocsType(getDocsType(doc));

            if (docRequest.getDocsType() == DocsType.OPERIODE) {
                docRequest.setAdresat(docs.get((docs.size() - 1)));
            }
            if (docRequest.getDocsType() != null) {
                docRequest.setUserId(user.getId());
                docRequest.setDate(new Date(System.currentTimeMillis()));
                docRequest.setStatus("Принят");
                docsDao.save(docRequest);
                retDocs.add(docRequest);
            }
        });
        return retDocs;

    }


    private DocsType getDocsType(String doc) {
        switch (doc) {
            case "Заказать справку о месте и продолжительности работы":
                return DocsType.OPERIODE;
            case "Заказать справку с места работы":
                return DocsType.SMESTARABOTI;
            case "Заказать справку о заработной плате":
                return DocsType.ODOHODAH;
            case "Заказать характеристику работника":
                return DocsType.HARAKTERISTIKA;
            default:
                return null;
        }
    }
}
