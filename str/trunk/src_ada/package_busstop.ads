with package_types, Text_io, Ada.Exceptions, interfaces.C;
use package_types, Text_io, Ada.Exceptions, interfaces.C;

package package_busstop is

    --------------------------
    -- objet protégé BusStop
    --------------------------
    protected type pt_BusStop is
        
        function getBusStopId return int;
        function getLineId return int;
        
        procedure setBusStopId(id : in int);
        procedure setLineId(id : in int);              
        
    private
        BusStopId : int;
        LineId : int; -- sera peut-etre un tableau de lignes
    end pt_BusStop;
	
end package_busstop;
