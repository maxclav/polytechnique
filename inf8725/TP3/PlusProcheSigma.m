function [sigma] = PlusProcheSigma(octave,sigmas)
%PLUSPROCHESIGMA 
    plusProcheSigma = sigmas(1);

    for i = 2:size(sigmas)
        if (abs(sigmas(i) - octave) < abs(plusProcheSigma - octave))
            plusProcheSigma = sigmas(i);
        end
    end
    
    sigma = plusProcheSigma;
end

