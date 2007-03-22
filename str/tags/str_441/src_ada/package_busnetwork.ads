-- Author: eperico
with package_types, package_busstop, package_bus, interfaces.C, package_constantes,Interfaces.C.Strings;
use package_types, package_busstop, package_bus, interfaces.C, package_constantes, Interfaces.C.Strings;

package package_busnetwork is
    
    --------------------------
    -- Déclaration des pragma
    --------------------------
    
    ---------------------------------
    -- Initialisation des objets ADA
    ---------------------------------
    -- initialisation des arrêts de bus
	procedure p_initBusStop(id_busstop : in int; line : in int);
	pragma export(CPP, p_initBusStop, "p_initBusStop");
    
    -- initialisation des bus sur une ligne
    procedure p_initBus(id_bus : in int; line : in int);
    pragma export(CPP, p_initBus, "p_initBus");
    
    ---------------------
    -- Commandes des bus
    ---------------------
    procedure p_startBus(id_bus : in int);
    pragma export(CPP, p_startBus, "p_startBus");
        
    procedure p_stopBus(id_bus : in int);
    pragma export(CPP, p_stopBus, "p_stopBus");
    
    procedure p_accelerateBus(id_bus : in int);
    pragma export(CPP, p_accelerateBus, "p_accelerateBus");
    
    procedure p_decelerateBus(id_bus : in int);
    pragma export(CPP, p_decelerateBus, "p_decelerateBus");
    
    -- envoi d'informations diverses au centre
    procedure receiveMessage(message : in chars_ptr);
    pragma import(CPP, receiveMessage, "receiveMessage");
    
    -- reception de la position d'un bus par le centre
	procedure receivePosition(ptr_pos : in t_ptr_t_position; speed : in int; busId : in int);
	pragma import(CPP, receivePosition, "receivePosition");
    
    ---------------------------------------------
    -- Envoi et réception de message prioritaire
    ---------------------------------------------
	-- envoie d'un message d'urgence au centre d'exploitation en cas de problème
	procedure p_sendPriorityMessage(ptr_mes : in t_ptr_t_priorityMessage);
	pragma export(CPP, p_sendPriorityMessage, "p_sendPriorityMessage");
    
    -- reception d'une simulation de problème depuis l'interface
    procedure simulateProblem(ptr_mes : in t_ptr_t_priorityMessage);
    pragma import(CPP, simulateProblem, "simulateProblem"); 
    
    ---------------------------------------------
    -- Déclaration de l'objet protégé BusNetwork
    ---------------------------------------------
    -- l'objet BusNetwork permet de partager les données entre les tâches
    -- dans notre cas, les lignes du réseau doivent être partagées
    
    -- type tableau de pointeurs sur des tâches bus
    -- le tableau contient tout les bus du réseau
    type BusTabType is array(integer range 1..NB_BUS) of t_ptr_tt_bus;
    
    protected BusNetwork is
        
        function getLine(lineId : in int) return t_line;        
        procedure setLine(lineId : in int; line : in t_line); 
        
        function getBusTable return BusTabType;
        function getBusById(id : in int) return t_ptr_tt_bus;
        
        procedure addBus(bus : in t_ptr_tt_bus);
        procedure addBusStopOnLine(id_busStop : in int; id_line : in int);
        
        private
            line_12 : t_line := (lineNumber => 12, busStopTable => (others => 0));
            line_24 : t_line := (lineNumber => 24, busStopTable => (others => 0));
            BusTable : BusTabType := (others => null);
    end BusNetwork;

end package_busnetwork;
