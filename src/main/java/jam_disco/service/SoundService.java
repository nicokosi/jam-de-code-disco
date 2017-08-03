package jam_disco.service;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SoundService {

   private final int EXTERNAL_BUFFER_SIZE = 524288; // 128Kb

   public String sound() throws Exception {
      try (InputStream file = getClass().getResourceAsStream("drum.wav")) {
         AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
         AudioFormat audioFormat = audioInputStream.getFormat();
         SourceDataLine line = null;
         DataLine.Info info = new DataLine.Info(SourceDataLine.class,
               audioFormat);
         line = (SourceDataLine) AudioSystem.getLine(info);
         line.open(audioFormat);
         line.start();

         int nBytesRead = 0;
         byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];
         while (nBytesRead != -1) {
            nBytesRead = audioInputStream.read(abData, 0, abData.length);
            int nBytesWritten = line.write(abData, 0, nBytesRead);
         }
         line.drain();
         line.close();
      }
   }

}