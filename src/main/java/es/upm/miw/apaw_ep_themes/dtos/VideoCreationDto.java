package es.upm.miw.apaw_ep_themes.dtos;

import es.upm.miw.apaw_ep_themes.exceptions.BadRequestException;

public class VideoCreationDto {

    String reference;

    String name;

    Boolean publicVideo;


    public VideoCreationDto(){ }

    public VideoCreationDto(String name, Boolean publicVideo){
        this.name = name;
        this.publicVideo = publicVideo;
        if(this.publicVideo == null){
            this.publicVideo = false;
        }
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Boolean getPublicVideo(){
        return this.publicVideo;
    }


    public void validate() {
        if (this.name == null || this.name.isEmpty()) {
            throw new BadRequestException("Incomplete VideoCreationDto. ");
        }
    }

    @Override
    public String toString() {
        return "VideoCreationDto{" +
                "reference='" + this.reference + '\'' +
                ", name=" + this.name +
                '}';
    }

}
