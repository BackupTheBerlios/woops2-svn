<%@ taglib uri="/cc-template" prefix="template" %>
<%@ taglib uri="/cc-utility" prefix="util" %>


<html>

<head>
<title>WOOPS</title>
<meta http-equiv="Content-Type" content="text/html;">
<util:base/>
<link href="images/css.css" rel="stylesheet" type="text/css">


<%-- Framework Includes --%>
<util:jsp directive="includes"/>
  
</head>

<body bgcolor="#ded9e6">

      <table class="table_spacer_top">
      <tr><td>
      </td></tr>
      </table>
      
<center>
<table width="900" class="table1">
  <tr>
  <td>
  
    <table   width="900" class="table2">
    <tr>
  
    <td width="200" valign="top">
    
      <table width="200"  class="table3">
      <tr><td>
      <center><img src="images/logo.gif"></center>
      </td></tr>
      </table>
      
      <table class="table_spacer">
      <tr><td>
      </td></tr>
      </table>
      
      <table  width="200" class="table4">
      <tr><td>
      <div class="identification" >
      
  <!-------#################------->
  <!-------DEBUT ENTETE MENU------->
<template:get name="menuHaut"/>
  <!-------FIN ENTETE MENU------->
  <!-------###############------->

  
 </div>
      </td></tr>
      </table>
      
      <table class="table_spacer">
      <tr><td>
      </td></tr>
      </table>
      
      <table width="200" class="table3">
      <tr><td>
      <div class="menu" >
      
  <!-------##########------->
  <!-------DEBUT MENU------->
  
<template:get name="menu"/>
  
  <!-------FIN MENU------->
  <!-------########------->
  
</div>
      </td></tr>
      </table>
    
    </td>

    <td width="3"></td>

    <td valign="top">
    
      <table   width="700" height="30"  class="table5">
      <tr><td>
      <div class="pageTitle" >
  <!-------###########------->
  <!-------DEBUT TITRE------->
<template:get name="title"/>
  <!-------FIN TITRE------->
  <!-------#########------->
  
  </div>
      </td></tr>
      </table>
      
      <table  width="200"  class="table_spacer">
      <tr><td>
      </td></tr>
      </table>
      
      <table width="700" height="200" class="table6">
      <tr><td valign="top">
      <div class="contenu">
  <!-------#############------->
  <!-------DEBUT CONTENU------->
  
<template:get name="contents"/>
 
  <!-------FIN CONTENU------->
  <!-------###########------->
 
  </div>
      </td></tr>
      </table>
      
    </td>
    
  
    </tr>
    </table>
  
  
  </td>
  </tr>
</table>
</center>

      <util:jsp directive="endofpage"/>  
      
      
</body>
</html>