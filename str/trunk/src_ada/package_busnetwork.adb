-- Author: eperico
with package_types, package_bus, package_busstop, package_constantes, Text_io, interfaces.C;
use package_types, package_bus, package_busstop, package_constantes, Text_io, interfaces.C;

package body package_busnetwork is
    
    invalidLineNumber : exception;
    invalidBusId : exception;
    BusTableIsFull : exception;
    
    -- indexBus pointe sur la première case vide (=0) du tableau de pointeur sur les tâches bus
    indexBus : integer := 1;
    
    -- les index 1 et 2 pointent respectivement sur la première case vide (ici vide = 0) 
    -- du tableau d'arrêts de bus de la ligne concernée
    index1 : integer := 1;
    index2 : integer := 1;
    
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
        
        function getBusTable return BusTabType is
        begin
            return BusTable;
        end getBusTable;
        
        -- retourne le pointeur sur la tache d'un bus concerné par l'id en paramètre
        function getBusById(id : in int) return t_ptr_tt_bus is
        currentBusId : int;
        begin
            for i in BusTable'first..BusTable'last loop
                if (BusTable(i) = null) then
                    raise invalidBusId;
                else
                    BusTable(i).all.getBusId(currentBusId);
                    if currentBusId = id then
                        put_line("Le bus trouvé est à la position "&integer'image(i));
                        return BusTable(i);
                    end if;
                end if;
            end loop;
            return null;
        end getBusById;
        
        -- ajoute un bus dans le tableau de Bus
        procedure addBus(bus : in t_ptr_tt_bus) is
        begin
            if indexBus = NB_BUS then
                raise BusTableIsFull;
            end if;
            BusTable(indexBus) := bus;
            put_line("Ajout d'un bus à la position "&integer'image(indexBus));
            indexBus := indexBus + 1;  
        end addBus;
        
        -- ajoute un arrêt de bus dans la tableau de la ligne passée en paramètre
        procedure addBusStopOnLine(id_busStop : in int; id_line : in int) is
            concernedLine : t_line; 
        begin
            concernedLine := BusNetwork.getLine(id_line);
            if id_line = 12 then
                concernedLine.busStopTable(index1) := id_busStop;
                index1 := index1 + 1;
            elsif id_line = 24 then            
                concernedLine.busStopTable(index2) := id_busStop;
                index2 := index2 + 1;
            else
                raise invalidLineNumber;
            end if;
                BusNetwork.setLine(id_line, concernedLine);
            
            put("busStop n°");put(int'image(id_busStop));
            put(" ajouté sur la ligne");put_line(int'image(id_line));
            put("Etat de la ligne: ");display(BusNetwork.getLine(id_line));
        end addBusStopOnLine;
    
    end BusNetwork;
        
    -------------------------
    -- Définition des pragma 
    -------------------------
    
    ---------------------------------
    -- Initialisation des objets ADA
    ---------------------------------
    
    -- initialisation d'un arrêt de bus
    procedure p_initBusStop(id_busstop : in int; line : in int) is
        busstop : pt_BusStop;
        --concernedLine : t_line;
    
    begin
        put_line("### busNetwork : p_initBusStop");
        busstop.setBusStopId(id_busstop);
        busstop.setLineId(line);
        
        --ajout du busstop créé sur la ligne passée en paramètre
        BusNetwork.addBusStopOnLine(id_busstop, line);
                
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
             
        ptr_bus := new tt_bus(id_bus, ptr_position, line);
        put("creation du bus n°");put(int'image(id_bus)); 
        put(" sur la ligne");put_line(int'image(line));
        
        -- ajout du bus créé dans le tableau de bus du réseau   
        BusNetwork.addBus(ptr_bus);
                
        exception
            when invalidLineNumber => put_line("Numéro de ligne invalide");
            when BusTableIsFull => put_line("Le tableau de bus est plein");
            
    end p_initBus;
    
    ---------------------
    -- Commandes des bus
    ---------------------
    
    procedure p_startBus(id_bus : in int) is
    begin
        put_line("### busNetwork : p_startBus");
        BusNetwork.getBusById(id_bus).all.start;
    exception
        when invalidBusId => put_line("Numéro de bus invalide");
    end p_startBus;
    
    procedure p_stopBus(id_bus : in int) is
    begin
        put_line("### busNetwork : p_stopBus");
        BusNetwork.getBusById(id_bus).all.stop;
    exception
        when invalidBusId => put_line("Numéro de bus invalide");
    end p_stopBus;
    
    procedure p_accelerateBus(id_bus : in int) is
    begin
        put_line("### busNetwork : p_accelerateBus");
        BusNetwork.getBusById(id_bus).all.accelerate;
    exception
        when invalidBusId => put_line("Numéro de bus invalide");
    end p_accelerateBus;
    
    procedure p_decelerateBus(id_bus : in int) is
    begin
        put_line("### busNetwork : p_decelerateBus");
        BusNetwork.getBusById(id_bus).all.decelerate;
    exception
        when invalidBusId => put_line("Numéro de bus invalide");
    end p_decelerateBus;
    
    ------------------------------------------------------------
    -- Simulation de problème et envoi de messages prioritaires
    ------------------------------------------------------------
    -- reception d'une simulation de problème depuis l'interface
    procedure p_simulateProblem(ptr_mes : in t_ptr_t_priorityMessage) is
    begin
        BusNetwork.getBusById(ptr_mes.all.busId).all.simulatePB(ptr_mes.all.code);
    end p_simulateProblem;
    
begin
    
	put_line("### initialisation des objets Ada ###");

end package_busnetwork;

