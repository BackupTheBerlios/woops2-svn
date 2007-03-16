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
                
        ------------------------------------------------
        -- tâche cyclique d'envoi de la position du bus
        ------------------------------------------------
        task tt_sendPosition;
        
        task body tt_sendPosition is
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
        end tt_sendPosition;
        
        -----------------------------------------------
        -- tâche cyclique pour le compteur de distance
        -----------------------------------------------
        task tt_Odometer;
        
        task body tt_Odometer is
            distanceMeterPerSecond : int;
        begin
            loop
                if (isStarted) then
                    -- calcul de la distance parcourue
                    distanceMeterPerSecond := speed * 1000/3600;
                    ptr_pos.all.distance := C_float(distanceMeterPerSecond);
                    delay(1.0);                    
                end if;               
            end loop;
        end tt_Odometer;
        
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

--    --------------------------
--    -- objet protégé Odomètre
--    --------------------------
--    protected body Odometer is
--        
--        procedure getCurrentDistance(d : out C_float) is
--        begin
--            d := Odometer.currentDistance;
--        end getCurrentDistance;
--
--        procedure setCurrentDistance(d : in C_float) is
--        begin
--            Odometer.currentDistance := d;
--        end setCurrentDistance;
--
--        procedure getTotalCoveredDistance(d : out C_float) is
--        begin
--            d := Odometer.totalCoveredDistance;
--        end getTotalCoveredDistance;
--
--        procedure setTotalCoveredDistance(d : in C_float) is
--        begin
--            Odometer.totalCoveredDistance := d;
--        end setTotalCoveredDistance;
--
--        procedure updateDistance(dis : in out C_float) is
--        begin
--            dis := Odometer.currentDistance;
--        end updateDistance;
--
--    end Odometer;   

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
