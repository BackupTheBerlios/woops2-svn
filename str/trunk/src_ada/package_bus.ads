-- Author: eperico
with package_types, interfaces.C;
use package_types, interfaces.C;

package package_bus is
        
    -------------
    -- Tâche Bus
    -------------
    -- le numéro de ligne sur laquelle se trouve le bus est renseignée dans la position
    task type tt_bus(idBus : int; initialPosition : t_ptr_t_position) is
        entry start;
        entry stop;
        -- entry accelerate;
        -- entry decelerate;
    end tt_bus;

    type t_ptr_tt_bus is access tt_bus;
    
    -------------------------
    -- objet protégé Capteur
    -------------------------
    -- VOIR L'UTILITÉ D'UN OBJET PROTEGÉ POUR ACCEDER A LA POSITION DU BUS
    protected Sensor is
        
        procedure getCurrentPosition(p : out t_position);
        procedure setCurrentPosition(p : in t_position);
        
        -- modifie le busStopId de la position courante du bus
        procedure updateBusPosition(bs : in out int);
    private
        currentPosition : t_position;
    end Sensor;    
    
    -----------------------
    -- objet protégé Radio
    -----------------------
    protected Radio is
        procedure sendPositionToCenter(ptr_pos : in t_ptr_t_position; speed : in int; busId : in int);
        --procedure sendPriorityMessage(ptr_mes : out t_ptr_t_priorityMessage);
        --procedure receiveCommand(ptr_com : in t_ptr_t_action);
    end Radio;
    
end package_bus;
