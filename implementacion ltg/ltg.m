function resultado = ltg(imagenoriginalparam, imagentransformadaparam)

C = [.5 .5];

imagenoriginal = imread(imagenoriginalparam);
imagentransformada = imread(imagentransformadaparam);

yiq_imagenoriginal =  rgb2ntsc(imagenoriginal);
yiq_imagendistorsionada =  rgb2ntsc(imagentransformada);

GM_x = Scharr(yiq_imagenoriginal(:,:,1));
GM_y = Scharr(yiq_imagendistorsionada(:,:,2));

GM_xy = (2 .* GM_y .* GM_y + C(1)) ./ (GM_x .^2 + GM_y.^2 + C(2));

G_g=mean2(GM_xy);

M=numel(GM_x);
s15=round(M*.15);
B = sort(GM_xy(:),'descend');
sum=0;
for i=1:s15
  sum =sum+B(i);    
end 
G_s=sum/s15;



I_x= (yiq_imagenoriginal(:,:,2));
I_y= (yiq_imagendistorsionada(:,:,2));

Q_x= (yiq_imagenoriginal(:,:,3));
Q_y= (yiq_imagendistorsionada(:,:,3));

I_mxy =  (2 .* I_x .* I_y + C(2)) ./ (I_x .^2 + I_y .^2 + C(2));
Q_mxy =  (2 .* Q_x .* Q_y + C(2)) ./ (Q_x .^2 + Q_y .^2 + C(2));

mediaIQ = mean2(I_mxy .* Q_mxy);

resultado = (G_s ./ G_g) .* (mediaIQ);

resultado;

