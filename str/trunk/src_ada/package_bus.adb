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
                    if speed < 30 then
                      speed := speed + 5;
                    end if;                
                    --Sensor.getCurrentPosition(ptr_pos.all);
                    put_line("tt_bus: envoi de la position");
                    put("vitesse du bus: ");put_line(int'image(speed));
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
                    if ptr_pos.all.distance >= 100.0 then
                        put_line("ON y EST");
                        stop;
                    end if;     
                end if;               
            end loop;
        end t_Odometer;
        
        
        -----------------------------------------------
        -- tâche cyclique pour le capteur
        -----------------------------------------------
        task t_Sensor;
        
        task body t_Sensor is
            currentLine : t_line;
        begin
            loop
                if (not isStarted) and (ptr_pos.all.distance >= 100.0) then
                    put_line("ON EST A L'ARRET");
                    stop;
--                elsif (not isStarted) then
--                    put_line("MISE A JOUR DE LA POSITION");
--                    -- TESTER LA MISE A JOUR DE LA POSITION
--                    -- le bus est arrêté
--                    -- on met a jour la position du bus
--                    ptr_pos.all.distance := 0.0;
--                    currentLine := BusNetwork.getLine(ptr_pos.all.lineNumber);
--                    IndexOfCurrentBusStop := IndexOfCurrentBusStop + 1; 
--                    ptr_pos.all.busStopId := currentLine.busStopTable(IndexOfCurrentBusStop);
--                    -- on simule l'entrée de passagers dans le bus
--                    delay(5.0);
                end if;               
            end loop;
        end t_Sensor;
        
    begin        
        loop
            accept start;
                put_line("tt_bus: Le bus"& int'image(id) & " a démarré");
                isStarted := true;
                --Sensor.setCurrentPosition(initialPosition.all);
            
            accept stop;
                isStarted := false;
                put_line("tt_bus: Le bus"& int'image(id) & " est arrêté");
                --Sensor.setCurrentPosition(initialPosition.all);            
        end loop;
    end tt_bus;

    -------------------------
    -- objet protégé Capteur
    -------------------------
    protected body Sensor is
   
        procedure getCurrentPosition(p : out t_position) is
        begin
            p := Sensor.currentPosition;
        end getCurrentPosition;

        procedure setCurrentPosition(p : in t_position) is
        begin
            Sensor.currentPosition := p;
        end setCurrentPosition;

        procedure updateBusPosition(bs : in out int) is
        begin
            bs := Sensor.currentPosition.busStopId;
        end updateBusPosition;

    end Sensor;

    -----------------------
    -- objet protégé Radio
    -----------------------
    protected body Radio is
        
        procedure sendPositionToCenter(ptr_pos : in t_ptr_t_position; speed : in int; busId : in int) is
        begin
            -- le centre recoit la position du bus
            --receivePosition(ptr_pos, speed, busId);
            null;
        end sendPositionToCenter;
        
        --procedure sendPriorityMessage(ptr_mes : out t_ptr_t_priorityMessage);
        --procedure receiveCommand(ptr_com : in t_ptr_t_action);
   end Radio;
    
end package_bus;
