function [ resultat ] = g2( q0, t0 )
%G2 Retourne le membre de droite de ED pour l'option 2.    
    
    % Constantes
    R_b = 0.0199;   % m // 1.99cm
    M_b = 0.00274;  % kg // 2.74g
    A_eff = pi*R_b^2;
    Mvol = 1.2;     % kg/m^3
    C_v = 0.5;
    
    % Arguments
    vitesse = q0(1:3);
    position = q0(4:6);
    vitesseAngulaire = q0(7:9);
    
    % Forces
    Fg = M_b*[0, 0, -9.8];
    Fv = (-1 * Mvol * C_v * A_eff / 2) * norm(vitesse) * vitesse;
    Ftot = Fg + Fv;
    
    % Accélération & Résultat
    acc = Ftot/M_b;
    resultat = [acc, vitesse, vitesseAngulaire];
end

