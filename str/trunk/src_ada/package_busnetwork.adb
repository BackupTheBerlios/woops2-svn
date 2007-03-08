package body package_busnetwork is
    
    with package_types, package_busstop, package_bus, package_line, package_constantes, Text_io, Ada.Exceptions, interfaces.C;
    use package_types, package_busstop, package_bus, package_line, package_constantes, Text_io, Ada.Exceptions, interfaces.C;

	type tabLines is array (1 .. nbLines) of  t_ptr_tt_line;
	type tabBusStop is array (1 .. nbBusStop) of  t_ptr_tt_busStop;
	type tabBus is array (1 .. nbBus) of  t_ptr_tt_bus;
    
    protected BusNetwork is
        
        procedure initBusStop(nb_busstop : in int);
        procedure initBus(nb_busstop : in int);
        procedure initLine(nb_busstop : in int);
        function getBusStop return tabBusStop;
        
        private
            lines : tabLines;
            busStop : tabBusStop;
            bus : tabBus;
    end BusNetwork;

	protected body BusNetwork is

		procedure initBusStop(nb_busstop : in int) is
		    ptr_position : t_ptr_t_position := new t_position'(1,0.0);
		    ptr_bs : t_ptr_tt_busStop;
		begin
			put_line("### BusNetwork : initBusStop");
            for i in 1..nb_busstop loop
                ptr_bs := new tt_busStop(i, ptr_position);
                busStop(i) := ptr_bs;
            end loop;
            put_line(int'image(nb_busstop) & " busStop crees");
		end initBusStop;
        
        procedure initBus(nb_bus : in int) is
            ptr_position : t_ptr_t_position := new t_position'(1,0.0);
            ptr_bs : t_ptr_tt_bus;
        begin
            put_line("### BusNetwork : initBus");
            for i in 1..nb_bus loop
                ptr_bs := new tt_bus(i, ptr_position);
                bus(i) := ptr_bs;
            end loop;
            put_line(int'image(nb_bus) & " bus crees");
       end initBus;
        
        procedure initLine(nb_line : in int) is
            ptr_position : t_ptr_t_position := new t_position'(1,0.0);
            ptr_ln : t_ptr_tt_line;
        begin
            put_line("### BusNetwork : initLine");
            for i in 1..nb_line loop
                ptr_ln := new tt_line(i);
                busStop(i) := ptr_bs;
            end loop;
            put_line(int'image(nb_busstop) & " lignes creees");
       end initLine;
        
        function getBusStop return tabBusStop is
            
        begin
            return BusNetwork.busStop;
        end getBusStop;

	end BusNetwork;
    
    -- definition des fonctions de delegation
    procedure p_initBusStop(nb_busstop : in int) is
    begin
      BusNetwork.initBusStop(nb_busstop);
    end p_initBusStop;
    
    -- definition des fonctions de delegation
    procedure p_initBus(nb_bus : in int) is
    begin
      BusNetwork.initBus(nb_bus);
    end p_initBus;
    
    -- definition des fonctions de delegation
    procedure p_initLine(nb_line : in int) is
    begin
      BusNetwork.initLine(nb_line);
    end p_initLine;
    
begin
	put_line("### initialisation de ada ###");
end package_busnetwork;

