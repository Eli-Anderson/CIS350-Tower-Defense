import sun.audio.*;

import java.io.FileInputStream;
import java.io.IOException;

public class GameMusic {

    public GameMusic (){
        AudioPlayer musicPlayer = AudioPlayer.player;
        AudioStream BGM;
        AudioData MusicData;

        ContinuousAudioDataStream loop = null;

        try {
            BGM = new AudioStream(new FileInputStream("Firefly-Jim_Yosef.mp3.aac"));
            MusicData = BGM.getData();
            loop = new ContinuousAudioDataStream(MusicData);

        }catch(IOException e){

        }

        musicPlayer.start(loop);

    }
}
