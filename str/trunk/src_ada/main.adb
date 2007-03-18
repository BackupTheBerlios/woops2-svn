-- Author: eperico
with package_types, package_busstop, package_bus, Text_io, interfaces.C, interfaces.C.strings;
use package_types, package_busstop, package_bus, Text_io, interfaces.C, interfaces.C.strings;

procedure main is   
    
    -- declaration du pointeur sur le busStop
    ptr_pos : t_ptr_t_position := new t_position'(2, 100, 0.0);
    bus : tt_bus(1, ptr_pos);
    
    ligne : t_line := (2, (20,30,40,50,60));
        
    --chaine : chars_ptr := new_string("pouet");
    --chaine1 : chars_ptr := new_string("une phrase longue pour tester le type");
    
begin
        put_line("Main => Debut");
        display(ligne);
        display(ptr_pos.all);
        bus.start;
        --delay(11.0);
        --bus.stop;
end main;
    