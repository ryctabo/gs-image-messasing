package hello;

import org.apache.commons.codec.binary.Base64;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.logging.Logger;

@Controller
public class ImageController {

    private static final Logger LOGGER = Logger.getLogger(ImageController.class.getName());

    private static final String FORMAT = "data:image/png;base64,%s";

    @MessageMapping("/image")
    @SendTo("/topic/images")
    public ImageResponse greeting(String msg) {
        LOGGER.info("Get image with this format: " + msg);
        String[] byteValues = msg.substring(1, msg.length() - 1).split(",");
        byte[] bytes = new byte[byteValues.length];

        for (int i = 0; i < byteValues.length; i++)
            bytes[i] = Byte.parseByte(byteValues[i].trim());

        byte[] imageBase64 = Base64.encodeBase64(bytes);
        LOGGER.info("Get response format.");
        String response = String.format(FORMAT, new String(imageBase64));
        LOGGER.info("Response: " + response.substring(0, 50));
        return new ImageResponse(response);
    }

}
