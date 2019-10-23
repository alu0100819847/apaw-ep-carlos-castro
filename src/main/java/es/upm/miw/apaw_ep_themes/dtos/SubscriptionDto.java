package es.upm.miw.apaw_ep_themes.dtos;

import es.upm.miw.apaw_ep_themes.entities.Subscription;

public class SubscriptionDto {

    private String id;

    private float donation;

    private String userId;

    private String chanelId;

    public SubscriptionDto() { }

    public SubscriptionDto(Subscription subscription){
        this.id = subscription.getId();
        this.userId = subscription.getUser().getId();
        this.chanelId = subscription.getChanel().getId();
    }

    @Override
    public String toString() {
        return "SubscriptionDto{" +
                "id='" + this.id + '\'' +
                "chanel='" + this.chanelId + '\'' +
                "user='" + this.userId + '\'' +
                ", donation='" + this.donation + '\'' +
                '}';
    }
}
