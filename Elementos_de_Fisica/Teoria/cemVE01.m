
clear all
close all
clc
tic


% INPUTS  ================================================================

% Number of grid point    [N = 1001]
   N = 1001;
   
% Charge  Q = [20, 0, 0, 0, 0] 
   Q = [20, 0, 0, 0, 0] .* 1e-6;
   
% Radius of circular charged conductor;   
   a = 0.2;
   
% X & Y components of position of charges  [0, 0, 0, 0, 0]
   xC = [0,  0,  0, 0, 0];
   yC = [0,  0,  0, 0, 0];

% 5 random charges   uncomment to run the program for 5 random charges
%   Q = (1 + 9 .* rand(5,1)) .* 1e-6;
%   xC = -2 + 4 .* rand(5,1); 
%   yC = -2 + 4 .* rand(5,1); 

% constants
   eps0 = 8.854e-12;
   kC = 1/(4*pi*eps0);
% Dimensions of region / saturation levels
%   [dimensions of region -2 to 2 / minR = 1e-6 / Esat = 1e6 / Vsat = 1e6]
   minX = -2;  
   maxX =  2;
   minY = -2;
   maxY =  2;
   minR = 1e-6;
   minRx = 1e-6;
   minRy = 1e-6;
   Vsat = kC * max(abs(Q)) / a;
   Esat = kC * max(abs(Q)) / a^2;
   
% SETUP  =================================================================
   
  % fields
    V = zeros(N,N);
    Ex = zeros(N,N); Ey = zeros(N,N);
  % [2D] region
    x  = linspace(minX,maxX,N);
    y = linspace(minY, maxY,N);
    
  % color of charged object  +  red   /   - black
    col1 = [1 0 0];
    if Q(1) < 0; col1 = [0 0 0]; end;
        
  % grid positions
    [xG, yG] = meshgrid(x,y);


% CALCULATION: POTENTIAL & ELECTRIC FIELD ================================

for n = 1 : 5
   Rx = xG - xC(n);
   Ry = yG - yC(n);
   
   index = find(abs(Rx)+ abs(Ry) == 0); 
   Rx(index) = minRx;  Ry(index) = minRy;
   
   R = sqrt(Rx.^2 + Ry.^2);
   R(R==0) = minR;
   V = V + kC .* Q(n) ./ (R);
   
   R3 = R.^3;
   Ex = Ex + kC .* Q(n) .* Rx ./ R3;
   Ey = Ey + kC .* Q(n) .* Ry ./ R3;
end

   if max(max(V)) >=  Vsat; V(V > Vsat)  = Vsat; end;
   if min(min(V)) <= -Vsat; V(V < -Vsat) = -Vsat; end;

   E = sqrt(Ex.^2 + Ey.^2);
   if max(max(E)) >=  Esat; E(E >  Esat)  =  Esat; end;
   if min(min(E)) <= -Esat; E(E < -Esat)  = -Esat; end;
   
   if max(max(Ex)) >=  Esat; Ex(Ex >  Esat)  =  Esat; end;
   if min(min(Ex)) <= -Esat; Ex(Ex < -Esat)  = -Esat; end;
   
   if max(max(Ey)) >=  Esat; Ey(Ey >  Esat)  =  Esat; end;
   if min(min(Ey)) <= -Esat; Ey(Ey < -Esat)  = -Esat; end;
   
%%
% Calcuation   LINE INTEGRAL     Nx1 Nx2 Ny1 Ny2  must all be ODD numbers
Nx1 = 551; Nx2 = Nx1 + 210;      % must add an EVEN number
Ny1 = 61;  Ny2 = Ny1 + 730;      % must add an EVEN number
f = Ex(Ny1,Nx1:Nx2);             % f must have an ODD number of elements
sx1 = x(Nx1); sx2 = x(Nx2);
Vx = -simpson1d(f,sx1,sx2);
f = Ey(Ny1:Ny2,Nx2)';
sy1 = y(Ny1); sy2 = y(Ny2);
Vy = -simpson1d(f,sy1,sy2);
V21 = Vx + Vy;
dV = V(Ny2,Nx2) - V(Ny1,Nx1);


%%

% GRAPHICS ===============================================================

%%

%%   
figure(2)   %2222222222222222222222222222222222222222222222222222222222222
   set(gcf,'units','normalized','position',[0.25 0.52 0.23 0.32]);
   zP = V./1e6;
   contourf(xG,yG,zP,16);
   %set(gca,'xLim',[-5,5]); set(gca,'yLim', [-5, 5]);
   %set(gca,'xTick',-5:5); set(gca,'yTick', -5:5);
   hold on
   
   pos = [-a, -a, 2*a, 2*a];
   h = rectangle('Position',pos,'Curvature',[1,1]);
   set(h,'FaceColor',col1,'EdgeColor',col1);
   
   xlabel('x  [m]'); ylabel('y  [m]');
   title('potential','fontweight','normal');
      
   shading interp
   h = colorbar;
   h.Label.String = 'V   [ MV ]';
   colormap(parula);
   
   
   set(gca,'fontsize',12);
   axis square
   box on

 
     %%

     toc
     
   