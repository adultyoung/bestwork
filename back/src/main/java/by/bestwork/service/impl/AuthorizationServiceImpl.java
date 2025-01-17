package by.bestwork.service.impl;

import by.bestwork.config.constant.ApplicationConstant;
import by.bestwork.service.AuthorizationService;
import by.bestwork.util.ApplicationConfig;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.util.store.FileDataStoreFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    private Logger logger = LoggerFactory.getLogger(AuthorizationServiceImpl.class);
    private GoogleAuthorizationCodeFlow flow;
    private FileDataStoreFactory dataStoreFactory;

    @Autowired
    private final ApplicationConfig config;

    public AuthorizationServiceImpl(ApplicationConfig config) {
        this.config = config;
    }

    @PostConstruct
    public void init() throws Exception {
        InputStreamReader reader = new InputStreamReader(config.getDriveSecretKeys());
        dataStoreFactory = new FileDataStoreFactory(new File(String.valueOf(config.getCredentialsFolder())));

        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(ApplicationConstant.JSON_FACTORY, reader);
        flow = new GoogleAuthorizationCodeFlow.Builder(ApplicationConstant.HTTP_TRANSPORT, ApplicationConstant.JSON_FACTORY, clientSecrets,
                ApplicationConstant.SCOPES).setDataStoreFactory(dataStoreFactory).build();
    }

    @Override
    public boolean isUserAuthenticated() throws Exception {
        Credential credential = getCredentials();
        if (credential != null) {
            boolean isTokenValid = credential.refreshToken();
            logger.debug("isTokenValid, " + isTokenValid);
            return isTokenValid;
        }
        return false;
    }

    @Override
    public Credential getCredentials() throws IOException {
        return flow.loadCredential(ApplicationConstant.USER_IDENTIFIER_KEY);
    }

    @Override
    public String authenticateUserViaGoogle() throws Exception {
        GoogleAuthorizationCodeRequestUrl url = flow.newAuthorizationUrl();
        String redirectUrl = url.setRedirectUri(config.getCALLBACK_URI()).setAccessType("offline").build();
        logger.debug("redirectUrl, " + redirectUrl);
        return redirectUrl;
    }

    @Override
    public void exchangeCodeForTokens(String code) throws Exception {
        // exchange the code against the access token and refresh token
        GoogleTokenResponse tokenResponse = flow.newTokenRequest(code).setRedirectUri(config.getCALLBACK_URI()).execute();
        flow.createAndStoreCredential(tokenResponse, ApplicationConstant.USER_IDENTIFIER_KEY);
    }

    @Override
    public void removeUserSession(HttpServletRequest request) throws Exception {
//		HttpSession session = request.getSession(false);
//        session = request.getSession(true);
//        if (session != null) {
//            session.invalidate();
//            logger.info("Logged Out...");
//        }
        // revoke token and clear the local storage
//		dataStoreFactory.getDataStore(config.getCredentialsFolder().getFilename()).clear();
    }

}
