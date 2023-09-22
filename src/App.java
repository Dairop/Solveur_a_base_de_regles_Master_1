public class App {
    public static void main(String[] args) throws Exception {

<<<<<<< HEAD
        testChainageAvant();
        //System.out.println("\n\n");
        //testChainageArriere();
=======
        //System.out.println(new Regle("R1 : a -> C ET b").equals(new Regle("R2 : a -> b ET C")));
        ChainageParPaquetTest();
>>>>>>> 9ab62acde84311f870eb0f4151d3b7b03e66a1cc
    }


    static void testChainageAvant(){
        BaseDeFaits bf = new BaseDeFaits();
        bf.ajouterFait(new Element("a(A)"));
        bf.ajouterFait(new Element("b"));

        BaseDeRegles br = new BaseDeRegles();
<<<<<<< HEAD
        br.ajouterRègle(new Regle("R1 : a(A) -> !b"));
        br.ajouterRègle(new Regle("R2 : b -> a"));
        br.ajouterRègle(new Regle("R3 : a ET b -> d"));
        br.ajouterRègle(new Regle("R4 : a ET b -> d"));
        br.ajouterRègle(new Regle("R5 : a ET d -> E"));
        br.ajouterRègle(new Regle("R6 : E -> a"));
        br.ajouterRègle(new Regle("R7 : !!d ET E -> !a"));
=======

        br.ajouterRègle(new Regle("R1 : a -> b"));
        br.ajouterRègle(new Regle("R2 : b -> c"));
        br.ajouterRègle(new Regle("R3 : a ET b -> c"));
        br.ajouterRègle(new Regle("R4 : a ET c -> d"));
>>>>>>> 9ab62acde84311f870eb0f4151d3b7b03e66a1cc

        
        Moteur moteur = new Moteur(bf, br, new ChainageAvant());
        moteur.executer();
    }


    static void ChainageParPaquetTest(){
        BaseDeFaits bf = new BaseDeFaits();
        bf.ajouterFait(new Element("a"));
        bf.ajouterFait(new Element("b"));

        BaseDeRegles br = new BaseDeRegles();

        br.ajouterRègle(new Regle("R1 : a -> b"));
        br.ajouterRègle(new Regle("R2 : b -> c"));
        br.ajouterRègle(new Regle("R3 : a ET b -> c"));
        br.ajouterRègle(new Regle("R4 : a ET c -> d"));

        
        Moteur moteur = new Moteur(bf, br, new ChainageParPaquet());
        moteur.executer();
    }


    static void testChainageArriere(){
        BaseDeFaits bf = new BaseDeFaits();
        bf.ajouterFait(new Element("a"));
        bf.ajouterFait(new Element("b"));

        BaseDeRegles br = new BaseDeRegles();
        br.ajouterRègle(new Regle("R1 : a -> !b"));
        br.ajouterRègle(new Regle("R2 : a -> e"));
        br.ajouterRègle(new Regle("R3 : a ET b -> c"));
        br.ajouterRègle(new Regle("R4 : c ET e -> !a"));
        br.ajouterRègle(new Regle("R5 : a ET d -> E"));
        br.ajouterRègle(new Regle("R6 : E -> a"));

        ChainageArriere ca = new ChainageArriere();
        ca.setObjectif(new Element("E"));
        Moteur moteur = new Moteur(bf, br, ca);
        moteur.executer();
    }
}
