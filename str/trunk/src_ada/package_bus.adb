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
                -- envoi de la position toutes les 20 secondes
                delay(periode);                
                --Sensor.getCurrentPosition(ptr_pos);
                put_line("tt_bus: envoi de la position");
                Radio.sendPositionToCenter(ptr_pos, speed, id);                
            end loop;
        end tt_sendPosition;
        
    begin
        
        loop
            accept start;
                put_line("tt_bus: Le bus"& int'image(id) & " a démarré");
                isStarted := true;
                Sensor.setCurrentPosition(initialPosition.all);            
        end loop;
    end tt_bus;

    --------------------------
    -- objet protégé Odomètre
    --------------------------
    protected body Odometer is
        
        procedure getCurrentDistance(d : out C_float) is
        begin
            d := Odometer.currentDistance;
        end getCurrentDistance;

        procedure setCurrentDistance(d : in C_float) is
        begin
            Odometer.currentDistance := d;
        end setCurrentDistance;

        procedure getTotalCoveredDistance(d : out C_float) is
        begin
            d := Odometer.totalCoveredDistance;
        end getTotalCoveredDistance;

        procedure setTotalCoveredDistance(d : in C_float) is
        begin
            Odometer.totalCoveredDistance := d;
        end setTotalCoveredDistance;

        procedure updateDistance(dis : in out C_float) is
        begin
            dis := Odometer.currentDistance;
        end updateDistance;

    end Odometer;   

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

        procedure updateBusStop(bs : in out int) is
        begin
            bs := Sensor.currentPosition.busStopId;
        end updateBusStop;

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
