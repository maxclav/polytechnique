function [] = ...
    AfficherPointsSimilaires(image1, image2, descripteurs1,descripteurs2,distances)
%AFFICHERPOINTSSIMILAIRES Summary of this function goes here
%   Detailed explanation goes here
    
    image1Double = imresize(image1, 2);
    image2Double = imresize(image2, 2);
    d = distances;

    % Trouver les n plus petites distances de la matrice
    nombrePointsSimilaires = 5;
    colors = {'magenta', 'red', 'blue', 'green', 'yellow'};
        
    for i = 1:nombrePointsSimilaires
        % https://www.mathworks.com/help/matlab/ref/min.html
        [value, idx] = min(d(:));
        [m, n] = ind2sub(size(d), idx);
        
        % On les met à l'infini pour ne pas retomber dessus au prochain
        % search de minumum.
        d(m,n) = Inf;
        d(n,m) = Inf;
        
        % Sauvegarder les positions
        x1 = descripteurs1(m,1)
        y1 = descripteurs1(m,2)
        positions1{i} = [x1, y1];
       
        x2 = descripteurs2(n,1)
        y2 = descripteurs2(n,2)
        positions2{i} = [x2, y2];
    end    
    
    % Afficher dans image 1
    figure;
    imshow(image1Double);
    for i = 1:nombrePointsSimilaires
        viscircles(positions1{i}, 50, ...
           'LineWidth', 0.05, 'Color', colors{mod(i,5)+1});
    end
    
    % Afficher dans image 2
    figure;
    imshow(image2Double);
    for i = 1:nombrePointsSimilaires
        viscircles(positions2{i}, 50, ...
           'LineWidth', 0.05, 'Color', colors{mod(i,5)+1});
    end
end

