function [ imageRehaussee, Laplacien ] = Rehaussement_Contour( Image, K, type)
%REHAUSSEMENT_CONTOUR Summary of this function goes here
%   Detailed explanation goes her
    
    % Masque Gaussien
    G =   [1 2 1;
           2 4 2;
           1 2 1] /16;

    % MasqueLaplacien (Type 1 est celui de l'énnoncé)
    if(type == 1)
        L = [-1 -1 -1; -1  8 -1; -1 -1 -1];
    elseif(type == 2)
        L = [1 1 1; 1  -8 1; 1 1 1];
    elseif(type == 3)
        L = [0 1 0; 1 -4 1; 0 1 0];
    elseif(type == 4)
        L = [0 -1 0; -1 4 -1; 0 -1 0];
    else
        L = [-1 -1 -1; -1  8 -1; -1 -1 -1];
    end
    
    % Algorithme de rehaussement de contour
    Ig = Convolution(Image, G);
    Laplacien = Convolution(Ig, L);
    imageRehaussee = Ig + K .* Laplacien;
    
    % « Attention, le laplacien peut renvoyer des valeurs négatives »
    % Voir première réponse à: https://www.mathworks.com/matlabcentral/answers/31927-help-how-to-replace-all-the-negative-values-in-an-array-with-zeros
    Laplacien(Laplacien<0) = 0;
    imageRehaussee(imageRehaussee < 0) = 0;
end

