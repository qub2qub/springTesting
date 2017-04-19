<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE HTML>
<html>
  <head>
    <title>Sample Form</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <style>
    	body { background-color: #eee; font: helvetica; }
    	#container { width: 500px; background-color: #fff; margin: 30px auto; padding: 30px; border-radius: 5px; }
    	.green { font-weight: bold; color: green; }
    	.message { margin-bottom: 10px; }
    	label {width:70px; display:inline-block;}
    	form {line-height: 160%; }
    	.hide { display: none; }
    </style>
  </head>
  <body>
	
	<div id="container">
	
		<h2>Subscribe to The Newsletter!</h2>
		<c:if test="${not empty message}"><div class="message green">${message}</div></c:if>

		<!--
		note that we are specifying the model attribute.
		This tells Spring to look for an attribute in the Model
		and bind it to the form.

		The action and method attributes can also be specified.
		If unspecified (as in this example), they default to the current URL and “POST”,
		respectively (just like regular HTML forms).
		-->

		<form:form modelAttribute="subscriber">

		  <!--
		  a path attribute. This must correspond to a getter or setter of the model attribute
		  (in our case, the Subscriber class) according to the standard Java bean convention
		  (get/is, set prefixed to field name with first letter capitalized).

		   When the page is loaded, the input fields are populated by Spring,
		   which calls the getter of each field bound to an input field.
		   When the form is submitted, the setters are called to save the values of the form to the object.
		  -->
			<label for="nameInput">Name: </label>
			<form:input path="name" id="nameInput" />
			<br/>
			
			<label for="ageInput">Age: </label>
			<form:input path="age" id="ageInput" />
			<br/>
			
			<label for="emailInput">Email: </label>
			<form:input path="email" id="emailInput" />
			<br/>
			
			<label for="genderOptions">Gender: </label>
			<form:select path="gender" id="genderOptions">
				<form:option value="">Select Gender</form:option>
				<form:option value="MALE">Male</form:option>
				<form:option value="FEMALE">Female</form:option>
			</form:select>
			<br/>
			
			<label for="newsletterCheckbox">Newsletter? </label>
			<form:checkbox path="receiveNewsletter" id="newsletterCheckbox" />
			<br/>
			<label for="frequencySelect">Freq:</label>
			<form:select path="newsletterFrequency" id="frequencySelect">
				<form:option value="">Select Newsletter Frequency: </form:option>
				<c:forEach items="${frequencies}" var="frequency">
					<form:option value="${frequency}">${frequency}</form:option>
				</c:forEach>
			</form:select>
			<br/>
			
			<br/>
			<input type="submit" value="Submit" />
		</form:form>
	</div>
	
	
	<script type="text/javascript">
	
		$(document).ready(function() {
			
			toggleFrequencySelectBox(); // show/hide box on page load
			
			$('#newsletterCheckbox').change(function() {
				toggleFrequencySelectBox();
			})
			
		});
		
		function toggleFrequencySelectBox() {
			if(!$('#newsletterCheckbox').is(':checked')) {
				$('#frequencySelect').val('');
				$('#frequencySelect').prop('disabled', true);
			} else {
				$('#frequencySelect').prop('disabled', false);
			}
		}
	
	</script>
	
  </body>
</html>