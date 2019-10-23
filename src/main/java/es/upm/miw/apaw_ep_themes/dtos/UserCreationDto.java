package es.upm.miw.apaw_ep_themes.dtos;

import es.upm.miw.apaw_ep_themes.exceptions.BadRequestException;

public class UserCreationDto {
    private String id;

    private String name;

    private String password;

    private String country;

    private String address;

    public UserCreationDto(){}

    public UserCreationDto(String name, String password, String country, String address){
        this.name = name;
        this.password = password;
        this.country = country;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public String getName(){
        return this.name;
    }

    public String getPassword(){
        return this.password;
    }

    public String getCountry(){
        return this.country;
    }

    public String getAddress(){
        return this.address;
    }

    public void validate() {
        if (this.name == null || this.password == null || this.name.isEmpty()|| this.password.isEmpty()  ) {
            throw new BadRequestException("Incomplete UserCreationDto. ");
        }
    }

    @Override
    public String toString(){
        return "UserCreationDto{" +
                "id='" + this.id + '\'' +
                ", name=" + this.name +
                ", password='" + this.password + '\'' +
                ", country='" + this.country + '\'' +
                ", address='" + this.address + '\'' +
                '}';
    }
}