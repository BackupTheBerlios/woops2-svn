with Text_io, interfaces.C, interfaces.C.strings;
use Text_io, interfaces.C, interfaces.C.strings;

-- paquetage contenant les types communs aux elements code en Ada
package package_types is

    -- pour les string, utilisation du type chars_ptr, accès avec value(variable de type chars_ptr)
    
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
		message : chars_ptr;
	end record;
	
	type t_ptr_t_priorityMessage is access t_priorityMessage;

end package_types;
