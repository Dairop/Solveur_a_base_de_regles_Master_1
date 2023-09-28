public class App implements Runnable{
    public static void main(String[] args) throws Exception {
        
        //System.out.println("\n\n");
        //testChainageArriere();

        Graphism.lancer();
        new Thread(new App()).start();
    }


    static void testChainageAvant(){



        BaseDeFaits bf = new BaseDeFaits();
        bf.ajouterFait(new Element("stupide(Dorian)"));
        bf.ajouterFait(new Element("beau(Dorian)"));
        bf.ajouterFait(new Element("giovanni(Test)"));


        BaseDeRegles br = new BaseDeRegles();
        br.ajouterRègle(new Regle("R1 : stupide(B) -> stupidePLUS(B)"));
        //br.ajouterRègle(new Regle("R2 : a -> e(B)"));
        //br.ajouterRègle(new Regle("R3 : a(B, !d) ET b -> c"));

        /* 
        BaseDeFaits bf = new BaseDeFaits();
        bf.ajouterFait(new Element("a(h0, az)"));
        bf.ajouterFait(new Element("b"));
        bf.ajouterFait(new Element("c(az)"));


        BaseDeRegles br = new BaseDeRegles();
        br.ajouterRègle(new Regle("R1 : a(B) -> !d ET f(B)"));
        br.ajouterRègle(new Regle("R2 : a -> e(B)"));
        br.ajouterRègle(new Regle("R3 : a(B, !d) ET b -> c"));*/
        
        MoteurZeroPlus moteur = new MoteurZeroPlus(bf, br, new ChainageAvant());
        Moteur.executer(moteur);
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
        Moteur.executer(moteur);
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
        Moteur.executer(moteur);
    }


    @Override
    public void run() {
        testChainageAvant();
    }
}
