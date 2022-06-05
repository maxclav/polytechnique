function [ estPlanDivision ] = EstPlanDivision(normale, points, reference)
%ESTPLANDIVISION Détecte un plan de division avec la distance entre les
%points "points" donnés et le plan de normale "normale" et au point de "reference"
%
%   Il y a un plan de division k si les distances aux points i sont
%   toutes positives.

    d1k = dot(normale, [points(1,:) 0] - [reference 0]);
    d2k = dot(normale, [points(2,:) 0] - [reference 0]);
    d3k = dot(normale, [points(3,:) 0] - [reference 0]);
    d4k = dot(normale, [points(4,:) 0] - [reference 0]);
    
    if(d1k > 0 && d2k > 0 && d3k > 0 && d4k > 0)
        estPlanDivision = 1;
    else
        estPlanDivision = 0;
    end
end

