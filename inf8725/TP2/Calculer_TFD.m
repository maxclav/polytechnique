function [ TFD_image_reel ] = Calculer_TFD( image )
%CALCULER_TFD Summary of this function goes here
%   Detailed explanation goes here

%     1) Calculer la TFD de l'Image avec la fonction fft2
%     fft2: % https://www.mathworks.com/help/matlab/ref/fft2.html
      TFD_image = fft2(image);
    
%     2) Centrer la TFD avec fftshift
%     fftshift: https://www.mathworks.com/help/matlab/ref/fftshift.html
      TFD_image_centre = fftshift(TFD_image);
 
%     3) Normaliser la TFD
      sizeX = size(TFD_image_centre, 1);
      sizeY = size(TFD_image_centre, 2);
      TFD_image_norm = TFD_image_centre/(sizeX*sizeY);
      
%     4) Récupérer la valeur réel avec abs
      TFD_image_reel = abs(TFD_image_norm);
end

