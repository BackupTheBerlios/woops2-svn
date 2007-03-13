with package_types, Text_io, Ada.Exceptions, interfaces.C;
use package_types, Text_io, Ada.Exceptions, interfaces.C;

package package_busstop is

    -- id de la ligne à ajouter a la tache
	task type tt_busStop (idBusStop : int ; ptr_position : access t_position) is
        entry getId(n : out int);
		entry getPosition(pos : out t_position);
		entry addLine(ptr_line : t_ptr_t_line);
	end tt_busStop;
	
	type t_ptr_tt_busStop is access tt_busStop;
	
end package_busstop;
