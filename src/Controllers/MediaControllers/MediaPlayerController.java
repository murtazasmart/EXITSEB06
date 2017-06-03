package Controllers.MediaControllers;

import Constances.Constance;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * Created by Bhagya Rathnayake on 6/1/2017.
 */
public class MediaPlayerController {

    boolean volume=Constance.isVolumeON;
    ImageView btnVolume;

    public void btnVolumeClicked(ImageView btnVol)
    {
        btnVolume=btnVol;


        if(volume==true)
        {
            System.out.println("CURRENT VOLUME IS ON"+volume);
            btnVolume.setImage(new Image("Images/Icons/ButtonIcons/speaker-3.png"));
            Constance.isVolumeON=false;
        }

        else if(volume==false)
        {
            System.out.println("CURRENT VOLUME IS OFF"+volume);
            btnVolume.setImage(new Image("Images/Icons/ButtonIcons/speaker-2.png"));
            Constance.isVolumeON=true;
        }

    }

}
