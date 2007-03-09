with package_types, package_constantes, interfaces.C;
use package_types, package_constantes, interfaces.C;

package body package_busstop is

	-- definition du type busStop qui est une tache
	-- idBusStop est l'identifiant du busStop
	-- initialPosition est la position du busStop
	task body tt_busStop is
		id : int;
		position : t_position;


		-- declaration d'un tableau permettant de stocker les station
		-- et de savoir si elles sont visible par le satellite
		tabLines : array(1..nbLines) of t_line;
		iTabLines : int := tabLines'first;

		-- variable permettant de savoir s'il y a des messages dans le buffer
		--hasInformation : boolean;

	begin
		id := idBusStop;
		position := ptr_position.all;

		put_line("BusStop n°" & int'image(id) & " créé");

		boucle : loop
			select
				accept getPosition(pos : out t_position)
				do
					put_line("BusStop -> getPosition");
					pos := position;
				end getPosition;
				
				-- a voir l'utilité
				begin
					null;
				end;

			or
				accept getId(n : out int)
				do
					put_line("BusStop -> getId");
					n := id;
				end getId;
				
				begin
					null;
				end;

			or
				when iTabLines <= tabLines'last =>
					accept addLine(ptr_line : t_ptr_t_line)
					do
						tabLines(iTabLines) := ptr_line.all;
					end addLine;
					
					begin
						put_line("BusStop " & int'image(id) & " : ajout d'une ligne");
						iTabLines := iTabLines + 1;
					end;
            or
                terminate;

			end select;

		end loop boucle;
        
        put_line("BusStop " & int'image(id) & " se termine");

	end tt_busStop;

end package_busstop;
