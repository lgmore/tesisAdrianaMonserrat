function[e1, e2, ltg] = pruebaJava(img, x, y, z)
%im1=imread(img);
%imgA = im2double(rgb2gray(im1));
%imgA = rgb2gray(im1);
[RGB MAP] = imread(img);
%RGB = ind2rgb(X,MAP); % convert indexed image to truecolor format
cform2lab = makecform('srgb2lab');
LAB = applycform(RGB, cform2lab); %convert image to L*a*b color space
L = LAB(:,:,1)/100; % scale the values to range from 0 to 1
tic
LAB(:,:,1)= adapthisteq(L,'NumTiles',[x,y],'clipLimit',z)*100;
toc
cform2srgb = makecform('lab2srgb');
imgB = applycform(LAB, cform2srgb); %convert back to RGB

%pause(1);

nombreArchivo=strcat('imagen26','_vent_',num2str(x),'_',num2str(y),'.png');
nombreDestino= 'I:\tesisAdrianaMonserrat2\tesisAdrianaMonserrat\imagenesPrueba\';
imwrite(imgB, fullfile(nombreDestino, nombreArchivo));

[im, map] = imread(strcat(nombreDestino,nombreArchivo));
if(isempty(map))                % image is RGB or grayscale
    if(size(im, 3) == 1)        % image is grayscale
        RGB = cat(3, im, im, im);
    end
else                            % image is indexed
    RGB = ind2rgb(im, map);
end                             % now 'im' is a RGB-image 

im2=RGB;
%e1= entropy(im1);
e1 = entropy(RGB);
e2= entropy(im2);
% if((e2/e1)>=1)
%     imwrite(imgB, fullfile('E:\Mis Documentos\imagenPrueba\ImagenesJavaLTG_2\', nombreArchivo));
% end

%ltg=ltg_index(im1,im2);
ltg=ltg_index(RGB,im2); 