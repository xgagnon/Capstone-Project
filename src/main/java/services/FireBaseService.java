package services;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FireBaseService {

    public static void FireBaseService() throws IOException {
        /*File file = new File(
                getClass().getClassLoader().getResource("key.json").getFile()
        );*/

        //FileInputStream fis = new FileInputStream(file);

            FileInputStream fis = new FileInputStream("C:\\Users\\vitor\\IdeaProjects\\Capstone-Project\\res\\key.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(fis))
                    .setDatabaseUrl("https://restaurants-3bb3e.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);

    }
}