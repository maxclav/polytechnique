
function [pcmNL, INqL, alpaNL] = Devoir1(angRot, vAngulaire, forces, posNL)
%% variables
% Navette
H1n = 27.93;    % m
R1n = 3.5;      % m
H2n = 0.31;     % m
R2n = R1n;      % m
Mn = 98883.1;   % kg / 109 tonnes
centre1n = [0 0 H1n/2];
centre2n = [0 0 H1n+(H2n/4)];

% Reservoir
H1r = 39.1;     % m
R1r = 4.2;      % m
H2r = 7.8;      % m
R2r = R1r;      % m
Mhyd = 97976;   % kg / 108 tonnes
Hhyd = (H1r+H2r)*2/3;    % m
Moxy = 572434;  % kg / 631 tonnes
Mr = Mhyd + Moxy;
centrehyd = [0 R1n+R1r Hhyd/2];
centreoxy1 = [0 R1n+R1r Hhyd+((H1r-Hhyd)/2)];
centreoxy2 = [0 R1n+R1r H1r+(H2r/4)];

% propulseurs
H1p = 39.9;     % m
R1p = 1.855;    % m
H2p = 5.6;      % m
R2p = R1p;      % m
Mip = 425470;   % kg / 469 tonnes
centre1p_g = [-R1r-R1p R1n+R1r H1p/2];
centre2p_g = [-R1r-R1p R1n+R1r H1p+(H2p/4)];
centre1p_d = [R1r+R1p R1n+R1r H1p/2];
centre2p_d = [R1r+R1p R1n+R1r H1p+(H2p/4)];

Mtot = Mn + Mr + Mip*2;

% volumes
V1n = pi*H1n*power(R1n,2);
V2n = pi*H2n*power(R2n,2)*1/3;
MVn = Mn / (V1n + V2n); % masse volumique
M1n = MVn * V1n;
M2n = MVn * V2n;

Vhyd = pi*Hhyd*power(R1r,2);
V1oxy = pi*(H1r-Hhyd)*power(R1r,2);
V2oxy = pi*H2r*power(R2r,2)*1/3;
MVoxy = Moxy / (V1oxy + V2oxy);
M1oxy = MVoxy * V1oxy;
M2oxy = MVoxy * V2oxy;

V1p = pi*H1p*power(R1p,2);
V2p = pi*H2p*power(R2p,2)*1/3;
MVp = Mip / (V1p + V2p);
M1p = MVp * V1p;
M2p = MVp * V2p;

%% centre de masse initial
CMn = (centre1n*M1n + centre2n*M2n)/Mn;
CMp1 = (centre1p_g*M1p + centre2p_g*M2p)/Mip;
CMp2 = (centre1p_d*M1p + centre2p_d*M2p)/Mip;
CMr = (centrehyd*Mhyd + centreoxy1*M1oxy + centreoxy2*M2oxy)/Mr;

Rci = (CMn*Mn + CMp1*Mip + CMp2*Mip + CMr*Mr)/Mtot;

%% centre de masse final
rotMat = [1 0 0; 0 cos(angRot), -sin(angRot); 0 sin(angRot) cos(angRot)];
pcmNL = (rotMat*Rci.').'+posNL;

%% Moment d'inertie
In1 = TranslationInertie(centre1n-Rci, M1n, MomentInertieCylindre(R1n, H1n, M1n));
In2 = TranslationInertie(centre2n-Rci, M2n, MomentInertieCone(R2n, H2n, M2n));

Ip1_g = TranslationInertie(centre1p_g-Rci, M1p, MomentInertieCylindre(R1p, H1p, M1p));
Ip2_g = TranslationInertie(centre2p_g-Rci, M2p, MomentInertieCone(R2p, H2p, M2p));

Ip1_d = TranslationInertie(centre1p_d-Rci, M1p, MomentInertieCylindre(R1p, H1p, M1p));
Ip2_d = TranslationInertie(centre2p_d-Rci, M2p, MomentInertieCone(R2p, H2p, M2p));

Ihyd = TranslationInertie(centrehyd-Rci, Mhyd, MomentInertieCylindre(R1r, Hhyd, Mhyd));
Ioxy1 = TranslationInertie(centreoxy1-Rci, M1oxy, MomentInertieCylindre(R1r, H1r - Hhyd, M1oxy));
Ioxy2 = TranslationInertie(centreoxy2-Rci, M2oxy, MomentInertieCone(R2r, H2r, M2oxy));

Itot = In1 + In2 + Ip1_g + Ip2_g + Ip1_d + Ip2_d + Ihyd + Ioxy1 + Ioxy2;
    
INL = rotMat*Itot*inv(rotMat);

%% Acceleration angulaire
Pf1 = [0 0 0] + posNL;
Pf2 = (rotMat*[-R1r-R1p R1n+R1r 0].').' + posNL;
Pf3 = (rotMat*[R1r+R1p R1n+R1r 0].').' + posNL;
T1 = cross(Pf1-Rci, (rotMat*[0 0 forces(1)].').');
T2 = cross(Pf2-Rci, (rotMat*[0 0 forces(2)].').');
T3 = cross(Pf3-Rci, (rotMat*[0 0 forces(3)].').');
Ttot= T1 + T2 + T3;

L = (INL*vAngulaire.').';

alpaNL = (inv(INL)*(Ttot+cross(L,vAngulaire)).').';
end
