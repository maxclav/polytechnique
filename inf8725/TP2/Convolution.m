function [ ImageFiltree ] = Convolution( Image, Masque )
%CONVOLUTION Renvoit la convolution d'une image et d'un masque
%   (notes de cours "Filtrage spatial d'images, page 20)   

    % Déterminer les index pour le padding
    [k,p] = size(Masque);
    k = (k-1)/2;
    p = (p-1)/2;

    % 0 padding
    % padarray: https://www.mathworks.com/help/images/ref/padarray.html
    ImageRembouree = padarray(Image, [k, p], 0, 'both');
    ImageFiltree = zeros(size(Image));
    
    [h, w] = size(Image);
    for i = 1:h
        for j = 1:w
            ImageFiltree(i,j) = sum(sum(double(ImageRembouree(i:i+2*k,j:j+2*p)) .* Masque));
        end
    end
end

