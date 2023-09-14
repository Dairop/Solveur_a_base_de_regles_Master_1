import java.util.Scanner;

import javax.swing.JOptionPane;

public class Moteur {
    private BaseDeFaits _baseDeFaits = new BaseDeFaits();
    private BaseDeRegles _baseDeRegles = new BaseDeRegles();
    private Strategie _strategie = null;

    private static boolean enConsole = false;

    public Moteur(){}

    public Moteur(BaseDeFaits baseDeFaits, BaseDeRegles baseDeRegles){
        this._baseDeFaits = baseDeFaits;
        this._baseDeRegles = baseDeRegles;
    }

    public Moteur(BaseDeFaits baseDeFaits, BaseDeRegles baseDeRegles, Strategie strategie){
        this._baseDeFaits = baseDeFaits;
        this._baseDeRegles = baseDeRegles;
        this._strategie = strategie;
    }

    public void executer(){
        try {
            verifierIncoherences();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this._strategie == null) return;
        this._strategie.executer(_baseDeFaits, _baseDeRegles);
    }

    //setter Strategie

    public String toString(){
        return this._baseDeFaits.toString() + " " + this._baseDeRegles.toString();
    }


    public void verifierIncoherences() throws Exception{
        _baseDeRegles.verifierIncoherences();
        _baseDeFaits.verifierIncoherences();    
    }

    public static void print(String msg) {
        if (enConsole)
            System.out.println(msg);
        else
            JOptionPane.showMessageDialog(null,msg);
    }

    public static String lireReponse(String msg) {
        if (enConsole){
            System.out.println(msg);
            Scanner sc = new Scanner(System.in);
            String reponse = sc.nextLine();
            sc.close();
            return reponse;
        }
        else
            return JOptionPane.showInputDialog(msg);
    }


}
