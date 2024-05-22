import java.util.*;
import java.util.stream.Collectors;

public class ProvaStream {
    public static void main(String[] args) {
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

        //creo una lista con i prezzi dei prodotti
        List<Double> l1 = prodotti.stream().map(prodotto -> prodotto.getPrezzo()).collect(Collectors.toList());
        l1.forEach(n -> System.out.println(n));

        System.out.println();

        //creo un set di prezzi dei prodotti(elimino i duplicati dei prezzi rispetto all'esempio precedente)
        Set<Double> s1 = prodotti.stream().map(prodotto -> prodotto.getPrezzo()).collect(Collectors.toSet());
        s1.forEach(n -> System.out.println(n));

        System.out.println();

        //creo una mappa che contiene coppie costituite da id del prodotto e prezzo del prodotto
        //Prodotto::getId è un method reference e sostituisce l'espressione Lambda p->p.getId()
        Map<Long, Double> m1 = prodotti.stream().
                collect(Collectors.toMap(Prodotto::getId, Prodotto::getPrezzo));
        m1.forEach((id, valore) -> System.out.println(id + " " + valore));

        System.out.println();
        //creo una mappa usando il groupingby che raggruppa per categoria di prodotti
        Map<String, List<Prodotto>> m2 =  prodotti.stream().
                collect(Collectors.groupingBy(Prodotto::getCategoria));
        m2.forEach((id, valore) -> System.out.println(id + " " + valore));

        System.out.println();

        //creo una mappa attraverso la grouping by con cui raggruppo per categoria e
        //conto quanti prodotti ci sono per categoria
        Map<String, Long> m3 = prodotti.stream().collect(Collectors.
                groupingBy(Prodotto::getCategoria, Collectors.counting()));
        m3.forEach((id, valore) -> System.out.println(id + " " + valore));

        System.out.println();

        //creo una mappa dal groupingby con cui raggruppo per categoria e per ogni categoria
        //calcolo la media dei prezzi dei prodotti
        Map<String, Double> m4 = prodotti.stream().collect(Collectors.groupingBy
                (Prodotto::getCategoria, Collectors.averagingDouble(Prodotto::getPrezzo)));
        m4.forEach((id, valore) -> System.out.println(id + " " + valore));

        System.out.println();

        //creo una mappa da groupingby con cui raggruppo per categoria e calcolo per ogni
        //categoria la somma dei prezzi dei prodotti di quella categoria
        Map<String, Double> m5 =prodotti.stream().collect(Collectors.groupingBy
                (Prodotto::getCategoria, Collectors.summingDouble(Prodotto::getPrezzo)));
        m5.forEach((id, valore) -> System.out.println(id + " " + valore));

        System.out.println();

        //calcolo la somma di tutti i prezzi dei prodotti usando summingDouble di collect
        double somma = prodotti.stream().map(Prodotto::getPrezzo).
                collect(Collectors.summingDouble(Double::doubleValue));

        System.out.println(somma);

        //calcolo la media di tutti i prezzi dei prodotti usando averagingDouble di collect
        double media = prodotti.stream().map(Prodotto::getPrezzo).
                collect(Collectors.averagingDouble(Double::doubleValue));

        System.out.println(media);

        //calcolo la statistica di tutti i prezzi dei prodotti usando summarizingDouble di collect
        //dalla statistica otterrò vari dati tra cui somme, medie, max, min, count
        DoubleSummaryStatistics stat = prodotti.stream().map(Prodotto::getPrezzo).
                collect(Collectors.summarizingDouble(Double::doubleValue));

        System.out.println(stat);

        System.out.println();

        //creo una mappa partizionando il contenuto dello stream in 2 partizioni
        //una partizione verifica la condizione del partitioningBy, l'altra no
        //il tutto viene messo in una mappa
        Map<Boolean, List<Prodotto>> m6= prodotti.stream().
                collect(Collectors.partitioningBy(p->p.getPrezzo()<50));
        m6.forEach((id, valore) -> System.out.println(id + " " + valore));

        System.out.println();

        //calcolo la somma dei prezzi di prodotti dello stream con reducing
        double somma2 = prodotti.stream().map(Prodotto::getPrezzo).
                collect(Collectors.reducing
                        (0.0, (s, elemento)->(s+elemento.doubleValue())));
        System.out.println(somma2);

        System.out.println();

        //ordino lo stream di prodotti secondo l'ordine decrescente del prezzo
        prodotti.stream().sorted(Comparator.comparingDouble(Prodotto::getPrezzo).reversed()).
                forEach(System.out::println);

        System.out.println();

        double somma3 = prodotti.stream().mapToDouble(Prodotto::getPrezzo).sum();
        System.out.println(somma3);

        System.out.println();

        //calcolo il massimo dei prezzi dei prodotti con il metodo mapToDouble
        OptionalDouble max= prodotti.stream().mapToDouble(Prodotto::getPrezzo).max();
        System.out.println(max.getAsDouble());

    }
}
