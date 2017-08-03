package jam_disco.web;

import jam_disco.service.HelloWorldService;
import jam_disco.service.SoundService;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

   @Autowired
   private HelloWorldService helloWorldService;

   @Autowired
   private SoundService soundService;

   @GetMapping("/")
   public String helloWorld() {
      return this.helloWorldService.getHelloMessage();
   }

   @GetMapping("/async")
   public Callable<String> helloWorldAsync() {
      return new Callable<String>() {
         @Override
         public String call() throws Exception {
            return "async: "
                  + SampleController.this.helloWorldService.getHelloMessage();
         }
      };
   }

   @GetMapping("/sound")
   public String sound() throws Exception {
      soundService.playSound();
      return "Played sound (maybe)";
   }

}
