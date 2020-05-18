package by.bestwork.service;

import by.bestwork.domain.ContractRequest;
import by.bestwork.dto.HelpBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface DriveService {

    public void uploadFile(MultipartFile multipartFile) throws Exception;

    ContractRequest saveContractRequest(ContractRequest contractRequest) throws IOException, GeneralSecurityException;

    String saveHelp(HelpBody help) throws IOException;
}
