with package_types, Text_io, Ada.Exceptions, interfaces.C;
use package_types, Text_io, Ada.Exceptions, interfaces.C;



package package_bus is

	-- task type permettant d'initialiser plusieurs bus sur le reseau
	task type tt_bus(idBus : int; lineNumber : access t_line; initialPosition : access t_position) is
		entry start;
		entry destroy;
	end tt_bus;

	type t_ptr_tt_bus is access tt_bus;

	-- procedure prenant en entrée une position et la mettant a jour
	procedure receiveInformation(act : in t_action) ;
	
	-- definition d'un objet protege permettant de manipuler
	-- le compteur de distance du bus
	protected Odometer is
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
		-- modifie le busStopId de la position courante du bus
		procedure updateBusStop(bs : in out int);
	private
		-- attribut
		currentPosition : t_position; -- := initialPosition.all;
	end Sensor;

	-- definition d'un objet protege permettant de manipuler
	-- le radio du bus
	protected Radio is
		procedure sendPosition(pos : out t_ptr_t_position);
		procedure sendPriorityMessage(mes : out t_ptr_t_priorityMessage);
		procedure receiveCommand(com : in t_ptr_t_action);
	end Radio;

end package_bus;
