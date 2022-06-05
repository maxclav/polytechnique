function [ ImageFermee ] = Fermeture( Image, ShapeType, Parameter)
%FERMETURE Summary of this function goes here
%   Detailed explanation goes here

    % Effectuez une fermeture sur l’image binaire afin de fermer les trous

    % strel: https://www.mathworks.com/help/images/ref/strel.html
    elementStructurant = strel(ShapeType, Parameter);
    
    % imclose: https://www.mathworks.com/help/images/ref/imclose.html
    ImageFermee = imclose(Image, elementStructurant);

end

