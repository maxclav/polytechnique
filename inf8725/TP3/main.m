%% Projet SIFT: Construction de panorama par recalage de deux images
% Traitement de signaux et d'images
% Francis Ouellet  (#1577496)
% Maxime Clavel    (#1693596)

%%
% Nettoyage du workspace
clear all;
close all;
clc;

% Chargement des images
%https://www.mathworks.com/help/matlab/ref/rgb2gray.html
imageGauche = (double(rgb2gray(imread('gauche.jpg')))/255);
imageDroite = (double(rgb2gray(imread('droite.jpg')))/255);

% 2.3 Descripteurs des images
% Cette fonction appelle 2.1 et 2.2
[descripteursGauche] = descriptionPointsCles(imageGauche);
[descripteursDroite] = descriptionPointsCles(imageDroite);

save('descripteursGauche', 'descripteursGauche');
save('descripteursDroite', 'descripteursDroite');

[distances] = distanceInterPoints(descripteursGauche, descripteursDroite);
save('distances', 'distances');

AfficherPointsSimilaires(imageGauche, imageDroite, descripteursGauche, descripteursDroite, distances);

%save('pointsClesGauche', 'pointsClesGauche');
%save('pointsClesDroite', 'pointsClesDroite');
