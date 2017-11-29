package hello;

import org.apache.commons.codec.binary.Base64;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.BinaryMessage;

import java.util.logging.Logger;

@Controller
public class ImageController {

    private static final Logger LOGGER = Logger.getLogger(ImageController.class.getName());

    private static final String FORMAT = "data:image/png;base64,%s";

    @MessageMapping("/image")
    @SendTo("/topic/images")
    public BinaryMessage greeting(String msg) {
        LOGGER.info("Get image and convert in Base64.");
        byte[] imageBase64 = Base64.encodeBase64(msg.getBytes());
        LOGGER.info("Get response format.");
        String response = String.format(FORMAT, new String(imageBase64));
        LOGGER.info("Response: " + response.substring(0, 50));
        return new BinaryMessage(response.getBytes());
    }

}
