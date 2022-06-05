function [vitesseA, vitesseB, vitesseAngA, vitesseAngB] = ...
    VitessesApresCollision(voitureA,voitureB,normale,pointContact)
%VITESSESAPRESCOLLISION Calcule les vitesses apr�es collision selon la
%m�thode des conditions initiales

    % On pourrait mettre epsilon en param�tre, mais il poss�de toujours la
    % m�me valeur pour le TP3.
    epsilon = 0.8;

    % Distances des centres de masse au point de contact
    rap = [pointContact - voitureA.Position 0];
    rbp = [pointContact - voitureB.Position 0];
    
    % Vitesses initiales
    va = [voitureA.Vitesse 0];
    vb = [voitureB.Vitesse 0];
    wa = [0 0 voitureA.VitesseAngulaire];
    wb = [0 0 voitureB.VitesseAngulaire];

    % Formules trouv�e au Chapitre 5, p.32
    vap = va + cross(wa, rap);
    vbp = vb + cross(wb, rbp);
    vab = vap - vbp;
    
    % Formules trouv�es au Chapitre 5, p.33
    % Contrairement � la normale fournie, qui est dirig�e vers l'ext�rieur
    % du solide, nous avons ici besoin de la normale dirig�e vers
    % l'int�rieur du solide.
    n = -normale;
    vr_minus = dot(n, vab);
    
    % Formules trouv�es au Chapitre 5, p.37
    j = -(1 + epsilon)*vr_minus / ((1/voitureA.Masse) + (1/voitureB.Masse));
    
    % Formules trouv�es au Chapitre 5, p.38
    vitesseA = va + ((n * j)/voitureA.Masse);
    vitesseB = vb - ((n * j)/voitureB.Masse);

    vitesseAngA = wa + (j * cross(rap, n) / voitureA.MomentInertie);
    vitesseAngB = wb - (j * cross(rbp, n) / voitureB.MomentInertie);
end

