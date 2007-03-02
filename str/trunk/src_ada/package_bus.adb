with package_espace,package_constante;
use package_constante;
package body package_balise is

	
	protected body generateIdMessage is	
		procedure getIdMessage(id : out int) is
		begin
			id := idMess;
			idMess := idMess + 1;
		end getIdMessage;
	end generateIdMessage;

	task body tt_balise is
		id : int;
		positionBalise : t_polarPosition;
		message : t_message;
		realTime : Time ;
		timeMessage : t_dateHour;
		jour : int;
		mois : int;
		annee : int;
		sec : duration;
		secInt : int;
		heures : int;
		minutes : int;
		secondes : int;
		
		isStarted : boolean := false;

		begin
		
		-- initialisation de la balise: id et position initiale
		id := idBalise;
		positionBalise := positionInitiale.all;

		loop
		select 
			when not isStarted =>
				accept start;
				put_line("La balise "& int'image(id) & " est lancée.");
				isStarted := true;
				
			
			or 
				when isStarted =>
				
				delay(periode);
				-- envoi d'un nouveau message toutes les 5 secondes
				-- mise a jour de la position
				getPosition(positionBalise);
				
				-- création du nouveau message : manque la date voir avec modifs alex
				-- et appeler la methode de fab pour qu'il recoive le message envoyé
				generateIdMessage.getIdMessage(message.idMessage);
				message.sender := BALISE;
				message.position := positionBalise;
				message.idSender := id;
				
				-- on convertit le time en t_dateHour
				realTime := Clock;
				jour := int(Day(realTime));
				mois := int(Month(realTime));
				annee := int(Year(realTime));
				sec := Seconds(realTime);
				secInt := int(sec);
				heures := secInt/3600;
				minutes := (secInt mod 3600)/60;
				secondes := (secInt mod 3600) mod 60;
				timeMessage.day := jour;
				timeMessage.month := mois;
				timeMessage.years := annee;
				timeMessage.hour := heures;
				timeMessage.minute := minutes;
				timeMessage.second := secondes;
				message.sendingDate := timeMessage;
				
				-- affichage pour savoir ce qui se passe et verifier que les valeurs sont bonnes
				--put_line("");
				--display(message);
				--put_line("");
				
				--put_line("test envoi du message");
				package_espace.p_received_message(message);
				--put_line("reussi");
			or
				accept destroy;
				put_line("destroy");
				
				-- destruction du recorder
				-- recorder.detroy;
				
				exit; -- pour sortir de la boucle
				
	
				
			
		end select ;
		end loop;
	end tt_balise;

	-- procedure prenant en entrée une position et la mettant a jour
	procedure getPosition(positionMAJ : IN OUT t_polarPosition) is
	
	positionInitiale : t_polarPosition;
	
	-- int compris entre 0 et 5 pour eviter de deplacer la balise trop loin
	subtype ChangementAngle is int range 0 .. 6; 
   	
	-- afin d'utiliser un random ...
	package ChangementPosition is new Ada.Numerics.Discrete_Random (ChangementAngle);
   	use ChangementPosition;    -- Rend Generator, Reset et Random visibles

	p : ChangementAngle;
	t : ChangementAngle;
	G : Generator;

	begin
	
	positionInitiale := positionMAJ;
 	Reset (G);          	-- Initialise le générateur (à faire une seule fois)
   	p := Random (G);    	-- Tire un nombre au hasard entre 0 et 3
	t := Random (G);	-- Tire un autre nombre au hasard
   	
	-- affichage de la position de depart
	--put("position initiale :");
	--display(positionInitiale);
	--put_line("");

	-- mise a jour de la nouvelle position avec les valeurs aléatoires
	positionMAJ.phi := modFloat((positionInitiale.phi + C_float(p)/10.0), 360.0);
	positionMAJ.theta := modFloat((positionInitiale.theta + C_float(t)/10.0), 360.0);

	-- affichage de la position mise a jour
	--put("position mise à jour :");
	--display(positionMAJ);
	--put_line("");

	end getPosition;

end package_balise;
