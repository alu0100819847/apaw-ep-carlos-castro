package es.upm.miw.apaw_ep_themes.dtos;

import es.upm.miw.apaw_ep_themes.entities.User;

public class UserBasicDto {

    private String id;

    private String name;

    private String password;

    private String country;

    private String address;

    public UserBasicDto(){}

    public UserBasicDto(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.password = user.getPassword();
        this.country = user.getCountry();
        this.address = user.getAddress();
    }


    public String getAddress(){
        return this.address;
    }

    public String getCountry(){
        return this.country;
    }

    public String getPassword(){
        return this.password;
    }

    public String getName(){
        return this.name;
    }

    public String getId(){
        return this.id;
    }

    @Override
    public String toString(){
        return "UserBasicDto{" +
                "id='" + this.id + '\'' +
                ", name=" + this.name +
                ", password='" + this.password + '\'' +
                ", country='" + this.country + '\'' +
                ", address='" + this.address + '\'' +
                '}';
    }
}