with package_types, package_constantes, interfaces.C;
use package_types, package_constantes, interfaces.C;

package body package_busstop is

	-- definition d'une tache enregistreur qui permet d'enregister les
	-- messages non prioritaires
	task type tt_buffer is
		entry put(info : in t_information);
		entry get(info : out t_information);
		-- faire une fonction display qui affiche le buffer

		-- SOLUTION TEMPORAIRE
		entry hasInformation(b : out boolean);
		-- entry destroy;
	end tt_buffer;

	-- definition du type busStop qui est une tache
	-- idBusStop est l'identifiant du busStop
	-- initialPosition est la position du busStop
	task body tt_busStop is
		id : int;
		position : t_position;

		-- l'enregistreur qui permet de sauvegarder les informations
		buffer : tt_buffer;

		-- variable pour gerer la reception des informations
		-- de maniere asynchrone
		--info : t_information;

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



	-- definition du corps de la tache buffer
	task body tt_buffer is

  		-- declaration et initialisation de notre structure de donnees
  		capacite: int := capaciteBuffer;
  		elems: array(int range 1..capacite) of t_information;
  		tete: int range 1..capacite := 1;
  		queue: int range 1..capacite := 1;
  		taille: int range 0..capacite := 0;

  		-- variable permettant de recuperer les elements que le
  		-- centre d'exploitation veut deposer
  		put_info : t_information;

  		--variable boolean permettant de savoir si on peut deposer/retirer ou non
  		put_accepted : boolean := false;
  		get_accepted : boolean := false;

  		-- definition des procedures put et get
  		procedure buffer_put(info : in t_information) is
  		begin
  			elems(queue) := info;
  			if queue = capacite then
  				queue := 1;
  			else
  				queue := queue + 1;
  			end if;
  			taille := taille + 1;
  		end buffer_put;

  		procedure buffer_get(info : out t_information) is
  		begin
  			info := elems(tete);
  			if tete = capacite then
  				tete := 1;
  			else
  				tete := tete + 1;
  			end if;
  			taille := taille - 1;
  		end buffer_get;

	begin
		loop
			put_accepted :=( taille < capacite );
			get_accepted :=( taille > 0 );
			select
				-- ASER
				when put_accepted =>
					accept put(info : in t_information)
					do
						-- on recupere l'elt dans une variable pour pouvoir
						-- effectuer l'ajout de l'elt plus tard(sans l'appellant)
        					put_info := info;
					end put;
					begin
						-- maintenant on ajoute l'elt sans l'appelant
						buffer_put(put_info);

					exception
        					when others =>
							put_line("tt_buffer : buffer_put");
        	  					raise;
					end;
			or

				when get_accepted =>
				accept get(info : out t_information) do
				begin
					buffer_get(info);

				exception
				when others =>
					put_line("tt_buffer : buffer_get");
					raise;
				end;
				end get;

			or
				accept hasInformation(b : out boolean)
				do
					b := get_accepted;
				end hasInformation;
			or
    			  	terminate;
    			end select;
  		end loop;
	end tt_buffer;

end package_busstop;
