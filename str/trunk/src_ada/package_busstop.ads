with package_types, Text_io, Ada.Exceptions, Ada.Calendar, interfaces.C;
use package_types, Text_io, Ada.Exceptions, Ada.Calendar, interfaces.C;

package package_busstop is

	-- definition du type busStop qui est une tache
	-- idBusStop est l'identifiant du busStop
	-- initialPosition est la position du busStop
	task type tt_busStop (idBusStop : int ; ptr_position : access t_position) is
		entry receiveInformation(ptr_info : t_ptr_t_information);
		entry display;
		entry getPosition(pos : out t_position);
		entry getId(n : out int);
		entry addLine(ptr_line : t_ptr_t_line);
	end tt_busStop;
	
	type t_ptr_tt_busStop is access tt_busStop;
	
end package_busstop;
