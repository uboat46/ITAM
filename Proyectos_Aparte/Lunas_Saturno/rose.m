hold on;
t = linspace(0,8*pi,201);
r = sqrt(abs(4*sin((1/8)*t)));
[x y]=pol2cart(t,r);
z= 0.1*(x.^2+y.^2);
fill3(-x+30,-y+30,z, 'k')
alpha(0.7)
grid on;

hold on;
t = linspace(0,8*pi,201);% cantidad de giros
r = sqrt(abs(3*sin((1/2)*t))); %tamaño de petalo
[x y]=pol2cart(t,r);
z= 0.1*(x.^2+y.^2);% crecimiento en z
fill3(-x+30,-y+30,z, 'b')
alpha(0.7)
grid on;