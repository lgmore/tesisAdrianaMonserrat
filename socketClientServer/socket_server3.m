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
disp('Waiting for connection');
fopen(t);
disp('Connection OK');
pause(1); 
% Read data from the socket
%for i=0:10
while (t.BytesAvailable > 0)
    DataReceived=fscanf(t)
end
%end

DataToSend='hola si hola si'
fwrite(t,DataToSend);
pause (0.5);

% Close and delete connection
fclose(t);
delete(t);

