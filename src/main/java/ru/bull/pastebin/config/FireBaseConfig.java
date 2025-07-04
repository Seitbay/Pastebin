package ru.bull.pastebin.config;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FireBaseConfig{


    @PostConstruct
    public void init() throws IOException {
        FileInputStream serviceAccount =
                new FileInputStream("src/main/resources/pastebin-acbc7-firebase-adminsdk-fbsvc-1439640ed7.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();


        FirebaseApp.initializeApp(options);
    }
}
