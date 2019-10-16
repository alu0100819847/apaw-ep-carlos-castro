package es.upm.miw.apaw_ep_themes.api_controllers;

import es.upm.miw.apaw_ep_themes.ApiTestConfig;
import es.upm.miw.apaw_ep_themes.dtos.UserBasicDto;
import es.upm.miw.apaw_ep_themes.dtos.UserCreationDto;
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

    UserBasicDto createUser(String name, String password, String country, String address) {
        UserCreationDto userCreationDto = new UserCreationDto(name, password, country, address);
        return this.webTestClient
                .post().uri(UserResource.USERS)
                .body(BodyInserters.fromObject(userCreationDto))
                .exchange()
                .expectStatus().isOk()
                .expectBody(UserBasicDto.class)
                .returnResult().getResponseBody();
    }
}