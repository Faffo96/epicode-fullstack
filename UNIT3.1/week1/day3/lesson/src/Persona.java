public class Persona {

    private String nome;
    private String cognome;
    private int eta;

    public TipoLavoro tipoLavoro;

    public static int contatore;

    public Persona(String nome, String cognome, int eta, TipoLavoro tipoLavoro){
        this.nome=nome;
        this.cognome=cognome;
        this.eta=eta;
        this.tipoLavoro=tipoLavoro;
        contatore++;
    }

    public Persona(String nome, String cognome){
        this.nome=nome;
        this.cognome=cognome;
        contatore++;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome=nome;
    }
}
