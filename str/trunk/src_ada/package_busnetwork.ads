-- Author: eperico
with package_types, package_busstop, interfaces.C;
use package_types, package_busstop, interfaces.C;

package package_busnetwork is
    
    --------------------------
    -- Déclaration des pragma
    --------------------------
    
    -- initialisation des arrêts de bus
	procedure p_initBusStop(id_busstop : in int; line : in int);
	pragma export(CPP, p_initBusStop, "p_initBusStop");
    
    -- initialisation des bus sur une ligne
    procedure p_initBus(id_bus : in int; line : in int);
    pragma export(CPP, p_initBus, "p_initBus");
    
    -- reception de la position d'un bus par le centre
	procedure receivePosition(ptr_pos : in t_ptr_t_position; speed : in int; busId : in int);
	pragma import(CPP, receivePosition, "receivePosition");
    
    -- creer les pragma start, stop, accelerate, decelerate

	-- envoie d'un message d'urgence au centre d'exploitation en cas de problème
	--procedure p_sendPriorityMessage(ptr_mes : out t_ptr_t_priorityMessage);
	--pragma export(CPP, p_sendPriorityMessage, "p_sendPriorityMessage");
    
    ---------------------------------------------
    -- Déclaration de l'objet protégé BusNetwork
    ---------------------------------------------
    -- l'objet BusNetwork permet de partager les données entre les tâches
    -- dans notre cas, les lignes du réseau doivent être partagées
    protected BusNetwork is
        
        function getLine(lineId : in int) return t_line;        
        procedure setLine(lineId : in int; line : in t_line);        
        
        private
            line_12 : t_line := (lineNumber => 12, busStopTable => (others => 0));
            line_24 : t_line := (lineNumber => 24, busStopTable => (others => 0));
    end BusNetwork;

end package_busnetwork;
