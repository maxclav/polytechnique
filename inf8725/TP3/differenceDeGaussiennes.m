function [DoGs, octaves, sigmas] = ...
    differenceDeGaussiennes(image_initiale, s, nb_octave)
%DIFFERENCEDEGAUSSIENNES 

    % Trouver les sigmas
    % "We must produce s + 3 images in the stack of blurred images for
    %  each octave"
    sigmas = zeros(s+3);
    
    % "The initial image is incrementally convolved with Gaussians to
    %  produce images separated by a constant factor k in scale space."
    k = 2^(1/s);
    sigmas(1) = 1.6; % "provides close to optimal repeatability"
    
    for i = 2:s+3
        sigmas(i) = sigmas(1) * k^(i-1);
    end
    
    % "The image doubling increases the number of stable keypoints by almost a factor of 4"
    % TODO? + Explications dans le rapport?
    
    for i = 1:nb_octave
        
        % Réduire l'image de moitié
        % https://www.mathworks.com/help/images/ref/imresize.html
        scaledImage = imresize(image_initiale, 1/2^(i-1));
        
        % Stocker la première image de l'octave (pas floue)
        octaves{i} = scaledImage;
        
        % Chaque pyramide contiendra s+3 images filtrées de la bonne
        % grosseur; Chaque DoG contiendra une image de moins
        [height, width] = size(scaledImage);
        pyramidesGaussiennes{i} = zeros(height, width, s+3);
        DoGs{i} = zeros(height, width, s+2);
        
        % Construire les pyramides de Gaussienne
        for j = 1:s+3
            %https://www.mathworks.com/help/images/ref/imgaussfilt.html
            pyramidesGaussiennes{i}(:,:,j) = imgaussfilt(scaledImage, sigmas(j));
        end
        
        % Construire les pyramides de DoG
        for j = 1:s+2
            DoGs{i}(:,:,j) = pyramidesGaussiennes{i}(:,:,j+1) - pyramidesGaussiennes{i}(:,:,j);
        end
        
    end
    
%     % Tracer la pyramide de Gaussienne construite à l'octave de notre choix
%     figure('Name' , 'Pyramide de Gaussienne à l octave initial');
%     for i = 1:6
%         subplot(3,2,i);
%         imshow(pyramidesGaussiennes{1}(:,:,i));
%         title(sigmas(i));
%     end
%      
%    % Tracer la DoG correspondante
%     figure('Name' , 'Différence de Gaussienne à l octave initial');
% 
%     for i = 1:5
%         subplot(3,2,i);
%         %https://www.mathworks.com/help/images/ref/mat2gray.html
%         imshow(mat2gray(DoGs{1}(:,:,i)));
%     end
end

