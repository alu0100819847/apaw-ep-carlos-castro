package es.upm.miw.apaw_ep_themes.entities;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Video {

    @Id
    String reference;

    String name;

    int reaction;

    Boolean publicVideo;

    public Video(String name, Boolean publicVideo){
        this.name = name;
        this.publicVideo = publicVideo;
        this.reaction = 0;
    }

    public String getName(){
        return this.name;
    }

    public String getReference(){
        return this.reference;
    }

    public int getReaction(){
        return this.reaction;
    }

    public Boolean getPublicVideo(){
        return this.publicVideo;
    }

    @Override
    public String toString() {
        return "Video{" +
                "reference='" + this.reference + '\'' +
                ", name=" + this.name +
                ", reaction='" + this.reaction + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPublicVideo(Boolean publicVideo) {
        this.publicVideo = publicVideo;
    }
}