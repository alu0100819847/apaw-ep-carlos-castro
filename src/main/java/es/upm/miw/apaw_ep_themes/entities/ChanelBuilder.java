package es.upm.miw.apaw_ep_themes.entities;

public class ChanelBuilder {

    Chanel chanel;

    ChanelBuilder(String name, String description, String topic){
        this.chanel = new Chanel(name, description, topic);
    }

    Chanel build(){
        return chanel;
    }
}



