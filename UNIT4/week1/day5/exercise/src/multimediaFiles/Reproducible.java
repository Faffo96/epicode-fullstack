package multimediaFiles;

public interface Reproducible {
    int getVolume();
    void setVolume(int volume);
    int getDuration();
    void setDuration(int duration);

    void volumeUp();
    void volumeDown();
    void play();
    void pause();
}
