% Planets radius and increment in radial position per hour
orbit = [15520 0.942422;
          238200 1.370218;
          294660 1.887802;
          377400 2.736915;
          527040 4.5175
          1221830 15.94542
          1481100 21.24661
          3561300 79.33018
          12952000 550.48];

      m1 = 1;
      m2 = 9;
      t = 8000;
      vel = 0.2;
twoMoonOrbit(orbit(m1,1), orbit(m1,2), orbit(m2,1), orbit(m2,2), t, vel);

% for i=1:length(orbit)
%     for j=1:length(orbit)
%         if ~(i == j)
%             if(i > j)
%                 sprintf('i: %d, j: %d = %d',i,j,orbit(i,2)/orbit(j,2))
%             else
%                 sprintf('i: %d, j: %d = %d',i,j,orbit(j,2)/orbit(i,2))
%             end
%         end
%     end
% end
% RELACIONES CERCANAS A 6
% 1 : 9
% 2 : 8
% 4 : 6
% 8 : 9