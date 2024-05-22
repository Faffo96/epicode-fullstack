package multimediaFiles;

public class Video extends MultimediaFile implements Reproducible, Brightness {
    private String title;
    private int duration;
    private int brightness;
    private int volume;

    public Video(String title, int duration) {
        this.title = title;
        this.duration = duration;
        this.brightness = 5;
        this.volume = 5;
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
    public int getVolume() {
        return this.volume;
    }

    @Override
    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public void volumeUp() {
        this.volume += 1;
    }

    @Override
    public void volumeDown() {
        this.volume -= 1;
    }

    @Override
    public void play() {
        String volumeSign = "";
        for (int i = 0; i < this.volume ; i++) {
            volumeSign = volumeSign + "!";
        }

        String brightnessSign = "";
        for (int i = 0; i < this.brightness ; i++) {
            brightnessSign = brightnessSign + "*";
        }

        for (int i = 0; i < this.duration; i++) {
            System.out.println("Video Title: " + this.title + " - Volume: " + volumeSign + " - Brightness: " + brightnessSign);
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void lightUp() {
        this.brightness += 1;
    }

    @Override
    public void lightDown() {
        this.brightness -= 1;
    }
}
