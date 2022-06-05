function [ momentInertie ] = MomentInertieCone(rayon, hauteur, masse)
    Ixx = (12*power(rayon, 2) + 3*power(hauteur, 2))/80;
    Iyy = Ixx;
    Izz = 3*power(rayon, 2)/10;
    momentInertie = masse*[Ixx 0 0; 0 Iyy 0; 0 0 Izz];
end

