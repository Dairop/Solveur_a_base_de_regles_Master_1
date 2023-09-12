public class Moteur {
    BaseDeFaits _baseDeFaits = new BaseDeFaits();
    BaseDeRegles _baseDeRegles = new BaseDeRegles();
    Strategie _strategie = null;

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
        if (this._strategie == null) return;
    }

    //setter Strategie

    public String toString(){
        return this._baseDeFaits.toString() + " " + this._baseDeRegles.toString();
    }
}
