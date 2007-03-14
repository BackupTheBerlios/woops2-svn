with package_types, package_busstop, package_bus, Text_io, interfaces.C, interfaces.C.strings;
use package_types, package_busstop, package_bus, Text_io, interfaces.C, interfaces.C.strings;

procedure main is   
    
    -- declaration du pointeur sur le busStop
    ptr_pos : t_ptr_t_position := new t_position'(1, 1, 0.0);
    
    ptr_line : t_ptr_t_line := new t_line'(1, (1,2,3));
    
    bus : tt_bus(1, ptr_pos);
        
    --chaine : chars_ptr := new_string("pouet");
    --chaine1 : chars_ptr := new_string("une phrase longue pour tester le type");
    
    --busStop : tt_busStop(1, ptr_pos);
    
    --info : t_ptr_t_information := new t_information'(1, 1, chaine);
    --info1 : t_ptr_t_information := new t_information'(1, 89, chaine1);
    
begin
        put_line("Main => Debut");
        bus.start;
end main;
    