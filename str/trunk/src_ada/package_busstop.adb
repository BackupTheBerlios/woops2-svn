with package_types, package_constantes, interfaces.C;
use package_types, package_constantes, interfaces.C;

package body package_busstop is

    -----------------
	-- Tâche BusStop
    -----------------
	task body tt_busStop is
		id : int;
		position : t_position;

		tabLines : array(1..nbLines) of t_line;
		iTabLines : int := tabLines'first;

	begin
		id := idBusStop;
		position := ptr_position.all;
		put_line("BusStop n°" & int'image(id) & " créé");

		loop
			select
                accept getId(n : out int)
                do
                    put_line("tt_busStop -> getId");
                    n := id;
                end getId;
                
            or
				accept getPosition(pos : out t_position)
				do
					put_line("tt_busStop -> getPosition");
					pos := position;
				end getPosition;
                
			or
				when iTabLines <= tabLines'last =>
					accept addLine(ptr_line : t_ptr_t_line)
					do
						tabLines(iTabLines) := ptr_line.all;
					end addLine;
                    
					put_line("BusStop " & int'image(id) & " : ajout d'une ligne");
					iTabLines := iTabLines + 1;
                    
			end select;

		end loop;
        
        put_line("BusStop " & int'image(id) & " se termine");

	end tt_busStop;

end package_busstop;
