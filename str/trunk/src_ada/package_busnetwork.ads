with package_types, package_busstop, interfaces.C;
use package_types, package_busstop, interfaces.C;

package package_busnetwork is
    
    --------------------------
    -- Déclaration des pragma
    --------------------------
    
    -- initialisation des arrêts de bus
	procedure p_initBusStop(nb_busstop : in int);
	pragma export(CPP, p_initBusStop, "p_initBusStop");

	-- confirmation de la creation d'arrets de bus.
	procedure returnInitBusStop;
	pragma import(CPP, returnInitBusStop, "returnInitBusStop");
    
    -- reception de la position d'un bus par le centre
	procedure receivePosition(ptr_pos : in t_ptr_t_position);
	pragma import(CPP, receivePosition, "receivePosition");

    ---------------------------------
    -- fonctions internes au package
    ---------------------------------
    procedure sendPositionToCenter(ptr_pos : in t_ptr_t_position);
    
	-- initialisation de bus
	--procedure p_initBus(nb_bus : in int);
	--pragma export(CPP, p_initBus, "p_initBus");

	-- initialisation de lignes de bus
	--procedure p_initLine(nb_line : in int);
	--pragma export(CPP, p_initLine, "p_initLine");

	-- envoie de la position d'un bus au centre d'exploitation
	--procedure p_sendPosition(ptr_pos : out t_ptr_t_position);
	--pragma export(CPP, p_sendPosition, "p_sendPosition");

	-- envoie d'un message d'urgence au centre d'exploitation en cas de problème
	--procedure p_sendPriorityMessage(ptr_mes : out t_ptr_t_priorityMessage);
	--pragma export(CPP, p_sendPriorityMessage, "p_sendPriorityMessage");

end package_busnetwork;
