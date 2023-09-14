public class App {
    public static void main(String[] args) throws Exception {

        System.out.println("Hello, World !");

        BaseDeFaits bf = new BaseDeFaits();
        bf.ajouterFait(new Element("a", false));

        BaseDeRegles br = new BaseDeRegles();
        br.ajouterRègle(new Regle("R1 : a -> b"));
        br.ajouterRègle(new Regle("R2 : b-> c"));
    
        BaseDeFaits bfCopie = BaseDeFaits.copy(bf);


        //bf.retireFait(new Element("a"));

        

        System.out.println(bfCopie.avoirValeurFait("a"));
        System.out.println("---------------");
        System.out.println(bf.toString());

        Moteur moteur = new Moteur(bf, br, new ChainageAvant());
        //moteur.executer();
    }


}
