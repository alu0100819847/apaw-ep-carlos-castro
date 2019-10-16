package es.upm.miw.apaw_ep_themes.dtos;

import es.upm.miw.apaw_ep_themes.entities.User;

public class UserBasicDto {

    private String id;

    private String name;

    private String password;

    private String country;

    private String address;

    public UserBasicDto(){}

    public UserBasicDto(String name, String password, String country, String address){
        this.name = name;
        this.password = password;
        this.country = country;
        this.address = address;
    }

    public UserBasicDto(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.password = user.getPassword();
        this.country = user.getCountry();
        this.address = user.getAddress();
    }

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }
    public String getCountry(){
        return this.country;
    }

    public void setCountry(String country){
        this.country = country;
    }

    public String getAddress(){
        return this.address;
    }

    public void setAddress(String address){
        this.address = address;
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