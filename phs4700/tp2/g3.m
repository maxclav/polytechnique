function [ resultat ] = g3( q0, t0 )
%G3 Retourne le membre de droite de ED pour l'option 3.    
    
    % Constantes
    M_b = 0.00274;  % kg // 2.74g
    R_b = 0.0199;   % m // 1.99cm
    A_eff = pi*R_b^2;
    Mvol = 1.2;     % kg/m^3
    C_v = 0.5;
    C_m = 0.29;
    R_b = 0.0199;   % m // 1.99cm
    
    % Arguments
    vitesse = q0(1:3);
    position = q0(4:6); % Pas utilisé
    vitesseAngulaire = q0(7:9);
    
    % Forces
    Fg = M_b*[0, 0, -9.8];
    Fv = (-1 * Mvol * C_v * A_eff / 2) * norm(vitesse) * vitesse;
    Fm = 4 * pi * C_m * Mvol * R_b^3 * cross(vitesseAngulaire, vitesse);
    Ftot = Fg + Fv + Fm;
    
    % Accélération & Résultats
    acc = Ftot/M_b;
    resultat = [acc, vitesse, vitesseAngulaire];
end

