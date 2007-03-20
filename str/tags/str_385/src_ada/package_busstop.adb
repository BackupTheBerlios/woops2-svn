-- Author: eperico
with package_types, package_constantes, interfaces.C;
use package_types, package_constantes, interfaces.C;

package body package_busstop is

    --------------------------
    -- objet protégé BusStop
    --------------------------
    protected body pt_BusStop is
        function getBusStopId return int is  
        begin
            return BusStopId;
        end getBusStopId;
        
        function getLineId return int is 
        begin
            return LineId;
        end getLineId;
        
        procedure setBusStopId(id : in int) is
        begin
            BusStopId := id;
        end setBusStopId;
        
        procedure setLineId(id : in int) is
        begin
            LineId := id;
        end setLineId;
        
    end pt_BusStop;

end package_busstop;
