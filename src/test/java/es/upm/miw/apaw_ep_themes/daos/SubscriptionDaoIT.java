package es.upm.miw.apaw_ep_themes.daos;

import es.upm.miw.apaw_ep_themes.TestConfig;
import es.upm.miw.apaw_ep_themes.entities.Chanel;
import es.upm.miw.apaw_ep_themes.entities.Subscription;
import es.upm.miw.apaw_ep_themes.entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class SubscriptionDaoIT {

    @Autowired
    private ChanelDao chanelDao;

    @Autowired
    private SubscriptionDao subscriptionDao;

    @Autowired
    private UserDao userDao;


    @Test
    void testCreateChanel(){
        User user = new User("Carlos", "ccarlos", "España", "Calle juan muñoz");
        this.userDao.save(user);

        Chanel chanel = new Chanel("CarlosHD", "Canal musical", "Musica");
        this.chanelDao.save(chanel);

        Subscription subscription = new Subscription(user, chanel);
        this.subscriptionDao.save(subscription);

        Subscription databaseSubscription =  this.subscriptionDao.findById(subscription.getId()).orElseGet(Assertions::fail);

        assertEquals(user.getId(), databaseSubscription.getUser().getId());
        assertEquals(chanel.getId(), databaseSubscription.getChanel().getId());
        assertEquals(0, databaseSubscription.getDonation());
        assertNotNull(databaseSubscription.getCreationDate());

    }
}
