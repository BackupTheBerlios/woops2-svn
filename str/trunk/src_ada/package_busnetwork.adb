-- Author: eperico
with package_types, package_bus, package_busstop, package_constantes, Text_io, interfaces.C;
use package_types, package_bus, package_busstop, package_constantes, Text_io, interfaces.C;

package body package_busnetwork is
    
    invalidLineNumber : exception;
    
    ----------------------------
    -- objet protégé BusNetwork
    ----------------------------
	protected body BusNetwork is
        
        function getLine(lineId : in int) return t_line is
        begin
            if lineId = 12 then
                return line_12;
            elsif lineId = 24 then
                return line_24;
            else
                raise invalidLineNumber;
            end if;
        end getLine;
        
        procedure setLine(lineId : in int; line : in t_line) is
        begin
            if lineId = 12 then
                line_12 := line;
            elsif lineId = 24 then
                line_24 := line;
            else
                raise invalidLineNumber;
            end if;
        end setLine;
    
    end BusNetwork;
        
    -------------------------
    -- Définition des pragma 
    -------------------------
    
    -- les index 1 et 2 pointent respectivement sur la première case vide (ici vide = 0) 
    -- du tableau d'arrêts de bus de la ligne concernée
    index1 : integer := BusNetwork.getLine(12).busStopTable'first;
    index2 : integer := BusNetwork.getLine(24).busStopTable'first;
    
    -- initialisation d'un arrêt de bus
    procedure p_initBusStop(id_busstop : in int; line : in int) is
        busstop : pt_BusStop;
        concernedLine : t_line;
    
    begin
        put_line("### busNetwork : p_initBusStop");
        busstop.setBusStopId(id_busstop);
        busstop.setLineId(line);
        
        --ajout du busstop créé sur la ligne passée en paramètre
        concernedLine := BusNetwork.getLine(line);
        if line = 12 then
            concernedLine.busStopTable(index1) := id_busstop;
            index1 := index1 + 1;
        elsif line = 24 then            
            concernedLine.busStopTable(index2) := id_busstop;
            index2 := index2 + 1;
        else
            raise invalidLineNumber;
        end if;
            BusNetwork.setLine(line, concernedLine);
        
        put("busStop n°");put(int'image(id_busstop));
        put(" ajouté sur la ligne");put_line(int'image(line));
        put("Etat de la ligne: ");display(BusNetwork.getLine(line));
        
        exception
            when invalidLineNumber => put_line("Numéro de ligne invalide");
    
    end p_initBusStop;
    
    
    -- initialisation d'un bus
    procedure p_initBus(id_bus : in int; line : in int) is
        startingBusStop : int;
        ptr_position : t_ptr_t_position;
        ptr_bus : t_ptr_tt_bus;
    begin
        put_line("### busNetwork : p_initBus");
        -- on prend le premier arrêt de bus de la ligne passée en paramètre
        startingBusStop := BusNetwork.getLine(line).busStopTable(1); 
        
        -- on positionne le bus en début de ligne
        put("starting busstop de la ligne "&int'image(line)&": ");put_line(int'image(startingBusStop));
        ptr_position := new t_position'(line, startingBusStop, 0.0); 
        
        ptr_bus := new tt_bus(id_bus, ptr_position);
        put("creation du bus n°");put(int'image(id_bus)); 
        put(" sur la ligne");put_line(int'image(line));  
        -- démarrage du bus
        ptr_bus.all.start;
        
        exception
            when invalidLineNumber => put_line("Numéro de ligne invalide");
            
    end p_initBus;
    
begin
    
	put_line("### initialisation des objets Ada ###");

end package_busnetwork;

