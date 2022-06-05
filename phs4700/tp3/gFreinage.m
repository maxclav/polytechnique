function [ resultat ] = gFreinage( q0, t0 )
%G1 Retourne le membre de droite de ED pour l'option 1.    
    
    % Arguments
    vitesse = q0(1:2);
    position = q0(4:5);
    vitesseAngulaire = q0(7);

    % Valeurs de la formule des notes de cours
    g = 9.8; 
    v = vitesse;
    if (norm(v) < 50)
        mu = 0.15 * (1 - (norm(v)/100));
    else
        mu = 0.075;
    end
        
    % Force: Ff = -mu * m * g * (v/norm(v));
    % Accélération: a = Ff / m
    % Conclusion: la masse est non importante
    acceleration = -mu * g * (v/norm(v));
    
    % Résultat
    % Les zéros servent à "padder"; Runge-Kutta prend des valeurs 3D.
    resultat = [acceleration, 0.0, vitesse, 0.0, vitesseAngulaire, [0.0 0.0]];
end

