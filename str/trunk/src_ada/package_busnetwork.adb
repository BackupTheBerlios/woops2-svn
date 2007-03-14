-- Author: eperico
with package_types, package_bus, package_busstop, package_constantes, Text_io, interfaces.C;
use package_types, package_bus, package_busstop, package_constantes, Text_io, interfaces.C;

package body package_busnetwork is
    
    invalidLineNumber : exception;
    index : integer;
    --l'index pointe sur la première case vide (ici vide = 0) du tableau d'arrêts de bus
    
    ----------------------------
    -- objet protégé BusNetwork
    ----------------------------
    -- l'objet BusNetwork permet de partager les données entre les tâches
    -- les lignes du réseau doivent être paratgées
    protected BusNetwork is
        
        function getLine_1 return t_line;
        function getLine_2 return t_line;
        
        procedure setLine_1(line : in t_line); 
        procedure setLine_2(line : in t_line);       
        
        private
            line_1 : t_line := (lineNumber => 1, busStopTable => (others => 0));
            line_2 : t_line := (lineNumber => 2, busStopTable => (others => 0));
    end BusNetwork;

	protected body BusNetwork is
        
        function getLine_1 return t_line is
        begin
            return line_1;
        end getLine_1;
        
        function getLine_2 return t_line is
        begin
            return line_2;
        end getLine_2;
        
        procedure setLine_1(line : in t_line) is
        begin
            line_1 := line;
        end setLine_1; 
        
        procedure setLine_2(line : in t_line) is
        begin
            line_2 := line;
        end setLine_2;
    
    end BusNetwork;
        
    -------------------------
    -- Définition des pragma 
    -------------------------
    
    -- initialisation d'un arrêt de bus
    procedure p_initBusStop(id_busstop : in int; line : in int) is
        busstop : pt_BusStop;
        concernedLine : t_line;
    
    begin
        put_line("### busNetwork : p_initBusStop");
        busstop.setBusStopId(id_busstop);
        busstop.setLineId(line);
        
        --ajout du busstop créé sur la ligne passée en paramètre
        if line = 1 then
            concernedLine := BusNetwork.getLine_1;
            index := concernedLine.busStopTable'first;
            concernedLine.busStopTable(index) := id_busstop;
            BusNetwork.setLine_1(concernedLine);
            put("acces a l'element"& integer'image(index)); put_line(int'image(BusNetwork.getLine_1.BusStopTable(index)));
        elsif line = 2 then
            concernedLine := BusNetwork.getLine_2;
            index := concernedLine.busStopTable'first;
            concernedLine.busStopTable(index) := id_busstop;
            BusNetwork.setLine_2(concernedLine);
            put("acces a l'element"& integer'image(index)); put_line(int'image(BusNetwork.getLine_2.BusStopTable(index)));
        else
            raise invalidLineNumber;
        end if;
        
        index := index + 1;
        put("busStop n°");put(int'image(id_busstop));
        put(" ajouté sur la ligne");put_line(int'image(line));
        
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
        if (line = 1) then
            startingBusStop := BusNetwork.getLine_1.busStopTable(1); 
        elsif (line = 2) then
            startingBusStop := BusNetwork.getLine_2.busStopTable(1);
        else
            raise invalidLineNumber;        
        end if;    
        -- on positionne le bus en début de ligne
        ptr_position := new t_position'(line, startingBusStop, 0.0); 
        
        put("creation du bus n°");put(int'image(id_bus)); 
        put(" sur la ligne");put_line(int'image(line));  
        
        ptr_bus := new tt_bus(id_bus, ptr_position);
        -- démarrage du bus
        ptr_bus.all.start;
        -- TODO ajout du bus créé sur la ligne passée en paramètre
        
        exception
            when invalidLineNumber => put_line("Numéro de ligne invalide");
            
    end p_initBus;
    
begin
    
	put_line("### initialisation des objets Ada ###");

end package_busnetwork;

