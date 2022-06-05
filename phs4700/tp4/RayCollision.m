classdef RayCollision
    properties
        Point = [];
        
        Ui = [];
        Ur = [];
        Ut = [];
        I = [];
        J = [];
        K = [];
        ThetaI = 0;
        ThetaT = 0;
        Nin;
        Nout;
        Type;
        
        Plane;
    end
    
    properties (Constant)
        TYPE_REFRACTION = "refraction";
        TYPE_REFLEXION = "reflexion";
        TYPE_STOP = "stop";
    end
    
    methods
        function obj = RayCollision(point, ui, i, nin, nout, plane)
            obj.Point = point;
            obj.Ui = ui;
            obj.Nin = nin;
            obj.Nout = nout;
            obj.I = i;
            obj.Plane = plane;
            
            j = cross(ui,i)/norm(cross(ui,i));
            obj.J = j;
            
            k = cross(i,j)/norm(cross(i,j));
            obj.K = k;
            
            thetaI = asind(dot(ui,k));
            obj.ThetaI = thetaI;
                        
            if(nin == -1)
                obj.Type = obj.TYPE_STOP;
            elseif(nout<nin)
                obj.Type = obj.TYPE_REFRACTION;
                thetaT = asind((nout/nin)*dot(ui,k));
                obj.ThetaT = thetaT;
                ut = -i*cosd(thetaT) + k*sind(thetaT);
                obj.Ut =  ut/norm(ut);
            elseif(nout>nin)
                dThetaI = abs(asind(nin/nout));
                if(-dThetaI <= thetaI && thetaI <= dThetaI)
                    obj.Type = obj.TYPE_REFRACTION;
                    thetaT = asind((nout/nin)*dot(ui,k));
                    obj.ThetaT = thetaT;
                    ut = -i*cosd(thetaT) + k*sind(thetaT);
                    obj.Ut =  ut/norm(ut);
                else
                    obj.Type = obj.TYPE_REFLEXION;
                    ur = i*cosd(thetaI) + k*sind(thetaI);
                    obj.Ur = ur/norm(ur);
                end
            else % nin == nout
                obj.Type = obj.TYPE_REFRACTION;
                obj.Ut = ui;
                obj.ThetaT = thetaI;
            end
            
        end
        
        function DrawAxis(obj)
            n = [obj.Point; (obj.Point + 0.5*obj.I)];
            u = [obj.Point; (obj.Point + -obj.Ui)];
            plot3(obj.Point(1), obj.Point(2), obj.Point(3), 'x', 'Color', [255/255 105/255 180/255]);
            plot3(n(:,1), n(:,2), n(:,3), '-k');
            plot3(u(:,1), u(:,2), u(:,3), '-', 'Color', [255/255 105/255 180/255]);
            
            j = [obj.Point; (obj.Point + 0.5*obj.J)];
            k = [obj.Point; (obj.Point + 0.5*obj.K)];
            plot3(j(:,1), j(:,2), j(:,3), '-y');
            plot3(k(:,1), k(:,2), k(:,3), '-g');
            
            if(obj.Type == obj.TYPE_REFRACTION)
                t = [obj.Point; (obj.Point + obj.Ut)];
                plot3(t(:,1), t(:,2), t(:,3), '-r');   
            elseif(obj.Type == obj.TYPE_REFLEXION)
                r = [obj.Point; (obj.Point + obj.Ur)];
                plot3(r(:,1), r(:,2), r(:,3), '-b');
            end
        end
        
        function DrawPoint(obj)
             plot3(obj.Point(:,1), obj.Point(:,2), obj.Point(:,3), 's', 'Color', obj.Plane.Color, 'MarkerFaceColor',obj.Plane.Color);   
        end
    end
end

