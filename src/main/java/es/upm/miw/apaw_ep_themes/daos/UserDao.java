package es.upm.miw.apaw_ep_themes.daos;

import es.upm.miw.apaw_ep_themes.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserDao extends MongoRepository<User, String> {
}
