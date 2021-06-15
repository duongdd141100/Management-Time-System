function isValidPassCf(idNewPass, idPassCf) {
    var newPass = document.getElementById(idNewPass)
    var passCf = document.getElementById(idPassCf)
    if(newPass.value == passCf.value) {
        return true
    } else {
        return false
    }
}

function checkValidPass(newPassId, cfPassId, formId, password, passwordId) {
    console.log(password + document.getElementById(passwordId).value)
    if(password != document.getElementById(passwordId).value) {
        alert('Password Incorrect!')
        return;
    }
    
    var newPass = document.getElementById(newPassId)
    if(newPass.value.trim().length == 0) {
        alert('Please Enter New Password')
        return;
    }
    var passCf = document.getElementById(cfPassId)
    if(newPass.value == passCf.value) {
        document.getElementById(formId).submit()
    } else {
        alert('Password Confirm Incorrect!')
        return
    }
    if(password == newPass.value) {
        alert('Your Password And New Password Cannot Be The Same!')
        return
    }
    alert("Change Password Successfully")
    
}