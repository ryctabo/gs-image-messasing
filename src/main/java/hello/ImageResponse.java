package hello;

public class ImageResponse {

    private String imageSrc;

    private String message;

    public ImageResponse() {
        this.message = "It's OK!";
    }

    public ImageResponse(String imageSrc) {
        this.imageSrc = imageSrc;
        this.message = "It's OK!";
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
