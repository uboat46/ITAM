function [] = simpleOrbitPosition(mD, oP, theta)
% mD = mean distance from a saturns moon to saturn
% oP = orbital period of a saturn's moon in earth days
% theta = actual degree of position of the planet
    hold on;
%     coords = linspace(0, 2*pi, 360*oP);
%     x = mD.*cos(coords);
%     y = mD.*sin(coords);
    velocity = 1/oP;
    
    

    for i= 1:length(x)
        hold on;
        plot(x(i),y(i),'*');
        pause(0.1);
    end

end