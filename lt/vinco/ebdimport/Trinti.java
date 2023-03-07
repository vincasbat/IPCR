/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.vinco.ebdimport;

import java.util.List;
import lt.vinco.ebdimport.entity.Classin;
import lt.vinco.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author vbatulevicius
 */
public class Trinti {

    public static int DeleteClassif(String idappli) {
        Session session = null;
Boolean OK = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
/*
            //Istriname  klasifikaciją:   
            String hql = "from Classin where idappli = :idappli";
            Query q = session.createQuery(hql);
            q.setParameter("idappli", idappli);
            List oldclass = q.list();
                if (oldclass.size() > 0) {
                for (int i = 0; i < oldclass.size(); i++) {
                    Classin oldcl = (Classin) oldclass.get(i);
                     session.delete(oldcl);
                }//for
            }//if   
*/
            
        Query deleteQuery = session.createSQLQuery("delete from classin where idappli = ?");
deleteQuery.setString(0, idappli);
int updated = deleteQuery.executeUpdate();    
            
   System.out.println("updated " + updated);         
            
            
            session.getTransaction().commit();
            OK = true;
            
            
            
        } catch (HibernateException   he) {
            he.printStackTrace();
            session.getTransaction().rollback();
           } 
            
        finally {
    session.flush();
    session.close();
     if (OK) {
        System.out.println("Klasifikacija ištrinta " + idappli);
        return 0;
        } else{
          System.out.println("Nepavyko ištrinti " + idappli);
            return -1;
     }
        }
        
        
       
        
    }

   
}//class
