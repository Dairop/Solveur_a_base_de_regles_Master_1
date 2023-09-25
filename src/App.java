public class App {
    public static void main(String[] args) throws Exception {

        testChainageAvant();
        //System.out.println("\n\n");
        //testChainageArriere();
    }


    static void testChainageAvant(){
        BaseDeFaits bf = new BaseDeFaits();
        bf.ajouterFait(new Element("a(h0, az)"));
        bf.ajouterFait(new Element("b"));
        bf.ajouterFait(new Element("c(az)"));


        BaseDeRegles br = new BaseDeRegles();
        br.ajouterRègle(new Regle("R1 : a(B) -> !d ET f(B)"));
        br.ajouterRègle(new Regle("R2 : a -> e(B)"));
        br.ajouterRègle(new Regle("R3 : a(B, !d) ET b -> c"));
        
        MoteurZeroPlus moteur = new MoteurZeroPlus(bf, br, new ChainageAvant());
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
        br.ajouterRègle(new Regle("R4 : c -> d"));
        br.ajouterRègle(new Regle("R5 : a ET d -> E"));
        br.ajouterRègle(new Regle("R6 : E -> a"));

        ChainageArriere ca = new ChainageArriere();
        ca.setObjectif(new Element("E"));
        Moteur moteur = new Moteur(bf, br, ca);
        moteur.executer();
    }
}
