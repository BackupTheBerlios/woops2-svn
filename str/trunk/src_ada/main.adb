with package_types, package_busstop, Text_io, interfaces.C;
use package_types, package_busstop, Text_io, interfaces.C;

procedure main is	

-- declaration du pointeur sur le busStop1
ptr_pos : t_ptr_t_position := new t_position'(1,0.0);
ptr_pos1 : t_ptr_t_position := new t_position'(1,150.0);

chaine : C_string := new char_array'(to_c("pouet"));
chaine1 : C_string := new char_array'(to_c("une phrase longue pour tester le type"));

bs1 : tt_busStop(1, ptr_pos);
bs2 : tt_busStop(2, ptr_pos1);

info : t_information := (1, 1, chaine);
info1 : t_information := (1, 89, chaine1);

begin
	put_line("Debut");
	bs1.receiveInformation(info);
	bs1.display;
	bs2.receiveInformation(info1);
	bs2.display;
end main;
