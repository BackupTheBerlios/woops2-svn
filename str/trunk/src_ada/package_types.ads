with Text_io, interfaces.C;
use Text_io, interfaces.C;

-- paquetage contenant les types communs aux elements code en Ada
package package_types is

	-- declaration du type C_string pour l'utilisation en cpp
	type C_string is access char_array;

	-- declaration du type position
	type t_position is 
	record
		busStopId : int;
		distance : C_float;
	end record;
	
	-- affiche une position
	procedure display(position : t_position);
	
	-- definition d'un type pointeur sur une position
	type t_ptr_t_position is access t_position;
	
	-- definition d'un type date
	type t_dateHour is
	record
		day : int range 1..31;
		month : int range 1..12;
		years : int;
		hour : int range 0..23;
		minute : int range 0..59;
		second : int range 0..59;
	end record;
	
	-- affiche une date-heure
	procedure display(dateHour : t_dateHour);
	
	-- definition d'un type pointeur sur dateHour
	type t_ptr_t_dateHour is access t_dateHour;
	
	-- definition du type information envoyé à un BusStop
	type t_information is 
	record
		lineNumber : int;
		idBus: int;
		message : C_string;
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
	end record;
	
	type t_ptr_t_line is access t_line;

end package_types;
