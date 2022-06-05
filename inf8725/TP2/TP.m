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
% Chargez l�image theArtist.png avec la fonction imread
% imread: https://www.mathworks.com/help/matlab/ref/imread.html
Image = imread('theArtist.png');

% Fonction Egalisation_Histogramme impl�ment�e
% voir Egalisation_Histogramme.m
ImageEgalisee = Egalisation_Histogramme(Image);

% Affichage r�sultats
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
title('Image �galis�e');
subplot(2,2,4);
imhist(ImageEgalisee);
title('Histogramme de l image �galis�e');
grid on;

% Question 2 (1 point)
% Voir Convolution.m

% Question 3 (0.5 point)
% L�image �galis�e semble tr�s bruit�e. Appliquez un flou gaussien � l�image
 %�galis� en la convoluant.
 
MasqueGaussien =   [1 2 1 2 1;
                     2 4 8 4 2;
                     1 8 18 8 1;
                     2 4 8 4 2;
                     1 2 1 2 1] /90;
                 
ImageFiltree = Convolution(ImageEgalisee, MasqueGaussien);

% Affichage r�sultats
figure('Name' , 'Question 2&3 - Convolution Gaussienne de l image (Moins de bruit)');
subplot(2,2,1);
imshow(ImageEgalisee);
title('Image �galis�e');
subplot(2,2,3);
imhist(ImageEgalisee);
title('Histogramme de l image �galis�e');
grid on;
subplot(2,2,2);
imshow(ImageFiltree);
title('Image flout�e');
subplot(2,2,4);
imhist(ImageFiltree);
title('Histogramme de l image flout�e');
grid on;

% Question 4 (1 point)
[ imageRehaussee, Laplacien ] = Rehaussement_Contour( ImageEgalisee, 1.2, 1 );

figure('Name', 'Question 4 - Resultat de rehaussement et le Laplacien');
subplot(2,3,1);
imshow(ImageFiltree);
title('Image flout�e mais d�grad�e');
subplot(2,3,4);
imhist(ImageFiltree);
title('Histogramme de l image flout�e');
grid on;
subplot(2,3,2);
imshow(imageRehaussee);
title('R�sultat du rehaussement');
subplot(2,3,5);
imhist(imageRehaussee);
title('Histogramme du r�sultat');
grid on;
subplot(2,3,3);
imshow(Laplacien);
title('Le Laplacien');
subplot(2,3,6);
imhist(Laplacien);
title('Histogramme du Laplacien');
grid on;

% Question 5 (1 point)
% Bien que les contours aient �t� rehauss�s, on voit appara�tre un nouveau 
% bruit.

% Afin d'en �tre sur, nous avons, dans un premier temps, test� diff�rentes valeurs du param�tre
% K. Comme nous pouvons le voir � la figure 4, la premi�re image utilise
% k=1,2 tel que demand� dans l'�nnonc�. La deuxi�me image utilise un K=0,2.
% Cette image a moins de bruit, mais les contours sont comme si on n'avait
% rien fait. La troisi�me image, quant � elle, a un K=2,4. Ces contours
% sont assez d�finis, mais il y a �norm�ment de bruit. Donc, plus on veut
% rehausser les contours en augmentant la veleur de K, plus le bruit s'�l�ve.
%
% Dans un deuxi�me temps, nous avons test� les quatres diff�rents masques
% Laplacien donn�s dans les notes de cours. Le filtre exig� dans l'�nnonc�
% semblait �tre le 2e meilleur filtre.
%
% Dans un troisi�me temps, nous pouvons utiliser un filtre Median afin de
% pr�server les contours tout en enlevant ce bruit qui somple �trede type
% "poivre et sel".
%
% Une solution id�ale serait donc de prendre le meilleur filtre
% (L = [0 -1 0; -1 4 -1; 0 -1 0]) et de diminuer un peu la valeur du
% param�etre K. 

% TESTS de l'algorithme de rehaussement de contour
% ... avec un Masque Laplacien qui diff�ere (4 possibles selon les notes de cours)
[ imageRehaussee2, Laplacien2 ] = Rehaussement_Contour( ImageEgalisee, 1.2, 4 );
% ... avec un param�tre K plus bas et plus lev�
[ imageRehaussee3, Laplacien3 ] = Rehaussement_Contour( ImageEgalisee, 0.2, 1 );
[ imageRehaussee4, Laplacien4 ] = Rehaussement_Contour( ImageEgalisee, 2.4, 1 );

figure('Name', 'Question 5 - Tests');
subplot(1,4,1);
imshow(imageRehaussee);
title('Masque original k=1,2');
subplot(1,4,4);
imshow(imageRehaussee2);
title('Masque diff�rent k=1,2');
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
% Chargez l�image pieces.jpg
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
title('Couleurs invers�es');

% Question 3 (1.5 points)
% On va tester avec des forme(Shapes) et des param�tres diff�rents de l'�l�ment structurant.

% Voir Fermeture.m
% Tests avec diff�rentes formes comme �l�ment de structure (SE)
ImageFermee1 = Fermeture( ImageBinaireInverse, 'square', 1);
ImageFermee2 = Fermeture( ImageBinaireInverse, 'sphere', 1);
ImageFermee3 = Fermeture( ImageBinaireInverse, 'diamond', 1);

figure('Name', 'Question 3 - Fermeture avec des diff�rentes formes pour l �l�ment structurant (SE)');
subplot(1,3,1);
imshow(ImageFermee1);
title('SE: Carr�');
subplot(1,3,2);
imshow(ImageFermee2);
title('SE: Sph�re');
subplot(1,3,3);
imshow(ImageFermee3);
title('SE: Diamand');

% Comme nous pouvons le voir dans la figure 3, la sph�re semble �tre une
% meilleure option afin de fermer les trous des pi�ces.

% Tests avec un param�tre (ici un rayon)
ImageFermee4 = Fermeture( ImageBinaireInverse, 'sphere', 1);
ImageFermee5 = Fermeture( ImageBinaireInverse, 'sphere', 10);

figure('Name', 'Question 3 - Fermeture avec une sph�re avec un rayon diff�rent');
subplot(1,2,1);
imshow(ImageFermee4);
title('r = 1');
subplot(1,2,2);
imshow(ImageFermee5);
title('r = 10');

% Aussi, plus le param�tre "Parameter" de notre fonction Fermeture( Image,
% ShapeType, Parameter) est grand, plus le remplissage est optimal.
% Cependant, le temps d'ex�cution augmente consid�rablement.
%
% La solution optimale est donc de prendre, parmis nos essaies, un
% �l�ment strucurant ayant comme forme une sph�re et un rayon (Parameter)
% �gal � 10.


% Question 4 (2 points)
% Nous utilisons la meilleure image ferm�e, soit ImageFermee5, tel
% qu'expliqu� � la question pr�c�dente.

disp('Montant total: ')
% Afin que le montant soit en format banque (� 2 d�cimales apr�s la virgule)
format bank
% Voir Compter_Monnaie.m
Compter_Monnaie(ImageFermee5) % $

%% Exercice 3 (3.5 points): Transform�e de Fourier 2D

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

% Affichez les TFDs calcul�s
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
% Tournez l�image Barres_Verticales.png de 70 degr�s avec l�aide de imrotate
% imrotate: https://www.mathworks.com/help/images/ref/imrotate.html
% Utilisez l�option bilinear et crop de la fonction imrotate
ImageVertTourne = imrotate(ImageVert, 70, 'bilinear', 'crop');

% Calculez TFD de l'image tourn�e
TFD_VertTourne = Calculer_TFD(ImageVertTourne);

% Affichez la TFD de l'image tourn�ee
figure('Name' , 'Question 3');
subplot(2,1,2);
imshow(log(1+TFD_VertTourne),[]);
subplot(2,1,1);
% Afficher l'image originale pour une meilleure analyse
imshow(ImageVertTourne);
title('Barres verticales tourn�es')

% Question 4 (1 point)
% Par la comparaison des images en 2, et en 3, on peut inf�rer que la 
% rotation d'une image se traduit par une rotation identique dans le plan 
% des fr�quences, tel qu'expliqu� ici: 
% (http://www.tsi.enst.fr/pages/enseignement/ressources/mti/ondelettes-2g/francais/Fourier/TF2D.htm)

%% Exercice 4 (6.5 points): Filtrage spectral

% Nettoyage du workspace
clear all;
close all;
clc;

% Question 1 (0.5 point)
% Chargez l�image maillot.png
imageMaillot = imread('maillot.png');

% Calculez sa TFD  et conserver la phase de la TFD (sans abs donc sans notre fonction cr��e)

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

% Affichez l'image et sa TFD calcul�e (mettre abs dans la fonction)
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

% Cr�er un filtre passe-bas gaussien dans le domaine spectral avec la
% fonction fspecial pour faire disparaitre les hachures de la poche.
% La taille de votre filtre doit �tre de la m�me taille que le spectre.
% fspecial: https://www.mathworks.com/help/images/ref/fspecial.html

% Testons 3 diff�rents suigmas et affichohns-les.
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
% param�tre sigma est 2 si l'on utilise la fonction imfilter.

% Question 4 (0.5 point)
% En vous inspirant de la m�thode pr�c�dente, cr�ez le filtre passe-haut qui
% affichera le maillot en blanc � l�exception du col qui restera noir.

% Comme vu dans le cours (Filtrage Spectral, p. 42), on peut construire un
% filtre passe-haut � partir d'un filtre passe-bas. Nous transformons
% chacun des filtres passe-bas test�s en filtres passe-haut pour les
% comparer. 

% Question 5 (1 point)
% Cr�ez un filtre qui vous permettra de conserver uniquement la texture des
% deux manches. Ici, vous pouvez utiliser le filtre qui vous convient . Il est possible de
% cr�er un filtre id�al dans Paint puis d�effectuer un seuillage sur l�image r�sultante

% Question 6 (1 point)
% ) Cr�ez un filtre qui vous permettra de faire disparaitre la texture du torse.
% Ici, vous pouvez utiliser le filtre qui vous convient

% Question 7 (0.5 point)
% Quelle est la diff�rence entre utiliser un filtre id�al ou un filtre Butterworth
% lors du filtrage ?
% Solution: Selon les notes de cours, le filtre de Butterworth est plus
% facile � mettre en oeuvre. De plus, il permet un contr�le sur la transition
% de coupureen augmentant l'ordre. Avec un ordre suffisamment �lev�, on se 
% rapproche d'un filtre id�al.

% Question 8 (0.5 point)
% Que se passe-t-il si on ne conserve pas la composante moyenne (fr�quence
% 0) dans les filtres ?
% Solution: Si on ne conserve pas la composante moyenne dans les
% filtres, l'image r�sultante risque d'�tre tr�s sombre, puisqu'elle
% contiendra des valeurs n�gatives (donc nulle � l'affichage).

% Question 9 (0.5 point)
% Filtre: Passe-haut, puisque les �l�ments apparaissent progressivement,
% puis que la couleur moyenne de l'image appara�t en dernier.
