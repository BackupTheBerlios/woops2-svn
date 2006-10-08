function setHyperLink(id, urlName) {
	var node = document.getElementById(id);
	node.href = url[urlName];
}

/*
+ ---------------------------------------------------------------------------------+
| Funktion.: checkInt(object)
| Zweck....: 
|
| Argumente: object
|
| Datum       Author            Bemerkung
| ----------  ----------------  ----------------------------------------------------
| 12.05.2001  G.Schulz (SCC)    Erstversion
|
+ ---------------------------------------------------------------------------------+
*/

function checkInt(value) {
	var iValue = 0;
	
	if ( isNaN(value) ) {
		return false;
	} else {
		return true;
	}
	
	/* Laeuft nicht unter IE4.0 !!!!
	try{
		iValue = parseInt(value);
		return true;
	} catch (e) {
		// Error
		// alert(e);
		return false;
	}*/
} // end checkInt()

/*
+ ---------------------------------------------------------------------------------+
| Funktion.: spinUp(Object)
| Zweck....: Erhöht einen Integerwert
|
| Argumente: Object
|
| Datum       Author            Bemerkung
| ----------  ----------------  ----------------------------------------------------
| 02.01.2001  G.Schulz (SCC)    Erstversion
|
+ ---------------------------------------------------------------------------------+
*/

function spinUp(obj) {
	if ( checkInt (obj.value) ){
		obj.value = parseInt(obj.value) + 1;
		// TODO obj.fireEvent("onchange");
	} else {
		alert(">" + obj.value +"< ist not a valid Number!");
	}

	return false;
}

/*
+ ---------------------------------------------------------------------------------+
| Funktion.: spinDown(Object)
| Zweck....: vermindert einen Integerwert
|
| Argumente: Object
|
| Datum       Author            Bemerkung
| ----------  ----------------  ----------------------------------------------------
| 02.01.2001  G.Schulz (SCC)    Erstversion
|
+ ---------------------------------------------------------------------------------+
*/

function spinDown(obj) {
	if ( checkInt (obj.value) ){
		obj.value = parseInt(obj.value) - 1;
		// TODO obj.fireEvent("onchange");
	} else {
		alert(">" + obj.value +"< ist not a valid Number!");
	}

	return false;
}


function inc(obj){
	var val = parseInt(obj.value);

	if (val == Number.NaN) {
		alert("[" + val + "] is not a Number!");
	} else {
		val++;
		obj.value = val;
	}
}

function dec(obj){
	var val = parseInt(obj.value);

	if (val == Number.NaN) {
		alert("[" + val + "] is not a Number!");
	} else {
		val--;
		obj.value = val;
	}
}

