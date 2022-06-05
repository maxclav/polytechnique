function [ resultat ] = gRoulement( q0, t0 )
    % Arguments
    vitesse = q0(1:3);
    position = q0(4:6);
    vitesseAngulaire = q0(7:9);

    resultat = [[0 0 0 ], vitesse, vitesseAngulaire];
end