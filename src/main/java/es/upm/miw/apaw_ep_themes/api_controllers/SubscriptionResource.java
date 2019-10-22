package es.upm.miw.apaw_ep_themes.api_controllers;

import es.upm.miw.apaw_ep_themes.business_controllers.SubscriptionBusinessController;
import es.upm.miw.apaw_ep_themes.dtos.SubscriptionDto;
import es.upm.miw.apaw_ep_themes.exceptions.BadRequestException;
import org.reactivestreams.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(SubscriptionResource.SUBSCRIPTION)
public class SubscriptionResource {

    public static final String SUBSCRIPTION = "/subscriptions";
    public static final String SEARCH = "/search";

    private SubscriptionBusinessController subscriptionBusinessController;

    @Autowired
    public SubscriptionResource(SubscriptionBusinessController subscriptionBusinessController) {
        this.subscriptionBusinessController = subscriptionBusinessController;
    }

    @GetMapping(value = SEARCH)
    public List<SubscriptionDto> readAllUsers(@RequestParam String q) {
        if (!"userId".equals(q.split(":")[0])) {
            throw new BadRequestException("query param q is incorrect, missing 'userId:'");
        }
        return this.subscriptionBusinessController.readUserSubscriptions(q.split(":")[1]);
    }
}
