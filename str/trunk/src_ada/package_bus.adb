with package_types, package_busstop, package_constantes, Text_io, Ada.Exceptions, Ada.Calendar, interfaces.C;
use package_types, package_busstop, package_constantes, Text_io, Ada.Exceptions, Ada.Calendar, interfaces.C;

package body package_bus is

	-- task type permettant d'initialiser plusieurs bus sur le reseau
	task body tt_bus is
	
	id : int := idBus;
	speed : int := 0;
	line : t_line := l.all;
	isStarted : boolean := false;
	ptr_pos : t_ptr_t_position := initialPosition;

	begin
		loop
			select
				accept start;
				put_line("Le bus "& int'image(id) & " a démarré");
				isStarted := true;
				Sensor.setCurrentPosition(initialPosition.all);				
			
			or 
				when isStarted =>
				
					delay(periode);
					-- envoi d'un nouveau message toutes les 5 secondes
					-- mise a jour de la position
					Sensor.getCurrentPosition(ptr_pos.all);
					--Radio.sendPosition(ptr_pos);

				accept destroy;
			end select;
		end loop;
	end tt_bus;

    --	definition d'un objet protege permettant de manipuler
    --	le compteur de distance du bus
	protected body Odometer is

		procedure getCurrentDistance(d : out C_float) is
		begin
			d := Odometer.currentDistance;
		end getCurrentDistance;

		procedure setCurrentDistance(d : in C_float) is
		begin
			Odometer.currentDistance := d;
		end setCurrentDistance;

		procedure getTotalCoveredDistance(d : out C_float) is
		begin
			d := Odometer.totalCoveredDistance;
		end getTotalCoveredDistance;

		procedure setTotalCoveredDistance(d : in C_float) is
		begin
			Odometer.totalCoveredDistance := d;
		end setTotalCoveredDistance;

		procedure updateDistance(dis : in out C_float) is
		begin
			dis := Odometer.currentDistance;
		end updateDistance;

	end Odometer;	

	-- definition d'un objet protege permettant de manipuler
	-- le capteur du bus
	protected body Sensor is
	
		procedure getCurrentPosition(p : out t_position) is
		begin
			p := Sensor.currentPosition;
		end getCurrentPosition;

		procedure setCurrentPosition(p : in t_position) is
		begin
			Sensor.currentPosition := p;
		end setCurrentPosition;

		procedure updateBusStop(bs : in out int) is
		begin
			bs := Sensor.currentPosition.busStopId;
		end updateBusStop;

	end Sensor;

	-- definition d'un objet protege permettant de manipuler
	-- le radio du bus
	protected body Radio is
		--procedure sendPosition(ptr_pos : out t_ptr_t_position);
		--procedure sendPriorityMessage(ptr_mes : out t_ptr_t_priorityMessage);
		--procedure receiveCommand(ptr_com : in t_ptr_t_action);
	end Radio;

end package_bus;
