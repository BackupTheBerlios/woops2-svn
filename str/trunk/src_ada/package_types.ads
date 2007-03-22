-- Author: eperico
with interfaces.C, interfaces.C.strings, package_constantes;
use interfaces.C, interfaces.C.strings, package_constantes;

-- paquetage contenant les types communs aux elements code en Ada
package package_types is
    
    -- type tableau d'arrêts de bus
    type BusStopTabType is array(integer range 1..NB_BUSSTOP) of int;
        
    --------------------------------
	-- declaration du type position
    --------------------------------
	type t_position is 
	record
        lineNumber : int;
		busStopId : int;
		distance : C_float;
	end record;
	
    -- definition d'un type pointeur sur une position
    type t_ptr_t_position is access t_position;
    
	-- affiche une position
	procedure display(position : t_position);
    
    ----------------------------------------------
    -- definition du type ligne suivie par un bus
    ----------------------------------------------
    type t_line is record 
        lineNumber : int;
        busStopTable : BusStopTabType;
    end record;
    
    type t_ptr_t_line is access t_line;
    
    -- affiche les informations d'une ligne
    procedure display(line : t_line);
    
    ----------------------------------------------------
	-- definition du type code d'un message prioritaire
    ----------------------------------------------------
	type t_code is (ACCIDENT, BREAKDOWN, AGRESSION, CONGESTION);

    ------------------------------------------
	-- definition du type Message prioritaire
    ------------------------------------------
	type t_priorityMessage is
	record
        busId : int;
		code : t_code;
	end record;
	
	type t_ptr_t_priorityMessage is access t_priorityMessage;
    
    -- affiche les informations d'un message prioritaire
    procedure display(pm : t_priorityMessage);

end package_types;
