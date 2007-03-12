with Text_io, interfaces.C, interfaces.C.strings;
use Text_io, interfaces.C, interfaces.C.strings;

-- paquetage contenant les types communs aux elements code en Ada
package package_types is

	-- declaration du type position
	type t_position is 
	record
		busStopId : int;
		distance : C_float;
        -- speed
        -- lineNumber ?
	end record;
	
	-- affiche une position
	procedure display(position : t_position);
	
	-- definition d'un type pointeur sur une position
	type t_ptr_t_position is access t_position;
	
	-- definition du type information envoyé à un BusStop - INUTILE
	type t_information is 
	record
		lineNumber : int;
		idBus: int;
		message : chars_ptr;
	end record;
	
	type t_ptr_t_information is access t_information;
	
	-- affiche une information
	procedure display(info : t_information);

	-- definition du type action
	type t_action is (ACCELERATE, DECELERATE, STOP);

	type t_ptr_t_action is access t_action;

	-- definition du type code d'un message prioritaire
	type t_code is (ACCIDENT, BREAKDOWN, AGRESSION, CONGESTION);

	-- definition du type Message prioritaire
	type t_priorityMessage is
	record
		code : t_code;
		message : string(1..5);
	end record;
	
	type t_ptr_t_priorityMessage is access t_priorityMessage;
	
	-- definition du type ligne suivie par un bus
	type t_line is record 
		number : int;
        pos : int; -- temporaire pr eviter l'erreur
        -- tableau de busstop
        -- tableau de bus - A VOIR
	end record;
	
	type t_ptr_t_line is access t_line;

end package_types;
