<%@ taglib uri="/cc-utility" prefix="util" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<html>
<head>
	<title>DateTime Picker</title>
	<script src="../../../global/jscript/formatter.js"></script>
	<script src="../../../global/jscript/utility.js"></script>
	<script src="../../../global/jscript/environment.js"></script>
	<script src="../../../global/jscript/common.js"></script>
	<script src="../../jscript/calendar_res.js"></script>
	<script src="../../jscript/calendar.js"></script>

	<link href="calendar.css" rel="stylesheet" type="text/css">
</head>

<body leftmargin=10 topmargin=10 background="winbgimg.gif">

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td>
			<%-- Do not remove/change the next line --%>
			<span id="dtp_cccal"></span>
		</td>
	</tr>
</table>

</body>
</html>

<%-- Include the calendar resources --%>
<util:calendarresources/>

<script>
var layout      = 0;                                   // Default Layout
var locale      = 'EN';                                // Default locale if not specified
var fieldId     = null;                                // Id of the field the calendar is assoziated to
var formatMask  = 'YYYY-MM-DD HH:mm:ss';               // Default mask if none specified;
var resPath     = '';                                  // Path for image resources (Backgrounds, buttons)
var winTitle    = '';

// retrieve the parameters form the url
if (null != window.location) {
	var winlocation = decodeURIComponent(window.location);
	var params      = HTTPUtil.getParameters(winlocation);
	fieldId         = HTTPUtil.getParameter('fieldid', params);
	field           = window.opener.document.getElementById(fieldId);	
	formatMask      = HTTPUtil.getParameter('mask', params);
	var lc          = HTTPUtil.getParameter('locale', params);
	locale          = (null == lc || lc.toUpperCase() == 'NULL') ? 'EN' : lc.toUpperCase();
	document.title  = DTPRes.getWindowTitle(locale);
}

// Initialize variables
var btnLabel    = '';
var btnTooltip  = '';
var btnAlt      = '';
var btnWidth    = 80;
var btnAlt      = '';
var btnCancel   = null;
var btnOk       = null;
var imgPrevYear = null;
var imgNextYear = null;

// --------------------------------------------------------
// Configure the Buttons
// --------------------------------------------------------
var imgSrc    = ['btnBkg1_left.gif', 'btnBkg1_middle.gif', 'btnBkg1_right.gif'];
var imgWidth  = [7, 0, 7];
var imgHeight = 24;

// (1) CANCEL Button
btnLabel    = '<util:resource key="fw.calendar.button.cancel.label"/>';
btnTooltip  = '<util:resource key="fw.calendar.button.cancel.tooltip"/>';
btnWidth    =  <util:resource key="fw.calendar.button.cancel.width"/>;
btnCancel   = new TextButton('btnCancel', btnLabel, btnWidth, resPath, imgSrc, imgWidth, imgHeight, btnTooltip);

// (2) OK Button
btnLabel    = '<util:resource key="fw.calendar.button.ok.label"/>';
btnTooltip  = '<util:resource key="fw.calendar.button.ok.tooltip"/>';
btnWidth    =  <util:resource key="fw.calendar.button.ok.width"/>;
btnOk     = new TextButton('btnOk', btnLabel, btnWidth, resPath, imgSrc, imgWidth, imgHeight, btnTooltip);

// (3) Create navigation buttons
btnTooltip  = '<util:resource key="fw.calendar.image.prevyear.tooltip"/>';
btnAlt      = '<util:resource key="fw.calendar.image.prevyear.alt"/>';
imgPrevYear = new Icon('imgPrevYear', '', 'btnLeft1.gif', 21, 25, btnTooltip, btnAlt);

btnTooltip  = '<util:resource key="fw.calendar.image.nextyear.tooltip"/>';
btnAlt      = '<util:resource key="fw.calendar.image.nextyear.alt"/>';
imgNextYear = new Icon('imgNextYear', '', 'btnRight1.gif', 21, 25, btnTooltip, btnAlt);

btnTooltip  = '<util:resource key="fw.calendar.image.prevmonth.tooltip"/>';
btnAlt      = '<util:resource key="fw.calendar.image.prevmonth.alt"/>';
imgPrevMonth = new Icon('imgPrevMonth', '', 'btnLeft1.gif', 21, 25, btnTooltip, btnAlt);

btnTooltip  = '<util:resource key="fw.calendar.image.nextmonth.tooltip"/>';
btnAlt      = '<util:resource key="fw.calendar.image.nextmonth.alt"/>';
imgNextMonth = new Icon('imgNextMonth', '', 'btnRight1.gif', 21, 25, btnTooltip, btnAlt);

// (4) Create array including all buttons and images
var buttons = [btnCancel, btnOk, imgPrevYear, imgNextYear, imgPrevMonth, imgNextMonth];

// --------------------------------------------------------
// Configure the calendar
// --------------------------------------------------------
var crtl_calendar = new Calendar('cccal');
crtl_calendar.setLocale(locale);
crtl_calendar.setOpener(window.opener);
crtl_calendar.setDayNameFormat(DayNameFormat.FIRSTTWOLETTER);   // display the first two characters of the dayname
crtl_calendar.setDayNameToUppercase(DayNameToUppercase.FULL);   // convert the first characters of the dayname to uppercase
crtl_calendar.setShowTimePicker(true);
crtl_calendar.setFirstDayOfWeek(1);								// start with monday
crtl_calendar.setShowDaysOfOtherMonths(false);                  // do not display days form the prev or next month
crtl_calendar.setCloseOnSelect(false);                          // display the ok/cancel button to close the calendar
crtl_calendar.setShowTodaySelector(true);                       // display "today" selector
crtl_calendar.setFieldId(fieldId);                              // id of the associated input field
crtl_calendar.setFormatMask(formatMask);                        // set the format mask
crtl_calendar.setPreselect(false);                               // open calendar with the date specified in the input field

// --------------------------------------------------------
// Render the calendar
// --------------------------------------------------------
var caldata = new CalendarPainterData(crtl_calendar, resPath, locale, layout, buttons);
CalendarPainter.render(caldata);
</script>