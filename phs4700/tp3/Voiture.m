classdef Voiture < handle
    %VOITURE Summary of this class goes here
    %   Detailed explanation goes here
    
    % Avons nous vraiment besoin des propriete initial ?
    
    properties
        Masse
        Longueur
        Largeur
        Hauteur = 1.8; %vraiment utile ?
        PositionInitial
        Position
        VitesseInitial
        Vitesse
        AngleInitial
        Angle
        VitesseAngulaireInitial
        VitesseAngulaire
        Couleur
        
        Trajectoire = [];
    end
        
    methods
        function obj = Voiture(masse, longueur, largeur, ri, vi, couleur)
            obj.Masse = masse;
            obj.Longueur = longueur;
            obj.Largeur = largeur;
            obj.PositionInitial = ri(1:2);
            obj.Position = obj.PositionInitial;
            obj.VitesseInitial = vi(1:2);
            obj.Vitesse = obj.VitesseInitial;
            obj.AngleInitial = atan2d(vi(2), vi(1));
            obj.Angle = obj.AngleInitial;
            obj.VitesseAngulaireInitial = vi(3);
            obj.VitesseAngulaire = obj.VitesseAngulaireInitial;
            obj.Couleur = couleur;
        end
        
        % Retourne les 4 coins de la voiture
        function coins = Coins(obj)
            coins = [];
            offsetX = obj.Longueur/2;
            offsetY = obj.Largeur/2;
            coins(1,:) = Rotation2D([obj.Position(1)-offsetX, obj.Position(2)-offsetY], obj.Angle, obj.Position);
            coins(2,:) = Rotation2D([obj.Position(1)+offsetX, obj.Position(2)-offsetY], obj.Angle, obj.Position);
            coins(3,:) = Rotation2D([obj.Position(1)+offsetX, obj.Position(2)+offsetY], obj.Angle, obj.Position);
            coins(4,:) = Rotation2D([obj.Position(1)-offsetX, obj.Position(2)+offsetY], obj.Angle, obj.Position);
        end
        
        function coinsInitial = CoinsInitial(obj)
            coinsInitial = [];
            offsetX = obj.Longueur/2;
            offsetY = obj.Largeur/2;
            coinsInitial(1,:) = Rotation2D([obj.PositionInitial(1)-offsetX, obj.PositionInitial(2)-offsetY], obj.AngleInitial, obj.PositionInitial);
            coinsInitial(2,:) = Rotation2D([obj.PositionInitial(1)+offsetX, obj.PositionInitial(2)-offsetY], obj.AngleInitial, obj.PositionInitial);
            coinsInitial(3,:) = Rotation2D([obj.PositionInitial(1)+offsetX, obj.PositionInitial(2)+offsetY], obj.AngleInitial, obj.PositionInitial);
            coinsInitial(4,:) = Rotation2D([obj.PositionInitial(1)-offsetX, obj.PositionInitial(2)+offsetY], obj.AngleInitial, obj.PositionInitial);
        end
        
        function momentInertie = MomentInertie(obj)
            M = obj.Masse;
            b = obj.Longueur;
            c = obj.Largeur;
            momentInertie = (b^2 + c^2)*M/12;
        end
        
        function Dessiner(obj)
             coins = obj.Coins();
             x = coins(:,1)';
             y = coins(:,2)';
             patch(x, y, obj.Couleur); 
        end
        
        function DessinerInitial(obj)
            coinsInitial = obj.CoinsInitial();
             x = coinsInitial(:,1)';
             y = coinsInitial(:,2)';
             patch(x, y, obj.Couleur); 
        end
        
        function DessinerTrajectoire(obj)
            plot(obj.Trajectoire(:,1),obj.Trajectoire(:,2), obj.Couleur);
        end
        
        function DessinerTout(obj)
            obj.DessinerInitial();
            obj.DessinerTrajectoire();
            obj.Dessiner();
        end
    end
end

