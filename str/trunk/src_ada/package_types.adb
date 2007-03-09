with package_types, package_busstop, Text_io, interfaces.C;
use package_types, package_busstop, Text_io, interfaces.C;

-- paquetage contenant les types communs aux elements code en Ada
package body package_types is

	
	-- affiche une position
	procedure display(position : t_position) is
	begin
		put("(busStopId => ");
		put(int'image(position.busStopId));
		put(", distance => ");
		put(C_float'image(position.distance));
		put(")");
		new_line;
	end display;
	
	-- affiche une information
	procedure display(info : t_information) is
	begin
		put("(Line n°");
		put(int'image(info.lineNumber));
		put(", Bus n°");
		put(int'image(info.idBus));
		put(" : ");
		put(To_ada(value(info.message)));
		put(")");
		new_line;
	end display;
	
end package_types;
