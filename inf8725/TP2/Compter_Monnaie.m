function [ montant ] = Compter_Monnaie( Image )
%COMPTER_MONNAIE Summary of this function goes here
%   Detailed explanation goes here

    % "ignorer les pièces de 1 dollar, 50 et 1 cent qui sont absentes. "
    % Donc, nous avons des 0,05$, 0,10$, 0,25$ et 2,00$.
    % Nos éléments structurants sont des disques avec des rayons de
    % 90, 110, 120, 140 et 200.
    
    % imerode : https://www.mathworks.com/help/images/ref/imerode.html
    % imdilate: https://www.mathworks.com/help/images/ref/imdilate.html
    % Différences: https://www.mathworks.com/matlabcentral/answers/uploaded_files/8310/morph1.jpg
    % bwlabel: https://www.mathworks.com/help/images/ref/bwlabel.html

    % Calculer le nombre total de pièeces
    nombreTotal = length(unique(bwlabel(imerode(Image, strel('disk', 90)))));
    
    % Calculer le nombre total de 0,10$
    nombre10cents = nombreTotal - length(unique(bwlabel(imerode(Image, strel('disk', 110)))));
    nombreRestant = nombreTotal - nombre10cents;
    
    % Calculer le nombre total de 0,05$
    nombre5cents = nombreRestant - length(unique(bwlabel(imerode(Image, strel('disk', 120)))));
    nombreRestant = nombreRestant - nombre5cents;

    % Calculer le nombre total de 0,25$
    nombre25cents = nombreRestant - length(unique(bwlabel(imerode(Image, strel('disk', 140)))));
    nombreRestant = nombreRestant - nombre25cents;
    
    % Calculer le nombre total de 2$
    nombre2dollars = nombreRestant - length(unique(bwlabel(imerode(Image, strel('disk', 200)))));
    
    
    montant = nombre5cents*0.05 + nombre10cents*0.1 + nombre25cents*0.25 + nombre2dollars*2;

end

