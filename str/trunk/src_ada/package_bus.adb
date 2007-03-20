-- Author: eperico
with package_types, package_busnetwork, package_constantes, Text_io;
use package_types, package_busnetwork, package_constantes, Text_io;

package body package_bus is
	
    -------------
    -- Tâche Bus
    -------------
    task body tt_bus is
    
        id : int := idBus;
        ptr_pos : t_ptr_t_position := initialPosition;
        speed : int := 0;
        isStarted : boolean := false;
        -- l'index précise l'index du dernier arrêt auquel le bus s'est arrêté
        IndexOfCurrentBusStop : integer := 1;
                
        ------------------------------------------------
        -- tâche cyclique d'envoi de la position du bus
        ------------------------------------------------
        task t_sendPosition;
        
        task body t_sendPosition is
        begin
            loop
                if (isStarted) then
                    -- envoi de la position toutes les 2 secondes
                    delay(periode);                
                    put_line("tt_bus: envoi de la position");
                    put("vitesse du bus "&int'image(id)&": ");put_line(int'image(speed));
                    -- on simule l'accélération du bus
                    if speed < 30 then
                        speed := speed + 10;
                    end if;
                    display(ptr_pos.all);
                    Radio.sendPositionToCenter(ptr_pos, speed, id); 
                end if;               
            end loop;
        end t_sendPosition;
        
        -----------------------------------------------
        -- tâche cyclique pour le compteur de distance
        -----------------------------------------------
        task t_Odometer;
        
        task body t_Odometer is
            distanceMeterPerSecond : C_float;
        begin
            loop
                if (isStarted) then
                    -- calcul de la distance parcourue
                    distanceMeterPerSecond := C_float(speed) * 1000.0/3600.0;
                    ptr_pos.all.distance := ptr_pos.all.distance + distanceMeterPerSecond;
                    delay(1.0);   
                end if;               
            end loop;
        end t_Odometer;
        
        
        ----------------------------------
        -- tâche cyclique pour le capteur
        ----------------------------------
        task t_Sensor;
        
        task body t_Sensor is
            currentLine : t_line;
        begin
            loop
                if (isStarted) and (ptr_pos.all.distance >= totalDistance) then
                    put_line("Distance > 100m");
                    stop;
                    delay(1.0);
                elsif (not isStarted) and (ptr_pos.all.distance > 0.0) then
                    put_line("MISE A JOUR DE LA POSITION");
                    -- le bus est arrêté
                    -- on met a jour la position du bus
                    ptr_pos.all.distance := 0.0;
                    currentLine := BusNetwork.getLine(ptr_pos.all.lineNumber);
                    IndexOfCurrentBusStop := IndexOfCurrentBusStop + 1; 
                    ptr_pos.all.busStopId := currentLine.busStopTable(IndexOfCurrentBusStop);
                    -- on simule l'entrée de passagers dans le bus
                    put_line("Les passagers montent dans le bus....");
                    delay(8.0);
                    put_line("tt_bus: Le bus" & int'image(id) & " quitte l'arrêt " & int'image(ptr_pos.all.busStopId));
                    start;
                end if;               
            end loop;
        end t_Sensor;
        
    begin        
        loop
            accept start;
                put_line("tt_bus: Le bus"& int'image(id) & " a démarré");
                isStarted := true;
                                    
            accept stop;
                isStarted := false;
                -- TEMPORAIRE: arrêt net du bus
                speed := 0;
                put_line("tt_bus: Le bus"& int'image(id) & " est arrêté");
            
            accept accelerate;
                if speed = 50 then
                    put_line("tt_bus: Le bus"& int'image(id) & " a atteind la vitesse limite");
                else
                    put_line("tt_bus: Le bus"& int'image(id) & " accelère");
                    speed := speed + 10;
                end if;
                                
            accept decelerate;
                if speed = 0 then
                    put_line("tt_bus: Le bus"& int'image(id) & " est arrêté");
                else
                    put_line("tt_bus: Le bus"& int'image(id) & " décélère");
                    speed := speed - 10;
                end if;                       
        end loop;
    end tt_bus;

    -----------------------
    -- objet protégé Radio
    -----------------------
    protected body Radio is
        
        procedure sendPositionToCenter(ptr_pos : in t_ptr_t_position; speed : in int; busId : in int) is
        begin
            -- le centre recoit la position du bus
            receivePosition(ptr_pos, speed, busId);
            --null;
        end sendPositionToCenter;
        
        --procedure sendPriorityMessage(ptr_mes : out t_ptr_t_priorityMessage);
        --procedure receiveCommand(ptr_com : in t_ptr_t_action);
   end Radio;
    
end package_bus;
