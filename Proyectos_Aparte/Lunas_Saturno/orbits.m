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

twoMoonOrbit(orbit(3,1), orbit(3,2), orbit(5,1), orbit(5,2), 720);