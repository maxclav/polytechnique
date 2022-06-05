classdef ConditionInitial
    %CONDITIONINITIAL Summary of this class goes here
    %   Detailed explanation goes here
    
    properties
        rai
        vai
        rbi
        vbi
        tb
    end
    
    methods
        function obj = ConditionInitial(rai, vai, rbi, vbi, tb)
            obj.rai = rai;
            obj.vai = vai;
            obj.rbi = rbi;
            obj.vbi = vbi;
            obj.tb = tb;
        end
        
        function Simulation(obj)
            [Coll tf raf vaf rbf vbf] = Devoir3(obj.rai, obj.vai, obj.rbi, obj.vbi, obj.tb)
        end
    end
    
end

