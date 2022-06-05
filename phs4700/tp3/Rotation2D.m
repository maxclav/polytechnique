function vecteurR = Rotation2D( vecteur, angle, centre )
rotmat = [cosd(angle), -sind(angle); sind(angle), cosd(angle)];
vecteurR = (rotmat*(vecteur-centre)')' + centre;
end

