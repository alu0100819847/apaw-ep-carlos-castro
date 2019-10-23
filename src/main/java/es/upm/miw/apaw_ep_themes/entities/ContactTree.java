package es.upm.miw.apaw_ep_themes.entities;

public interface ContactTree {
    boolean isComposite();

    void add(ContactTree contactTree);

    void remove(ContactTree contactTree);

    int size();

    String getEmail();

    int getTelephone();

}
