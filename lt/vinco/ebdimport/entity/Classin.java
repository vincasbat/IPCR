package lt.vinco.ebdimport.entity;
// Generated Mar 6, 2013 11:04:14 PM by Hibernate Tools 3.2.1.GA

import java.sql.Date;

/**
 * Classin generated by hbm2java
 */
public class Classin  implements java.io.Serializable {

     private ClassinId id;
   
    private Short ipcversion; 
    private Short odclass;
    private Short odlink;
    private Short tyipcclass;
    
    private Date dtversion;
     private Short symbpos; //2 arba 1
       private Short tyclassif; // 1
       private Date dtaction;
       private Short stclassif; // 1
       private Short scclassif; // 1
        private String origin; // EP
       
       
    
     public Classin() {
         this.tyipcclass = 1;     // patikslinti
     }

    public ClassinId getId() {
        return id;
    }

    public void setId(ClassinId id) {
        this.id = id;
    }

    
     
     
     

    public Short getIpcversion() {
        return ipcversion;
    }

    public void setIpcversion(Short ipcversion) {
        this.ipcversion = ipcversion;
    }

   

    public Short getOdclass() {
        return odclass;
    }

    public void setOdclass(Short odclass) {
        this.odclass = odclass;
    }

    public Short getOdlink() {
        return odlink;
    }

    public void setOdlink(Short odlink) {
        this.odlink = odlink;
    }

    public Short getTyipcclass() {
        return tyipcclass;
    }

    public void setTyipcclass(Short tyipcclass) {
        this.tyipcclass = tyipcclass;
    }

    public Date getDtversion() {
        return dtversion;
    }

    public void setDtversion(Date dtversion) {
        this.dtversion = dtversion;
    }

    public Short getSymbpos() {
        return symbpos;
    }

    public void setSymbpos(Short symbpos) {
        this.symbpos = symbpos;
    }

    public Short getTyclassif() {
        return tyclassif;
    }

    public void setTyclassif(Short tyclassif) {
        this.tyclassif = tyclassif;
    }

    public Date getDtaction() {
        return dtaction;
    }

    public void setDtaction(Date dtaction) {
        this.dtaction = dtaction;
    }

    public Short getScclassif() {
        return scclassif;
    }

    public void setScclassif(Short scclassif) {
        this.scclassif = scclassif;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Short getStclassif() {
        return stclassif;
    }

    public void setStclassif(Short stclassif) {
        this.stclassif = stclassif;
    }
    

    
    


}


