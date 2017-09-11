% simple orbit
mimas = [185520 360/(0.942422*24)];
r = zeros(360,3);
r(1,1) = mimas(1)*cosd(0);
r(1,2) = mimas(1)*sind(0);
r(1,3) = 0;

for i= 2:360
    r(i,3) = r(i-1,3)+mimas(2);
    r(i,1) = mimas(1)*cosd(r(i,3));
    r(i,2) = mimas(1)*sind(r(i,3));
end

for i= 1:360
    hold on;
    plot(r(i,1),r(i,2),'*');
    pause(0.1);
end
    
