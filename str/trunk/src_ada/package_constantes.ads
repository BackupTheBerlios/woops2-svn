with interfaces.C, package_types;
use interfaces.C, package_types;

package package_constantes is

	------------------------------------------------------
	-- constante utilisees par les busStops
	------------------------------------------------------
	
	-- capacite maximal du recorder
	capaciteBuffer : constant int := 50;

	-- nombre de lignes sur lesquelles un busStop peut se trouver 
	nbLines : constant int := 10;
	
end package_constantes; 
