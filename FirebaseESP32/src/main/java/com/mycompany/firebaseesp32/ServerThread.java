/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.firebaseesp32;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.database.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class ServerThread implements Runnable {

    private Scanner in = null;
    private PrintWriter out = null;
    private Socket socket;
    private String name;
    Firestore firestore;
    
    public ServerThread(){
        
    };
        
    public ServerThread(Socket socket, String name) throws IOException {
        this.socket = socket;
        this.name = name;
        this.in = new Scanner(this.socket.getInputStream());
        this.out = new PrintWriter(this.socket.getOutputStream(), true);
        new Thread(this).start();
    }
    
    private void insertData(String colection, String documento, Map<String, Object> data) {
        try {
            if (firestore != null) {
                DocumentReference docRef = firestore.collection(colection).document(documento);
                ApiFuture<WriteResult> result = docRef.set(data);
                System.out.println("" + result.get().getUpdateTime());
            }

        } catch (ExecutionException ex) {
            Logger.getLogger(frmServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(frmServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void run() {
        RealtimeFirebase fbs = null;
        try {
            fbs = new RealtimeFirebase();
        } catch (IOException e) {
            e.printStackTrace();
        }

//      new FirestoreDatabase();

        DatabaseReference ref = fbs.getDb()
                .getReference("/");
        ref.addValueEventListener(new ValueEventListener() {

            public void onDataChange(DataSnapshot dataSnapshot) {
                Object document = dataSnapshot.getValue();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                String dc = document.toString();
                String save = "";
                
                if (checkId(dc)) {
                    System.out.println(dtf.format(now));
                    System.out.println(dc.substring(4,dc.length() - 1));
                    save += dc.substring(4,dc.length() - 1)+dtf.format(now);
                    
                

                } else {
                    System.out.println("Something went wrong!");
                }

            }

            public void onCancelled(DatabaseError error) {
                System.out.print("Error: " + error.getMessage());
            }
        });

    }

    public boolean checkId(String str) {
        if (str != null) {
            int length = str.length();
            String id = str.substring(4, length - 1);
            if (id.length() == 10) {
                return true;
            }
            return false;
        }
        return false;
    }

}
