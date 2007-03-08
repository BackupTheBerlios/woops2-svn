with package_types, Text_io, Ada.Exceptions, interfaces.C;
use package_types, Text_io, Ada.Exceptions, interfaces.C;

package package_busnetwork is

	
    -- initialisation des arrêts de bus
	procedure p_initBusStop(nb_busstop : in int);
	pragma export(CPP, p_initBusStop, "p_initBusStop");
    
    -- initialisation des bus
    procedure p_initBus(nb_bus : in int);
    pragma export(CPP, p_initBus, "p_initBus");
    
    -- initialisation des lignes
    procedure p_initLine(nb_line : in int);
    pragma export(CPP, p_initLine, "p_initLine");
    
    procedure receivePosition(ptr_pos : in t_ptr_t_position);
    pragma import(CPP, receivePosition, "receivePosition");
    
    -- initialisation d'arrêts mainde bus
    procedure receiveInformation(ptr_info : t_ptr_t_information);
    pragma import(CPP, receiveInformation, "receiveInformation");
    
    -- réception des informations relatives à une ligne pour un arrêt de bus
    --procedure receiveInformation (ptr_info : in t_ptr_t_information);
    --pragma import(CPP, ReceiveInformation, "receiveInformation");
    
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
