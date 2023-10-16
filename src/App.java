public class App implements Runnable{
    public static void main(String[] args) throws Exception {
        
        //System.out.println("\n\n");
        //testChainageArriere();

        Graphism.lancer();
        new Thread(new App()).start();
    }


    static void testChainageAvant(){
        BaseDeFaits bf = new BaseDeFaits();
        /*
        bf.ajouterFait(new Element("coherent(lent, tortue)"));
        bf.ajouterFait(new Element("tortue"));
        bf.ajouterFait(new Element("carapace(tortue)"));
        bf.ajouterFait(new Element("!carapace(oiseau)"));
        */

        BaseDeRegles bre = new BaseDeRegles();
        /*
        bre.ajouterRègle(new Regle("R1 : carapace(Animal) -> !ailes ET lent"));
        bre.ajouterRègle(new Regle("R2 : tortue -> lent"));
        bre.ajouterRègle(new Regle("R3 : coherent(lent, Animal) -> !oiseau"));
        */

        //ça serait bien d'avoir une méthode plus simple pour en créer
        //HashMap<String, Variable> variables = new HashMap<>();

        /*
        ArrayList<String> valeursPossibles1 = new ArrayList<String>();
        valeursPossibles1.add("lent"); valeursPossibles1.add("moyen"); valeursPossibles1.add("rapide");
        variables.put("Vitesse", new Variable("Vitesse", valeursPossibles1));
        
        ArrayList<String> valeursPossiblesL = new ArrayList<String>();
        valeursPossiblesL.add("oiseau"); valeursPossiblesL.add("tortue");
        variables.put("Animal", new Variable("Animal", valeursPossiblesL));
        */
        
       
    }


    @Override
    public void run() {
        //testChainageAvant();
    }
}
