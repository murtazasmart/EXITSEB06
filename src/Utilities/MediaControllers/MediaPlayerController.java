package Utilities.MediaControllers;

import Utilities.Constances.Constance;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;


/**
 * Created by Bhagya Rathnayake on 6/1/2017.
 */
public class MediaPlayerController {

    ImageView btnVolume;
    private final MediaPlayer mediaPlayer;
    private final MediaPlayer sfxPlayer;
    String bip=new File("src/Resources/music/theme.mp3").toURI().toString();
    String mouseOver=new File("src/Resources/music/sfx1.mp3").toURI().toString();
    Media hit = new Media(bip);
    Media sfxMO = new Media(mouseOver);

    public MediaPlayerController()
    {
        mediaPlayer = new MediaPlayer(hit);
        sfxPlayer= new MediaPlayer(sfxMO);

    }

    public void playMusic()
    {
        Constance.mediaPlayerController=this;
        mediaPlayer.play();
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }

    public void sfxMouseOver()
    {
        sfxPlayer.play();
    }

    public void sfxMouseLeave()
    {
        sfxPlayer.stop();
    }

    public int muteAll()
    {
        System.out.println("MUTE ACCESSED");
        MediaPlayer.Status status = Constance.mediaPlayerController.mediaPlayer.getStatus();
        System.out.println(status.toString());
        if(status.equals(MediaPlayer.Status.PLAYING))
        {
            mediaPlayer.pause();
            return 1;

        }
        else if(status.equals(MediaPlayer.Status.PAUSED))
        {
            mediaPlayer.play();
            return 2;
        }

        return 0;
    }

}
