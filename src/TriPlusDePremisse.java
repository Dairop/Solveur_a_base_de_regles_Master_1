public class TriPlusDePremisse extends Tri{

    public BaseDeRegles trier(BaseDeRegles br, BaseDeFaits bf) {
        br.trierPlusDePremices();
        //Moteur.print(br.toString());
        return br;
    }
    
}
