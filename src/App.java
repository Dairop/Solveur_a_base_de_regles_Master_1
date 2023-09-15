public class App {
    public static void main(String[] args) throws Exception {

        testChainageAvant();
        testChainageArriere();
    }


    static void testChainageAvant(){
        BaseDeFaits bf = new BaseDeFaits();
        bf.ajouterFait(new Element("a"));
        bf.ajouterFait(new Element("b"));

        BaseDeRegles br = new BaseDeRegles();
        br.ajouterRègle(new Regle("R1 : a -> !b"));
        br.ajouterRègle(new Regle("R2 : a-> b"));
        br.ajouterRègle(new Regle("R3 : a ET b -> d"));
        br.ajouterRègle(new Regle("R4 : a ET b -> d"));
        br.ajouterRègle(new Regle("R5 : a ET d -> E"));
        br.ajouterRègle(new Regle("R6 : E -> a"));
        br.ajouterRègle(new Regle("R7 : !!d ET E -> !a"));

        
        Moteur moteur = new Moteur(bf, br, new ChainageAvant());
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
        br.ajouterRègle(new Regle("R4 : c ET e -> !d"));
        br.ajouterRègle(new Regle("R5 : a ET d -> E"));
        br.ajouterRègle(new Regle("R6 : E -> a"));

        ChainageArriere ca = new ChainageArriere();
        ca.setObjectif(new Element("E"));
        Moteur moteur = new Moteur(bf, br, ca);
        moteur.executer();
    }
}
