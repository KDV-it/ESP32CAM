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
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 *
 * @author ASUS
 */
public class RealtimeFirebase {

    FirebaseDatabase db;
    Firestore data; 

    public RealtimeFirebase() throws IOException {
        File file = new File(
                getClass().getClassLoader().getResource("esp32-cam.json").getFile()
        );

        FileInputStream fis = new FileInputStream(file);

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(fis))
                .setDatabaseUrl("https://esp32-cam-b1f5b-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .build();

        var apps = FirebaseApp.getApps();

        if (apps.isEmpty()) {
            FirebaseApp.initializeApp(options);

        }
        db = FirebaseDatabase.getInstance();
        data = FirestoreClient.getFirestore();
    }

    // realtime db
    public FirebaseDatabase getDb() {
        return db;
    }
    
    //firestore db
    public Firestore getData() {
        return data;
    }

}
