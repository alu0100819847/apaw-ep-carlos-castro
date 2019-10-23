package es.upm.miw.apaw_ep_themes.daos;

import es.upm.miw.apaw_ep_themes.TestConfig;
import es.upm.miw.apaw_ep_themes.entities.Chanel;
import es.upm.miw.apaw_ep_themes.entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class UserDaoIT {

    private User user;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ChanelDao chanelDao;

    @BeforeEach
    void beforeEach(){
        this.user = new User("Carlos", "ccarlos", "España", "Calle juan muñoz");
        this.userDao.save(this.user);
    }

    @Test
    void testCreate() {

        User databaseUser = this.userDao.findById(this.user.getId()).orElseGet(Assertions::fail);
        assertEquals("Carlos", databaseUser.getName());
        assertEquals("ccarlos", databaseUser.getPassword());
        assertEquals("España", databaseUser.getCountry());
        assertEquals("Calle juan muñoz", databaseUser.getAddress());
    }

    @Test
    void testAddChanel(){
        Chanel chanel = new Chanel("CarlosHD", "Canal musical", "Musica");
        this.chanelDao.save(chanel);
        User databaseUser = this.userDao.findById(this.user.getId()).orElseGet(Assertions::fail);
        databaseUser.setChanel(chanel);
        this.userDao.save(databaseUser);
        assertEquals(chanel.getId(), databaseUser.getChanel().getId());




    }
}
