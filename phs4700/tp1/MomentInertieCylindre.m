function [ momentInertie ] = MomentInertieCylindre(rayon, hauteur, masse)
    Ixx = (power(rayon,2)/4) + (power(hauteur,2)/12);
    Iyy = Ixx;
    Izz = power(rayon,2)/2;
    momentInertie = masse*[Ixx 0 0; 0 Iyy 0; 0 0 Izz];
end

