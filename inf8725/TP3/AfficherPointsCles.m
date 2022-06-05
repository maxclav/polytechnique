function [] = AfficherPointsCles(image, pointsCles)
%AFFICHERPOINTSCLES Summary of this function goes here
%   Detailed explanation goes herefigure;

    colors = {'magenta', 'red', 'blue', 'green', 'yellow'};
    
    figure;
    imshow(image);
    [dim, nbPointsCles] = size(pointsCles);
    for point = 1:nbPointsCles
        ligne = pointsCles{point}(1);
        colonne = pointsCles{point}(2);
        octave = pointsCles{point}(3);
        angle = pointsCles{point}(4);

       % Affichage des cercles et orientations
       pos = [ligne,colonne];
       viscircles(pos,25*octave, ...
           'LineWidth', 0.005, 'Color', colors{mod(octave,6)+1});
       line([pos(1), pos(1)+(25*octave)*cos(angle)], [pos(2), pos(2)+(25*octave)*sin(angle)], ...
          'LineWidth', 0.005, 'Color', colors{mod(octave,6)+1});
    end
end

