with package_types, package_constantes, interfaces.C;
use package_types, package_constantes, interfaces.C;

package body package_line is  

    -- definition du type busStop qui est une tache
    -- idBusStop est l'identifiant du busStop
    -- initialPosition est la position du busStop
    task body tt_line is
        
        number : int;

        -- declaration d'un tableau permettant de stocker les station
        -- et de savoir si elles sont visible par le satellite
        tabBusStop : array(1..nbBusStop) of t_busStop;
        iTabLines : int := tabLines'first;

    begin
        id := idBusStop;
        position := ptr_position.all;

        put_line("BusStop n°" & int'image(id) & " créé");

        boucle : loop
            select
                accept getPosition(pos : out t_position)
                do
                    put_line("BusStop -> getPosition");
                    pos := position;
                end getPosition;
    
            or
                when iTabLines <= tabLines'last =>
                    accept addLine(ptr_line : t_ptr_t_line)
                    do
                        tabLines(iTabLines) := ptr_line.all;
                    end addLine;
                       
                    begin
                        put_line("BusStop " & int'image(id) & " : ajout d'une ligne");
                        iTabLines := iTabLines + 1;
                    end;
            or
                    terminate;
    
            end select;
        end loop boucle;
            
        put_line("BusStop " & int'image(id) & " se termine");
    
     end tt_line;

end package_line;
