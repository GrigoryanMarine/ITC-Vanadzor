function addSelectYears() {
  var curDate = new Date();
  var year = curDate.getFullYear(); 
  for(i=1900;i<=year;i++){
      var x = document.getElementById("year");
      var option = document.createElement("option");
      option.text = i;
      x.add(option);
  }
}
  
function addSelectDays(daysCount) {
  for(i=1;i<=daysCount;i++){
      var x = document.getElementById("day");
      var option = document.createElement("option");
      option.text = i;
      x.add(option);
  }
}

function validateForm() {
    var x = document.forms["myForm"]["name"].value;
    if (x==null || x=="") {
       alert("First name must be filled out");
       return false;
    }
    var x = document.forms["myForm"]["email"].value;
    var atpos = x.indexOf("@");
    var dotpos = x.lastIndexOf(".");
    if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length) {
       alert("Not a valid e-mail address");
       return false;
    }
    var x = document.forms["myForm"]["lastname"].value;
    if (x==null || x=="") {
       alert("Last name must be filled out");
       return false;
    }
    var x = document.forms["myForm"]["pass"].value;
    if (x>5 || x<13"") {
       alert("password must contain 6-12  simbols");
       return false;
    }


}
