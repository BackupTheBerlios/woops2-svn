with package_types, package_busstop, package_bus, Text_io, interfaces.C, interfaces.C.strings;
use package_types, package_busstop, package_bus, Text_io, interfaces.C, interfaces.C.strings;

procedure main is   
    
    -- declaration du pointeur sur le busStop1
    ptr_pos : t_ptr_t_position := new t_position'(1,0.0);
    ptr_pos1 : t_ptr_t_position := new t_position'(1,150.0);
    
    ptr_line : t_ptr_t_line := new t_line'(1, 2);
    
    bus1 : tt_bus(1, ptr_line, ptr_pos);
    
    --chaine : chars_ptr := new_string("pouet");
    --chaine1 : chars_ptr := new_string("une phrase longue pour tester le type");
    
    --bs1 : tt_busStop(1, ptr_pos);
    --bs2 : tt_busStop(2, ptr_pos1);
    
    --info : t_ptr_t_information := new t_information'(1, 1, chaine);
    --info1 : t_ptr_t_information := new t_information'(1, 89, chaine1);
    
begin
        put_line("Debut");
        bus1.start; 
end main;
    