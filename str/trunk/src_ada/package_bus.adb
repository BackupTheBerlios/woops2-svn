-- Author: eperico
with package_types, package_busnetwork, package_constantes, Text_io, interfaces.C.strings;
use package_types, package_busnetwork, package_constantes, Text_io, interfaces.C.strings;

package body package_bus is
	
    -------------
    -- Tache Bus
    -------------
    task body tt_bus is
    
        busId : int := idBus;
        ptr_pos : t_ptr_t_position := initialPosition;
        lineId : int := idLine;
        
        speed : int := 0;
        isStarted : boolean := false;
        hasProblem : boolean := false;
        -- l'index precise l'index du dernier arret auquel le bus s'est arrete
        IndexOfCurrentBusStop : integer := 1;
        
        -- message permettant l'envoie d'informations diverses au centre
        message : chars_ptr ;
                
        ------------------------------------------------
        -- tache cyclique d'envoi de la position du bus
        ------------------------------------------------
        task t_sendPosition;
        
        task body t_sendPosition is
        begin
            loop
                if (isStarted) and (not hasProblem) then
                    -- envoi de la position toutes les 2 secondes
                    delay(PERIOD);                
                    put_line("tt_bus: envoi de la position");
                    put("==> Vitesse du bus "&int'image(busId)&": ");put_line(int'image(speed));
                    display(ptr_pos.all);
                    Radio.sendPositionToCenter(ptr_pos, speed, busId); 
                end if;               
            end loop;
        end t_sendPosition;
        
        -----------------------------------------------
        -- tache cyclique pour le compteur de distance
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
            nextBusStop : int;
            
        begin
            loop
                if (isStarted) and (ptr_pos.all.distance >= TOTALDISTANCE) then
                    stop;
                    delay(1.0);
                elsif (not isStarted) and (ptr_pos.all.distance > 0.0) and (not hasProblem) then
                    put_line("MISE A JOUR DE LA POSITION");
                    -- le bus est arrêté, on met a jour la position du bus
                    ptr_pos.all.distance := 0.0;
                    currentLine := BusNetwork.getLine(ptr_pos.all.lineNumber);
                    IndexOfCurrentBusStop := IndexOfCurrentBusStop + 1; 
                    nextBusStop := currentLine.busStopTable(IndexOfCurrentBusStop);
                    
                    -- on teste le prochain arrêt de bus pour verifier qu'il n'est pas le dernier
                    --if nextBusStop = 0 then
                    if (currentLine.busStopTable(IndexOfCurrentBusStop + 1) = 0) then
                        put_line("LE BUS" &int'image(busId)& " ARRIVE AU TERMINUS DE LA LIGNE " &int'image(lineId));
                        message := new_string("Les passagers descendent du bus...");
                        IndexOfCurrentBusStop := 1;
                        ptr_pos.all.busStopId := currentLine.busStopTable(1);
                        ptr_pos.all.distance := 0.0;
                    else
                        ptr_pos.all.busStopId := nextBusStop;
                        -- on simule l'entrée de passagers dans le bus
                        message := new_string("Les passagers montent dans le bus...");
                    end if;
                                        
                    receiveMessage(message);
                    delay(WAITING_TIME);
                    put_line("tt_bus: Le bus" & int'image(busId) & " quitte l'arrêt " & int'image(ptr_pos.all.busStopId));
                    start;
                end if;               
            end loop;
        end t_Sensor;
      
    begin        
        loop
            select
                accept getBusId(id : out int)
                do
                    id :=  busId;
                end getBusId;
            or    
                when (not hasProblem) =>
                accept start;
                    isStarted := true;
                    speed := 5;
                    put_line("tt_bus: Le bus"& int'image(busId) & " a démarré");
            or                            
                accept stop;
                    isStarted := false;
                    speed := 0;
                    Radio.sendPositionToCenter(ptr_pos, speed, busId); 
            or
                when (isStarted) and (speed < MAX_SPEED) and (not hasProblem) =>
                accept accelerate;
                    -- on augmente la vitesse de 5 km/h
                    speed := speed + 5;
                    put_line("tt_bus: Le bus"& int'image(busId) & " accelère");
            or
                when (isStarted) and (speed > 5) and (not hasProblem) =>
                accept decelerate;
                    -- on diminue la vitesse de 5 km/h
                    speed := speed - 5;
                    put_line("tt_bus: Le bus"& int'image(busId) & " décélère");
            or
                accept simulatePB(ptr_code : in t_ptr_t_code)
                do
                    hasProblem := true;
                    put_line("tt_bus: Le bus"& int'image(busId) & " a un PROBLEME");
                    Radio.sendPriorityMessage(new t_priorityMessage'(busId, ptr_code));
                    if ptr_code.all = BREAKDOWN then
                        speed := 0;
                        put_line("tt_bus: Bus"& int'image(busId) & " en réparation.....");
                        Radio.sendPositionToCenter(ptr_pos, speed, busId); 
                        delay(REPAIR_TIME);
                        hasProblem := false;
                        speed := 5;
                        put_line("tt_bus: Le bus"& int'image(busId) & " redémarre");
                    else
                        isStarted := false;
                        speed := 0;
                        Radio.sendPositionToCenter(ptr_pos, speed, busId); 
                    end if;
                end simulatePB;
            end select;                              
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
        end sendPositionToCenter;
        
        procedure sendPriorityMessage(ptr_mes : in t_ptr_t_priorityMessage) is
        begin
            display(ptr_mes.all);
            -- le centre recoit le message d'urgence
            receivePriorityMessage(ptr_mes);
        end sendPriorityMessage;
        
    end Radio;
    
end package_bus;
