classdef PointCle
    %POINTCLE Summary of this class goes here
    %   Detailed explanation goes here
    
    properties
        positionX;
        positionY;
        octave;
        orientation;
    end
    
    methods
        function obj = PointCle(positionX,positionY, octave, orientation)
            %POINTCLE Construct an instance of this class
            %   Detailed explanation goes here
            obj.positionX = positionX;
            obj.positionY = positionY;
            obj.octave = octave;
            obj.orientation = orientation;
        end
        
        function pos = position(obj)
            %METHOD1 Summary of this method goes here
            %   Detailed explanation goes here
            pos = [obj.positionX, obj.positionY];
        end
        
    end
end

