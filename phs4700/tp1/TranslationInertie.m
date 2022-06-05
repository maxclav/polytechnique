function [ momentInertieFinal ] = TranslationInertie( distance, masse, momentInertie )
    translation = [0 0 0; 0 0 0; 0 0 0];
    translation(1,1) = power(distance(2), 2) + power(distance(3), 2);
    translation(1,2) = -distance(1)*distance(2);
    translation(1,3) = -distance(1)*distance(3);
    translation(2,1) = -distance(2)*distance(1);
    translation(2,2) = power(distance(1), 2) + power(distance(3), 2);
    translation(2,3) = -distance(2)*distance(3);
    translation(3,1) = -distance(3)*distance(1);
    translation(3,2) = -distance(3)*distance(2);
    translation(3,3) = power(distance(1), 2) + power(distance(2), 2);
    
    momentInertieFinal = momentInertie + masse * translation;
end

