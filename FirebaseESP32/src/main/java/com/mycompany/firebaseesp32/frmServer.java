/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.firebaseesp32;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import java.io.IOException;
import com.google.firebase.database.ValueEventListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.gson.*;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.*;
import java.text.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author ASUS
 */
public class frmServer extends javax.swing.JFrame {

    RealtimeFirebase fbs = null;

    DatabaseReference ref = null;

    private static JSONObject jObject = null;

    private Date firebaseLastConnectedTime = null;

    private boolean isConnected = false;

    /**
     * Creates new form frmClient
     */
    public frmServer() throws IOException {
        initComponents();
        fillDataJTable("1911062456", "Vo Duy Khang", "March 22, 2023 at 12:45:09PM UTC+7", (byte) 1);
        fillDataJTable("1911062456", "Vo Duy Khang", "March 22, 2023 at 12:45:09PM UTC+7", (byte) 1);
        fillDataJTable("1911062456", "Vo Duy Khang", "March 22, 2023 at 12:45:09PM UTC+7", (byte) 1);
        fillDataJTable("1911062456", "Vo Duy Minh", "March 22, 2023 at 12:45:09PM UTC+7", (byte) 1);

    }

    public void run() {
        try {
            this.fbs = new RealtimeFirebase();

            this.ref = fbs.getDb()
                    .getReference("/diemdanh");
            this.ref.addValueEventListener(new ValueEventListener() {
                public void onDataChange(DataSnapshot dataSnapshot) {
                    handleDataChange(dataSnapshot);
                }

                public void onCancelled(DatabaseError error) {
                    System.out.print("Error: " + error.getMessage());
                }
            });

            this.firebaseLastConnectedTime = new Date();
            this.isConnected = true;
            this.jButton1.setText("Disconnect");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Kết nối Firebase thất bại!");
            e.printStackTrace();
        }

    }

    public void insertDataFireStore() throws FileNotFoundException, IOException, InterruptedException, ExecutionException {

        this.fbs = new RealtimeFirebase();

        DocumentReference docRef = fbs.getData().collection("users").document("alovelace1");
        // Add document data  with id "alovelace" using a hashmap
        Map<String, Object> data = new HashMap<>();
        data.put("first", "ATA");
        data.put("last", "Love2");
        data.put("born", 1815);
        //asynchronously write data
        ApiFuture<WriteResult> result = docRef.set(data);
        // ...
        // result.get() blocks on response
        System.out.println("Update time : " + result.get().getUpdateTime());
    }

    public long convertEpochTime(String time) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy - HH:mm:SS");
        Date date = df.parse(time);
        long epoch = date.getTime();
        return epoch;

    }

    public String convertEpochTime(int time) {
        Date date = new Date(time);
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
        String formatted = format.format(date);
        return formatted;

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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listData = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();
        cbbTime = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Connection");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        listData.setColumns(20);
        listData.setRows(5);
        jScrollPane1.setViewportView(listData);

        jButton2.setText("Thoát");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        cbbTime.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1 Phút", "5 Phút", "10 Phút", "15 Phút", "30 Phút" }));
        cbbTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTimeActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "MSSV", "Name", "Time", "Status"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        jButton3.setText("Save");

        jButton4.setText("Export to Excel File");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("ĐIỂM DANH SINH VIÊN");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(cbbTime, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4))
                .addContainerGap(14, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(359, 359, 359))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbTime))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addGap(13, 13, 13)
                        .addComponent(jButton4)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void handleDataChange(DataSnapshot dataSnapshot) {
        Object document = dataSnapshot.getValue();
        String dc = document.toString();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement je = JsonParser.parseString(dc);
        String prettyJsonString = gson.toJson(je).trim();

        String el = "{ data: " + prettyJsonString + "}";
        JSONObject ob = new JSONObject(el);

        JSONObject songs = ob.getJSONObject("data");
        Iterator x = songs.keys();

        String resultData = "";
        while (x.hasNext()) {
            String key = (String) x.next();

            if (key.equals("1")) {
                continue;
            }

            String k = String.valueOf(songs.get(key));
            k = k.substring(1, k.length() - 1);

            String[] arrTime = k.split(",");

            String lastScanTime = arrTime[arrTime.length - 1];

            long epoch = Long.parseLong(lastScanTime);
            Date date = new Date(epoch * 1000);
            SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy - HH:mm:SS");
            String dates = DATE_FORMAT.format(date);

            // Filter the time after the Firebase is initialized
            Calendar initCal = Calendar.getInstance();
            initCal.setTime(this.firebaseLastConnectedTime);

            Calendar scanCal = Calendar.getInstance();
            scanCal.setTime(date);

            if (initCal.compareTo(scanCal) >= 0) {
                continue;
            }

            if (this.isLated(date)) {
                resultData += (key + " : " + dates + " (Lated)" + "\n");
            } else {
                resultData += (key + " : " + dates + "\n");
            }
        }

        updateTextArea(resultData);
    }

    private int getComboBoxNumber() {
        String selected = this.cbbTime.getSelectedItem().toString();
        String mystr = selected.replaceAll("[^\\d]", "");
        return Integer.parseInt(mystr);
    }

    private boolean isLated(Date scanTimeRaw) {
        // Get calendar object from firebase's last connected time
        Calendar lastConn = Calendar.getInstance();
        lastConn.setTime(this.firebaseLastConnectedTime);
        lastConn.add(Calendar.MINUTE, this.getComboBoxNumber());

        // Get calendar object from scan time
        Calendar scanTime = Calendar.getInstance();
        scanTime.setTime(scanTimeRaw);

        return lastConn.compareTo(scanTime) < 0;
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (this.isConnected) {
            this.ref.onDisconnect();
            this.fbs.db.goOffline();

            this.fbs = null;
            this.jButton1.setText("Connect");
            this.isConnected = false;
            return;
        }
        run();
        try {
            insertDataFireStore();
        } catch (IOException ex) {
            Logger.getLogger(frmServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(frmServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(frmServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        System.exit(0);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cbbTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTimeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbTimeActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.showSaveDialog(this);
            File saveFile = jFileChooser.getSelectedFile();

            if (saveFile != null) {
                saveFile = new File(saveFile.toString() + ".xlsx");
                Workbook wb = new XSSFWorkbook();
                Sheet sheet = wb.createSheet("diemdanh");

                Row rowCol = sheet.createRow(0);
                for (int i = 0; i < jTable1.getColumnCount(); i++) {
                    Cell cell = rowCol.createCell(i);
                    cell.setCellValue(jTable1.getColumnName(i));
                }

                for (int j = 0; j < jTable1.getRowCount(); j++) {
                    Row row = sheet.createRow(j + 1);
                    for (int k = 0; k < jTable1.getColumnCount(); k++) {
                        Cell cell = row.createCell(k);
                        if (jTable1.getValueAt(j, k) != null) {
                            cell.setCellValue(jTable1.getValueAt(j, k).toString());
                        }
                    }
                }
                FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
                wb.write(out);
                wb.close();
                out.close();
                openFile(saveFile.toString());
            } else {
                JOptionPane.showMessageDialog(null, "Error al generar archivo");
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException io) {
            System.out.println(io);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    public void updateTextArea(String data) {
        listData.setText(data);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new frmServer().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(frmServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

//    Jtable to excel
    public void fillDataJTable(String mssv, String name, String time, byte status) {
        String[] columns = new String[]{
            "Mssv", "Name", "Time", "Status"
        };
        String sts = "";
        if (status != 1) {
            sts = "Tre";
        } else {
            sts = "Dung Gio";
        }
//        Object[][] data = new Object[][]{
//            {mssv, name, time, sts}, //            {"1911060595","Pham Trung Tin","March 22, 2023 at 12:45:09PM UTC+7","1"},
//        //            {"1911060565","Le Van Thang","March 22, 2023 at 12:47:09PM UTC+7","1"},
//        //            {"1911062443","Le Van Huy","March 22, 2023 at 12:50:09PM UTC+7","1"},
//        //            
//        };
        Object data[] = new Object[] {mssv, name, time, sts};
        String data2[] = {mssv, name, time, sts};
        
//        DefaultTableModel model = (new DefaultTableModel(data, columns))jTable1.getModel();
//        jt.setModel(model);
//        JTable jt = new JTable(model);
//        model.addRow(data);
        
    }

    public void openFile(String file) {
        try {
            File path = new File(file);
            Desktop.getDesktop().open(path);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbbTime;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea listData;
    // End of variables declaration//GEN-END:variables
}
