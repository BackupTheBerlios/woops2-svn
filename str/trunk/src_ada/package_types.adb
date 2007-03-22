-- Author: eperico
with package_types, package_busstop, Text_io, interfaces.C;
use package_types, package_busstop, Text_io, interfaces.C;

-- paquetage contenant les types communs aux elements code en Ada
package body package_types is

	
	-- affiche une position
	procedure display(position : t_position) is
	begin
        put("Affichage position en Ada: ");
        put("(lineNumber => ");
        put(int'image(position.lineNumber));
		put(", busStopId => ");
		put(int'image(position.busStopId));
		put(", distance => ");
		put(C_float'image(position.distance));
		put(")");
		new_line;
	end display;
	
	-- affiche les informations d'une ligne
	procedure display(line : t_line) is
	begin
        put("Affichage ligne en Ada: ");
		put("(Line n°");
		put(int'image(line.lineNumber));
        put(", liste arrets => ");
        for i in 1..line.BusStopTable'Length loop
            put(int'image(line.busStopTable(i)));
        end loop;
		put(")");
		new_line;
	end display;
    
    -- affiche les informations d'un message prioritaire
    procedure display(pm : t_priorityMessage) is
    begin
        put("Affichage message prioritaire en Ada: ");
        put("(Bus n°");
        put(int'image(pm.busId));
        put(", type de problème: ");
        if (pm.code = ACCIDENT) then put("ACCIDENT");
        elsif (pm.code = BREAKDOWN) then put("PANNE");
        elsif (pm.code = AGRESSION) then put("AGRESSION");
        elsif (pm.code = CONGESTION) then put("EMBOUTEILLAGE");
        else put("PROBLEME INDETERMINE");
        end if;
        put(")");
        new_line;
    end display;
	
end package_types;
