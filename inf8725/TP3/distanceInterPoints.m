function [distances] = ...
    distanceInterPoints(points_image1,points_image2)
%DISTANCEINTERPOINTS Summary of this function goes here
%   Detailed explanation goes here
    [m, longueurDescripteur] = size(points_image1);
    [n, longueurDescripteur] = size(points_image2);

    distances = zeros(m,n);
    
    for i = 1:m
        for j = 1:n
            d = 0;
            for k = 3:130
                d = d + (points_image1(i,k) - points_image2(j,k))^2;
            end
            distances(i,j) = sqrt(d);
        end
    end
end

