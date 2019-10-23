package es.upm.miw.apaw_ep_themes.api_controllers;

import es.upm.miw.apaw_ep_themes.business_controllers.UserBusinessController;
import es.upm.miw.apaw_ep_themes.dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UserResource.USERS)
public class UserResource {
    public static final String USERS = "/users";
    public static final String ID_ID = "/{id}";
    public static final String VIDEOS = "/videos";
    public static final String CHANEL = "/chanel";
    public static final String REFERENCE = "/{reference}";

    private UserBusinessController userBusinessController;

    @Autowired
    public UserResource(UserBusinessController userBusinessController) {
        this.userBusinessController = userBusinessController;
    }

    @PostMapping
    public UserBasicDto create(@RequestBody UserCreationDto userCreationDto) {
        userCreationDto.validate();
        return this.userBusinessController.create(userCreationDto);
    }

    @GetMapping
    public List<UserBasicDto> readAllUsers() {
        return this.userBusinessController.readAllUsers();
    }

    @PostMapping(value = ID_ID + CHANEL + VIDEOS)
    public VideoBasicDto createVideo(@PathVariable String id, @RequestBody VideoCreationDto videoCreationDto) {
        videoCreationDto.validate();
        return this.userBusinessController.createVideo(id, videoCreationDto);
    }

    @GetMapping(value = ID_ID + CHANEL + VIDEOS)
    public List<VideoBasicDto> readAllUsers(@PathVariable String id) {
        return this.userBusinessController.readAllVideos(id);
    }

    @PostMapping(value = ID_ID + CHANEL)
    public ChanelDto createChanel(@PathVariable String id, @RequestBody ChanelDto chanelDto) {
        chanelDto.validate();
        return this.userBusinessController.createChanel(id, chanelDto);
    }

    @PutMapping(value = ID_ID + CHANEL + VIDEOS + REFERENCE)
    public void updateVideo(@PathVariable String reference, @RequestBody VideoBasicDto videoBasicDto) {
        videoBasicDto.validate();
        this.userBusinessController.updateVideo(reference, videoBasicDto);
    }

    @DeleteMapping(value = ID_ID + CHANEL + VIDEOS + REFERENCE)
    public void deleteVideo(@PathVariable String reference) {
        this.userBusinessController.deleteVideo(reference);
    }

    @PatchMapping(value = ID_ID)
    public void patch(@PathVariable String id, @RequestBody UserPatchDto userPatchDto) {
        userPatchDto.validate();
        this.userBusinessController.patch(id, userPatchDto);
    }
}