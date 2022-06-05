classdef Plane
    % La classe Plane représente un panneau à 4 points. Un Plane peut être
    % utilisé afin de déterminé s'il existe une collision avec un rayon (Raycast) et
    % peut le rediriger selon son indice de réfraction
    % Le Rectangle et le Cylindre sont composé de plusieurs Plane
    
    properties
        Number;
        Points;
        Color = [-1 -1 -1]; % [-1 -1 -1] = aucune couleur - transparent
        Nin = -1; % indice de refraction : -1 = aucune refraction / stop le rayon
        Nout = -1;
        
        Normale;
        d; % pour l'équation du plan : ax+by+cz+d=0 où a,b,c sont les composantes normales.
        
        Type = "square";
        Radius = -1;
        Center = [];
    end
    
    methods
        function obj = Plane(points, number)
            obj.Number = number;
            obj.Points = points;
            obj.Normale = cross(points(1,:) - points(2,:), points(2,:) - points(3,:));
            obj.Normale = obj.Normale/norm(obj.Normale);
            
            obj.d = -1*sum(obj.Normale.*points(1,:));
        end
        
        function Draw(obj)            
            if(isequal(obj.Color,[-1 -1 -1]))
                patch(obj.Points(:,1), obj.Points(:,2), obj.Points(:,3), 'b', 'FaceColor', 'none', 'EdgeColor', 'b');
            else
                patch(obj.Points(:,1), obj.Points(:,2), obj.Points(:,3), obj.Color);
            end
            
            % Déssiner la normale
            if(obj.Type == Plane.TYPE_SQUARE())
                o = obj.Points(1,:) + (obj.Points(3,:)-obj.Points(1,:))/2;
                p = obj.Normale + o;
                plot3([o(1), p(1)], [o(2), p(2)], [o(3), p(3)], '-k');
            elseif(obj.Type == Plane.TYPE_CIRCLE())
                o = obj.Center;
                p = obj.Normale + o;
                plot3([o(1), p(1)], [o(2), p(2)], [o(3), p(3)], '-k');
            end
        end
        
        function pointCollision = DetectCollision(obj, raycast)
            pointCollision = -1;
            
            % résoudre: Nx(Ax*t+Bx) + Ny(Ay*t+By) + Nz(Az*t+Bz) = d
            N = obj.Normale;
            A = raycast.Direction/norm(raycast.Direction);
            B = raycast.Origine;
            
            %tOrigine = -1*(dot(N,B) + obj.d)/dot(N,A);
            t = -1*(dot(N,B) + obj.d)/dot(N,A);
            
            point = A*t + B;
            point = round(point,4); % sinon, les rayon passe a traver certain plan
            
            isInsidePlane = false;
            
            % Si le point se trouve sur la plan
            if(obj.Type == Plane.TYPE_SQUARE())
                isInsidePlane = point(1) >= min(obj.Points(:,1)) && point(1) <= max(obj.Points(:,1)) && ...
                    point(2) >= min(obj.Points(:,2)) && point(2) <= max(obj.Points(:,2)) && ...
                    point(3) >= min(obj.Points(:,3)) && point(3) <= max(obj.Points(:,3));
            elseif(obj.Type == Plane.TYPE_CIRCLE())
                dist = norm(obj.Center - point);
                isInsidePlane = obj.Radius >= dist;
            end
            
            % t > 0+dt résout certains problèmes de précision relier au
            % round du point
            dt = 0.001;
            if(t > (0+dt) && ~isequal(point, raycast.Origine) && isInsidePlane)
                u = (point - raycast.Origine);
                ui = u/norm(u);
                nin = obj.Nin;
                nout = obj.Nout;
                i = N;
                
                angle = acosd(dot(-ui,N)/(norm(-ui)*norm(i)));
                
                % Si le rayon frappe la face arrière du plan
                if(angle > 90)
                    nin = obj.Nout;
                    nout = obj.Nin;
                    i = -1*N;
                end
                
                pointCollision = RayCollision(point, ui, i, nin, nout, obj);
            end
        end
    end
    
    methods(Static)
        function type = TYPE_SQUARE()
            type = "square";
        end
        
        function type = TYPE_CIRCLE()
            type = "circle";
        end
    end
end

