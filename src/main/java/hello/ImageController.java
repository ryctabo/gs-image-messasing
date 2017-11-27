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
    public String greeting(byte[] bytes) {
        LOGGER.info("Get image from bytes array.");
        String response = Base64.encodeBase64String(bytes);
        LOGGER.fine(response);
        return response;
    }

}
