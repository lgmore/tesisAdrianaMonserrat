%% Matlab script for testing TCP/IP communication with matlab
%% This script is the receiver part
%% Written by Philippe Lucidarme
%% http://www.lucidarme.me
 
%% TCP/IP Receiver
 
% Clear console and workspace
close all;
clear all;
clc;
 
% Configuration and connection
disp ('Receiver started');
t=tcpip('127.0.0.1', 5555,'NetworkRole','server');
 
% Wait for connection
 
% Read data from the socket
%for i=0:10

iterar= true;
disp('Waiting for connection');
fopen(t);
disp('Connection OK');
pause(1);
while iterar
    tic
    DataReceived=[];
    %tic
    while (t.BytesAvailable > 0)
        
        DataReceived=fscanf(t);
               
    end
    %recibir los parametros, procesar la imagen y devolver los valores
    %de entropia de las imagenes y ssim para evaluacion
    if (isempty(DataReceived))
        DataReceived=[];
    else
        C = strsplit(DataReceived,',');
        nombreimagen= C{1};
        ventanaX=str2double(C{2});
        ventanaY=str2double(C{3});
        clipLimit=str2double(C{4});

        %escribir la respuesta de la implementacion
        [entropiaOrig,entropiaEnhanced,ltg]=pruebaJava(nombreimagen, ventanaX, ventanaY, clipLimit);
        formatSpec = '%f,%f,%f';
        DataToSend= sprintf(formatSpec,entropiaOrig,entropiaEnhanced,ltg);
        fwrite(t,DataToSend);
        %pause (0.5);
        DataReceived=[];
    end
    %se controla el tiempo desde que inicia hasta que termina
    toc
end


%end
% Close and delete connection
fclose(t);
delete(t);

