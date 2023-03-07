/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.vinco.ebdimport;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.xml.xpath.XPathExpressionException;
import lt.vinco.ebdimport.entity.Classin;
import lt.vinco.ebdimport.entity.ClassinId;
import lt.vinco.ebdimport.entity.History;
import lt.vinco.ebdimport.entity.HistoryId;
import lt.vinco.ebdimport.entity.Ptappli;
import lt.vinco.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.w3c.dom.Attr;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Vincas Batulevičius
 */
public class KeistiIPCR {
    //Logininmas:
 private final static Logger logger = Logger.getLogger(KeistiIPCR.class.getName());
 private static FileHandler fh = null;
 
 public static void init(){
 try {
 fh=new FileHandler("./loggerIPCR.log", false);
 } catch (SecurityException | IOException e) {
 e.printStackTrace();
 }

 fh.setFormatter(new SimpleFormatter());
 logger.addHandler(fh);
 fh.setLevel(Level.ALL);
logger.setLevel(Level.ALL);
 }
public static int UpdateB510EP(String extidpatent, Node nodeIPCR) throws XPathExpressionException {
        System.out.println("IPCR įrašymo pradžia. extidpatent: " + extidpatent);


             PatentasDB pdb = EBDimport.YraDB(extidpatent);
         if (!pdb.YraDB) {
         System.out.println("Duomenų bazėje nėra patento  " + extidpatent);
         return 0;
         }
         String idappli = pdb.idappli;
         System.out.println("+++++++ Tinkamas patentas +++++++++ " + idappli + " " + pdb.lgstappli);
               Session session = null;
        String pagr_klas = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
              session.beginTransaction();

         if(Trinti.DeleteClassif(idappli)==-1)
         {
         return -1;         
         };
               
               
            //Klasifikacijos:
            List<Classif> classifs = new ArrayList<>();
            String sequence = "0";
            NodeList nodes = nodeIPCR.getChildNodes();

          
            if (nodes != null) {
                for (int i = 0; i < nodes.getLength(); i++) {
                    Classif classif = new Classif();
                    String ipcr = nodes.item(i).getFirstChild().getTextContent();
     //     System.out.println("ipcr: " + ipcr);
                    String klasif = ipcr.substring(0, 19).trim();
                    String klasif_metai = ipcr.substring(19, 27);
                    String first = ipcr.substring(28, 29);
                    String sdtaction = ipcr.substring(30, 38);
String BRVD = ipcr.substring(38,39);
String HMG = ipcr.substring(39,40);
String orig = ipcr.substring(40,42);
                    String inventive = ipcr.substring(29, 30);
                    if (inventive.equalsIgnoreCase("N")) {
                        classif.setTyclassif(Short.valueOf("2"));
                    }
                    if (inventive.equalsIgnoreCase("I")) {
                        classif.setTyclassif(Short.valueOf("1"));
                    }

                    System.out.print("Klasif: " + klasif + " "  + klasif_metai + " " + first);
                    System.out.print(" dtaction: " + sdtaction);
                    System.out.print(" inventive: " + inventive);
                    System.out.print(" BRVD: " + BRVD);
                    System.out.print(" HMG: " + HMG);
                    System.out.print(" orig: " + orig);
                    Boolean bfirst = first.equalsIgnoreCase("F");
                    classif.setIpcclass(klasif);
                    classif.setIpcversionindicator(klasif_metai);
   switch (HMG) {
            case "H":  classif.setHmg(Short.valueOf("1"));
                     break;
            case "M": classif.setHmg(Short.valueOf("2"));
                     break;
            case "G": classif.setHmg(Short.valueOf("3"));
                     break;
            default: break;
        }                 
       switch (BRVD) {
            case "B":  classif.setBrdv(Short.valueOf("1"));
                     break;
            case "R": classif.setBrdv(Short.valueOf("2"));
                     break;
            case "V": classif.setBrdv(Short.valueOf("3"));
                     break;
            case "D": classif.setBrdv(Short.valueOf("4"));
                     break;    
            default: break;
        }              
   
 classif.setOrig(orig);
                    NamedNodeMap atrs = nodes.item(i).getAttributes();
                    int len = atrs.getLength();
                    for (int j = 0; j < len; j++) {
                        Attr attr = (Attr) atrs.item(j);
                        String nodename = attr.getNodeName();
                        String nodevalue = attr.getNodeValue();
                        System.out.println(" " + nodename + "=\"" + nodevalue + "\"");
                        if (nodename.equalsIgnoreCase("sequence")) {
                            sequence = nodevalue;
                        }
                        classif.setSequence(sequence);
                    }//for 
                    classif.setSdtaction(sdtaction);
                    if (bfirst) {
                        pagr_klas = klasif;
                        classif.setSymbpos(Short.valueOf("1"));
                    } else {
                        classif.setSymbpos(Short.valueOf("2"));
                    }
                    
             Boolean jauyraklasif = false;
            for(Classif cl : classifs) 
            {
            if(cl.getIpcclass().equalsIgnoreCase(klasif)) jauyraklasif = true;
            }
             
       if(!jauyraklasif)  {    classifs.add(classif);    }
              
              
              
                } //for
                //Tikriname ar yra F (first):    
                Boolean yraFirst = false;
                for (Classif cl : classifs) {
                    if (cl.getSymbpos() == 1) {
                        yraFirst = true;
                    }
                }
                if (!yraFirst) {
                    for (Classif cl : classifs) {
                        if (cl.getSequenceShort() == 1) {
                            cl.setSymbpos(Short.valueOf("1"));
                            pagr_klas = cl.getIpcclass();
                        }//if
                    }
                }//if nera first
  

            }//if not null

            System.out.println("pagr_klas: " + pagr_klas);
   logger.log( Level.FINE, "Pagrindine klase {0} ", pagr_klas );
          Ptappli ptappli = (Ptappli) session.get(Ptappli.class, idappli);
             ptappli.setIpcmclass(pagr_klas);
             ptappli.setVipcmclass(8); //????? patikrinti 
             session.update(ptappli);


             //Įrašome į klasifikacijoos lentelę kitas klases:         
             for (int i = 0; i < classifs.size(); i++) {
             ClassinId clid = new ClassinId();
             clid.setIdappli(idappli);
             clid.setIpcclass(classifs.get(i).getIpcclass());

             Classin classin = new Classin();
             classin.setId(clid);
             classin.setIpcversion(Short.valueOf("8"));
             classin.setOdclass(classifs.get(i).getSequenceShort());
             classin.setOdlink(Short.valueOf("0"));      //----------------  ??? pasitikslinti
             classin.setTyipcclass(Short.valueOf("0"));// pagal Zenoną tn 0
         java.sql.Date klas_versija = 
                 EBDimport.getSqldate(classifs.get(i).getIpcversionindicator());    
 
         classin.setDtversion(klas_versija);
             classin.setSymbpos(classifs.get(i).getSymbpos());
             classin.setTyclassif(classifs.get(i).getTyclassif());
             classin.setDtaction(EBDimport.getSqldate(classifs.get(i).getSdtaction()));  
             classin.setStclassif(classifs.get(i).getBrdv());
             classin.setScclassif(classifs.get(i).getHmg());
             classin.setOrigin(classifs.get(i).getOrig()); //?


             session.save(classin);
         
             }    //for classisfs
            
             // history:   idoper = 105
           

             //Irasome i History lentele:          
             String hq = "select max(h.id.odhisto)  from History h where h.id.idappli = :idappli ";
             Query qu = session.createQuery(hq);
             qu.setParameter("idappli", idappli);
             Short odh = (Short) qu.uniqueResult();
             Short newodh = (short) (odh.intValue() + 1);
             System.out.println("odh: " + odh);
             System.out.println("newodh: " + newodh);

             History history = new History();
             HistoryId hid = new HistoryId();
             hid.setIdappli(idappli);
             hid.setOdhisto(newodh);
             history.setId(hid);
             history.setIdoper("105");  


             Date dtop = new Date();
             java.sql.Date dtoper = new java.sql.Date(dtop.getTime());
            
             history.setDtoper(dtoper); 
             history.setSthisto(new Short("4"));      
             history.setIduseroper(new Short("12"));
            

             history.setStpay(new Short("1"));  
            
             history.setLvlpubli(new Short("0")); 
             history.setDtaction(dtoper);
             session.save(history);

                  
            
                 session.getTransaction().commit();
                      logger.log(Level.INFO, "Įrašytas IPCR\t" + extidpatent);
        } catch (HibernateException he) {
            he.printStackTrace();
            session.getTransaction().rollback();
            logger.log(Level.SEVERE, "Klaida įrašant IPCR\tEP" + extidpatent);
          
        } finally {
    session.flush();
                    session.close();
        }

        System.out.println("IPCR įrašymo pabaiga__________________________________ " + extidpatent);
        return 0;
    } 
    
  public static String getFailasSuIPCR(String doknumeris){
      
  File xmlaplankas = new File("D:\\DBextract\\all");
  File[] xmls = xmlaplankas.listFiles();
  int doknr = Integer.parseInt(doknumeris);
  
  for(File xml : xmls){
  if(EBDimport.getExtension(xml).equalsIgnoreCase("xml")){
    String failovardas = xml.getName();
    String[] minmax = failovardas.split("_");
    int min = Integer.parseInt(minmax[0]);
     int max = Integer.parseInt(minmax[1]); 
     if(doknr>=min && doknr<=max) return xml.getAbsolutePath();
  }//if
  }//for
  return "";
  }  
    
    
    
}//class
