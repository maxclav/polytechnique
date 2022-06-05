function [Coll, tf, raf, vaf, rbf, vbf] = Devoir3(rai, vai, rbi, vbi, tb)

% Initalisation des voitures
voitureA = Voiture(1540, 4.78, 1.82, rai, vai, 'b');
voitureB = Voiture(1010, 4.23, 1.6, rbi, [vbi(1:2), 0], 'r');

% Déclaration des constantes
gA = @gFreinage;
gB = @gRoulement;
dt = 0.01;
minVitesse = 0.01;  % 1cm/s
precisionRequise = 0.01; % +/- 1cm

% Valeurs initiales
t = 0;
t_nonRaffine = 0;
collision = 0;
posFreinage = voitureB.PositionInitial;
i = 0;
precisionInsuffisante = 0;

% La simulation se termine si les 2 voitures sont suffisament ralenties
minVitessesAtteintes = @(A,B,min) ...
    (norm(A.Vitesse) < min && norm(B.Vitesse) < min);

% Valeurs initiales de  q0A-B (changent à la fin de chaque itération)
q0A(1:2) = voitureA.Vitesse;
q0A(3) = 0.0;
q0A(4:5) = voitureA.Position;
q0A(6) = 0.0;
q0A(7) = voitureA.VitesseAngulaire;
q0A(8:9) = [0.0 0.0];
q0B(1:2) = voitureB.Vitesse;
q0B(3) = 0.0;
q0B(4:5) = voitureB.Position;
q0B(6) = 0.0;
q0B(7) = voitureB.VitesseAngulaire;
q0B(8:9) = [0.0 0.0];

while(collision == 0 ...
        && ~minVitessesAtteintes(voitureA, voitureB, minVitesse))
    
    t = t_nonRaffine + i*dt;
           
    % La voiture B commence à freiner
    if(t == tb) % très dépendant de dt
        voitureB.VitesseAngulaire = vbi(3);
        q0B(7) = voitureB.VitesseAngulaire;
        posFreinage = voitureB.Position;
        gB = @gFreinage;
    end
    
    % Calcul des nouvelles valeurs pour t+1
    qsA = SEDRK4t0(q0A, t, dt, gA);
    qsB = SEDRK4t0(q0B, t, dt, gB);
    
    voitureA.Vitesse = qsA(1:2);
    voitureA.Position = qsA(4:5);
    voitureA.Angle = voitureA.Angle + voitureA.VitesseAngulaire*dt;
    voitureA.Trajectoire = [voitureA.Trajectoire(:,:); voitureA.Position];
    
    voitureB.Vitesse = qsB(1:2);
    voitureB.Position = qsB(4:5); 
    voitureB.Angle = voitureB.Angle + voitureB.VitesseAngulaire*dt;
    voitureB.Trajectoire = [voitureB.Trajectoire(:,:); voitureB.Position];
    
    % ====================== Detection Collision ======================
    % _/!\_ Ne pas utiliser la métode par rayon seule, est imprécise _/!\_
    % Mais ça peut une donner une bonne idée de la position des collisions
    % 
    % On utilise cette méthode comme pré-traitement:
    %   - Elle est simple d'application et performante;
    %   - Si elle ne détecte aucune collision potentielle, on est assuré 
    %     que les deux solides ne sont pas en contact;
    %   - Si elle détecte une collision potentielle, on doit utiliser une
    %     méthode plus précise;
    collisionPotentielle = CollisionParRayon(voitureA, voitureB);    
    
    % Lorsque le pré-traitement détecte une collision potentielle, la
    % méthode des plans de division est utilisée pour confirmer.
    if (collisionPotentielle == 1)
        
        % Collision entre plans de voitureA et points de voitureB
        [collisionConfirmee, precision, normale, pointContact] = ...
            CollisionParPlans(voitureA, voitureB);
        
        % L'ordre est important; On peut détecter une collision de A à B
        % mais pas de B à A (et vice-versa).
        if (collisionConfirmee == 0)
            % L'inverse: 
            % Collision entre plans de voitureB et points de voitureA
            [collisionConfirmee, precision, normale, pointContact] = ...
                CollisionParPlans(voitureB, voitureA);
        end
        
        % Collision trouvée; Est-elle assez précise?
        if (collisionConfirmee == 1)
            if (precision <= precisionRequise)
                collision = 1;
                Coll = 0;
                tf = t;
                raf = [voitureA.Position, voitureA.Angle];
                rbf = [voitureB.Position, voitureB.Angle];
                [vitesseA, vitesseB, vitesseAngA, vitesseAngB] = ...
                    VitessesApresCollision(voitureA, voitureB, normale, pointContact);
                vaf = [vitesseA(1:2), vitesseAngA(3)];
                vbf = [vitesseB(1:2), vitesseAngB(3)];
            else
                % Pas assez précis;
                % On doit annuler la dernière itération et raffiner
                precisionInsuffisante = 1;
            end
        end
    end
    % ================================================================
    
    % Collision détectée mais précision insuffisante:
    % On retourne en arrière et raffine
    if (precisionInsuffisante == 1)
        precisionInsuffisante = 0;
        
        % Retour aux valeurs précédentes
        voitureA.Vitesse = q0A(1:2);
        voitureA.Position = q0A(4:5);

        voitureB.Vitesse = q0B(1:2);
        voitureB.Position = q0B(4:5);
        
        % Raffinement
        t_nonRaffine = t_nonRaffine + t;
        dt = dt/10;        
    else

        % t+1 devient t
        q0A = qsA;
        q0B = qsB;
        i = i + 1;
    end
end

% Les voitures sont suffisamment ralenties 
% et aucune collision n'est survenue.
if( collision ~= 1)
    Coll = 1;
    tf = t;
    raf = [voitureA.Position, voitureA.Angle];
    vaf = [voitureA.Vitesse, voitureA.VitesseAngulaire];
    rbf = [voitureB.Position, voitureB.Angle];
    vbf = [voitureB.Vitesse, voitureB.VitesseAngulaire];
end

% Affichage
figure;
hold on;
grid on;
voitureA.DessinerTout();
voitureB.DessinerTout();
plot(posFreinage(1), posFreinage(2), 'r*'); % freinage
xlabel('x (m)');
ylabel('y (m)');
xlim([-10, 150]);        
ylim([-10, 150]); 
end