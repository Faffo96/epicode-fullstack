import org.apache.commons.io.FileUtils;

import javax.imageio.IIOException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

public class ProvaCommonsIO {
    public static void main(String[] args) {
        File file = new File("./../persistence/newFile.txt");
        try {
            FileUtils.writeStringToFile(file, "Epicode\n", Charset.defaultCharset(), true);

            System.out.println(FileUtils.readFileToString(file, Charset.defaultCharset()));

            FileUtils.deleteQuietly(file);


        }catch (IOException e){
            System.out.println(e.getMessage());
        }

        Prodotto libro1 = new Prodotto(100l, "Il signore degli anelli", "Libri", 101);
        Prodotto libro2 = new Prodotto(101L, "Il giorno nero", "Libri", 102);
        Prodotto libro3 = new Prodotto(102L, "Collision", "Libri", 10);
        Prodotto libro4 = new Prodotto(103L, "Marracash", "Libri", 13);
        Prodotto baby1 = new Prodotto(104L, "pannolini", "Baby", 7);
        Prodotto baby2 = new Prodotto(105L, "tutina", "Baby", 15);
        Prodotto baby3 = new Prodotto(106L, "cappello", "Baby", 10);
        Prodotto boys1 = new Prodotto(107L, "scarpe", "Boys", 70);
        Prodotto boys2 = new Prodotto(108L, "felpa", "Boys", 40);
        Prodotto boys3 = new Prodotto(109L, "pantalone", "Boys", 40);

        List<Prodotto> prodotti = List.of(libro1, libro2,libro3,libro4,boys1,boys2,boys3,baby1,baby2,baby3);

        prodotti.stream().map(prodotto -> prodotto.getId().longValue()+"@"+prodotto.getNome()+"@"
        +prodotto.getCategoria()+"@"+ prodotto.getPrezzo()).forEach(s -> System.out.println(s));



    }
}
