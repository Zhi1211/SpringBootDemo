function checkEmpty(field, value){
	if(!value){
		return "請輸入"+field+"\n";
	}
	return "";
}
function checkAccount(account){
	let errorMsg = "";	
	errorMsg += this.checkEmpty("帳號",account);
	return errorMsg;
	
}
function checkPassword(password){
	let errorMsg = "";	
	errorMsg += this.checkEmpty("密碼",password);
	return errorMsg;

	const lowerCaseLetters = /[a-z]/g;
	if(!(password.match(lowerCaseLetters))){
		errorMsg += "密碼應包含小寫字母"+"\n";
	}
	const upperCaseLetters = /[A-Z]/g;
	if(!(password.match(upperCaseLetters))){
		errorMsg += "密碼應包含大寫字母"+"\n";
	}
	const numbers = /[0-9]/g;
	if(!(password.match(numbers))){
		errorMsg += "密碼應包含數字"+"\n";
	}
	if(!(password.length >= 8)){
		errorMsg += "密碼長度請大於8個字元"+"\n";		
	}
	return errorMsg;
}                
function checkGender(gender){
	let errorMsg = "";
	errorMsg += this.checkEmpty("性別",gender);
	return errorMsg;
}
function checkBirthday(birthday){
	let errorMsg = "";
	const today = new Date().getTime();
	const inputDate = new Date(birthday.value).getTime();
	errorMsg += this.checkEmpty("生日",birthday);
	if(inputDate > today) errorMsg += '生日不能大於今天'
	return errorMsg;
}