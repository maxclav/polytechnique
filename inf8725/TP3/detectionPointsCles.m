function [points] = ...
    detectionPointsCles(DoGs, octaves, sigmas, ...
    seuil_contraste, r_courb_principale)
%Extraction des points-cl�s SIFT d'une image (octave)

    % 1. Pour chaque DoG d'une octave, on d�tecte des points cl�s.
    %    (Sauf la premi�re et la derni�re image)
    [dim, nbOctaves] = size(DoGs);
    nbPointsClesTotal = 0;
    
    for octave=1:nbOctaves
       [height, width, nbDoGs] = size(DoGs{octave});
       
       % Valeurs interm�diaires
       nbExtrema = 0;
       nbEliminesParContraste = 0;
       nbPointsArretesElimines = 0;
       nbPointsRestant = 0;
       
       % Choisir L(x,y)
       plusProcheSigma = PlusProcheSigma(octave, sigmas);
       L = imgaussfilt(octaves{octave}, plusProcheSigma);
       %L = octaves{octave};
       
       for k = 2:nbDoGs-1
           % La DoG courante est ses voisins
           DoG = DoGs{octave}(:,:,k);
           DoGAvant = DoGs{octave}(:,:,k+1);
           DoGApres = DoGs{octave}(:,:,k-1);  
           
           % Les gradients, pour rejeter les points aux ar�tes
           % https://www.mathworks.com/matlabcentral/answers/67893-how-do-i-calculate-hessian-matrix-for-an-image
           [Dx, Dy] = gradient(double(DoG));
           [Dxx, Dxy] = gradient(Dx);
           [Dxy, Dyy] = gradient(Dy);
           
           %plusProcheSigma = sigmas(k);
           
           % Voisinage � prendre en compte pour les histogrammes
           % d'orientation
           largeurVoisinage = ceil(6*sigmas(k));
           if mod(largeurVoisinage, 2) == 0
               largeurVoisinage = largeurVoisinage + 1;
           end

           for i = 3:height-2
               for j = 3:width-2
                   % On d�tecte les points cl�s candidats
                   if (estExtremumLocal(DoG, DoGAvant,DoGApres, i, j) == 1)
                       nbExtrema = nbExtrema + 1;
                       % On augmente les �limin�s, mais ce sera annul� plus
                       % tard au besoin
                       nbEliminesParContraste = nbEliminesParContraste + 1;
                       
                       % 2. Ensuite, on fait une localisation pr�cise des points-cl�s.
                       
                       %   2.2. On rejete les points-cl�s avec un contraste bas
                       %   La diff�rence de gaussiennes donne des valeurs
                       %   entre -1 et 1. Les contrastes bas sont ceux dont
                       %   leur valeur absolue est inf�rieure au seuil.
                       if (abs(DoG(i,j)) > seuil_contraste)
                           nbEliminesParContraste = nbEliminesParContraste - 1;
                           nbPointsArretesElimines = nbPointsArretesElimines + 1;
                           
                           %   2.3. On rejete les points-cl�s situ�s sur des
                           %   arr�tes de contours de courbures
                           Tr = Dxx(i,j) + Dyy(i,j);
                           Det = Dxx(i,j)*Dyy(i,j) - (Dxy(i,j)^2);
                           r = r_courb_principale;
                           
                           % "A poorly defined peak in the difference-of-Gaussian
                           % function will have a large principal curvature across
                           % the edge but a small one in the perpendicular direction."
                           % Donc, on rejette si la courbature est trop grande.
                           if ((Tr^2)/Det < ((r+1)^2)/r)                               
                               nbPointsArretesElimines = nbPointsArretesElimines - 1;
                               
                               % Ce point candidat est donc un point cl�s choisis
                               nbPointsRestant = nbPointsRestant + 1;
                               
                               % 3. On fait une assignation des orientations
                               % "The scale of the keypoint is used to select the Gaussian
                               % smoothed image, L, with the closest scale, so that all 
                               % computations are performed in a scale-invariant manner. 
                               % For each image sample, L(x, y), at this scale, the gradient
                               % magnitude, m(x, y), and orientation, ?(x, y), is precomputed
                               % using pixel differences."
                                                              
                               if (i-floor(largeurVoisinage/2) > 1 && i+floor(largeurVoisinage/2) < height-2 ...
                                       && j-floor(largeurVoisinage/2) > 1 && j+floor(largeurVoisinage/2) < width-2)
                                            
                                   % Histogramme: (360 / 10) cat�gories d'angles
                                   histoOrientation = zeros(1,36);
                                   amplitudes = zeros(1, 8);
                                   angles = zeros(1, 8);

                                   % On calcule l'amplitude et l'angle des voisins
                                   index = 1;
                                   for x = i-floor(largeurVoisinage/2):i+floor(largeurVoisinage/2)
                                       for y = j-floor(largeurVoisinage/2):j+floor(largeurVoisinage/2)
                                           amplitudes(index) = sqrt(...
                                               (L(x+1,y) - L(x-1,y))^2 ...
                                             + (L(x,y+1) - L(x,y-1))^2);

                                           angles(index) = rad2deg(atan(...
                                               (L(x,y+1) - L(x,y-1)) ...
                                             / (L(x+1,y) - L(x-1,y)))) + 180;

                                           % On trouve la cat�gorie d'angles
                                           % dans lequel l'angle trouv�
                                           % correspond, au 10 degr�s pr�s.
                                           noIntervalle = floor(angles(index) / 10) + 1;

                                           % On augmente la valeur de
                                           % l'histogramme de fa�on pond�r�e
                                           % par rapport � son amplitude et le
                                           % sigma.
                                           histoOrientation(noIntervalle) = ...
                                               histoOrientation(noIntervalle) + imgaussfilt(amplitudes(index), 1.5*octave);

                                         index = index + 1;
                                       end
                                   end

                                   % Cr�e le point cl� principal selon
                                   % l'orientation dominante
                                   [maxValeurPonderee, maxIndex] = max(histoOrientation);
                                   angleDominant = (maxIndex - 1)*10;

                                   % Ajoute ce point aux points cl�s
                                   nbPointsClesTotal = nbPointsClesTotal + 1;
                                   points{nbPointsClesTotal} = [j*2^(octave-1), i*2^(octave-1), octave, angleDominant];

                                   % Cherche pour des orientations dominantes
                                   % secondaires
                                   for x = 1:36
                                       if (x ~= maxIndex && histoOrientation(x) >= 0.8*maxValeurPonderee)

                                           % Cr�e le point cl� secondaire
                                           % trouv� selon son orientation
                                           angleDominantSecondaire = x*10;

                                           % Ajoute ce point aux point cl�s
                                           nbPointsClesTotal = nbPointsClesTotal + 1;
                                           points{nbPointsClesTotal} = [j*2^(octave-1), i*2^(octave-1), octave, angleDominantSecondaire];
                                       end
                                   end
                               end
                           end
                       end
                   end                    
               end
           end
       end
%        octave
%        size(octaves{octave})
%        nbExtrema
%        nbEliminesParContraste
%        nbPointsArretesElimines
%        nbPointsRestant
    end
    nbPointsClesTotal
end

