package es.upm.miw.apaw_ep_themes.entities;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Contact implements ContactTree{

    @Id
    private int id;

    private String email;

    private int telephone;

    public Contact(String email, int telephone){
        this.email = email;
        this.telephone = telephone;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public int getTelephone(){
        return this.telephone;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id='" + this.id + '\'' +
                ", email=" + this.email +
                ", telephone='" + this.telephone + '\'' +
                '}';
    }

    @Override
    public boolean isComposite() {
        return false;
    }

    @Override
    public void add(ContactTree contactTree) {
        //Not a composite
    }

    @Override
    public void remove(ContactTree contactTree) {
        //Not a composite
    }

    @Override
    public int size() {
        return 0;
    }
}
