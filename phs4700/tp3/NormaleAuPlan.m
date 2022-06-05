function [ normale ] = NormaleAuPlan(point1, point2)
%NORMALEAUPLAN Retourne la normal au plan formé des deux points fournis et
%d'un troisième de valeur [point1(1) point1(2) 1]
%   Important: Il faut donner les points de façon à ce que, avec le
%   troisième point construit, le plan puisse compter ses points dans le
%   sens anti-horaire (p1-2-3).

    % Points
    qk1 = [point1 0];
    qk2 = [point2 0];
    qk3 = qk1 + [0 0 1];

    % Direction générale
    pk1 = qk1 - qk2;
    pk2 = qk1 - qk3;

    % Normale
    nk = cross(pk1, pk2);
    normale = nk/norm(nk);
end

