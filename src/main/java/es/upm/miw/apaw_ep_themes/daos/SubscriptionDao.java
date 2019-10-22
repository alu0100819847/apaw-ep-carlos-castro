package es.upm.miw.apaw_ep_themes.daos;

import es.upm.miw.apaw_ep_themes.entities.Subscription;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubscriptionDao extends MongoRepository<Subscription, String> {
}
