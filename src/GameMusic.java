import sun.audio.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.FileInputStream;
import java.io.IOException;

public class GameMusic {

private AudioData MusicData;
private AudioPlayer musicPlayer = AudioPlayer.player;
private AudioStream BGM;
private ContinuousAudioDataStream loop = null;

    public GameMusic (){
        try {
            /*
            BGM = new AudioStream(new FileInputStream("resources/Firefly-Jim_Yosef.wav"));
            MusicData = BGM.getData();
            loop = new ContinuousAudioDataStream(MusicData);
            musicPlayer.start(loop); */

            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(SoundManager.class.getResourceAsStream("C://temp/my.mp3"));
            clip.open(inputStream);
            clip.start();

        }catch(IOException e){
            System.out.println(e);
        }

    }

    public void startMusic() {
        musicPlayer.start(loop);
    }
}
