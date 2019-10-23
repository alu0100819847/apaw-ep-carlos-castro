package es.upm.miw.apaw_ep_themes.daos;

import es.upm.miw.apaw_ep_themes.TestConfig;
import es.upm.miw.apaw_ep_themes.entities.Chanel;
import es.upm.miw.apaw_ep_themes.entities.Video;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sun.security.krb5.internal.ccache.CredentialsCache;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class ChanelDaoIT {

    Chanel chanel;

    @Autowired
    private ChanelDao chanelDao;
    
    @Autowired
    private VideoDao videoDao;

    @BeforeEach
    void beforeEach(){
        this.chanel = new Chanel("CarlosHD", "Canal musical", "Musica");
        this.chanelDao.save(chanel);
    }

    @Test
    void testCreateChanel(){
        Chanel databaseChanel = this.chanelDao.findById(this.chanel.getId()).orElseGet(Assertions::fail);
        assertEquals("CarlosHD", databaseChanel.getName());
        assertEquals("Canal musical", databaseChanel.getDescription());
        assertEquals("Musica", databaseChanel.getTopic());
    }

    @Test
    void testAddVideo(){
        Chanel databaseChanel = this.chanelDao.findById(this.chanel.getId()).orElseGet(Assertions::fail);
        Video video = new Video("Maqueta", false);
        this.videoDao.save(video);

        databaseChanel.getVideos().add(video);
        this.chanelDao.save(databaseChanel);

        assertEquals(1, databaseChanel.getVideos().size());
        assertEquals("Maqueta", databaseChanel.getVideos().get(0).getName());
        assertEquals(false, databaseChanel.getVideos().get(0).getPublicVideo());
        assertEquals(0, databaseChanel.getVideos().get(0).getReaction());

    }
}
