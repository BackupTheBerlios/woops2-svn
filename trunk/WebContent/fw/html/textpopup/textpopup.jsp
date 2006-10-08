<%@ taglib uri="/WEB-INF/tlds/cc-controls.tld" prefix="ctrl" %>
<%@ taglib uri="/WEB-INF/tlds/cc-utility.tld" prefix="util" %>

<html>
<head>
	<%-- Title --%>
	<title>Textpopup</title>

	<util:base/>

	<%-- Framework Includes --%>
	<util:jsp directive="includes"/>

</head>
<body bottommargin="0" topmargin="0" leftmargin="0" rightmargin="0" marginheight="0" marginwidth="0" onLoad="init();">

<table height="100%" width="100%" border="0" cellspacing="0" cellpadding="5" class="fc">
	<tr valign="top" height="100%">
		<td class="fd">
			<textarea id="textpopup" style="width:100%; height:100%; font-size: 10pt"></textarea>
		</td>
	</tr>
	<tr height="25">
		<td align="right" valign="middle" class="fd">
			<table border="0" cellspacing="0" cellpadding="3">
				<tr>
					<td nowrap>
						<ctrl:button
							name="btnCancel"
							text="fw.textpopup.button.cancel"
							id="btnCancel"
							title="fw.textpopup.button.cancel.tooltip"
							onclick="onCancel();"/>
					</td>
					<td nowrap id="button" style="display:block; padding-left:5px;">
						<ctrl:button
							name="btnApply"
							text="fw.textpopup.button.apply"
							id="btnApply"
							title="fw.textpopup.button.apply.tooltip"
							onclick="onClose();"/>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</html>
</body>


<script>
var locale        = 'EN';                                // Default locale if not specified
var fieldId       = null;                                // Id of the field the calendar is assoziated to
var field         = null;
var winTitle      = '';
var maxlength     = 0;
var winHeight     = 0;
var isMaxlength   = false;
var remainingText = '<util:resource key="fw.textarea.maxlength.message"/>';
var winTitle      = '<util:resource key="fw.textpopup.window.title"/>';
var ta_textpopup  = null;
var readonly      = false;
var initalvalue   = null; // BUG safari onchange

if (null != window.location) {
	var winlocation = decodeURIComponent(window.location);
	var params   = HTTPUtil.getParameters(winlocation);
	fieldId      = HTTPUtil.getParameter('fieldid', params);
	field        = window.opener.document.getElementById(fieldId);
	maxlength    = HTTPUtil.getParameter('maxlength', params);
	isMaxlength = (null == maxlength || isNaN(maxlength) || 0 == maxlength) ? false : true;
	winHeight    = HTTPUtil.getParameter('height', params);
	var lc       = HTTPUtil.getParameter('locale', params);
	locale       = (null == lc || lc.toUpperCase() == 'NULL') ? 'EN' : lc.toUpperCase();
	readonly     = ('true' == HTTPUtil.getParameter('readonly', params)) ? true : false;

	// set text
	var textarea = document.getElementById('textpopup');
	var obj = window.opener.document.getElementById(fieldId);
	if (null != obj) {
		textarea.value = obj.value;
		initalvalue = obj.value;
	}

	if (readonly) {
		textarea.setAttribute('readOnly', readonly);
		
		// do not display the Apply button
		var tb = document.getElementById('button');
		tb.style.display = 'none';
	}
	
}
	// create a textarea object
	ta_textpopup = new Textarea('textpopup', maxlength, remainingText, true);

	if (isMaxlength) {
		if (!readonly) {
			textarea.style.height = parseInt(winHeight) - 60;
		} else {
			textarea.style.height = parseInt(winHeight) - 40;
		}
	} else {
		textarea.style.height = '100%';
	}
	
	// set the window title
	document.title = winTitle;
</script>

<script>
var messageCancel = '<util:resource key="fw.textpopup.button.cancel.message"/>';

function isDirty() {
	if (safari) {
		var newvalue = document.getElementById('textpopup').value;
		return (initalvalue != newvalue);
	} else {
		return ta_textpopup.isDirty();
	}
}
function onCancel() {
	// check if the area has changed
	if (isDirty()) {
		var rtc = window.confirm(messageCancel);

		if (rtc) {
			window.close();
		} else {
			return false;
		}
	} else{
		window.close();
	}
}
function onClose() {
	// check if the area has changed
	if (isDirty()) {
		if (null != window.opener) {
			var field = window.opener.document.getElementById(fieldId);

			if (null != field) {
				field.value = document.getElementById('textpopup').value;
			//	field.onchange();
			}
		}
	}

	window.close();
}
</script>
