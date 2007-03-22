-- Author: eperico
with package_types, package_busstop, package_bus, Text_io, interfaces.C, interfaces.C.strings;
use package_types, package_busstop, package_bus, Text_io, interfaces.C, interfaces.C.strings;

procedure main is   
    
    -- declaration du pointeur sur le busStop
    ptr_pos : t_ptr_t_position := new t_position'(2, 100, 0.0);    
    ligne : t_line := (2, (20,30,40,50,60, others => 0));
    
begin
        put_line("Main => Debut");
        display(ligne);
        display(ptr_pos.all);
end main;
    