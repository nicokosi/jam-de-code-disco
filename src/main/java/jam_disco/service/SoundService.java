package jam_disco.service;


import java.io.File;
import org.openbase.jul.audio.AudioDataImpl;
import org.openbase.jul.audio.AudioPlayer;
import org.springframework.stereotype.Component;

@Component
public class SoundService {

   public void playSound() throws Exception {
      play("drum.wav");
   }

   private void play(String fileName) throws Exception {
      new AudioPlayer()
            .playAudio(new AudioDataImpl(
                  new File(
                        getClass().getResource("/" + fileName).getPath())), false);
   }

}
