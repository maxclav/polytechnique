function [ ImageEgalisee ] = Egalisation_Histogramme( Image )
%EGALISATION_HISTOGRAMME Applique une �galisation d'histogramme � une image
%   (notes de cours "Filtrage spatial d'images, page 20)

    % Histogramme cumulatif normalis� de l'image de d�part
    Histo = imhist(Image);
    T = cumsum(Histo)/sum(Histo);
    
    % Cr�ation de l'image finale
    [m,n] = size(Image);
    I = ones(m,n);
    
    % �galisation 
    for i = 1:m
        for j = 1:n
            pixel = Image(i,j) + 1;
            I(i,j) = T(pixel);
        end
    end
    
    ImageEgalisee = I;
end

