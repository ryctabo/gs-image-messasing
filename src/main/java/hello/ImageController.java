package hello;

import org.apache.commons.codec.binary.Base64;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.logging.Logger;

@Controller
public class ImageController {

    private static final Logger LOGGER = Logger.getLogger(ImageController.class.getName());

    @MessageMapping("/image")
    @SendTo("topic/images")
    public String greeting(String msg) {
        LOGGER.info("Get image from bytes array.");
        String response = Base64.encodeBase64String(msg.getBytes());
        LOGGER.info("Convert image to Base64.");
        LOGGER.info("image -> " + response);
        return response;
    }

}
