/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.vinco.ebdimport;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import lt.vinco.ebdimport.entity.Ptappli;
import lt.vinco.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Vincas Batulevičius
 */
public class EBDimport {
    
 
static File flog = null;
  public  static Logger log = null;
    static FileHandler hand = null;

    public static void createLogger() throws IOException {
        flog = new File("logai");
        if (!flog.exists()) {
            flog.mkdir();
        }
        if (!flog.exists()) {
            throw new IOException("Turi būti sukurtas log aplankas logai");
        }
        hand = new FileHandler("logai\\ebd.log");
        log = Logger.getLogger(EBDimport.class.getName());
        log.addHandler(hand);
        SimpleFormatter formatter = new SimpleFormatter();
        hand.setFormatter(formatter);
     }
    
   
    static Logger logkeit = null;
    static FileHandler handkeit = null;

    public static void createKeitLogger() throws IOException {
      handkeit = new FileHandler("logai\\ipcr.log");
        logkeit = Logger.getLogger(KeistiIPCR.class.getName());
        logkeit.addHandler(handkeit);
        SimpleFormatter formatter = new SimpleFormatter();
        handkeit.setFormatter(formatter);
     }
    
    
    
    
    
    
    


//------------------------------------------------------//    
    public static String formatDate(String date) {
        StringBuilder builder = new StringBuilder(date);
        builder.insert(4, " ");
        builder.insert(7, " ");
        return builder.toString();
    }

    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');
        if (i > 0 && i < s.length() - 1) {
            ext = s.substring(i + 1).toLowerCase();
        }
        if (ext == null) {
            return "";
        }
        return ext;
    }

  
    
    

    public static java.sql.Date getSqldate(String strDate) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        Date date = null;
        try {
            date = df.parse(strDate);
        } catch (ParseException ex) {
            Logger.getLogger(EBDimport.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new java.sql.Date(date.getTime());

    }
    
    
    public static java.sql.Date addYears(final java.util.Date date, final int years) {
        java.sql.Date calculatedDate = null;

        if (date != null) {
            final GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(Calendar.YEAR, years);
            calculatedDate = new java.sql.Date(calendar.getTime().getTime());
        }

        return calculatedDate;
    }  
    
    
    public static PatentasDB YraDB(String extidpatent){
     PatentasDB pdb = new PatentasDB();
    pdb.YraDB = false;
    pdb.idappli = null;
    pdb.idagent = null;
     pdb.lgstappli = null;
     Session session = null; 
        try {
             session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
        
            Criteria criteria = session.createCriteria(Ptappli.class);  
    criteria.add(Restrictions.eq("extidpatent", "EP"+extidpatent));
 List pt =(List <Ptappli>) criteria.list();
 if((pt!=null)&&(pt.size()>0)){
Ptappli ptappli = (Ptappli)pt.get(0);
pdb.idappli  = ptappli.getIdappli();
pdb.idagent  = ptappli.getIdagent();
 pdb.lgstappli = ptappli.getLgstappli();
 //System.out.println("idappli: "+idappli);
//System.out.println(ptappli.getEngtitle());
pdb.YraDB = true;
} 
         session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
            session.getTransaction().rollback();
            System.out.println("Tikrinant duomenis įvyko klaida. Dokumento nr. "+ extidpatent);
       
        }
     return pdb;
    }
    
    
      public static PatentasDB YraDBextidappli(String extidappli){
     PatentasDB pdb = new PatentasDB();
    pdb.YraDB = false;
    pdb.idappli = null;
    pdb.idagent = null;
     pdb.lgstappli = null;
     Session session = null; 
        try {
             session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
        
            Criteria criteria = session.createCriteria(Ptappli.class);  
    criteria.add(Restrictions.eq("extidappli", "EP"+extidappli));
 List pt =(List <Ptappli>) criteria.list();
 if((pt!=null)&&(pt.size()>0)){
Ptappli ptappli = (Ptappli)pt.get(0);
pdb.idappli  = ptappli.getIdappli();
pdb.idagent  = ptappli.getIdagent();
 pdb.lgstappli = ptappli.getLgstappli();
 //System.out.println("idappli: "+idappli);
//System.out.println(ptappli.getEngtitle());
pdb.YraDB = true;
} 
         session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
            session.getTransaction().rollback();
            System.out.println("Įrašant duomenis įvyko klaida. Dokumento paraiskos nr. "+ extidappli);
       
        }
        
         finally { session.flush();  session.close(); }
     return pdb;
    }  
    
    
    
    
    
    
}//class

