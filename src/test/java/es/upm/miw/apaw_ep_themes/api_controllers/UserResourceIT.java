package es.upm.miw.apaw_ep_themes.api_controllers;

import es.upm.miw.apaw_ep_themes.ApiTestConfig;
import es.upm.miw.apaw_ep_themes.daos.UserDao;
import es.upm.miw.apaw_ep_themes.dtos.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ApiTestConfig
class UserResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private UserDao userDao;

    @Test
    void testCreate() {
        UserBasicDto userBasicDto = createUser("Carlos", "ccarlos", "Madrid", "Calle universidad");
        assertEquals("Carlos", userBasicDto.getName());
        assertEquals("ccarlos", userBasicDto.getPassword());
        assertEquals("Madrid", userBasicDto.getCountry());
        assertEquals("Calle universidad", userBasicDto.getAddress());
    }

    @Test
    void testCreateUserException() {
        UserCreationDto userCreationDto = new UserCreationDto("Carlos", null, "Madrid", "Calle universidad");
        this.webTestClient
                .post().uri(UserResource.USERS)
                .body(BodyInserters.fromObject(userCreationDto))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void ReadAll() {
        UserCreationDto userCreationDto = new UserCreationDto("Carlos", "ccarlos", "Madrid", "Calle universidad");
        this.webTestClient
                .post().uri(UserResource.USERS)
                .body(BodyInserters.fromObject(userCreationDto))
                .exchange()
                .expectStatus().isOk();
        List<UserBasicDto> list =
                this.webTestClient
                        .get().uri(UserResource.USERS)
                        .exchange()
                        .expectStatus().isOk()
                        .expectBodyList(UserBasicDto.class)
                        .returnResult().getResponseBody();
        assertTrue(list.size() > 0);
        assertNotNull(list.get(0).getId());
        assertNotNull(list.get(0).getName());
        assertNotNull(list.get(0).getPassword());
        assertNotNull(list.get(0).getPassword());
        assertNotNull(list.get(0).getPassword());
    }

    @Test
    void testCreateVideo() {
        String userId = createUser("Carlos", "ccarlos", "Madrid", "Calle universidad").getId();
        VideoCreationDto videoCreationDto = new VideoCreationDto("Presentacion Carlos", true);
        this.webTestClient
                .post().uri(UserResource.USERS + UserResource.ID_ID + UserResource.CHANEL + UserResource.VIDEOS, userId)
                .body(BodyInserters.fromObject(videoCreationDto))
                .exchange()
                .expectStatus().isOk();

    }

    @Test
    void testCreateVideoException() {
        String userId = createUser("Carlos", "ccarlos", "Madrid", "Calle universidad").getId();
        VideoCreationDto videoCreationDto = new VideoCreationDto(null, true);
        this.webTestClient
                .post().uri(UserResource.USERS + UserResource.ID_ID + UserResource.CHANEL + UserResource.VIDEOS, userId)
                .body(BodyInserters.fromObject(videoCreationDto))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);

    }

    UserBasicDto createUser(String name, String password, String country, String address) {
        UserCreationDto userCreationDto = new UserCreationDto(name, password, country, address);
        ChanelDto chanelDto = new ChanelDto("CarlosHD", "Canal de videos", "Informatica");
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

    @Test
    void ReadAllVideos() {
        String userId = createUser("Carlos", "ccarlos", "Madrid", "Calle universidad").getId();
        VideoCreationDto videoCreationDto = new VideoCreationDto("Presentacion Carlos", true);
        this.webTestClient
                .post().uri(UserResource.USERS + UserResource.ID_ID + UserResource.CHANEL + UserResource.VIDEOS, userId)
                .body(BodyInserters.fromObject(videoCreationDto))
                .exchange()
                .expectStatus().isOk();

        List<VideoBasicDto> list =
                this.webTestClient
                        .get().uri(UserResource.USERS + UserResource.ID_ID + UserResource.CHANEL + UserResource.VIDEOS, userId)
                        .exchange()
                        .expectStatus().isOk()
                        .expectBodyList(VideoBasicDto.class)
                        .returnResult().getResponseBody();
        assertTrue(list.size() > 0);
        assertNotNull(list.get(0).getReference());
        assertNotNull(list.get(0).getName());
        assertNotNull(list.get(0).getPublicVideo());
    }

    @Test
    void testPutVideo() {
        String userId = createUser("Carlos", "ccarlos", "Madrid", "Calle universidad").getId();
        VideoCreationDto videoCreationDto = new VideoCreationDto("Presentacion Carlos", true);
        String videoReference = this.webTestClient
                .post().uri(UserResource.USERS + UserResource.ID_ID + UserResource.CHANEL + UserResource.VIDEOS, userId)
                .body(BodyInserters.fromObject(videoCreationDto))
                .exchange()
                .expectStatus().isOk()
                .expectBody(VideoBasicDto.class)
                .returnResult().getResponseBody().getReference();

        VideoBasicDto videoBasicDto = new VideoBasicDto("Presentacion Carlos 2", false);
        this.webTestClient
                .put().uri(UserResource.USERS + UserResource.ID_ID + UserResource.CHANEL + UserResource.VIDEOS + UserResource.REFERENCE, userId, videoReference)
                .body(BodyInserters.fromObject(videoBasicDto))
                .exchange()
                .expectStatus().isOk();
    }
}