with package_types, package_bus, package_busstop, package_constantes, Text_io, interfaces.C;
use package_types, package_bus, package_busstop, package_constantes, Text_io, interfaces.C;

package body package_busnetwork is
    
    ----------------------------
    -- objet protégé BusNetwork
    ----------------------------
    protected BusNetwork is
        
        function getLine_1 return t_line;
        function getLine_2 return t_line; 
              
        private
            -- L'OBJET BUSNETWORK PERMET DE PARTAGER LES DONNEES ENTRE LES TACHES
            -- LES 2 LIGNES DU RESEAU DOIVENT ETRE PARTAGÉES (TYPE T_LINE)
            -- UNE LIGNE POSSEDE LE TABLEAU DE BUSSTOP ET PEUT-ETRE LE TABLEAU DE BUS
        
            -- TODO initialer les 2 lignes
            line_1 : t_line;
            line_2 : t_line;
    end BusNetwork;

	protected body BusNetwork is
                
        function getLine_1 return t_line is
        begin
            return line_1;
        end getLine_1;
        
        function getLine_2 return t_line is
        begin
            return line_2;
        end getLine_2;
    
    end BusNetwork;
    
    
    -------------------------
    -- Définition des pragma 
    -------------------------
    
    -- initialisation d'un arrêt de bus
    procedure p_initBusStop(id_busstop : in int; line : in int) is
        busstop : pt_BusStop;
        lineTmp : t_line;
    
    begin
        put_line("### busNetwork : p_initBusStop");
        busstop.setBusStopId(id_busstop);
        busstop.setLineId(line);
        
        --ajout du busstop créé sur la ligne passée en paramètre
        if line = 1 then
            lineTmp := BusNetwork.getLine_1;
        else
            lineTmp := BusNetwork.getLine_2;
        end if;
        put("taille du tableau de busstop: "); put_line(int'image(lineTmp.BusStopTable'length));
        lineTmp.BusStopTable(lineTmp.BusStopTable'last) := id_busstop;
        put("acces a l'element 1: "); put_line(int'image(lineTmp.BusStopTable(1)));
        
        put("busStop n° ");put(int'image(id_busstop));
        put(" ajouté sur la ligne ");put_line(int'image(line));
    end p_initBusStop;
    
    
    -- initialisation d'un bus
    procedure p_initBus(id_bus : in int; line : in int) is
        ptr_position : t_ptr_t_position := new t_position'(1, 1, 0.0);
        ptr_bus : t_ptr_tt_bus;
    begin
        put_line("### busNetwork : p_initBus");
        -- TODO ajout du bus créé sur la ligne line
        ptr_bus := new tt_bus(id_bus, ptr_position);
        ptr_bus.all.start;
        put("creation du bus n° ");put_line(int'image(id_bus));
    end p_initBus;
    
    
    ---------------------------------
    -- fonctions internes au package
    ---------------------------------
    
begin
    
	put_line("### initialisation des objets Ada ###");

end package_busnetwork;

