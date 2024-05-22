package multimediaFiles;

public class Audio extends MultimediaFile implements Reproducible {
    private String title;
    private int duration;
    private int volume;

    public Audio(String title, int duration) {
        this.title = title;
        this.duration = duration;
        this.volume = 5;
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
        return this.duration;
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

        for (int i = 0; i < this.duration; i++) {
            System.out.println("Video Title: " + this.title + " - Volume: " + volumeSign);
        }
    }

    @Override
    public void pause() {

    }
}
