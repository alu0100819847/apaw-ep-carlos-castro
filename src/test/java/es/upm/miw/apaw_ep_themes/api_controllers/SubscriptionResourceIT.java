package es.upm.miw.apaw_ep_themes.api_controllers;

import es.upm.miw.apaw_ep_themes.ApiTestConfig;
import es.upm.miw.apaw_ep_themes.daos.SubscriptionDao;
import es.upm.miw.apaw_ep_themes.daos.UserDao;
import es.upm.miw.apaw_ep_themes.dtos.ChanelDto;
import es.upm.miw.apaw_ep_themes.dtos.SubscriptionDto;
import es.upm.miw.apaw_ep_themes.dtos.UserBasicDto;
import es.upm.miw.apaw_ep_themes.dtos.UserCreationDto;
import es.upm.miw.apaw_ep_themes.entities.Subscription;
import es.upm.miw.apaw_ep_themes.entities.User;
import es.upm.miw.apaw_ep_themes.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ApiTestConfig
public class SubscriptionResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private SubscriptionDao subscriptionDao;

    @Autowired
    private UserDao userDao;

    @Test
    void readUserSubscriptions() {
        String userId = this.createSubscription();
        List<SubscriptionDto> list =
                this.webTestClient
                        .get().uri(uriBuilder ->
                                uriBuilder.path(SubscriptionResource.SUBSCRIPTION + SubscriptionResource.SEARCH)
                                    .queryParam("q", "userId:"+userId)
                                    .build())
                        .exchange()
                        .expectStatus().isOk()
                        .expectBodyList(SubscriptionDto.class)
                        .returnResult().getResponseBody();
        String chanelId = this.userDao.findById(userId).orElseThrow(() -> new NotFoundException("User id: " + userId)).getChanel().getId();
        assertTrue(list.size() > 0);
        assertEquals(userId,  list.get(0).getUserId());
        assertEquals(0,  list.get(0).getDonation());
    }

    @Test
    void readUserSubscriptionsException() {
        String userId = this.createSubscription();
        this.webTestClient
                .get().uri(uriBuilder ->
                uriBuilder.path(SubscriptionResource.SUBSCRIPTION + SubscriptionResource.SEARCH)
                        .queryParam("q", ":"+userId)
                        .build())
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);
    }

    String createSubscription(){
        String userId = createUser("Carlos", "ccarlos", "Madrid", "Calle universidad").getId();
        String userId2 = createUser("Carlos2", "ccarlos", "Madrid", "Callee universidad").getId();

        User user = this.userDao.findById(userId).orElseThrow(() -> new NotFoundException("User id: " + userId));
        User user2 = this.userDao.findById(userId2).orElseThrow(() -> new NotFoundException("User id: " + userId2));
        Subscription subscription = new Subscription(user, user2.getChanel());
        subscriptionDao.save(subscription);
        return userId;
    }

    UserBasicDto createUser(String name, String password, String country, String address) {
        UserCreationDto userCreationDto = new UserCreationDto(name, password, country, address);
        ChanelDto chanelDto = new ChanelDto(name, "Canal de videos", "Informatica");
        UserBasicDto userBasicDto = this.webTestClient
                .post().uri(UserResource.USERS)
                .body(BodyInserters.fromObject(userCreationDto))
                .exchange()
                .expectStatus().isOk()
                .expectBody(UserBasicDto.class)
                .returnResult().getResponseBody();

        this.webTestClient
                .post().uri(UserResource.USERS + UserResource.ID_ID + UserResource.CHANEL, userBasicDto.getId())
                .body(BodyInserters.fromObject(chanelDto))
                .exchange()
                .expectStatus().isOk()
                .expectBody(ChanelDto.class)
                .returnResult().getResponseBody();
        return userBasicDto;
    }
}
