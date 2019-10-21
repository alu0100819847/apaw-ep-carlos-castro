package es.upm.miw.apaw_ep_themes.dtos;

import es.upm.miw.apaw_ep_themes.entities.Video;
import es.upm.miw.apaw_ep_themes.exceptions.BadRequestException;

public class VideoBasicDto {

    String reference;

    String name;

    Boolean publicVideo;

    public VideoBasicDto(){ }

    public VideoBasicDto(Video video){
        this.reference = video.getReference();
        this.name = video.getName();
        this.publicVideo = video.getPublicVideo();
    }

    public VideoBasicDto(String name, Boolean publicVideo){
        this.name = name;
        this.publicVideo = publicVideo;
    }

    public String getReference(){
        return this.reference;
    }

    public String getName(){
        return this.name;
    }

    public Boolean getPublicVideo(){
        return this.publicVideo;
    }

    public void validate() {
        if (this.name == null || this.name.isEmpty()) {
            throw new BadRequestException("Incomplete VideoBasicDto. ");
        }
    }

    @Override
    public String toString() {
        return "VideoBasicDto{" +
                "reference='" + this.reference + '\'' +
                ", name=" + this.name +
                '}';
    }
}