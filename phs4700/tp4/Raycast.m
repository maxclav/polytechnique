classdef Raycast < handle    
    properties
        % X = Ax*t+Bx; Yx = Ay*t+By; Z = Ay*t+Bz
        Origine; % B
        Direction; % A - la direction doit être normalisé
        OrigineInitial;
        DirectionInitial;
        
        PointDeContact = [];
        
        DistanceMaximale = 50; %cm
        
        Trajectoire = [];
    end
    
    methods
        function obj = Raycast(origine, direction)
            obj.Origine = origine;
            obj.OrigineInitial = obj.Origine;
            obj.Direction = direction/norm(direction);
            obj.DirectionInitial = obj.Direction;
            obj.Trajectoire(end+1,:) = obj.Origine;
        end
        function Draw(obj)
            t = obj.Trajectoire;
            if(isempty(obj.PointDeContact))
                t(end+1,:) = obj.DistanceMaximale*obj.Direction+obj.Origine;
            elseif(~isempty(obj.PointDeContact))
                plot3(obj.PointDeContact(1), obj.PointDeContact(2), obj.PointDeContact(3), 'x', 'Color', [255/255 105/255 180/255]); % Rayon rose
            end
            plot3(t(:,1), t(:,2), t(:,3), '-', 'Color', [255/255 105/255 180/255]); % Rayon rose
        end
        
        function DrawVirtualPoint(obj, color)
            if(~isempty(obj.PointDeContact))
                totalDist = 0;
                [R,~] = size(obj.Trajectoire);
                for i = 1:R-1
                    segment = obj.Trajectoire(i,:) - obj.Trajectoire(i+1,:);
                    totalDist = totalDist + norm(segment);
                end
                
                v = totalDist*obj.DirectionInitial + obj.OrigineInitial;
                plot3(v(1), v(2), v(3), '.', 'Color', color, 'MarkerFaceColor',color);   
            end
        end
        
        function Redirection(obj, newOrigin, newDirection)
            obj.Trajectoire(end+1,:) = newOrigin;
            obj.Origine = newOrigin;
            obj.Direction = newDirection;
        end
        
        function Stop(obj, endPoint)
            obj.Trajectoire(end+1,:) = endPoint;
            obj.PointDeContact = endPoint;
        end
    end
end

