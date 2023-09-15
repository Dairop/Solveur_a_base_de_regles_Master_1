public class App {
    public static void main(String[] args) throws Exception {

        testChainageAvant();
    }


    static void testChainageAvant(){
        BaseDeFaits bf = new BaseDeFaits();
        bf.ajouterFait(new Element("a"));
        bf.ajouterFait(new Element("!b"));
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
}
