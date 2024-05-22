
import multimediaFiles.Audio;
import multimediaFiles.Img;
import multimediaFiles.MultimediaFile;
import multimediaFiles.Video;
import java.util.Scanner;

public class Main {
            public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);
                MultimediaFile[] folder = new MultimediaFile[5];

                for (int i = 0; i < folder.length; i++) {
                    System.out.println("Che tipo di file multimediale vuoi creare?");
                    System.out.println("1. Img");
                    System.out.println("2. Video");
                    System.out.println("3. Audio");

                    int scelta = scanner.nextInt();
                    scanner.nextLine();

                    switch (scelta) {
                        case 1:
                            System.out.println("Inserisci il nome dell'immagine:");
                            String nomeImg = scanner.nextLine();
                            folder[i] = new Img(nomeImg);
                            break;
                        case 2:
                            System.out.println("Inserisci il titolo del video:");
                            String titoloVideo = scanner.nextLine();
                            System.out.println("Inserisci la durata del video:");
                            int durataVideo = scanner.nextInt();
                            folder[i] = new Video(titoloVideo, durataVideo);
                            break;
                        case 3:
                            System.out.println("Inserisci il titolo dell'audio:");
                            String titoloAudio = scanner.nextLine();
                            System.out.println("Inserisci la durata dell'audio:");
                            int durataAudio = scanner.nextInt();
                            folder[i] = new Audio(titoloAudio, durataAudio);
                            break;
                        default:
                            System.out.println("Scelta non valida.");
                            i--; // Ripeti l'iterazione per richiedere nuovamente l'input
                    }
                }

                for (int i = 0; i < folder.length; i++) {
                    System.out.println("File " + (i + 1) + ":");

                    if (folder[i] instanceof Img) {
                        ((Img) folder[i]).show();
                    } else if (folder[i] instanceof Video) {
                        ((Video) folder[i]).play();
                    } else if (folder[i] instanceof Audio) {
                        ((Audio) folder[i]).play();
                    }
                }

                scanner.close();
            }
}