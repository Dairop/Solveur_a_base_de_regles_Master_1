public class Moteur {
    private BaseDeFaits _baseDeFaits = new BaseDeFaits();
    private BaseDeRegles _baseDeRegles = new BaseDeRegles();
    private Strategie _strategie = null;

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
        this._strategie.executer(_baseDeFaits, _baseDeRegles);
    }

    //setter Strategie

    public String toString(){
        return this._baseDeFaits.toString() + " " + this._baseDeRegles.toString();
    }
}
