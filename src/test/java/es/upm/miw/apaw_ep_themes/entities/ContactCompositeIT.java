package es.upm.miw.apaw_ep_themes.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactCompositeIT {

    private ContactTree library1;
    private ContactTree library2;
    private ContactTree library3;
    private ContactTree library4;

    @BeforeEach
    void beforeEach() {
        this.library1 = new ContactComposite("c@hotm.es", 2134132131);
        this.library2 = new Contact("d@hotm.es", 2134132131);
        this.library3 = new ContactComposite("e@hotm.es", 2134132131);
        this.library4 = new Contact("l@hotm.es", 2134132131);
        this.library1.add(library2);
        this.library1.add(library3);
        this.library3.add(library4);
    }

    @Test
    void addRemoveTest(){
        assertEquals(2, library1.size());
        assertEquals(0, library2.size());
        ContactTree library5 = new Contact("ll@hotm.es", 2134132131);
        this.library1.add(library5);
        assertEquals(3, library1.size());
        this.library1.remove(library5);
        this.library1.remove(library2);
        assertEquals(1, library1.size());
    }

    @Test
    void isCompositeTest(){
        assertEquals(true, library1.isComposite());
        assertEquals(false, library2.isComposite());
    }

    @Test
    void getTest(){
        assertEquals("c@hotm.es", library1.getEmail());
        assertEquals(2134132131, library1.getTelephone());
    }
}
