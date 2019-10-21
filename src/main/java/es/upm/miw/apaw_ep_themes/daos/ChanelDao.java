package es.upm.miw.apaw_ep_themes.daos;

import es.upm.miw.apaw_ep_themes.entities.Chanel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChanelDao extends MongoRepository<Chanel, String> {
}
