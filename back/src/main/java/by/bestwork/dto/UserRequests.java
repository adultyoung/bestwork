package by.bestwork.dto;

import by.bestwork.domain.ContractRequest;
import by.bestwork.domain.PereRaspredRequest;
import by.bestwork.domain.PractiseRequest;
import by.bestwork.domain.RaspredRequest;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class UserRequests implements Serializable {

    Set<ContractRequest> contractRequests;
    Set<PereRaspredRequest> pereRaspredRequests;
    Set<RaspredRequest> raspredRequests;
    Set<PractiseRequest> practiseRequests;
}
