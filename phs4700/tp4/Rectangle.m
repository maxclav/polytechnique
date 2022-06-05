classdef Rectangle
    properties
        Planes;
        Dimension = [1 2 5]; %m
        Position = [3.5 4 14.5]; %m
        Nin = -1;
        Nout;
    end
    
    properties (Access = private)
        vertices = ...
            [4 3 12;...
            4 3 17;...
            3 3 17;...
            3 3 12;...
            3 5 12;...
            4 5 12;...
            4 5 17;...
            3 5 17];

        faces = ...
            [3 8 5 4;...
            7 2 1 6;...
            1 2 3 4;...
            5 8 7 6;...
            6 1 4 5;...
            3 2 7 8];
        
        facevertexcdata = ...
            [1 0 0;...
            0 1 1;...
            0 1 0;...
            1 1 0;...
            0 0 1;...
            1 0 1];
    end
    
    methods
        function obj = Rectangle(nout)
            obj.Nout = nout;
            obj.Planes = PlanesFactory.BuildPlanesWithColor(obj.vertices, obj.faces, obj.facevertexcdata, -1, nout);
        end
        
        function Draw(obj)
            RectanglePatch = struct('faces', obj.faces, 'vertices', obj.vertices, 'facevertexcdata', obj.facevertexcdata);
            patch(RectanglePatch);
            shading faceted;
        end
        
        function DrawFromPlanes(obj)
            for i = 1:length(obj.Planes)
                obj.Planes{i}.Draw();
            end
        end
        
        function DrawFaceless(obj)
            RectanglePatch = struct('faces', obj.faces, 'vertices', obj.vertices, 'facevertexcdata', obj.facevertexcdata, 'FaceColor', 'none');
            patch(RectanglePatch);
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

