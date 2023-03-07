/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.vinco.ebdimport;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import lt.vinco.ebdimport.entity.Ptappli;
import lt.vinco.util.HibernateUtil;
import org.apache.poi.ss.usermodel.Sheet;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Vincas Batulevičius
 */
public class Langas extends javax.swing.JFrame {

    /**
     * Creates new form Langas
     */
    public Langas() {
        
     URL imgUrl = null;
        ImageIcon imgIcon = null;

        imgUrl = Langas.class.getResource("res/ico.gif");
        imgIcon = new ImageIcon(imgUrl);
        Image img = imgIcon.getImage();
        this.setIconImage(img);   
        
       
        
        initComponents();
        
    
     chooser.setCurrentDirectory(new File(System.getProperty("user.home")));   
          jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lt/vinco/ebdimport/res/star.jpg"))); // NOI18N
setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
   
    private void initComponents() {

        btnDir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        chooser = new javax.swing.JFileChooser();
        jLabel2 = new javax.swing.JLabel();
        txtDir = new javax.swing.JTextField();
        btnTest = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnRun = new javax.swing.JButton();
        btnTESTAS = new javax.swing.JButton();
        btnTrinti = new javax.swing.JButton();
        txtTrinti = new javax.swing.JTextField();
        btnGetidappli = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        btnConnection = new javax.swing.JButton();
        lblCon = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("IPCR įkėlimas");

        btnDir.setText("Nurodyti patentų failą");
        btnDir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDirActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 255));
        jLabel1.setText("IPCR (2015-03-18)");

        jLabel2.setText("               ");

        txtDir.setText("IPCR");

        btnTest.setText("Įrašyti IPCR");
        btnTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTestActionPerformed(evt);
            }
        });

        btnRun.setText("Peržiūrėti patentų sąrašą XML faile");
        btnRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRunActionPerformed(evt);
            }
        });

        btnTESTAS.setText("Test");
        btnTESTAS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTESTASActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(339, 339, 339)
                .addComponent(btnRun, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnTESTAS, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRun)
                .addGap(47, 47, 47)
                .addComponent(btnTESTAS)
                .addGap(23, 23, 23))
        );

        btnTrinti.setForeground(new java.awt.Color(255, 0, 51));
        btnTrinti.setText("x");
        btnTrinti.setToolTipText("Ar tikrai ištrinti?");
        btnTrinti.setEnabled(false);
        btnTrinti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrintiActionPerformed(evt);
            }
        });

        txtTrinti.setText("idappli");

        btnGetidappli.setText("idappli by doknr");
        btnGetidappli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetidappliActionPerformed(evt);
            }
        });

        jLabel3.setText("Programavo Vincas Batulevičius");

        btnConnection.setText("Connection");
        btnConnection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectionActionPerformed(evt);
            }
        });

        lblCon.setText("Connection...");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lt/vinco/ebdimport/res/vincasoft5.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblCon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(chooser, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(95, 95, 95))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(btnDir, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnTest, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtDir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(btnTrinti)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtTrinti, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnGetidappli)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnConnection)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel3))
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(9, 9, 9))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(chooser, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDir)
                    .addComponent(txtDir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(lblCon)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTest)
                    .addComponent(btnTrinti)
                    .addComponent(txtTrinti, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGetidappli)
                    .addComponent(jLabel3)
                    .addComponent(btnConnection))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDirActionPerformed
KeistiIPCR.init();
try {
 fh=new FileHandler("./loggerIPCR_pagr.log", false);
 } catch (SecurityException | IOException e) {
 e.printStackTrace();
 }

 fh.setFormatter(new SimpleFormatter());
 logger.addHandler(fh);
 fh.setLevel(Level.ALL);
logger.setLevel(Level.ALL);

  //    System.out.println(KeistiIPCR.getFailasSuIPCR(txtDir.getText()));
  //    if(true ) return;    
        chooser.setDialogTitle("Patentų sąrašas");
    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    chooser.setApproveButtonText("Pasirinkti");
    chooser.setAcceptAllFileFilterUsed(false);
     chooser.setCurrentDirectory(new File("C:\\Users\\vbatulevicius\\Documents\\NetBeansProjects\\IPCR"));
    if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
        
  File  failas = chooser.getSelectedFile();
  
    if(!"txt".equalsIgnoreCase(EBDimport.getExtension(failas))) {
        JOptionPane.showMessageDialog(this, "Ne TXT failas!",  "Vincas", JOptionPane.OK_OPTION);
        return;
        }
     IsFailo(failas);
     }// if approve 
    }//GEN-LAST:event_btnDirActionPerformed

    private void btnRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRunActionPerformed
        try {
            String failas_su_patento_ipcr = null;
   chooser.setDialogTitle("Patentų sąrašas");
    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    chooser.setMultiSelectionEnabled(true);
    chooser.setApproveButtonText("Pasirinkti");
    chooser.setAcceptAllFileFilterUsed(false);
  //   chooser.setCurrentDirectory(new File("C:\\Users\\vbatulevicius\\Documents\\NetBeansProjects\\IPCR"));
     chooser.setCurrentDirectory(new File("D:\\DBextract"));
    if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
        
  //File  failas = chooser.getSelectedFile();
 // chooser.setCurrentDirectory(failas.getParentFile());
  
  File[] failai = chooser.getSelectedFiles();
  for (File failas : failai){
  
  failas_su_patento_ipcr = failas.getAbsolutePath();
        System.out.println(failas_su_patento_ipcr);
        if(!"xml".equalsIgnoreCase(EBDimport.getExtension(failas))) {
        JOptionPane.showMessageDialog(this, "Ne XML failas!",  "Vincas", JOptionPane.OK_OPTION);
        return;
        }
      
            
            //  
       //   failas_su_patento_ipcr =  "DOCDB-201508-006-EP-0163.xml";
            File filas = new File(failas_su_patento_ipcr);
            DocumentBuilderFactory domFactory_ = DocumentBuilderFactory.newInstance();
            domFactory_.setNamespaceAware(true);
            DocumentBuilder builder_ = null;
            builder_ = domFactory_.newDocumentBuilder();
            Document docas = builder_.parse(filas.getAbsolutePath());
            XPath xpath_ = XPathFactory.newInstance().newXPath();
            
            xpath_.setNamespaceContext(new NamespaceContext() {
                public String getNamespaceURI(String prefix) {
                    return prefix.equals("exch") ? "http://www.epo.org/exchange" : null;
                }
                
                public Iterator<?> getPrefixes(String val) {
                    return null;
                }
                
                public String getPrefix(String uri) {
                    return null;
                }
            });
            
            NodeList dnrs=null;
            String xpatstring_ = "//exch:exchange-documents/exch:exchange-document[@kind='B1']/@doc-number";
            System.out.println(xpatstring_);
            dnrs  = (NodeList)xpath_.evaluate(xpatstring_, docas, XPathConstants.NODESET);
         int i= 0; 
   String max = "-99999999"; 
   String min = "99999999"; 
            if (dnrs != null) {
 for( i=0; i<dnrs.getLength(); i++) {System.out.println(dnrs.item(i).getTextContent());
if (Integer.parseInt(dnrs.item(i).getTextContent())<= Integer.parseInt(min)) {   min = dnrs.item(i).getTextContent();  
}

if (Integer.parseInt(dnrs.item(i).getTextContent())>= Integer.parseInt(max)) {   max = dnrs.item(i).getTextContent(); 
}
}           }//if
            
  System.out.println( "min: " + min + " max: "+max);        
            System.out.println( "+++++++++++ "+ dnrs.getLength() );
            
  //Rename:
            
   failas.renameTo(new File(failas.getParent()+"\\"+min+"_"+max+"_.xml"));
            
            
            
          //    JOptionPane.showMessageDialog(this, "Yra "+i+" patentų!",  "Vincas", JOptionPane.OK_OPTION);
  }//for File failas   
       JOptionPane.showMessageDialog(this, "Darbas baigtas!",  "Vincas", JOptionPane.OK_OPTION);       
       }// if approve          
            
        } catch (XPathExpressionException ex) {
            Logger.getLogger(Langas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Langas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(Langas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Langas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnRunActionPerformed

    private void btnTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTestActionPerformed
        
            try {
   String failas_su_patento_ipcr = null;             
    if(patentufailai.isEmpty()){
  JOptionPane.showMessageDialog(this, "Nenurodyas patentų sąrašo failas!",  "Vincas", JOptionPane.OK_OPTION);
      return;  
    }    
    
    failas_su_patento_ipcr =  "0997040_0999999_.xml";
 //  failas_su_patento_ipcr = "zenonui_LT.xml"; 
    
    
   DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
                domFactory.setNamespaceAware(true); 
    DocumentBuilder builder = null;        
                builder = domFactory.newDocumentBuilder(); 
 
  //Greitai:              
  //File file = new File(failas_su_patento_ipcr);
 // Document doc = builder.parse(file.getAbsolutePath());             
                
  XPath xpath = XPathFactory.newInstance().newXPath();
                  xpath.setNamespaceContext(new NamespaceContext() {
                    public String getNamespaceURI(String prefix) {
                        return prefix.equals("exch") ? "http://www.epo.org/exchange" : null;
                    }
                    
                    public Iterator<?> getPrefixes(String val) {
                        return null;
                    }
                    
                    public String getPrefix(String uri) {
                        return null;
                    }
                });  
                
                
                
    int k = 0;
         for (String doknumeris : patentufailai)    {
      k++;  
      setTitle("Tvarkomas " +k+ " patentas "+  doknumeris + " iš " + patentufailai.size());  
     
         
    failas_su_patento_ipcr = KeistiIPCR.getFailasSuIPCR(doknumeris);
 System.out.println("Patento "+doknumeris + " xml failas "+ failas_su_patento_ipcr); 
 logger.info("Patento "+doknumeris + " xml failas "+ failas_su_patento_ipcr);
 
 if(failas_su_patento_ipcr.length()<3) {
  System.out.println(doknumeris + " xml failas nerastas");
  logger.severe(doknumeris + " xml failas nerastas");
  continue;
    }
                
  //Lėtai:       
    File file = new File(failas_su_patento_ipcr);
    Document doc = builder.parse(file.getAbsolutePath());

                
String pagr_klas = null;

Node klasifikacijos;
NodeList kl=null;
String xpatstring = "//exch:exchange-documents/exch:exchange-document[@kind='B1' and @doc-number='"+doknumeris+"']/exch:bibliographic-data/exch:classifications-ipcr";
System.out.println(xpatstring);
klasifikacijos  = (Node)xpath.evaluate(xpatstring, doc, XPathConstants.NODE);
if (klasifikacijos != null) {
   if( KeistiIPCR.UpdateB510EP(doknumeris, klasifikacijos)==-1) {
        logger.severe("NEPAVYKO!!!!    ištrinti? "+doknumeris);
       continue;
   }
}//not null
  else 
  {     System.out.println("doknumerio nera "+doknumeris); 
  logger.warning("doknumerio nera "+doknumeris);
  }    

                        
  }  //for patentufailai    
         
          JOptionPane.showMessageDialog(this, "Darbas baigtas!" ,  "Vincas", JOptionPane.OK_OPTION);
          
            } catch (SAXException ex) {
                Logger.getLogger(Langas.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
            Logger.getLogger(Langas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XPathExpressionException ex) {
            Logger.getLogger(Langas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Langas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnTestActionPerformed

    private void btnTrintiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrintiActionPerformed
      
    }//GEN-LAST:event_btnTrintiActionPerformed

    private void btnGetidappliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetidappliActionPerformed
      String doknr = txtTrinti.getText().trim();
      if ((doknr==null) || (doknr.length()<6)|| doknr.contains("EP")) {
      JOptionPane.showMessageDialog(this, "Neteisingas EP nr. Turi būti be EP!",  "Vincas", JOptionPane.OK_OPTION);
      return;
      }
        PatentasDB pdb = EBDimport.YraDB(doknr);
       if(pdb.YraDB) {txtTrinti.setText(pdb.idappli);
       }
       else   txtTrinti.setText("idappli");
        
    }//GEN-LAST:event_btnGetidappliActionPerformed

    private void btnConnectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnectionActionPerformed
           
     Session session = null; 

        try {
             session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
      Connection connection = session.connection();
DatabaseMetaData metaData = connection.getMetaData();
System.out.println(metaData.getURL()); 

JOptionPane.showMessageDialog(this, metaData.getURL(),  "Vincas", JOptionPane.INFORMATION_MESSAGE);

lblCon.setText(metaData.getURL());
        
  session.getTransaction().commit();
       
    } catch (HibernateException he) {
        he.printStackTrace();
         session.getTransaction().rollback();
     }
  catch (SQLException se) {
        se.printStackTrace();
         session.getTransaction().rollback();
     }      
  finally{
    session.flush();
    session.close();
 }     
           
       
        
    }//GEN-LAST:event_btnConnectionActionPerformed

    private void btnTESTASActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTESTASActionPerformed
        Session session = null;
        try {
             session = HibernateUtil.getSessionFactory().openSession();
              session.beginTransaction();
   //String hq = "select p.extidpatent  from Ptappli p where p.vipcmclass = 7 and p.lgstappli = :idappli";
    //         Query qu = session.createQuery(hq);
            // qu.setParameter("idappli", idappli);            
     Criteria criteria = session.createCriteria(Ptappli.class);  
    criteria.add(Restrictions.eq("vipcmclass", 7));
     criteria.add(Restrictions.eq("lgstappli", 20));
   
 List pt =(List <Ptappli>) criteria.list();
 if((pt!=null)&&(pt.size()>0)){
     for (int i = 0; i<pt.size(); i++)
         System.out.println( ((Ptappli)pt.get(i)).getExtidpatent());
 
 }
   JOptionPane.showMessageDialog(this, "Baigta!",  "Vincas", JOptionPane.OK_OPTION);        
     System.out.println( pt.size());          
        
        } catch (HibernateException he) {
            he.printStackTrace();
            session.getTransaction().rollback();
       } finally {
    session.flush();
                    session.close();
        }

        System.out.println("-------Pabaiga---------- ");
        
    }//GEN-LAST:event_btnTESTASActionPerformed

    
    
 private String readFile(String pathname) throws IOException {

    File file = new File(pathname);
    StringBuilder fileContents = new StringBuilder((int)file.length());
    Scanner scanner = new Scanner(file);
    String lineSeparator = System.getProperty("line.separator");

    try {
        while(scanner.hasNextLine()) {        
            fileContents.append(scanner.nextLine() + lineSeparator);
        }
        return fileContents.toString();
    } finally {
        scanner.close();
    }
}    
    
     
 private void IsFailo(File failas){
      txtDir.setText(failas.toString());
       patentufailai.clear();
        BufferedReader input = null;
        List<String> isfailo = new ArrayList<>();
        try {
            input = new BufferedReader(new FileReader(failas));
            String line = null;

            while ((line = input.readLine()) != null) {

               
                    String docnum = line.trim();
                    patentufailai.add(docnum);
                    System.out.println(docnum);
               
                // System.out.println(line);    
            } //while
        } catch (FileNotFoundException ex) {
            //        Logger.getLogger(Forma.class.getName()).log(Level.SEVERE, null, ex);
        } catch (java.io.IOException ex) {
            //        Logger.getLogger(Forma.class.getName()).log(Level.SEVERE, null, ex);
        }
     System.out.println(patentufailai.toString());

        
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
            java.util.logging.Logger.getLogger(Langas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Langas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Langas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Langas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Langas().setVisible(true);
            }
        });
    }

    
 private final static Logger logger = Logger.getLogger(Langas.class.getName());
 private static FileHandler fh = null;   
 List<String> patentufailai = new ArrayList<>();   
    private File Xmlsource = null;
private File Xmlresultdir = null;
    private File dir = null;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConnection;
    private javax.swing.JButton btnDir;
    private javax.swing.JButton btnGetidappli;
    private javax.swing.JButton btnRun;
    private javax.swing.JButton btnTESTAS;
    private javax.swing.JButton btnTest;
    private javax.swing.JButton btnTrinti;
    private javax.swing.JFileChooser chooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblCon;
    private javax.swing.JTextField txtDir;
    private javax.swing.JTextField txtTrinti;
    // End of variables declaration//GEN-END:variables


   
    public static boolean RASYTI_DB = false; 
     
public static  Sheet sheet = null;
}
