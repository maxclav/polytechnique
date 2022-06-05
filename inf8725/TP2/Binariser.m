function [ ImageBinaire ] = Binariser( Image, Seuil )
%BINARISER Summary of this function goes here
%   Detailed explanation goes here

    % Implémentez une fonction Binariser qui met à 0 tous les pixels se trouvant
    % en dessous du seuil, et à 255 tous les pixels se trouvant sur le seuil ou au dessus.

    % Donne false=0 donc 0*255 = 0 OU true=1 donc 1*255 = 255
    ImageBinaire = (Image >= Seuil) * 255;
end

