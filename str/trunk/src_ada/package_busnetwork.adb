with package_types, package_bus, package_busstop, package_constantes, Text_io, interfaces.C;
use package_types, package_bus, package_busstop, package_constantes, Text_io, interfaces.C;

package body package_busnetwork is

	--type tabLines is array (1 .. nbLines) of  t_ptr_tt_line;
	type tabBusStop is array (1 .. nbBusStop) of  t_ptr_tt_busStop;
	--type tabBus is array (1 .. nbBus) of  t_ptr_tt_bus;
    
    ----------------------------
    -- objet protégé BusNetwork
    ----------------------------
    protected BusNetwork is
        
        procedure initLine(nb_line : in int);
        procedure initBusStop(nb_busstop : in int);
        procedure initBus(nb_bus : in int; line : in int);    
                
        private
            --lines : tabLines;
            busStopTable : tabBusStop;
            --bus : tabBus;
    end BusNetwork;

	protected body BusNetwork is

        procedure initLine(nb_line : in int) is
        begin
            put_line("### BusNetwork : initLine");
            put_line(int'image(nb_line));put(" lignes créées");
        end initLine;
        
    	procedure initBusStop(nb_busstop : in int) is
    		ptr_position : t_ptr_t_position := new t_position'(1,0.0);
    		ptr_bs : t_ptr_tt_busStop;
    	begin
    		put_line("### BusNetwork : initBusStop");
    		for i in 1..nb_busstop loop
    			ptr_bs := new tt_busStop(i, ptr_position);
    			busStopTable(i) := ptr_bs;
    		end loop;
    		put_line(int'image(nb_busstop));put(" busStop créés");
    		returnInitBusStop;
        end initBusStop;
        
        
        -- le numero de ligne est utile seulement ici pr ajouter le bus au tableua de ligne
        -- pas utile dans la tache
        procedure initBus(nb_bus : in int; line : in int) is
            ptr_position : t_ptr_t_position := new t_position'(1,0.0);
            --ptr_tt_bus : t_ptr_tt_bus;
            
        begin
            put_line("### BusNetwork : initBus");
            --for i in 1..nb_bus loop
                --ptr_tt_bus := new tt_bus(i, ptr_line, ptr_position);
            --end loop;
            put_line(int'image(nb_bus));put(" bus créés");
        end initBus;
    
    end BusNetwork;
    
    
    ----------------------------------------
    -- Définition des pragma par délégation
    ----------------------------------------
    procedure p_initLine(nb_line : in int) is
    begin
        put_line("### busNetwork : p_initLine");
        BusNetwork.initLine(nb_line);
    end p_initLine;
    
	procedure p_initBusStop(nb_busstop : in int) is
	begin
        put_line("### busNetwork : p_initBusStop");
        BusNetwork.initBusStop(nb_busstop);
    end p_initBusStop;
    
    procedure p_initBus(nb_bus : in int; line : in int) is
    begin
        put_line("### busNetwork : p_initBus");
        BusNetwork.initBus(nb_bus, line);
    end p_initBus;
    
    
    ---------------------------------
    -- fonctions internes au package
    ---------------------------------
    procedure sendPositionToCenter(ptr_pos : in t_ptr_t_position) is
    begin
        put_line("sendPositionToCenter");
        receivePosition(ptr_pos);
    end sendPositionToCenter;
    
begin
    
	put_line("### initialisation des objets Ada ###");

end package_busnetwork;

