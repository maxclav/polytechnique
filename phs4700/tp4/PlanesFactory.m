classdef PlanesFactory
    methods(Static)
        function planes = BuildPlanesWithColor(vertices, faces, color, nin, nout)
            %BuildPlanesWithColor Construct multiple instances of Planes
            % with a color for each face
            [Row, Col] = size(faces);
            planes = cell(1,Row);
            for r = 1:Row
                points = zeros(Col,3);
                for c = 1:Col
                    points(c,:) = vertices(faces(r,c),:);
                end
                plane = Plane(points, r);
                plane.Color = color(r,:);
                plane.Nin = nin;
                plane.Nout = nout;
                planes{r} = plane;
            end
        end
        
        function planes = BuildPlanes(vertices, faces,  nin, nout)
            %BuildPlanes Construct multiple instances of transparent Planes
            [Row, Col] = size(faces);
            planes = cell(1,Row);
            for r = 1:Row
                points = zeros(Col,3);
                for c = 1:Col
                    points(c,:) = vertices(faces(r,c),:);
                end
                plane = Plane(points, r);
                plane.Nin = nin;
                plane.Nout = nout;
                planes{r} = plane;
            end
        end
    end
end

