public class App {
    public static void main(String[] args) throws Exception {


        BaseDeFaits bf = new BaseDeFaits();
        bf.ajouterFait(new Element("a"));

        BaseDeRegles br = new BaseDeRegles();
        br.ajouterRègle(new Regle("R1 : a -> b"));
        br.ajouterRègle(new Regle("R2 : b-> c"));
    
        BaseDeFaits bfCopie = BaseDeFaits.copy(bf);


        //bf.retireFait(new Element("a"));

        

        
        Moteur moteur = new Moteur(bf, br, new ChainageAvant());
        moteur.executer();
    }


}
