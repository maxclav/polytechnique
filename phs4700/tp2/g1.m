function [ resultat ] = g1( q0, t0 )
%G1 Retourne le membre de droite de ED pour l'option 1.    
    
    % Constantes
    M_b = 0.00274;  % kg // 2.74g
    
    % Arguments
    vitesse = q0(1:3);
    position = q0(4:6);
    vitesseAngulaire = q0(7:9);
    
    % Forces
    Fg = M_b*[0, 0, -9.8];
    Ftot = Fg;
    
    % Accélération & Résultat
    acc = Ftot/M_b;
    resultat = [acc, vitesse, vitesseAngulaire];
end

