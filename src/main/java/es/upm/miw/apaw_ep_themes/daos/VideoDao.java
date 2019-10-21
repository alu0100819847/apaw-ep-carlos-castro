package es.upm.miw.apaw_ep_themes.daos;

import es.upm.miw.apaw_ep_themes.entities.Video;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VideoDao extends MongoRepository<Video, String> {

}
