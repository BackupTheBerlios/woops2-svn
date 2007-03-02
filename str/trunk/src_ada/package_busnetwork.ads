with package_types, package_busstop, Text_io, Ada.Exceptions, Ada.Calendar, interfaces.C;
use package_types, package_busstop, Text_io, Ada.Exceptions, Ada.Calendar, interfaces.C;

package package_busnetwork is

	procedure p_initBusStop(nb_busstop : in int);
	pragma export(CPP, p_initBusStop, "p_initBusStop");
	
end package_busnetwork;
