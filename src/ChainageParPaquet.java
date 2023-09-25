import java.util.ArrayList;

public class ChainageParPaquet implements Strategie{

    ArrayList<ArrayList<String>> _blocs = new ArrayList<>();

    @Override
    public void executer(BaseDeFaits baseDeFaits, BaseDeRegles baseDeRegles, boolean trace) {
        BaseDeFaits baseDeFaitTampon = BaseDeFaits.copy(baseDeFaits);
        BaseDeRegles baseDeReglesTampon = BaseDeRegles.copy(baseDeRegles);

        //on va remplir les blocs
        boolean encoreDesBlocks = true;
        int numeroBlock = 0;
        while(encoreDesBlocks){
            ArrayList<String> blockAcompleter = new ArrayList<>();
            numeroBlock++;
            String reponse = Moteur.lireReponse("Block numéro : "+numeroBlock + ". Mettez le nom des règles dans ce block séparés d'un ';'\nExemple : R1;R2;modusPonens;\nSi c'est le dernier block, à la fin du champ, mettez un ';' \nPar exemple : 'R1;'' OU 'r2;modus ponens;'\nSi ce n'est pas le dernier : 'R1;R2; ma règle R3' :");
            //si on a comme dernier caractères ; alors c'est le dernier blocs
            encoreDesBlocks = !(reponse.charAt(reponse.length()-1) == ';');
            String[] regles = reponse.split(";");
            for (int i = 0; i < regles.length;i++)
                blockAcompleter.add(regles[i].trim());

            _blocs.add(blockAcompleter);
            
        }
        
        Moteur.print("\n\nOn a les blocs suivants : \n"+_blocs.toString());

        Chronometre.start();
        //on fais les calculs sur tous les blocs
        for (int i = 0;i < _blocs.size();i++){
            for (int j = 0; j < _blocs.get(i).size();j++){
                Regle r = baseDeReglesTampon.avoirRegleParNom(_blocs.get(i).get(j));
                
                //Si aucune règle avec ce nom, on laisse l'utilisateur choisir
                if (r == null){
                    String reponse = Moteur.lireReponse("La règle "+_blocs.get(i).get(j) +" n'existe pas.\n1:Passer la règle\n2:Renommer la règle\n3:Arrêter le programme");
                    if (reponse.contains("1"))
                        continue;
                    else if (reponse.contains("2")){
                        while(baseDeReglesTampon.avoirRegleParNom(reponse) == null)
                            reponse = Moteur.lireReponse("\n\nLa règle de nom : '"+_blocs.get(i).get(j)+"' n'existe pas.\n.On a la base de règles : "+baseDeReglesTampon.toString()+"\nChoisir la règle (nom) :").trim();
                        _blocs.get(i).set(j, reponse);
                    }else
                        return;
                    
                }
                r = baseDeReglesTampon.avoirRegleParNom(_blocs.get(i).get(j));

                //ici on va vérifier si la base de faits vérifie les prémices de la règle
                boolean regleUtile = true;
                for (int k = 0; k < r.taillePremice();k++){
                    if (!baseDeFaitTampon.contient(r.avoirPremiceParIndice(k))){
                        Moteur.lireReponse(" On a : "+r.avoirPremiceParIndice(k).toString()+"qui marche pas.\n");
                        regleUtile = false;
                    }
                }

                //si la règle peut etre utilisé on ajoute toutes ses conséquents à la base de faits
                if (regleUtile){
                    String message = "La règle est utilisable : "+r.toString()+"\n On rajoute donc les conséquents : ";
                    for (int k = 0; k < r.tailleConsequent();k++){
                        if (!baseDeFaitTampon.contient(r.avoirConsequentParIndice(k))){
                            message+=" ; "+r.avoirConsequentParIndice(k).toString();
                            baseDeFaitTampon.ajouterFait(r.avoirConsequentParIndice(k).clone());
                        }
                    }
                    if (trace)
                        Moteur.print(message);
                }
                
            }
        }
        Chronometre.stop();

        Moteur.print("Chainage par paquets réussi avec les paquets : "+_blocs.toString()+"\nNouvelle base de faits : \n"+baseDeFaitTampon.toString()+" en "+Chronometre.time()+" ms");
    }


}