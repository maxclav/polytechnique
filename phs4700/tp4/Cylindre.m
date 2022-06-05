classdef Cylindre
    properties
        Planes;
        Rayon = 2; %cm
        Hauteur = 18; %cm
        Position = [4 4 11]; %cm
        IndiceRefraction;
        Nin;
        Nout;
    end
    
    properties (Access = private)
        X;
        Y;
        Z;
        vertices;
        faces;
        facevertexcdata
    end
    
    methods
        function obj = Cylindre(nin, nout)
            obj.Nin = nin;
            obj.Nout = nout;
            
            [x,y,z] = cylinder(obj.Rayon);
            
            obj.X = x + obj.Position(1);
            obj.Y = y + obj.Position(2);
            obj.Z = z *obj.Hauteur  + obj.Position(3) - obj.Hauteur/2;
            
            [obj.faces, obj.vertices, obj.facevertexcdata] = surf2patch(obj.X,obj.Y,obj.Z);
            obj.Planes = PlanesFactory.BuildPlanes(obj.vertices, obj.faces, nin, nout);
            
            topPoint = [];
            topPoint (:,1) = obj.X(2,:)';
            topPoint (:,2) = obj.Y(2,:)';
            topPoint (:,3) = obj.Z(2,:)';
            topPlane = Plane(topPoint, numel(obj.Planes)+1);
            topPlane.Type = Plane.TYPE_CIRCLE();
            topPlane.Nin = nin;
            topPlane.Nout = nout;
            topPlane.Radius = obj.Rayon;
            center = obj.Position;
            center(3) = center(3) + obj.Hauteur/2;
            topPlane.Center = center;
            obj.Planes{end+1} = topPlane;
            
            topPoint = [];
            topPoint (:,1) = fliplr(obj.X(1,:))';
            topPoint (:,2) = fliplr(obj.Y(1,:))';
            topPoint (:,3) = fliplr(obj.Z(1,:))';
            topPlane = Plane(topPoint, numel(obj.Planes)+1);
            topPlane.Type = Plane.TYPE_CIRCLE();
            topPlane.Nin = nin;
            topPlane.Nout = nout;
            topPlane.Radius = obj.Rayon;
            center = obj.Position;
            center(3) = center(3) - obj.Hauteur/2;
            topPlane.Center = center;
            obj.Planes{end+1} = topPlane;
        end
        
        function Draw(obj)            
            m = mesh(obj.X,obj.Y,obj.Z);
            m.FaceAlpha = 0;
            m.EdgeColor = 'blue';
            m.LineWidth= 0.01;
        end
        
        function DrawFromPlanes(obj)
            for i = 1:length(obj.Planes)
                obj.Planes{i}.Draw();
            end
        end
        
        function collision = detectCollision(obj, raycast)
            pointsCollision = {};
            for i = 1:numel(obj.Planes)
                point = obj.Planes{i}.DetectCollision(raycast);
                
                if(~isequal(point, -1))
                    pointsCollision{end+1} = point;
                end
            end
            collision = pointsCollision;
        end
    end
    
end

