function[e1, e2, ssimval] = pruebaJavaSSIM1(img, x, y, z)
im1=imread(img);
imgA = im2double(rgb2gray(im1));
imgB= adapthisteq(imgA,'NumTiles',[x,y],'clipLimit',z);
% nombreArchivo=strcat('imagen26','_vent_',num2str(x),'_',num2str(y),'.png');
% nombreDestino= 'E:\Mis Documentos\imagenPrueba\ImagenesJavaSSIM1_1\';
% imwrite(imgB, fullfile(nombreDestino, nombreArchivo));

e1= entropy(imgA);
e2= entropy(imgB);
% if((e2/e1)>=1)
%     imwrite(imgB, fullfile('E:\Mis Documentos\imagenPrueba\ImagenesJavaSSIM1_MOD_2\', nombreArchivo));
% end

ssimval=ssim(imgA,imgB);
 