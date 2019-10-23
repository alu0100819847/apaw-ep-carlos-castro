package es.upm.miw.apaw_ep_themes.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChanelBuilderIT {
    @Test
    void testBookBuilder(){
        Chanel chanel = new ChanelBuilder("CarlosHD", "Cosas de videos", "humor").build();
        assertEquals("CarlosHD", chanel.getName());
        assertEquals("Cosas de videos", chanel.getDescription());
        assertEquals("humor", chanel.getTopic());
    }
}
