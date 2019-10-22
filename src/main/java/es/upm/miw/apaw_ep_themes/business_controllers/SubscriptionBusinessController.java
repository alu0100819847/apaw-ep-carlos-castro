package es.upm.miw.apaw_ep_themes.business_controllers;

import es.upm.miw.apaw_ep_themes.daos.SubscriptionDao;
import es.upm.miw.apaw_ep_themes.dtos.SubscriptionDto;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class SubscriptionBusinessController {

    private SubscriptionDao subscriptionDao;

    SubscriptionBusinessController(SubscriptionDao subscriptionDao){
        this.subscriptionDao = subscriptionDao;
    }

    public List<SubscriptionDto> readUserSubscriptions(String userId) {
            return this.subscriptionDao.findAll().stream()
                    .filter(subscription -> subscription.getUser().getId().equals(userId))
                    .map(SubscriptionDto::new)
                    .collect(Collectors.toList());
    }
}
