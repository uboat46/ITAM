function [] = twoMoonOrbit(mD1, oP1, mD2, oP2, n, vel)

deltaD1 = 360/ (oP1*24)
deltaD2 = 360/ (oP2*24)
r = zeros(n,6);

% fisrt moon coords
r(1,1) = mD1 * cosd(0);
r(1,2) = mD1 * sind(0);
r(1,3) = 0;
% second moon coords
r(1,4) = mD2 * cosd(0);
r(1,5) = mD2 * sind(0);
r(1,6) = 0;

for i= 2:n
    r(i,3) = r(i-1,3) + (deltaD1*3);
    r(i,1) = mD1 * cosd(r(i,3));
    r(i,2) = mD1 * sind(r(i,3));
    
    r(i,6) = r(i-1,6) + (deltaD2*3);
    r(i,4) = mD2 * cosd(r(i,6));
    r(i,5) = mD2 * sind(r(i,6));
end

hold off;
plot(mD1*cos(linspace(0,2*pi,360)),mD1*sin(linspace(0,2*pi,360)));
hold on;
plot(mD2*cos(linspace(0,2*pi,360)),mD2*sin(linspace(0,2*pi,360)));

moon1 = line('XData',r(1,1), 'YData',r(1,2), 'Color','r', ...
    'Marker','o', 'MarkerSize',6, 'LineWidth',2);
moon2 = line('XData',r(1,4), 'YData',r(1,5), 'Color','r', ...
    'Marker','o', 'MarkerSize',6, 'LineWidth',2);

for i= 2:n
    set(moon1, 'XData',r(i,1), 'YData',r(i,2))
    set(moon2, 'XData',r(i,4), 'YData',r(i,5))
    hold on;
    plot([r(i,1) r(i,4)], [r(i,2) r(i,5)],'k')
    pause(vel);
end
    
end