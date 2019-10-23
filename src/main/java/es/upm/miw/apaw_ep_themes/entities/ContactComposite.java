package es.upm.miw.apaw_ep_themes.entities;

import java.util.ArrayList;
import java.util.List;

public class ContactComposite implements ContactTree {

    private String email;
    private int telephone;

    private List<ContactTree> contactTreeList;

    public ContactComposite(String email, int telephone) {
        this.email = email;
        this.telephone = telephone;
        this.contactTreeList = new ArrayList<>();
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
    public boolean isComposite() {
        return true;
    }

    @Override
    public void add(ContactTree contactTree) {
        contactTreeList.add(contactTree);
    }

    @Override
    public void remove(ContactTree contactTree) {
        contactTreeList.remove(contactTree);
    }

    @Override
    public int size() {
        return contactTreeList.size();
    }
}
