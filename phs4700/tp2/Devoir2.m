function [coup, tf, rbf, vbf] = Devoir2(option, rbi, vbi, wbi)
%% Dimensions des objets
% table
H_t = 0.76;         % m (direction z)
Longeur_t = 2.74;   % m (direction x)
Largeur_t = 1.525;  % m (direction y)
x_t = [0 Longeur_t Longeur_t 0];
y_t = [0 0 Largeur_t Largeur_t];
z_t = [H_t H_t H_t H_t];

% filet
H_f = 0.1525;   % m // 12.25cm
L_f = 1.83;     % m
D_f = 0.1525;   % m
x_f = [Longeur_t/2 Longeur_t/2 Longeur_t/2 Longeur_t/2];
y_f = [-D_f -D_f Largeur_t+D_f Largeur_t+D_f];
z_f = [H_t H_t+H_f H_t+H_f H_t];

% balle sphérique creuse
M_b = 0.00274;  % kg // 2.74g
R_b = 0.0199;   % m // 1.99cm
V_max = 35;     % m/s
Vang_max = 940; % rads/s

%% Vitesses maximum
if norm(vbi) > V_max
    vbi = vbi/norm(vbi)*V_max;
    disp("vbi trop grand, diminuation de la norme du vecteur jusqu'a 35");
end

if norm(wbi) > Vang_max
    wbi = wbi/norm(wbi)*Vang_max;
    disp("wbi trop grand, diminuation de la norme du vecteur jusqu'a 940");
end

%% Choix de la fonction g(q0,t0)
if option == 1
    g = @g1;
elseif option == 2
    g = @g2;
elseif option == 3
    g = @g3;    
else
    disp('option non existante');
end

%% Calcul de trajectoire
% Vecteurs des positions cumulées
x = [];
y = [];
z = [];

% Premier q0 (change à la fin de chaque itération)
q0 = [vbi, rbi, wbi];

% Valeurs initiales
dt = 0.01;
collision = 0;
i = 0;

% TODO : ajouter la vitesse finale
while(collision ~= 1)

    % Temps et Temps+1
    t = i*dt;
    tt = (i+1)*dt;
    
    % q0(4:6) = position
    xt = q0(4);
    yt = q0(5);
    zt = q0(6);
    
    % toutes les positions depuis le début
    x = [x, xt];
    y = [y, yt];
    z = [z, zt];
    
    % valeurs pour Temps+1
    qs = SEDRK4t0(q0, t, dt, g);
    vb = qs(1:3);

    % qs(4:6) = position à Temps+1
    xtt = qs(4);
    ytt = qs(5);
    ztt = qs(6);

    % Regarde si la balle touche le filet
    if xt<Longeur_t/2 && Longeur_t/2<xtt || xt>Longeur_t/2 && Longeur_t/2>xtt
        xa = (xtt - xt)/dt;
        ya = (ytt - yt)/dt;
        za = (ztt - zt)/dt;
        xb = xtt-xa*tt;
        yb = ytt-ya*tt;
        zb = ztt-za*tt;
        
        if rbi(1) < Longeur_t/2
            tp = (Longeur_t/2 - xb - R_b)/xa;
        else
            tp = (Longeur_t/2 - xb + R_b)/xa;
        end

        xp = xa*tp+xb;
        yp = ya*tp+yb;
        zp = za*tp+zb;

        % SI la balle touche vraiment le filet
        if zp > H_t && zp < H_t + H_f + R_b && yp > -D_f - R_b && yp < Largeur_t + D_f + R_b
            x = [x, xp];
            y = [y, yp];
            z = [z, zp];

            % Valeurs de sortie
            collision = 1;
            rbf = [xp yp zp];
            coup = 2;                
            tf = tp;
            vbf = vb;
            
            
            %patch('Faces', fvc.faces, 'Vertices', fvc.vertices, 'FaceColor', [1, 0, 0]);
        end
    % Regarde si la balle touche la table
    elseif zt > H_t && ztt < H_t
        xa = (xtt - xt)/dt;
        ya = (ytt - yt)/dt;
        za = (ztt - zt)/dt;
        xb = xtt-xa*tt;
        yb = ytt-ya*tt;
        zb = ztt-za*tt;
        
        tp = (H_t - zb + R_b)/za;
        xp = xa*tp+xb;
        yp = ya*tp+yb;
        zp = za*tp+zb;


        % SI la balle touche le cote entre 0 et Longeur_t/2
        if xp > 0 - R_b && xp < Longeur_t/2 && yp > 0 - R_b && yp < Largeur_t + R_b
            x = [x, xp];
            y = [y, yp];
            z = [z, zp];

            % Valeurs de sortie
            collision = 1;
            rbf = [xp yp zp];
            tf = tp;
            vbf = vb;
            if rbi(1) < Longeur_t/2
                coup = 1;
            else
                coup = 0;
            end
        % SI la balle touche le cote entre Longeur_t/2 et Longeur_t
        elseif xp > Longeur_t/2 && xp < Longeur_t + R_b && yp > 0 - R_b && yp < Largeur_t + R_b
            x = [x, xp];
            y = [y, yp];
            z = [z, zp];

            % Valeurs de sortie
            collision = 1;
            rbf = [xp yp zp];
            tf = tp;
            vbf = vb;
            if rbi(1) < Longeur_t/2
                coup = 0;
            else
                coup = 1;
            end
        end
    % SI la balle touche le sol
    elseif zt > 0 && ztt < 0
        xa = (xtt - xt)/dt;
        ya = (ytt - yt)/dt;
        za = (ztt - zt)/dt;
        xb = xtt-xa*tt;
        yb = ytt-ya*tt;
        zb = ztt-za*tt;

        tp = -zb/za;
        xp = xa*tp+xb;
        yp = ya*tp+yb;
        zp = za*tp+zb;

        x = [x, xp];
        y = [y, yp];
        z = [z, zp];

        % Valeurs de sortie
        collision = 1;
        coup = 3;
        rbf = [xp yp zp];
        tf = tp;
        vbf = vb;
    end
    i = i + 1;
    % qs devient q0 de la prochaine itération
    q0(1:6) = qs(1:6);
end


%% Affichage
plot3(x,y,z);
hold on;
grid on;
[xs,ys,zs] = sphere;
xs = xs*R_b + xp;
ys = ys*R_b + yp;
zs = zs*R_b + zp;
fvc = surf2patch(xs,ys,zs);
patch('Faces', fvc.faces, 'Vertices', fvc.vertices, 'FaceColor', [1, 0, 0]);
patch(x_t, y_t, z_t, 'g');  % display table
patch(x_f, y_f, z_f, 'y');  % display filet
xlabel('x');
ylabel('y');
zlabel('z');
xlim([-0.13, 2.87]);        % valeur trouver a la main pour mettre la table dans le centre
ylim([-0.7375, 2.2625]);    % valeur trouver a la main pour mettre la table dans le centre
zlim([0, 3]);
view(3);

%% usefull copy paste
%[coup,tf,rbf,vbf] = Devoir2(1, [0 0.5 1.1], [4 0 -0.8], [0 -70 0])
%[coup,tf,rbf,vbf] = Devoir2(1, [0 0.4 1.14], [10 1 0.2], [0 100 -50])
%[coup,tf,rbf,vbf] = Devoir2(1, [2.74 0.5 1.14], [-5 0 0.2], [0 100 0])
%[coup,tf,rbf,vbf] = Devoir2(1, [0 0.3 1], [10 -2 0.2], [0 10 -100])
%[coup,tf,rbf,vbf] = Devoir2(1, [0 0.4 1.14], [7 1 0.2], [0 100 -50])
%[coup,tf,rbf,vbf] = Devoir2(1, [0 0.5 1.14], [5 0 0.2], [0 100 0])
%[coup,tf,rbf,vbf] = Devoir2(1, [2.74 0.5 1.14], [-7 0 0.2], [0 100 0])

end