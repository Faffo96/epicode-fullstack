package multimediaFiles;

public class Img extends MultimediaFile implements Brightness {
    private String title;
    private int brightness;

    public Img(String title) {
        this.title = title;
        this.brightness = 5;
    }

    @Override
    public int getBrightness() {
        return this.brightness;
    }

    @Override
    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

    @Override
    public void lightUp() {
        this.brightness += 1;
    }

    @Override
    public void lightDown() {
        this.brightness -= 1;
    }

    public void show() {
        String brightnessSign = "";
        for (int i = 0; i < this.brightness ; i++) {
            brightnessSign = brightnessSign + "*";
        }

        System.out.println("Image Title: " + this.title + " - Brightness: " + brightnessSign);
    }
}
