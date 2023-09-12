public class App {
    public static void main(String[] args) throws Exception {
        Element e1 = new Element("nomFun", true);
        Element e2 = new Element("nomMoinsFunl", false);

        BaseDeFaits b1 = new BaseDeFaits();
        b1.ajouterFait(e1);
        b1.ajouterFait(e2);

        System.out.println(b1.toString());

        b1.retireFait(e2);
        System.out.println(b1.toString());

    }
}
