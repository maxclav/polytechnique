function [ collisionPotentielle ] = CollisionParRayon(voitureA, voitureB)
%COLLISIONPARRAYON D�tecte une collision par la m�thode des rayons
%   Retourne 1 si une collision potentielle est d�tect�e, 0 sinon.
%
%   Si elle ne d�tecte aucune collision potentielle, on est assur� 
%   qu'il n'y a pas de collision. On ne peut rien conclure si une 
%   collision potentielle est d�tect�e.

    pointsA = voitureA.Coins();
    pointsB = voitureB.Coins();
    
    rayonA = norm(voitureA.Position - pointsA(1,:));
    rayonB = norm(voitureB.Position - pointsB(1,:));

    distCollision = rayonA + rayonB;
    distVoitures = abs(voitureA.Position - voitureB.Position);
    
    if(distVoitures <= distCollision)
        collisionPotentielle = 1;
    else
        collisionPotentielle = 0;
    end
end

