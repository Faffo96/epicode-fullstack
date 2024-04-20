public class UsaPersona {

    public static void main(String[] args) {
        Persona p = new Persona("Roberto","Calabria", 23, TipoLavoro.PROGRAMMATORE);
        System.out.println(p.getNome());
        //System.out.println(p.cognome);
        //System.out.println(p.eta);

        System.out.println(p.contatore);

        Persona p2 = new Persona("Carla", "Franchi");
        System.out.println(p2.getNome());
        //System.out.println(p2.cognome);
        //System.out.println(p2.eta);


        System.out.println(p2.contatore);

        Persona p3 = new Persona("Carla", "Franchi");

        System.out.println(Persona.contatore);
        System.out.println(Persona.contatore);
        System.out.println(Persona.contatore);

        p=null;

        p=p2;

        p.setNome("Flavia");

        System.out.println(p2.getNome());

        p2=null;

        System.out.println(p.getNome());

        //System.out.println(p2.nome);

        p2=p;

        System.out.println(p2.getNome());

        System.out.println(p==p2);

        System.out.println(p.equals(p2));

        p2 = new Persona("Roberto", "Giani");

        System.out.println(p2.getNome());

        System.out.println(p==p2);

        System.out.println(p.equals(p2));


    }
}
