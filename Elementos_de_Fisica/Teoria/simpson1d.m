function integral = simpson1d(f,a,b)


num = length(f);               % number of data points

sc = 2*ones(num,1);
sc(2:2:num-1) = 4;
sc(1) = 1; sc(num) = 1;

h = (b-a)/(num-1);

integral = (h/3) * f * sc;

end



