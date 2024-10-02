package at.hakspittal.usermanager;

//Der User soll als Tabelle "User" in der DB erzeugt werden.

import java.lang.annotation.Inherited;
import javax.annotation.processing.Generated;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {

    //Primärschlüssel
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id; 

    //Attribute
    private String firstname;
    private String lastname;

    //Standardkonstruktor
    public User(){

    }

    //GETTER/SETTER
    public String getFirstname(){
        return this.firstname;
    }

    public void setFirstname(String firstname){
        this.firstname = firstname;
    }

    public String getLastname(){
        return this.lastname;
    }

    public void setLastname(String lastname){
        this.lastname = lastname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    

}