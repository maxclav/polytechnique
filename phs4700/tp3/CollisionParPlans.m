function [ collisionConfirmee, precision, normale, pointContact ] = ...
    CollisionParPlans(voiture1, voiture2)
%COLLISIONPARPLANS Détecte une collision par plans de division (en 2D)
%   Retourne CollisionConfirmee = 1 si une collision est est survenue, 
%   0 sinon et precision, la distance du point de collision s'il y a lieu.
%
%   Plus précis que la méthode par rayon pour les solides convexes.

    % Le nom des variables est conforme avec les notes de cours
    % (Chapire 5, p.64 à p.74)
    
    %
    %  points1(4,:)                  points1(3,:)
    %   q32 & q41                     q22 & q31
    %       ------------------------------
    %       |                            |
    %       |         Voiture 1          |
    %       |                            |
    %       ------------------------------
    %   q11 & q42                     q12 & q21
    %  points1(1,:)                  points1(2,:)
    %
    % qk3 = qk1 + [0 0 z]; on choisit z = 1 ou -1 de façon à s'assurer que les 3
    % points décrivent un parcours antihoraire pour un observateur à 
    % l'extérieur du solide (pour k = (1,2,3,4)).
            
    collisionConfirmee = 0;
    precision = Inf;
    normale = 0;
    pointContact = 0;
    
    % Surfaces
    points1 = voiture1.Coins();    
    n1 = NormaleAuPlan(points1(1,:), points1(2,:));
    n2 = NormaleAuPlan(points1(2,:), points1(3,:));
    n3 = NormaleAuPlan(points1(3,:), points1(4,:));
    n4 = NormaleAuPlan(points1(4,:), points1(1,:));
    
    % Points à comparer
    points2 = voiture2.Coins();
    
    % Si on trouve un plan de division, il n'y a pas de collision.    
    if (EstPlanDivision(n1, points2, points1(1,:)) == 1 ...
            || EstPlanDivision(n2, points2, points1(2,:)) == 1 ...
            || EstPlanDivision(n3, points2, points1(3,:)) == 1 ...
            || EstPlanDivision(n4, points2, points1(4,:)) == 1)
        return;
    end
    
    % Si on ne trouve pas de plan de division, on cherche un point à
    % l'intérieur du solide.
    normales(1,:) = n1;
    normales(2,:) = n2;
    normales(3,:) = n3;
    normales(4,:) = n4;
    
    [estDansSolidePlan1, precision1, normalePlan1] = ...
        EstDansSolide(normales, points2(1,:), points1);
    [estDansSolidePlan2, precision2, normalePlan2] = ...
        EstDansSolide(normales, points2(2,:), points1);
    [estDansSolidePlan3, precision3, normalePlan3] = ...
        EstDansSolide(normales, points2(3,:), points1);
    [estDansSolidePlan4, precision4, normalePlan4] = ...
        EstDansSolide(normales, points2(4,:), points1);
    
    if (estDansSolidePlan1)
        collisionConfirmee = 1;
        precision = precision1;
        normale = normalePlan1;
        pointContact = points2(1,:);
    elseif (estDansSolidePlan2)
        collisionConfirmee = 1;
        precision = precision2;
        normale = normalePlan2;
        pointContact = points2(2,:);
    elseif (estDansSolidePlan3)
        collisionConfirmee = 1;
        precision = precision3;
        normale = normalePlan3;
        pointContact = points2(3,:);
    elseif (estDansSolidePlan4)
        collisionConfirmee = 1;
        precision = precision4;
        normale = normalePlan4;
        pointContact = points2(4,:);
    else
        % Fausse alerte; pas de collision
        collisionConfirmee = 0;
    end               
    
end

