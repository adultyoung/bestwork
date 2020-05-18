package by.bestwork.service;

import by.bestwork.dao.DocsDao;
import by.bestwork.dao.UserDao;
import by.bestwork.domain.Docs;
import by.bestwork.domain.User;
import by.bestwork.dto.UserRequests;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final ContractRequestService contractRequestService;
    private final PereRaspredRequestService pereRaspredRequestService;
    private final RaspredRequestService raspredRequestService;
    private final PractiseRequestService practiseRequestService;
    private final UserDao userDao;
    private final DocsDao docsDao;

    public UserService(ContractRequestService contractRequestService, PereRaspredRequestService pereRaspredRequestService, RaspredRequestService raspredRequestService, PractiseRequestService practiseRequestService, UserDao userDao, DocsDao docsDao) {
        this.contractRequestService = contractRequestService;
        this.pereRaspredRequestService = pereRaspredRequestService;
        this.raspredRequestService = raspredRequestService;
        this.practiseRequestService = practiseRequestService;
        this.userDao = userDao;
        this.docsDao = docsDao;
    }


    public User save(User user) {

        return userDao.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public User loadUserById(Long id) {
        Optional<User> user = userDao.findById(id);

        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User not found");
        }

        return user.get();
    }

    public User updateUser(User user, User userFromDb) {
        if (
                !user.getEmail().isEmpty() &&
                        !user.getFirstName().isEmpty() &&
                        !user.getLastName().isEmpty() &&
                        !user.getOtchestvo().isEmpty() &&
                        !user.getTel().isEmpty()
        ) {
            userFromDb.setFirstName(user.getFirstName());
            userFromDb.setLastName(user.getLastName());
            userFromDb.setEmail(user.getEmail());
            userFromDb.setOtchestvo(user.getOtchestvo());
            userFromDb.setTel(user.getTel());
            return userDao.save(userFromDb);
        }
        return null;
    }

    public List<Docs> getDocsList(Long userId) {
        return docsDao.findAllByUserId(userId);
    }

    public UserRequests getUserRequests(Long id) {
        UserRequests requests = new UserRequests();
        requests.setContractRequests(contractRequestService.getContractRequestsForUser(id));
        requests.setPereRaspredRequests(pereRaspredRequestService.getPereRaspredRequestsForUser(id));
        requests.setRaspredRequests(raspredRequestService.getraspredRequestsForUser(id));
        requests.setPractiseRequests(practiseRequestService.getpractiseRequestsForUser(id));
        return requests;
    }

    public UserRequests getAllRequests() {
        UserRequests requests = new UserRequests();
        requests.setContractRequests(contractRequestService.findAll());
        requests.setPereRaspredRequests(pereRaspredRequestService.findAll());
        requests.setRaspredRequests(raspredRequestService.findAll());
        requests.setPractiseRequests(practiseRequestService.findAll());
        return requests;
    }

    public User getUser(Long id) {
        return userDao.getOne(id.toString());
    }
}
