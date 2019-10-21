package es.upm.miw.apaw_ep_themes.business_controllers;

import es.upm.miw.apaw_ep_themes.daos.ChanelDao;
import es.upm.miw.apaw_ep_themes.daos.UserDao;
import es.upm.miw.apaw_ep_themes.daos.VideoDao;
import es.upm.miw.apaw_ep_themes.dtos.*;
import es.upm.miw.apaw_ep_themes.entities.Chanel;
import es.upm.miw.apaw_ep_themes.entities.User;
import es.upm.miw.apaw_ep_themes.entities.Video;
import es.upm.miw.apaw_ep_themes.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserBusinessController {

    private UserDao userDao;
    private VideoDao videoDao;
    private ChanelDao chanelDao;

    @Autowired
    public UserBusinessController(UserDao userDao, VideoDao videoDao, ChanelDao chanelDao) {
        this.userDao = userDao;
        this.videoDao = videoDao;
        this.chanelDao = chanelDao;
    }

    public UserBasicDto create(UserCreationDto userCreationDto) {
        User user = new User(userCreationDto.getName(), userCreationDto.getPassword(), userCreationDto.getCountry(), userCreationDto.getAddress());
        this.userDao.save(user);
        return new UserBasicDto(user);
    }

    public List<UserBasicDto> readAllUsers() {
        List<User> users = this.userDao.findAll();
        return users.stream().map(UserBasicDto::new).collect(Collectors.toList());
    }

    public VideoBasicDto createVideo(String id, VideoCreationDto videoCreationDto) {
        Chanel chanel = this.findUserByIdAssured(id).getChanel();

        Video video = new Video(videoCreationDto.getName(), videoCreationDto.getPublicVideo());
        this.videoDao.save(video);
        chanel.getVideos().add(video);
        this.chanelDao.save(chanel);
        return new VideoBasicDto(video);
    }

    public ChanelDto createChanel(String id, ChanelDto chanelDto){
        User user = this.findUserByIdAssured(id);
        Chanel chanel = new Chanel(chanelDto.getName(), chanelDto.getDescription(), chanelDto.getTopic());
        this.chanelDao.save(chanel);
        user.setChanel(chanel);
        this.userDao.save(user);
        return new ChanelDto(chanel);
    }

    private User findUserByIdAssured(String id) {
        return this.userDao.findById(id).orElseThrow(() -> new NotFoundException("User id: " + id));
    }

    public List<VideoBasicDto> readAllVideos(String id){
        List<Video> videos = findUserByIdAssured(id).getChanel().getVideos();
        return videos.stream().map(VideoBasicDto::new).collect(Collectors.toList());
    }

    private Video findVideoByReferenceAssured(String reference) {
        return this.videoDao.findById(reference).orElseThrow(() -> new NotFoundException("User id: " + reference));
    }

    public void updateVideo(String id, String reference, VideoBasicDto videoBasicDto) {
        Video video = findVideoByReferenceAssured(reference);
        video.setName(videoBasicDto.getName());
        video.setPublicVideo(videoBasicDto.getPublicVideo());
        videoDao.save(video);
    }

    public void deleteVideo(String id, String reference) {
        Video video = findVideoByReferenceAssured(reference);
        this.videoDao.delete(video);
    }
}