with package_types, package_busstop, package_constantes, Text_io, Ada.Exceptions, Ada.Calendar, interfaces.C;
use package_types, package_busstop, package_constantes, Text_io, Ada.Exceptions, Ada.Calendar, interfaces.C;

package body package_busnetwork is

	--type tabLines is array (1 .. nbLines) of  t_ptr_tt_line;
	type tabBusStop is array (1 .. nbBusStop) of  t_ptr_tt_busStop;
	--type tabBus is array (1 .. nbBus) of  t_ptr_tt_bus;

        protected BusNetwork is
                procedure initBusStop(nb_busstop : in int);
        private
                --lines : tabLines;
		busStop : tabBusStop;
		--bus : tabBus;
        end BusNetwork;

	protected body BusNetwork is

		procedure initBusStop(nb_busstop : in int) is
		ptr_position : t_ptr_t_position := new t_position'(1,0.0);
		ptr_bs1 : t_ptr_tt_busStop;
		begin
			put_line("BusNetwork initBusStop");
			ptr_bs1 := new tt_busStop(nb_busstop, ptr_position);
            put_line("Bus cree");
			
			--ajout des sat dans le tableau
			busStop(1) := ptr_bs1;
            put_line("Bus ajoute au tab de bus");
            toto(1);
		end initBusStop;

	end BusNetwork;

	procedure p_initBusStop(nb_busstop : in int) is
	begin
		BusNetwork.initBusStop(nb_busstop);
	end p_initBusStop;
begin
	put_line("initialisation de ada");
end package_busnetwork;

