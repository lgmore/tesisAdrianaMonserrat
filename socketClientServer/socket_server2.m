%% Java TCP Server
% Example implementation of a java tcp server

function socket_server2()

import java.net.*;
import java.io.*;


    %% Create Socket
    port = input('Enter port number: ');
    Server = ServerSocket (port);

    %% Listen to connection
    disp('Waiting for a connection...')
    connected = Server.accept;
    
    
    iStream = connected.getInputStream;
    oStream = connected.getOutputStream;
    cleanupObj = onCleanup(@()cleanMeUp(connected,iStream,oStream));
    % Greets the client
    %oStream.sendS('Welcome client!')
     disp('se conecto...')
    % Waiting for messages from client
    while ~(iStream.available)
    end
    readS(iStream);


    %% Communication
    msg = '';
    while true
     % Waits for messages from client
     while ~(iStream.available)
     end
     msg = readS(iStream);

     if isempty(strfind(msg,'!q'))
      % Sends message to client
      disp 'Server''s turn!'
      cmd = input('Toclient&gt;&gt; ', 's');
      oStream.sendS(cmd);
     else
      oStream.sendS(msg);
     end
    end
    pause(2);
    function cleanMeUp(connected,iStream,oStream)
        disp('Error caught')
        %fclose all
        
        connected.close
        iStream.close
        oStream.close
        pause(1)
        disp (['Connection ended: ' datestr(now)]);
    end    
    

end

