with package_types, package_busnetwork, Text_io, Ada.Exceptions, interfaces.C;
use package_types, package_busnetwork, Text_io, Ada.Exceptions, interfaces.C;



package package_bus is

	-- task type permettant d'initialiser plusieurs bus sur le reseau
	task type tt_bus(idBus : int; l : t_ptr_t_line; initialPosition : t_ptr_t_position) is
		entry start;
		entry destroy;
	end tt_bus;

	type t_ptr_tt_bus is access tt_bus;
	
	-- definition d'un objet protege permettant de manipuler
	-- le compteur de distance du bus
	protected Odometer is
		procedure getCurrentDistance(d : out C_float);
		procedure setCurrentDistance(d : in C_float);
		procedure getTotalCoveredDistance(d : out C_float);
		procedure setTotalCoveredDistance(d : in C_float);
		-- modifie la distance de la position courante du bus
		procedure updateDistance(dis : in out C_float);
	private
		-- attributs
		currentDistance : C_float := 0.0;
		totalCoveredDistance : C_float := 0.0;
	end Odometer;

	-- definition d'un objet protege permettant de manipuler
	-- le capteur du bus
	protected Sensor is
		
		procedure getCurrentPosition(p : out t_position);
		procedure setCurrentPosition(p : in t_position);
		-- modifie le busStopId de la position courante du bus
		procedure updateBusStop(bs : in out int);
	private
		-- attribut
		currentPosition : t_position;
	end Sensor;

	-- definition d'un objet protege permettant de manipuler
	-- le radio du bus
	protected Radio is
		procedure sendPosition(ptr_pos : in t_ptr_t_position);
		--procedure sendPriorityMessage(ptr_mes : out t_ptr_t_priorityMessage);
		--procedure receiveCommand(ptr_com : in t_ptr_t_action);
	end Radio;

end package_bus;
