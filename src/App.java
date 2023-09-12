public class App {
    public static void main(String[] args) throws Exception {

        System.out.println("Hello, World !");


        BaseDeRegles br1 = new BaseDeRegles();
        br1.ajouterRègle(new Regle("R1 : Test -> C"));
        br1.ajouterRègle(new Regle("R2 : Test ET Giovanni ET Dorian-> C ET DORIAN"));
    
        System.out.println(br1.toString());
    }


}
