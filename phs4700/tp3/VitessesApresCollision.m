function [vitesseA, vitesseB, vitesseAngA, vitesseAngB] = ...
    VitessesApresCollision(voitureA,voitureB,normale,pointContact)
%VITESSESAPRESCOLLISION Calcule les vitesses aprèes collision selon la
%méthode des conditions initiales

    % On pourrait mettre epsilon en paramètre, mais il possède toujours la
    % même valeur pour le TP3.
    epsilon = 0.8;

    % Distances des centres de masse au point de contact
    rap = [pointContact - voitureA.Position 0];
    rbp = [pointContact - voitureB.Position 0];
    
    % Vitesses initiales
    va = [voitureA.Vitesse 0];
    vb = [voitureB.Vitesse 0];
    wa = [0 0 voitureA.VitesseAngulaire];
    wb = [0 0 voitureB.VitesseAngulaire];

    % Formules trouvée au Chapitre 5, p.32
    vap = va + cross(wa, rap);
    vbp = vb + cross(wb, rbp);
    vab = vap - vbp;
    
    % Formules trouvées au Chapitre 5, p.33
    % Contrairement à la normale fournie, qui est dirigée vers l'extérieur
    % du solide, nous avons ici besoin de la normale dirigée vers
    % l'intérieur du solide.
    n = -normale;
    vr_minus = dot(n, vab);
    
    % Formules trouvées au Chapitre 5, p.37
    j = -(1 + epsilon)*vr_minus / ((1/voitureA.Masse) + (1/voitureB.Masse));
    
    % Formules trouvées au Chapitre 5, p.38
    vitesseA = va + ((n * j)/voitureA.Masse);
    vitesseB = vb - ((n * j)/voitureB.Masse);

    vitesseAngA = wa + (j * cross(rap, n) / voitureA.MomentInertie);
    vitesseAngB = wb - (j * cross(rbp, n) / voitureB.MomentInertie);
end

