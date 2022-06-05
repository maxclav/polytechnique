function [descripteurs] = descriptionPointsCles(image_initiale)
%DESCRIPTIONPOINTSCLES Summary of this function goes here
%   Detailed explanation goes here

    imageDouble = imresize(image_initiale, 2);

    % 2.1 Construction des différences de Gaussiennes
    % seuil_contraste: "For the experiments in this paper, all extrema with a 
    % value of |D(xˆ)| less than 0.03 were discarded"
    % r_courb_principale: "The experiments in this paper use a value of r = 10, 
    % which eliminates keypoints that have a ratio between the principal 
    % curvatures greater than 10."
    [DoGs, octaves, sigmas] = differenceDeGaussiennes(imageDouble, 3, 3);
    
    % 2.2 Détection de points clés
    [pointsCles] = detectionPointsCles(DoGs, octaves, sigmas, 0.03, 10);

    % Affichage intermédiaire
    %AfficherPointsCles(imageDouble, pointsCles);
    
    % 2.3 Descripteurs SIFT
    [dim, nbPointsCles] = size(pointsCles);
    
    % Orientations initiales
    [dim, nbOctaves] = size(octaves);
    for octave = 1:nbOctaves
        I = octaves{octave};
        [height, width] = size(I);
        for i = 2:height-1
            for j = 2:width-1
                amplitudes(i,j, octave) = sqrt(...
                       (I(i+1,j) - I(i-1,j))^2 ...
                     + (I(i,j+1) - I(i,j-1))^2);

                angles(i,j,octave) = rad2deg(atan(...
                       (I(i,j+1) - I(i,j-1)) ...
                     / (I(i+1,j) - I(i-1,j)))) + 180;
            end
        end
    end
    
    % Transforme les points clés en descripteurs
    nbDescripteurs = 0;
    for pointCle = 1:nbPointsCles
        y = pointsCles{pointCle}(1);
        x = pointsCles{pointCle}(2);
        octave = pointsCles{pointCle}(3);
        
        anglePointCle = pointsCles{pointCle}(4);
        nbIntervalles = 360/8;
        
        % Pour ksa fasse 16x16
        haut = x - 7;
        bas = x + 8;
        gauche = y - 8;
        droite = y + 7;
                        
        %if (haut > 1 && bas < height-2 && gauche > 1 && droite < width-2)
           nbDescripteurs = nbDescripteurs + 1;
           
           % Les amplitudes sont pondérées d'un coup
           amplitudesAvoisinantes = imgaussfilt(amplitudes(haut:bas, gauche:droite, octave), 1.5*octave);
            
           % La soustraction permet l'invariance à la rotation
           anglesAvoisinants = angles(haut:bas, gauche:droite, octave) - anglePointCle;

           % Histogrammes pour chaque voisinage de 4x4     
           histogrammes = [];
           % Chaque voisinage de 4x4
           for a = 1:4
               for b = 1:4
                   histogramme = zeros(1,8);
                   
                   % un histogramme par voisinage de 4x4
                   for i = 1:4
                       for j = 1:4
                           noIntervalle = floor(mod(anglesAvoisinants((a-1)*4+i,(b-1)*4+j),360)/nbIntervalles)+1;
                           histogramme(noIntervalle) = histogramme(noIntervalle) + ...
                               amplitudesAvoisinantes((a-1)*4+1,(b-1)*4+j);
                       end
                   end
                   % Append to an array:
                   % https://www.mathworks.com/matlabcentral/answers/87549-append-to-an-array?requestedDomain=www.mathworks.com
                   histogrammes = [histogrammes histogramme];
               end
           end
               
           % Normaliser les histogrammes
           histogrammesNormalises = histogrammes ./ norm(histogrammes);
           
           % Afin de diminuer la sensibilité au changement de luminosité
           % Les valeurs sont plafonées à 0.2 et l'histogramme est de
           % nouveau normalisé
           for i = 1:numel(histogrammesNormalises)
               histogrammesNormalises(i) = min(histogrammesNormalises(i), 0.2);
           end
           histogrammesRenormalises = histogrammesNormalises ./ norm(histogrammesNormalises);
           descripteurs(nbDescripteurs, :) = [pointsCles{pointCle}(1), pointsCles{pointCle}(2), histogrammesRenormalises];
           
        %end        
    end
end