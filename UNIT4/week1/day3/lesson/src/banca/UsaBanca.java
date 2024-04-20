package banca;

public class UsaBanca {
    public static void main(String[] args) {
        ContoBancario contoBancario = new ContoBancario("32342342");

        System.out.println("Il numero del conto è "+contoBancario.getNumero());
        System.out.println("Il bilancio del conto è " + contoBancario.getBilancio());

        contoBancario.deposita(500);

        System.out.println("Il bilancio del conto è " + contoBancario.getBilancio());

        boolean risultatoPrelievo = contoBancario.prelievo(600);

        System.out.println("Il risultato del prelievo è "+risultatoPrelievo);
        System.out.println("Il bilancio del conto è " + contoBancario.getBilancio());

        risultatoPrelievo = contoBancario.prelievo(450);

        System.out.println("Il risultato del prelievo è "+risultatoPrelievo);
        System.out.println("Il bilancio del conto è " + contoBancario.getBilancio());

    }

}
