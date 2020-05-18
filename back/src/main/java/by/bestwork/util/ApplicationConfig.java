package by.bestwork.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
@PropertySource("classpath:application.properties")
public class ApplicationConfig {

    @Value("${google.oauth.callback.uri}")
    private String CALLBACK_URI;

    private InputStream driveSecretKeys = new ClassPathResource("googleDrive/client.json").getInputStream();

    private InputStream credentialsFolder = new ClassPathResource("googleDrive/credentials").getInputStream();

    private String temporaryFolder = new ClassPathResource("static/contracts/").getPath();

    public ApplicationConfig() throws IOException {
    }

    public String getCALLBACK_URI() {
        return CALLBACK_URI;
    }

    public void setCALLBACK_URI(String CALLBACK_URI_p) {
        CALLBACK_URI = CALLBACK_URI_p;
    }

    public InputStream getDriveSecretKeys() {
        return driveSecretKeys;
    }

    public void setDriveSecretKeys(InputStream driveSecretKeys) {
        this.driveSecretKeys = driveSecretKeys;
    }

    public InputStream getCredentialsFolder() {
        return credentialsFolder;
    }

    public void setCredentialsFolder(InputStream credentialsFolder) {
        this.credentialsFolder = credentialsFolder;
    }

    public String getTemporaryFolder() {
        return temporaryFolder;
    }

    public void setTemporaryFolder(String temporaryFolder) {
        this.temporaryFolder = temporaryFolder;
    }

}
