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
	
	-- affiche une date-heure
	procedure display(dateHour : t_dateHour) is
	begin
		put(int'image(dateHour.day));
		put("/");
		put(int'image(dateHour.month));
		put("/");
		put(int'image(dateHour.years));
		put(" ");
		put(int'image(dateHour.hour));
		put(":");
		put(int'image(dateHour.minute));
		put(":");
		put(int'image(dateHour.second));
	end display;

	-- affiche une information
	procedure display(info : t_information) is
	begin
		put("(Line n°");
		put(int'image(info.lineNumber));
		put(", Bus n°");
		put(int'image(info.idBus));
		put(" : ");
        put(value(info.message));
		put(")");
		new_line;
	end display;
	
end package_types;
