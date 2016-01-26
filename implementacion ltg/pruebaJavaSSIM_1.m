function[e1, e2, ssimval] = pruebaJavaSSIM_1(img, x, y, z)
im1=imread(img);
imgA = im2double(rgb2gray(im1));
imgB= adapthisteq(imgA,'NumTiles',[x,y],'clipLimit',z);
nombreArchivo=strcat('imagen','_vent',num2str(x),'.png');
nombreDestino= 'E:\Mis Documentos\imagenPrueba\ImagenesJavaSSIM_1MOD\';
imwrite(imgB, fullfile(nombreDestino, nombreArchivo));

e1= entropy(imgA);
e2= entropy(imgB);
if((e2/e1)>=1)
    imwrite(imgB, fullfile('E:\Mis Documentos\imagenPrueba\ImagenesJavaSSIM_1MOD_2\', nombreArchivo));
end
ssimval=ssim_index1(imgA,imgB);
 