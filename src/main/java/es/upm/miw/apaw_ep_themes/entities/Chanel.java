
package es.upm.miw.apaw_ep_themes.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.List;

@Document
public class Chanel {

    @Id
    private int id;

    private String name;

    private String description;

    private String topic;

    private List<Video> videos;

    public Chanel(String name, String description, String topic){
        this.name = name;
        this.description = description;
        this.topic = topic;
        this.videos = new ArrayList<>();
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

    public List<Video> getVideos(){
        return this.videos;
    }

}
