<html>
<head>
	<meta charset="utf-8">
	<title>Registration</title>
    <meta name="author" content="Gor Julhakyan" />
    <meta name="Description" content="Registration Form in JavaScript, CSS , HTML & PHP" />
    <link href='http://fonts.googleapis.com/css?family=Roboto|Oswald:400,700' rel='stylesheet' type='text/css'>
    <link href="css/style.css" rel="stylesheet">
    <script src="js/validator.js"></script>
</head>	

<body>
	<div class="container">
		<div>
			<h1 class="title">Registration</h1>
		</div>
       <form action="php/conect.php" method="POST" name="form" >
       		<div class="raw">
				<div id="namerrorBox"></div>
				<input type="text" name="Name" id="name" value="" placeholder="First Name *" >
            </div>
            <div class="raw">    
				<div id="lnamerrorBox"></div>
				<input type="text" name="LastName" id="lastname" value="" placeholder="Last Name *" >
            </div>
			<div class="raw">
       			<div id="emailerrorBox"></div>
        		<input type="text" name="Email" id="email" value=""  placeholder="E-mail *" >
     		</div>
			<div class="raw">
				<div id="passerrorBox"></div>
				<input type="password" name="Password" id="password" value=""  placeholder="Password *" >
			</div>
           	<a href="javascript:void(0)" onclick="showHide('block_id')" class="a-btn">
                <span class="a-btn-slide-text">Hide / Show</span>
                <span class="a-btn-icon-right"><span></span></span>
			</a>
            
            
   			<div id="block_id">
     
   
        		<div class="birthday-title">Birth Date</div>
				<div id="date" style="margin: 5px 19px;" onchange="calculateAge()">
                    <select name="Birthday_year" id="year" onchange="loadMonths(this);"></select>
        			<select name="Birthday_month" id="months" style="display: none;" onchange="loadDays(this);">
                        <option value="1">Jenuary</option>
                        <option value="2">February</option>
                        <option value="3">March</option>
                        <option value="4">April</option>
                        <option value="5">May</option>
                        <option value="6">June</option>
                        <option value="7">July</option>
                        <option value="8">August</option>
                        <option value="9">September</option>
                        <option value="10">October</option>
                        <option value="11">November</option>
                        <option value="12">December</option>
                    </select>
                    <select name="Birthday_day" id="days" style="display: none;" ></select>
      			</div>
			<div class="raw" style="margin: 10px 30px;">
				<div id="ageerrorBox"></div>
                <label class="lab" for="age">Age</label>
				<input type="text" name="Age" id="age" min="12" value=""  placeholder="?" >&nbsp;&nbsp;<span class="lab">Years</span>
			</div>
        	<div class="gender-title">Gender</div>
				<div class="raw" style="margin: 10px 30px;">
					<input type="radio" name="gender" id="male" value="male"/><span class="lab">&nbsp;Male</span>
					<input type="radio" name="gender" id="female" value="female"><span class="lab">&nbsp;Female</span>
            	</div></div>
                <br>
       		<div>
				<input type="submit" name="submit" >
        		<p id="sign_user" onclick="Submit()">Sign Up</p>
      		</div>
<p id="age"></p>
     	</form>
	</div>
		<div class="log">
		<div>
			<a href="php/login.php" class="login">Login</a>
		</div>
		<div></div>
	</div>
</body>
</html>
