function [xi, yi, zi, face] = Devoir4(nout, nin, poso)
    xi = [];
    yi = [];
    zi = [];
    face = [];

    rectangle = Rectangle(nin);
    cylindre = Cylindre(nin, nout);
    
    N = 50; % x
    M = 50; % y 
    
    dir = rectangle.Position - poso;
    
    [theta, phi] = cart2sph(dir(1), dir(2), dir(3));
    theta = radtodeg(theta);
    phi = 90 - radtodeg(phi);
    
    az_max = theta + 25;
    theta_min = theta - 35;
    phi_max = phi + 40;
    phi_min = phi - 15;

    raycasts = cell(N, M);
    
    for n = 1:N
        theta_n = theta_min + (az_max - theta_min)/(2*N)*(2*n - 1);
        for m = 1:M
            phi_m = phi_min + (phi_max - phi_min)/(2*M)*(2*m - 1);
            dir = Direction(theta_n, phi_m);
            raycasts{n, m} = Raycast(poso, dir);
        end
    end    
    
    figure;
    hold on;
    %rectangle.Draw();
    rectangle.DrawFaceless()
    cylindre.Draw();
    %rectangle.DrawFromPlanes();
    %cylindre.DrawFromPlanes();
    
    % range of raycast
    Raycast(poso, Direction(az_max, phi_max)).Draw();
    Raycast(poso, Direction(az_max, phi_min)).Draw();
    Raycast(poso, Direction(theta_min, phi_max)).Draw();
    Raycast(poso, Direction(theta_min, phi_min)).Draw();  
    
    MaxReflexionInterne = 100;
    for i = 1:numel(raycasts)
        r = raycasts{i};
        
        stop = false;
        NbReflexionInterne = 0;
        while(~stop)
            pointsCollisionCylindre = cylindre.detectCollision(r);
            pointsCollisionRectangle = rectangle.detectCollision(r);
            
            rayCollisions = [pointsCollisionCylindre, pointsCollisionRectangle];   
            if(~isempty(rayCollisions))
                dists = zeros(1,numel(rayCollisions));
                for y = 1:numel(rayCollisions)
                    dists(y) = norm(rayCollisions{y}.Point - r.Origine);
                end
                
                [~,idx] = min(dists);
                
                rayCollision = rayCollisions{idx};
                
                if(rayCollision.Type == rayCollision.TYPE_STOP)
                    r.Stop(rayCollision.Point);
                    %rayCollision.DrawPoint();
                    %r.Draw();
                    r.DrawVirtualPoint(rayCollision.Plane.Color);
                    
                    xi(end+1) = rayCollision.Point(1);
                    yi(end+1) = rayCollision.Point(2);
                    zi(end+1) = rayCollision.Point(3);
                    face(end+1) = rayCollision.Plane.Number;
                    
                    stop = true;
                elseif(rayCollision.Type == rayCollision.TYPE_REFLEXION)
                    r.Redirection(rayCollision.Point, rayCollision.Ur);
                    NbReflexionInterne = NbReflexionInterne + 1;
                    if(NbReflexionInterne >= MaxReflexionInterne)
                        stop = true;
                    end
                elseif(rayCollision.Type == rayCollision.TYPE_REFRACTION)
                    r.Redirection(rayCollision.Point, rayCollision.Ut);
                end
            else
                stop = true;
            end
        end
    end
    
    plot3(poso(1), poso(2), poso(3),'o',...
    'MarkerEdgeColor','b',...
    'MarkerSize',5)
    
    xlabel('x (cm)');
    ylabel('y (cm)');
    zlabel('z (cm)');
    xlim([-14, 22]);        
    ylim([-14, 22]);
    zlim([0, 36]);
    grid on;
    view(3);
end