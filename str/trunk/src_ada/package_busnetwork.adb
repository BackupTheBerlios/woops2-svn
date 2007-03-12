with package_types, package_busstop, package_constantes, Text_io, Ada.Exceptions, Ada.Calendar, interfaces.C;
use package_types, package_busstop, package_constantes, Text_io, Ada.Exceptions, Ada.Calendar, interfaces.C;

package body package_busnetwork is

	--type tabLines is array (1 .. nbLines) of  t_ptr_tt_line;
	type tabBusStop is array (1 .. nbBusStop) of  t_ptr_tt_busStop;
	--type tabBus is array (1 .. nbBus) of  t_ptr_tt_bus;
    
    protected BusNetwork is
        
        procedure initBusStop(nb_busstop : in int);
        function getBusStop return tabBusStop;
        
        private
            --lines : tabLines;
            busStop : tabBusStop;
            --bus : tabBus;
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
		put_line(int'image(nb_busstop));
		put(" busStop crees");
		returnInitBusStop;
    end initBusStop;
        
    function getBusStop return tabBusStop is
    begin
        return BusNetwork.busStop;
    end getBusStop;

end BusNetwork;
    
	-- definition des fonctions de delegation
	procedure p_initBusStop(nb_busstop : in int) is
	begin
	put_line("### BusNetwork : initBusStop");
	BusNetwork.initBusStop(nb_busstop);

	end p_initBusStop;
    
    procedure sendPositionToCenter(ptr_pos : in t_ptr_t_position) is
    begin
        receivePosition(ptr_pos);
        null;
    end sendPositionToCenter;
    
begin
	put_line("### initialisation de ada ###");
end package_busnetwork;

