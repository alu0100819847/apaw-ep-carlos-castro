package es.upm.miw.apaw_ep_themes.entities;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {

    @Id
    private String id;

    private String name;

    private String password;

    private String country;

    private String address;

    @DBRef
    private Chanel chanel;

    public User(String name, String password, String country, String address){
        this.name = name;
        this.password = password;
        this.country = country;
        this.address = address;
    }



    public String getName(){
        return this.name;
    }

    public String getId(){
        return this.id;
    }

    public String getCountry(){
        return this.country;
    }

    public String getPassword(){
        return this.password;
    }


    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress(){
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Chanel getChanel(){
        return this.chanel;
    }

    public void setChanel(Chanel chanel){
        this.chanel = chanel;
    }

    @Override
    public String toString(){
        return "User{" +
                "id='" + this.id + '\'' +
                ", name=" + this.name +
                ", password='" + this.password + '\'' +
                ", country='" + this.country + '\'' +
                ", address='" + this.address + '\'' +
                ", chanel='" + this.chanel + '\'' +
                '}';
    }


}