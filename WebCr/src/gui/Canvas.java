/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Set;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import webcr.HashSerializer;

/**
 *
 * @author chabineza
 */
public class Canvas extends javax.swing.JFrame {

    /**
     * Creates new form Canvas
     *
     */
    public HashMap cashe = new HashMap();
    private String keyWord;

    public Canvas() {
        initComponents();
        this.jPanel1.setVisible(true);
        this.urlTextField.setText("http://textfiles.com");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        urlTextField = new javax.swing.JTextField();
        searchKeyWordLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        progressBar = new javax.swing.JProgressBar();
        jLabel2 = new javax.swing.JLabel();
        indexSearchButton = new javax.swing.JRadioButton();
        documentSearchButton = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        keyWordField = new javax.swing.JTextField();
        urlLabel = new javax.swing.JLabel();
        searchButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        outPutTextPane = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        urlTextField.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        urlTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        searchKeyWordLabel.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        searchKeyWordLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        searchKeyWordLabel.setText("Search String");
        searchKeyWordLabel.setFocusCycleRoot(true);
        searchKeyWordLabel.setFocusTraversalPolicyProvider(true);

        progressBar.setToolTipText("");
        progressBar.setString("0");
        progressBar.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                progressBarStateChanged(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(progressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        indexSearchButton.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        indexSearchButton.setText("Index Search");

        documentSearchButton.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        documentSearchButton.setSelected(true);
        documentSearchButton.setText("Document Search");
        documentSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                documentSearchButtonActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Search Type");

        keyWordField.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N

        urlLabel.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        urlLabel.setText("Web page URL");

        searchButton.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        outPutTextPane.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jScrollPane1.setViewportView(outPutTextPane);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(searchKeyWordLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                    .addComponent(urlLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(urlTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(indexSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(27, 27, 27)
                                        .addComponent(documentSearchButton)
                                        .addGap(23, 23, 23))
                                    .addComponent(keyWordField))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(indexSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(documentSearchButton)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(urlTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(urlLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(searchKeyWordLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(keyWordField, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(searchButton)
                .addGap(18, 37, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        searchKeyWordLabel.getAccessibleContext().setAccessibleDescription("searchStr");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:

    }//GEN-LAST:event_jTextField1ActionPerformed

    private void documentSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_documentSearchButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_documentSearchButtonActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        progressBar.setValue(0);
        keyWord = keyWordField.getText();

        outPutTextPane.setVisible(true);
        new Thread() {
            public void run() {
                for (int i = 0; i <= 100; i++) {
                    try {
                        sleep(6);
                        progressBar.setValue(i);

                        if (progressBar.getValue() <= 99) {
                            jLabel2.setText("Search in progress, please wait ...");
                        } else {
                            jLabel2.setText("Search complete!");
                            HashSerializer hsr = new HashSerializer();
                            HashMap<String, Set<URL>> hm = (HashMap<String, Set<URL>>) hsr.deSerialize("cash.ser");
                            for (URL url : hm.get(keyWord)) {
                                appendURLtoPane(url);

                            }
                            // outPutTextPane.setText(out);
                        }

                    } catch (InterruptedException ex) {
                        System.out.println("Something went wrong with the progress bar \n" + ex);
                    }
                }

            }

            private void appendURLtoPane(URL url) {
                try {
                    Document doc = outPutTextPane.getDocument();
                    doc.insertString(doc.getLength(), url.toString() + '\n', null);
                } catch (BadLocationException exc) {
                    exc.printStackTrace();
                }
                
//                JLabel l = new JLabel(url.toString() + "</br>");
//                l.setAlignmentX(0.0f);
//                l.setFont(outPutTextPane.getFont());
//                l.setCursor(new Cursor(Cursor.HAND_CURSOR));
//                l.addMouseListener(new MouseAdapter() {
//                    public void mouseClicked(MouseEvent me) {
//                        // your code
//                    }
//                });
//                outPutTextPane.insertComponent(l);
            }

        }.start();
    }//GEN-LAST:event_searchButtonActionPerformed

    private void progressBarStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_progressBarStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_progressBarStateChanged
    private String searchFile(String file, String keyWord) {
        Set<String> contentSet;
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            String[] lineContent;
            try {
                while ((line = reader.readLine()) != null) {
                    lineContent = line.split("###");
                    if (lineContent[0].equals(keyWord)) {

                        return lineContent[1];
                    }
                }
                reader.close();
            } catch (IOException ex) {
                System.out.println("!! file not found ! " + ex);
                ex.printStackTrace();
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();

        }
        return null;
    }

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton documentSearchButton;
    private javax.swing.JRadioButton indexSearchButton;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField keyWordField;
    private javax.swing.JTextPane outPutTextPane;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JButton searchButton;
    private javax.swing.JLabel searchKeyWordLabel;
    private javax.swing.JLabel urlLabel;
    private javax.swing.JTextField urlTextField;
    // End of variables declaration//GEN-END:variables

    public void setCache(HashMap c) {
        cashe = c;
    }

}
