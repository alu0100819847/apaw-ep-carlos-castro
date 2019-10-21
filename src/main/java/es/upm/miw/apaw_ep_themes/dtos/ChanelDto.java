package es.upm.miw.apaw_ep_themes.dtos;

import es.upm.miw.apaw_ep_themes.entities.Chanel;
import es.upm.miw.apaw_ep_themes.exceptions.BadRequestException;

public class ChanelDto {

    private String id;

    private String name;

    private String description;

    private String topic;

    public ChanelDto(){}

    public ChanelDto(String name, String description, String topic){
        this.name = name;
        this.description = description;
        this.topic = topic;
    }

    public ChanelDto(Chanel chanel){
        this.id = chanel.getId();
        this.name = chanel.getName();
        this.description = chanel.getDescription();
        this.topic = chanel.getTopic();
    }

    public String getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public String getTopic(){
        return this.topic;
    }

    public void validate(){
        if (this.name == null || this.name.isEmpty() ) {
            throw new BadRequestException("Incomplete VideoDto. ");
        }

    }

    @Override
    public String toString() {
        return "ChanelDto{" +
                "id='" + id + '\'' +
                ", name='" + this.name + '\'' +
                ", description='" + this.description + '\'' +
                ", topic='" + this.topic + '\'' +
                '}';
    }
}
