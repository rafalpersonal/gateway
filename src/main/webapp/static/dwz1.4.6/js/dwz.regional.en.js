/**
 * @author ZhangHuihua@msn.com
 */
(function($){
	// jQuery validate
	$.extend($.validator.messages, {
		required: "required field",
		remote: "Please correct this value",
		email: "Please correct the email",
		url: "Please correct the URL value",
		date: "Please correct the date value",
		dateISO: "Please correct the date(ISO) value.",
		number: "Please correct the number value.",
		digits: "Allow digital number",
		creditcard: "Please use a valid credit card.",
		equalTo: "Please enter the same value above.",
		accept: "Please enter string with valid suffix.",
		maxlength: $.validator.format("String length must be no more than {0}"),
		minlength: $.validator.format("String length must be more than {0}"),
		rangelength: $.validator.format("String length must be more than {0} and less than {1}"),
		range: $.validator.format("Please enter a value between {0} and {1}."),
		max: $.validator.format("Please enter a value less than  {0} "),
		min: $.validator.format("Please enter a  value more than {0} "),
		
		alphanumeric: "Letters of the alphabet, digital, underline",
		lettersonly: "Letters of the alphabet only",
		phone: "Letters of digital, spaces, brackets only"
	});
	
	// DWZ regional
	$.setRegional("datepicker", {
		dayNames: ['Sun', 'Mon', 'Tue', 'Wed', 'Thur', 'Fri', 'Sat'],
		monthNames: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'June', 'July', 'Aug', 'Sept', 'Oct', 'Nov', 'Dec']
	});
	$.setRegional("alertMsg", {
		title:{error:"Error", info:"Info", warn:"Warning", correct:"Correct", confirm:"Confirm"},
		butMsg:{ok:"OK", yes:"Yes", no:"No", cancel:"Cancel"}
	});
})(jQuery);