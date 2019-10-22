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

    public float getDonation(){
        return this.donation;
    }

    @Override
    public String toString() {
        return "ChanelBasicDto{" +
                "id='" + this.id + '\'' +
                ", donation='" + this.donation + '\'' +
                '}';
    }
}
