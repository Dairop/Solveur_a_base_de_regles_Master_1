import java.util.ArrayList;
import java.util.HashMap;

public class App {
    public static void main(String[] args) throws Exception {

        testChainageAvant();
        //System.out.println("\n\n");
        //testChainageArriere();
    }


    static void testChainageAvant(){
        BaseDeFaits bf = new BaseDeFaits();
        bf.ajouterFait(new Element("coherent(lent, tortue)"));
        bf.ajouterFait(new Element("tortue"));
        bf.ajouterFait(new Element("carapace(tortue)"));
        bf.ajouterFait(new Element("!carapace(oiseau)"));


        BaseDeRegles bre = new BaseDeRegles();
        bre.ajouterRègle(new Regle("R1 : carapace(Animal) -> !ailes ET lent"));
        bre.ajouterRègle(new Regle("R2 : tortue -> lent"));
        bre.ajouterRègle(new Regle("R3 : coherent(lent, Animal) -> !oiseau"));


        //ça serait bien d'avoir une méthode plus simple pour en créer
        HashMap<String, Variable> variables = new HashMap<>();

        ArrayList<String> valeursPossibles1 = new ArrayList<String>();
        valeursPossibles1.add("lent"); valeursPossibles1.add("moyen"); valeursPossibles1.add("rapide");
        variables.put("Vitesse", new Variable("Vitesse", valeursPossibles1));
        
        ArrayList<String> valeursPossiblesL = new ArrayList<String>();
        valeursPossiblesL.add("oiseau"); valeursPossiblesL.add("tortue");
        variables.put("Animal", new Variable("Animal", valeursPossiblesL));



        MoteurZeroPlus moteur = new MoteurZeroPlus(bf, bre, new ChainageAvant(), variables, false);
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
