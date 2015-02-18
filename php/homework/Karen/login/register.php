<?php include 'header.php'; ?>
<script src="js/functions.js"></script>
<?php 
    if($_REQUEST['err']==2){
        echo "<div class='error'>This login is exist</div>";
    }
?>
<div class="form-container"id="form-container" >
    <form id="registerform" action="request.php" onsubmit="return validateForm()" method="post">
        <div class='input-text required'>
            <input type="text" name="login" id="login" placeholder="Login*"/>
            <p class="error hidden">Invalid Login! Check that name does not contain special symbols(e.g. %, $, #. @ etc.)</p>
            <p class="error hidden">This field can not be empty</p>
        </div>
        <div class='input-text required'>
            <input type="text" name="name" id="name" placeholder="Name*"/>
            <p class="error hidden">Invalid Name! Check that name does not contain special symbols(e.g. %, $, #. @ etc.)</p>
            <p class="error hidden">This field can not be empty</p>
        </div>
        <div class='input-text required'>
            <input type="text" name="lastname" id="lastname" placeholder="Last name*"/>
            <p class="error hidden">Invalid Last Name! Check that name does not contain special symbols(e.g. %, $, #. @ etc.)</p>
            <p class="error hidden">This field can not be empty</p>
        </div>
        <div class='input-text required'>
            <input type="text" name="email" id="email" placeholder="Email*"/>
            <p class="error hidden">Invalid Email! Check the format of the email, input value should be of email format (e.g. user@example.com)</p>
            <p class="error hidden">This field can not be empty</p>
        </div>
        <div class='input-text required'>
            <input type="password" name="password" id="password" placeholder="Password*"/>
            <p class="error hidden">Invalid Name! Password should contain at least one digit, at least one lower case, at least one upper case, at least 6 from the mentioned characters</p>
            <p class="error hidden">This field can not be empty</p>
        </div>
        <div class="selects no-required">
            <div class='input-select'>
                <label>Year</label>
                <select name="year" id="year" onchange="updateAge()"></select>
            </div>
            <div class='input-select'>
                <label>Month</label>
                <select name="month" id="month" onchange="updateDays()">
                    <option>January</option>
                    <option>February</option>
                    <option>March</option>
                    <option>April</option>
                    <option>May</option>
                    <option>June</option>
                    <option>July</option>
                    <option>August</option>
                    <option>September</option>
                    <option>October</option>
                    <option>November</option>
                    <option>December</option>
                </select>
            </div>
            <div class='input-select'>
                <label>Day</label>
                <select name="day" id="day" onchange="updateAge()"></select>
            </div>
        </div>
        <div class='input-text no-required'>
            <label>Age</label>
            <input type="text" name="age" id="age" placeholder="Age" disabled="disabled"/>
        </div>
        <div class="input-radios no-required">
            <div class='input-radio'>
                <label>Male</label>
                <input type="radio" name="sex" id="male" value="male"/>
            </div>
            <div class='input-radio'>
                <label>Female</label>
                <input type="radio" name="sex" id="female" value="famale" />
            </div>
        </div>

        <div class='input-text'>
            <a id="link-switch" rel="showed" href="#" onclick="hideRequiredFields()">hide no required fields</a>
        </div>
        <!--Submit-->
        <div class='input-submit'>
            <input type="submit" name="submit" id="submit" value="Create account"/>
        </div>
    </form>
    <a href="index.php">Sign in</a>
</div>
<?php include 'footer.php'; ?>