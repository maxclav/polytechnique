function [ estDansSolide, precision, normale ] = EstDansSolide(normales, point, references)
%ESTDANSSOLIDE D�tecte si un point est � l'int�rieur d'un solide selon les
%normales et les points de r�f�rence de ses surfaces
%
%   Le point i est � l'int�rieur du volume si dik <= 0 pour toutes les
%   surfaces k.
    di1 = dot(normales(1,:), [point 0] - [references(1,:) 0]);
    di2 = dot(normales(2,:), [point 0] - [references(2,:) 0]);
    di3 = dot(normales(3,:), [point 0] - [references(3,:) 0]);
    di4 = dot(normales(4,:), [point 0] - [references(4,:) 0]);
    
    if(di1 <= 0 && di2 <= 0 && di3 <= 0 && di4 <= 0)
        estDansSolide = 1;
        precision = abs(max([di1, di2, di3, di4]));
        if (precision == abs(di1))
            normale = normales(1,:);
        elseif (precision == abs(di2))
            normale = normales(2,:);
        elseif (precision == abs(di3))
            normale = normales(3,:);
        else
            normale = normales(4,:);
        end
    else
        estDansSolide = 0;
        precision = Inf;
        normale = 0;
    end
end

