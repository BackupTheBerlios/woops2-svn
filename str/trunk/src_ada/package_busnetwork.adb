with package_types, package_busstop, package_constantes, Text_io, Ada.Exceptions, Ada.Calendar, interfaces.C;
use package_types, package_busstop, package_constantes, Text_io, Ada.Exceptions, Ada.Calendar, interfaces.C;

package body package_busnetwork is

	--type tabLines is array (1 .. nbLines) of  t_ptr_tt_line;
	type tabBusStop is array (1 .. nbBusStop) of  t_ptr_tt_busStop;
	--type tabBus is array (1 .. nbBus) of  t_ptr_tt_bus;

        protected BusNetwork is
                procedure initBusStop;
        private
                --lines : tabLines;
		busStop : tabBusStop;
		--bus : tabBus;
        end BusNetwork;

	protected body BusNetwork is

		procedure initBusStop is
		ptr_position : t_ptr_t_position := new t_position'(1,0.0);
		ptr_bs1 : t_ptr_tt_busStop;
		begin
			put_line("BusNetwork initBusStop");
			ptr_bs1 := new tt_busStop(1, ptr_position);
			
			--ajout des sat dans le tableau
			busStop(1) := ptr_bs1;
		end initBusStop;

	end BusNetwork;

	procedure p_initBusStop(nb_busstop : in int) is
	begin
		BusNetwork.initBusStop;
	end p_initBusStop;
    
--    procedure receiveInformation(ptr_info : in t_ptr_t_information) is  
--    begin
--        busStop(1).all.receiveInformation(ptr_info);
--    end receiveInformation;

end package_busnetwork;

