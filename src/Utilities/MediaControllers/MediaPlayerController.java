package Utilities.MediaControllers;

import Utilities.Constances.Constance;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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

    private final boolean volume=Constance.isVolumeON;
    ImageView btnVolume;
    private final MediaPlayer mediaPlayer;
    private final MediaPlayer sfxPlayer;
    String bip=new File("src/Resources/music/smile.mp3").toURI().toString();
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

    public void btnVolumeClicked(ImageView btnVol)
    {
        btnVolume=btnVol;


        if(volume==true)
        {
            System.out.println("CURRENT VOLUME IS ON"+volume);
            btnVolume.setImage(new Image("Resources/Images/Icons/ButtonIcons/speaker-3.png"));
            mediaPlayer.setMute(true);
            Constance.isVolumeON=false;
        }

        else if(volume==false)
        {
            System.out.println("CURRENT VOLUME IS OFF"+volume);
            btnVolume.setImage(new Image("Resources/Images/Icons/ButtonIcons/speaker-2.png"));
            Constance.isVolumeON=true;
            mediaPlayer.setMute(false);
        }

    }

}
