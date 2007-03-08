with package_types, package_busstop, package_constantes, interfaces.C;
use package_types, package_busstop, package_constantes, interfaces.C;

package body package_line is  

    -- definition du type Line qui est une tache
    -- idLine est l'identifiant de la ligne
    task body tt_line is
        
        idLine : int;

        -- declaration d'un tableau permettant de stocker les lignes du réseau
        tabBusStop : array(1..nbBusStop) of t_ptr_tt_busStop;
        iTabBusStop : int := tabBusStop'first;
        
        -- tableau de bus a faire

    begin
        idLine := number;

        put_line("Line n°" & int'image(idLine) & " créée");

        boucle : loop
            select
                accept getNumber(n : out int)
                        do
                            put_line("Line -> getNumber");
                            n := idLine;
                        end getNumber;        
            or
                
                when iTabBusStop <= tabBusStop'last =>
                    accept addBusStop(ptr_busStop : t_ptr_tt_busStop)
                    do
                        tabBusStop(iTabBusStop) := ptr_busStop;
                    end addBusStop;
                       
                    begin
                        put_line("Line n°" & int'image(idLine) & " : ajout d'un busStop");
                        iTabBusStop := iTabBusStop + 1;
                    end;
            or
                    terminate;
    
            end select;
        end loop boucle;
            
        put_line("Line n°" & int'image(idLine) & " se termine");
    
    end tt_line;

end package_line;
