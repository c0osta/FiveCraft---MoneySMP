package ccode.fivecraft.moneysmp.core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Config
{
    public static void load() {
        // ustaw ścieżkę pliku credentials.json
        String credentialsFilePath = "plugins/FiveCraft/credentials.json";
        File credentialsFile = new File(credentialsFilePath);

// sprawdź, czy plik credentials.json już istnieje
        if (!credentialsFile.exists()) {
            try {
                // utwórz plik credentials.json
                credentialsFile.createNewFile();

                // zapisz zawartość do pliku credentials.json
                FileWriter fileWriter = new FileWriter(credentialsFile);
                fileWriter.write("{\n" +
                        "  \"installed\": {\n" +
                        "    \"client_id\": \"572169574640-eg7vq936m0bhrp74edsathmq1j4c5npl.apps.googleusercontent.com\",\n" +
                        "    \"project_id\": \"YOUR_PROJECT_ID\",\n" +
                        "    \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
                        "    \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
                        "    \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
                        "    \"client_secret\": \"572169574640-eg7vq936m0bhrp74edsathmq1j4c5npl.apps.googleusercontent.com\",\n" +
                        "    \"redirect_uris\": [\n" +
                        "      \"urn:ietf:wg:oauth:2.0:oob\",\n" +
                        "      \"http://localhost\"\n" +
                        "    ]\n" +
                        "  }\n" +
                        "}");

                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
