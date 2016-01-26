function[e1, e2, ltg] = pruebaJava(img, x, y, z);
im1=imread(img);
imgA = im2double(rgb2gray(im1));
imgB= adapthisteq(imgA,'NumTiles',[x,y],'clipLimit',z);
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
e1= entropy(im1);
e2= entropy(im2);
% if((e2/e1)>=1)
%     imwrite(imgB, fullfile('E:\Mis Documentos\imagenPrueba\ImagenesJavaLTG_2\', nombreArchivo));
% end

ltg=ltg_index(im1,im2);
 