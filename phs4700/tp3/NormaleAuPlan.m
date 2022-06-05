function [ normale ] = NormaleAuPlan(point1, point2)
%NORMALEAUPLAN Retourne la normal au plan form� des deux points fournis et
%d'un troisi�me de valeur [point1(1) point1(2) 1]
%   Important: Il faut donner les points de fa�on � ce que, avec le
%   troisi�me point construit, le plan puisse compter ses points dans le
%   sens anti-horaire (p1-2-3).

    % Points
    qk1 = [point1 0];
    qk2 = [point2 0];
    qk3 = qk1 + [0 0 1];

    % Direction g�n�rale
    pk1 = qk1 - qk2;
    pk2 = qk1 - qk3;

    % Normale
    nk = cross(pk1, pk2);
    normale = nk/norm(nk);
end

