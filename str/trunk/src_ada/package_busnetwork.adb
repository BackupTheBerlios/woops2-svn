with package_types, package_bus, package_busstop, package_constantes, Text_io, interfaces.C;
use package_types, package_bus, package_busstop, package_constantes, Text_io, interfaces.C;

package body package_busnetwork is

	--type tabLines is array (1 .. nbLines) of  t_ptr_tt_line;
	type tabBusStop is array (1 .. nbBusStop) of  t_ptr_tt_busStop;
	--type tabBus is array (1 .. nbBus) of  t_ptr_tt_bus;
    
    
    ----------------------------
    -- objet protégé BusNetwork
    ----------------------------
    protected BusNetwork is
        
        --procedure initLine(nb_line : in int);
        --creation des getter sur line_1 et line_2  
              
        private
            -- L'OBJET BUSNETWORK PERMET DE PARTAGER LES DONNEES ENTRE LES TACHES
            -- LES 2 LIGNES DU RESEAU DOIVENT ETRE PARTAGÉES (TYPE T_LINE)
            -- UNE LIGNE POSSEDE LE TABLEAU DE BUSSTOP ET LE TABLEAU DE BUS
            line_1 : t_line;
            line_2 : t_line;
    end BusNetwork;

	protected body BusNetwork is

        procedure initLine(nb_line : in int) is
        begin
            put_line("### BusNetwork : initLine");
            put_line(int'image(nb_line));put(" lignes créées");
        end initLine;
    
    end BusNetwork;
    
    
    ----------------------------------------
    -- Définition des pragma par délégation
    ----------------------------------------
    
    -- initialisation d'une ligne
    procedure p_initLine(id_line : in int) is
    begin
        put_line("### busNetwork : p_initLine");
        put_line("creation du busStop n° ");put_line(int'image(id_line));
    end p_initLine;
    
    
    -- initialisation d'un arrêt de bus
	procedure p_initBusStop(id_busstop : in int; line : in int) is
        ptr_position : t_ptr_t_position := new t_position'(1, 1, 0.0);
        ptr_bs : t_ptr_tt_busStop;
    begin
        put_line("### busNetwork : p_initBusStop");
        ptr_bs := new tt_busStop(id_busstop, ptr_position);
        -- TODO ajout du busstop créé sur la ligne line
        put_line("creation du busStop n° ");put_line(int'image(id_busstop));
    end p_initBusStop;
    
    
    -- initialisation d'un bus
    procedure p_initBus(id_bus : in int; line : in int) is
        ptr_position : t_ptr_t_position := new t_position'(1, 1, 0.0);
        bus : tt_bus(id_bus, ptr_position);
    begin
        put_line("### busNetwork : p_initBus");
        -- TODO ajout du bus créé sur la ligne line
        bus.start;
        put_line("creation du bus n° ");put_line(int'image(id_bus));
    end p_initBus;
    
    
    ---------------------------------
    -- fonctions internes au package
    ---------------------------------
    procedure sendPositionToCenter(ptr_pos : in t_ptr_t_position; speed : in int; busId : in int) is
    begin
        put_line("sendPositionToCenter");
        receivePosition(ptr_pos, speed, busId);
    end sendPositionToCenter;
    
begin
    
	put_line("### initialisation des objets Ada ###");

end package_busnetwork;

