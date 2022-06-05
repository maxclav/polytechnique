type Egalisation_Histogramme.m
type Convolution.m
type Rehaussement_Contour.m
type Binariser.m
type Fermeture.m
type Compter_Monnaie.m
type Calculer_TFD.m

%% Exercice 1 (5 points): Rehaussement d'image

% Nettoyage du workspace
clear all;
close all;
clc;

% Question 1 (1.5 points)
% Chargez l’image theArtist.png avec la fonction imread
% imread: https://www.mathworks.com/help/matlab/ref/imread.html
Image = imread('theArtist.png');

% Fonction Egalisation_Histogramme implémentée
% voir Egalisation_Histogramme.m
ImageEgalisee = Egalisation_Histogramme(Image);

% Affichage résultats
figure('Name' , 'Question 1 - Egalisation d histogrammes (Moins sombre)');
subplot(2,2,1);
imshow(Image);
title('Image originale sombre');
subplot(2,2,3);
imhist(Image);
title('Histogramme image originale');
grid on;
subplot(2,2,2);
imshow(ImageEgalisee);
title('Image égalisée');
subplot(2,2,4);
imhist(ImageEgalisee);
title('Histogramme de l image égalisée');
grid on;

% Question 2 (1 point)
% Voir Convolution.m

% Question 3 (0.5 point)
% L’image égalisée semble très bruitée. Appliquez un flou gaussien à l’image
 %égalisé en la convoluant.
 
MasqueGaussien =   [1 2 1 2 1;
                     2 4 8 4 2;
                     1 8 18 8 1;
                     2 4 8 4 2;
                     1 2 1 2 1] /90;
                 
ImageFiltree = Convolution(ImageEgalisee, MasqueGaussien);

% Affichage résultats
figure('Name' , 'Question 2&3 - Convolution Gaussienne de l image (Moins de bruit)');
subplot(2,2,1);
imshow(ImageEgalisee);
title('Image égalisée');
subplot(2,2,3);
imhist(ImageEgalisee);
title('Histogramme de l image égalisée');
grid on;
subplot(2,2,2);
imshow(ImageFiltree);
title('Image floutée');
subplot(2,2,4);
imhist(ImageFiltree);
title('Histogramme de l image floutée');
grid on;

% Question 4 (1 point)
[ imageRehaussee, Laplacien ] = Rehaussement_Contour( ImageEgalisee, 1.2, 1 );

figure('Name', 'Question 4 - Resultat de rehaussement et le Laplacien');
subplot(2,3,1);
imshow(ImageFiltree);
title('Image floutée mais dégradée');
subplot(2,3,4);
imhist(ImageFiltree);
title('Histogramme de l image floutée');
grid on;
subplot(2,3,2);
imshow(imageRehaussee);
title('Résultat du rehaussement');
subplot(2,3,5);
imhist(imageRehaussee);
title('Histogramme du résultat');
grid on;
subplot(2,3,3);
imshow(Laplacien);
title('Le Laplacien');
subplot(2,3,6);
imhist(Laplacien);
title('Histogramme du Laplacien');
grid on;

% Question 5 (1 point)
% Bien que les contours aient été rehaussés, on voit apparaître un nouveau 
% bruit.

% Afin d'en être sur, nous avons, dans un premier temps, testé différentes valeurs du paramètre
% K. Comme nous pouvons le voir à la figure 4, la première image utilise
% k=1,2 tel que demandé dans l'énnoncé. La deuxième image utilise un K=0,2.
% Cette image a moins de bruit, mais les contours sont comme si on n'avait
% rien fait. La troisième image, quant à elle, a un K=2,4. Ces contours
% sont assez définis, mais il y a énormément de bruit. Donc, plus on veut
% rehausser les contours en augmentant la veleur de K, plus le bruit s'élève.
%
% Dans un deuxième temps, nous avons testé les quatres différents masques
% Laplacien donnés dans les notes de cours. Le filtre exigé dans l'énnoncé
% semblait être le 2e meilleur filtre.
%
% Dans un troisième temps, nous pouvons utiliser un filtre Median afin de
% préserver les contours tout en enlevant ce bruit qui somple êtrede type
% "poivre et sel".
%
% Une solution idéale serait donc de prendre le meilleur filtre
% (L = [0 -1 0; -1 4 -1; 0 -1 0]) et de diminuer un peu la valeur du
% paramèetre K. 

% TESTS de l'algorithme de rehaussement de contour
% ... avec un Masque Laplacien qui diffèere (4 possibles selon les notes de cours)
[ imageRehaussee2, Laplacien2 ] = Rehaussement_Contour( ImageEgalisee, 1.2, 4 );
% ... avec un paramètre K plus bas et plus levé
[ imageRehaussee3, Laplacien3 ] = Rehaussement_Contour( ImageEgalisee, 0.2, 1 );
[ imageRehaussee4, Laplacien4 ] = Rehaussement_Contour( ImageEgalisee, 2.4, 1 );

figure('Name', 'Question 5 - Tests');
subplot(1,4,1);
imshow(imageRehaussee);
title('Masque original k=1,2');
subplot(1,4,4);
imshow(imageRehaussee2);
title('Masque différent k=1,2');
subplot(1,4,2);
imshow(imageRehaussee3);
title('Masque original k=0,2');
subplot(1,4,3);
imshow(imageRehaussee4);
title('Masque original k=2.4');


%% Exercice 2 (5 points): Compteur de monnaie

% Nettoyage du workspace
clear all;
close all;
clc;

% Question 1 (0.5 point)
% Chargez l’image pieces.jpg
Image = imread('pieces.jpg');
% Convertissez la en niveau de gris
ImageGris = rgb2gray(Image);

% Affichez cette image
figure('Name', 'Question 1 - Niveau de gris');
subplot(1,2,1);
imshow(Image);
title('Image originale');
subplot(1,2,2);
imshow(ImageGris);
title('Image en gris');

% Question 2 (1 point)
% Voir Binariser.m
ImageBinaire = Binariser(ImageGris, 250);
% Inverser les couleurs
ImageBinaireInverse = 255 - ImageBinaire;

% Affichez l'image binaire
figure('Name', 'Question 2 - Binariser');
subplot(1,2,1);
imshow(ImageBinaire);
title('Couleurs originales');
subplot(1,2,2);
imshow(ImageBinaireInverse);
title('Couleurs inversées');

% Question 3 (1.5 points)
% On va tester avec des forme(Shapes) et des paramètres différents de l'élément structurant.

% Voir Fermeture.m
% Tests avec différentes formes comme élément de structure (SE)
ImageFermee1 = Fermeture( ImageBinaireInverse, 'square', 1);
ImageFermee2 = Fermeture( ImageBinaireInverse, 'sphere', 1);
ImageFermee3 = Fermeture( ImageBinaireInverse, 'diamond', 1);

figure('Name', 'Question 3 - Fermeture avec des différentes formes pour l élément structurant (SE)');
subplot(1,3,1);
imshow(ImageFermee1);
title('SE: Carré');
subplot(1,3,2);
imshow(ImageFermee2);
title('SE: Sphère');
subplot(1,3,3);
imshow(ImageFermee3);
title('SE: Diamand');

% Comme nous pouvons le voir dans la figure 3, la sphère semble être une
% meilleure option afin de fermer les trous des pièces.

% Tests avec un paramètre (ici un rayon)
ImageFermee4 = Fermeture( ImageBinaireInverse, 'sphere', 1);
ImageFermee5 = Fermeture( ImageBinaireInverse, 'sphere', 10);

figure('Name', 'Question 3 - Fermeture avec une sphère avec un rayon différent');
subplot(1,2,1);
imshow(ImageFermee4);
title('r = 1');
subplot(1,2,2);
imshow(ImageFermee5);
title('r = 10');

% Aussi, plus le paramètre "Parameter" de notre fonction Fermeture( Image,
% ShapeType, Parameter) est grand, plus le remplissage est optimal.
% Cependant, le temps d'exécution augmente considérablement.
%
% La solution optimale est donc de prendre, parmis nos essaies, un
% élément strucurant ayant comme forme une sphère et un rayon (Parameter)
% égal à 10.


% Question 4 (2 points)
% Nous utilisons la meilleure image fermée, soit ImageFermee5, tel
% qu'expliqué à la question précédente.

disp('Montant total: ')
% Afin que le montant soit en format banque (à 2 décimales après la virgule)
format bank
% Voir Compter_Monnaie.m
Compter_Monnaie(ImageFermee5) % $

%% Exercice 3 (3.5 points): Transformée de Fourier 2D

% Nettoyage du workspace
clear all;
close all;
clc;

% Question 1 (0.5 point)
% Chargez les images
ImageVert = imread('Barres_Verticales.png');
ImageHoriz = imread('Barres_Horizontales.png');
ImageObli = imread('Barres_Obliques.png');

% Afficher les images
figure('Name' , 'Question 1 - Des barres...');
subplot(1,3,1);
imshow(ImageVert);
title('verticales')
subplot(1,3,2);
imshow(ImageHoriz);
title('horizontales')
subplot(1,3,3);
imshow(ImageObli);
title('obliques')

% Question 2 (1.5 points)

% Calculez les TFD des trois images
TFD_vert = Calculer_TFD(ImageVert);
TFD_horz = Calculer_TFD(ImageHoriz);
TFD_obli = Calculer_TFD(ImageObli);

% Affichez les TFDs calculés
% Pour des fins de visualisation, nous utilisons log(1 + TFD(IM)).
figure('Name' , 'Question 2 - Images des barres et de leurs histogrammes');
subplot(2,3,4);
imshow(log(1 + TFD_vert),[]);
subplot(2,3,5);
imshow(log(1 + TFD_horz),[]);
subplot(2,3,6);
imshow(log(1 + TFD_obli),[]);
% Afficher les images originales pour une meilleure analyse
subplot(2,3,1);
imshow(ImageVert);
title('verticales')
subplot(2,3,2);
imshow(ImageHoriz);
title('horizontales')
subplot(2,3,3);
imshow(ImageObli);
title('obliques')

% Question 3 (0.5 point)
% Tournez l’image Barres_Verticales.png de 70 degrés avec l’aide de imrotate
% imrotate: https://www.mathworks.com/help/images/ref/imrotate.html
% Utilisez l’option bilinear et crop de la fonction imrotate
ImageVertTourne = imrotate(ImageVert, 70, 'bilinear', 'crop');

% Calculez TFD de l'image tournée
TFD_VertTourne = Calculer_TFD(ImageVertTourne);

% Affichez la TFD de l'image tournéee
figure('Name' , 'Question 3');
subplot(2,1,2);
imshow(log(1+TFD_VertTourne),[]);
subplot(2,1,1);
% Afficher l'image originale pour une meilleure analyse
imshow(ImageVertTourne);
title('Barres verticales tournées')

% Question 4 (1 point)
% Par la comparaison des images en 2, et en 3, on peut inférer que la 
% rotation d'une image se traduit par une rotation identique dans le plan 
% des fréquences, tel qu'expliqué ici: 
% (http://www.tsi.enst.fr/pages/enseignement/ressources/mti/ondelettes-2g/francais/Fourier/TF2D.htm)

%% Exercice 4 (6.5 points): Filtrage spectral

% Nettoyage du workspace
clear all;
close all;
clc;

% Question 1 (0.5 point)
% Chargez l’image maillot.png
imageMaillot = imread('maillot.png');

% Calculez sa TFD  et conserver la phase de la TFD (sans abs donc sans notre fonction créée)

% 1) Calculez la TFD de l'Image avec la fonction fft2
% fft2: % https://www.mathworks.com/help/matlab/ref/fft2.html
TFD_maillot = fft2(imageMaillot);

% 2) Centrez la TFD avec fftshift
% fftshift: https://www.mathworks.com/help/matlab/ref/fftshift.html
TFD_maillot_centre = fftshift(TFD_maillot);

% 3) Normalisez la TFD
sizeX = size(TFD_maillot_centre, 1);
sizeY = size(TFD_maillot_centre, 2);
TFD_maillot_norm = TFD_maillot_centre/(sizeX*sizeY);

% Affichez l'image et sa TFD calculée (mettre abs dans la fonction)
% Pour des fins de visualisation, nous utilisons log(1 + TFD(IM)).
figure('Name' , 'Question 1 - TFD du maillot');
subplot(1,2,1);
imshow(imageMaillot);
title('Image originale');
subplot(1,2,2);
TFD_maillot_reel = abs(TFD_maillot_norm);
imshow(log(1+TFD_maillot_reel),[]);
title('Histogramme');

% Note: c'est plus clair sans "[]" dans imshow comme ceci
% imshow(log(1+TFD_maillot_reel));

% Question 2 (1 point)
figure('Name' , 'Question 2 - Identification des textures');
schemaMaillot = imread('Schema_maillot.png');
imshow(schemaMaillot);

% Question 3 (1 point)

% Créer un filtre passe-bas gaussien dans le domaine spectral avec la
% fonction fspecial pour faire disparaitre les hachures de la poche.
% La taille de votre filtre doit être de la même taille que le spectre.
% fspecial: https://www.mathworks.com/help/images/ref/fspecial.html

% Testons 3 différents suigmas et affichohns-les.
h1 = fspecial('gaussian', size(imageMaillot), 1);
h2 = fspecial('gaussian', size(imageMaillot), 2);
h3 = fspecial('gaussian', size(imageMaillot), 3);

% imfilter: https://www.mathworks.com/help/images/ref/imfilter.html
maillot_filtre1 = imfilter(imageMaillot, h1);
maillot_filtre2 = imfilter(imageMaillot, h2);
maillot_filtre3 = imfilter(imageMaillot, h3);

figure('Name' , 'Question 3 - Faire disparaitre les hachures de la poche');
subplot(1,4,1);
imshow(imageMaillot);
title('Sans filtre');
subplot(1,4,2);
imshow(maillot_filtre1);
title('Sigma = 1');
subplot(1,4,3);
imshow(maillot_filtre2);
title('Sigma = 2');
subplot(1,4,4);
imshow(maillot_filtre3);
title('Sigma = 3');

% Comme nous pouvons le remarquer, la valeur minimale que doit avoir le
% paramètre sigma est 2 si l'on utilise la fonction imfilter.

% Question 4 (0.5 point)
% En vous inspirant de la méthode précédente, créez le filtre passe-haut qui
% affichera le maillot en blanc à l’exception du col qui restera noir.

% Comme vu dans le cours (Filtrage Spectral, p. 42), on peut construire un
% filtre passe-haut à partir d'un filtre passe-bas. Nous transformons
% chacun des filtres passe-bas testés en filtres passe-haut pour les
% comparer. 

% Question 5 (1 point)
% Créez un filtre qui vous permettra de conserver uniquement la texture des
% deux manches. Ici, vous pouvez utiliser le filtre qui vous convient . Il est possible de
% créer un filtre idéal dans Paint puis d’effectuer un seuillage sur l’image résultante

% Question 6 (1 point)
% ) Créez un filtre qui vous permettra de faire disparaitre la texture du torse.
% Ici, vous pouvez utiliser le filtre qui vous convient

% Question 7 (0.5 point)
% Quelle est la différence entre utiliser un filtre idéal ou un filtre Butterworth
% lors du filtrage ?
% Solution: Selon les notes de cours, le filtre de Butterworth est plus
% facile à mettre en oeuvre. De plus, il permet un contrôle sur la transition
% de coupureen augmentant l'ordre. Avec un ordre suffisamment élevé, on se 
% rapproche d'un filtre idéal.

% Question 8 (0.5 point)
% Que se passe-t-il si on ne conserve pas la composante moyenne (fréquence
% 0) dans les filtres ?
% Solution: Si on ne conserve pas la composante moyenne dans les
% filtres, l'image résultante risque d'être très sombre, puisqu'elle
% contiendra des valeurs négatives (donc nulle à l'affichage).

% Question 9 (0.5 point)
% Filtre: Passe-haut, puisque les éléments apparaissent progressivement,
% puis que la couleur moyenne de l'image apparaît en dernier.
