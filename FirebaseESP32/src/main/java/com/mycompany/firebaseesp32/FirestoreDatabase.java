/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.firebaseesp32;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author ASUS
 */
public class FirestoreDatabase {
    Firestore firestore;
   public FirestoreDatabase(){
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream is = classLoader.getResourceAsStream("esp32.json");

            GoogleCredentials googleCrendentials = GoogleCredentials.fromStream(is);
            FirebaseOptions op = new FirebaseOptions.Builder()
                    .setCredentials(googleCrendentials)
                    .build();
            FirebaseApp.initializeApp(op);
            firestore = FirestoreClient.getFirestore();

        } catch (IOException ex) {

        } catch (java.lang.IllegalStateException e) {
        }
   }
}
