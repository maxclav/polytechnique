function [ ImageEgalisee ] = Egalisation_Histogramme( Image )
%EGALISATION_HISTOGRAMME Applique une égalisation d'histogramme à une image
%   (notes de cours "Filtrage spatial d'images, page 20)

    % Histogramme cumulatif normalisé de l'image de départ
    Histo = imhist(Image);
    T = cumsum(Histo)/sum(Histo);
    
    % Création de l'image finale
    [m,n] = size(Image);
    I = ones(m,n);
    
    % Égalisation 
    for i = 1:m
        for j = 1:n
            pixel = Image(i,j) + 1;
            I(i,j) = T(pixel);
        end
    end
    
    ImageEgalisee = I;
end

