Vue.component("administratorCreateManager-page", {
	data: function () {
		    return {
		      username: "",
		      password: "",
		      name: "",
		      surname: "",
		      picked: null,
		      pickedF: null,
		      date: null
		    }
	},
	template: ` 
<div>
<ul>
  <li><a class="active" href="#/asp">Profile</a></li>
  <li><a class="active" href="#/act">Add Trainer</a></li>
  <li><a class="active" href="#/acm">Add Manager</a></li>
   <li><a class="active" href="#/aso">Add Sport Object</a></li>
  <li><a class="active" href="#/lic">Customers</a></li>
  <li><a class="active" href="#/lim">Managers</a></li>
  <li><a class="active" href="#/lit">Trainers</a></li>
<li><a class="active" href="#/lia">Administrators</a></li>
<li><a class="active" href="#/acp">Comments</a></li>
<li><a class="active" href="#/also">Sport Objects</a></li>
<li><a class="active" href="#/alt">Trainings</a></li>
<li><a class="active" href="#/acpc">Add Promo Code</a></li>
<li><a class="active" href="#/apcv">Promo Codes</a></li>
  <li><a href="#/lu">Log out</a></li>
</ul>
<br>
<br>
<table>
	<tr>
	<td>Kreiranje menadzera</td>
	</tr>
	
	<tr>
		<td><label for="username">Username:</label></td>
		<td><input type="text" v-model="username" id="username"></td>
	</tr>
	
	<tr>
		<td><label for="password">Password:</label></td>
		<td><input type="password" v-model="password" id="password"></td>
	</tr>
	
	<tr>
		<td><label for="name">Name:</label></td>
		<td><input type="text" v-model="name" id="name"></td>
	</tr>
	
	<tr>
		<td><label for="surname">Surname:</label></td>
		<td><input type="text" v-model="surname" id="surname"></td>
	</tr>
	
	<tr>
		<td><label for="gender">Gender:</label></td>
		<td><input type="radio" id="male" name="gender" value="male" v-model="picked">
			<label for="male">Male</label>
			<input type="radio" id="female" name="gender" value="female" v-model="pickedF">
			<label for="female">Female</label>
		</td>
	</tr>
	
	<tr>
		<td><label for="date">Date of birth:</label></td>
		<td><input type="date" v-model="date" id="date"></td>
	</tr>
	
	<tr>
		<td></td>
		<td><button v-on:click="createManager()">Register</button></td>
	</tr>
	
</table>
</div>
`
	, 
	methods : {						
		createManager : function() {
			if (this.username === "" || this.password === "" || this.name === "" || this.surname === "" || (this.picked === null && this.pickedF === null) || this.date === null) {
				toast("All fields must be filled!")
				return;
			}
		
			if (this.picked === "male")
			{
			axios
    		.post("rest/managers/createManager", {
    			username: this.username,
			 	 password: this.password,
			 	 name: this.name,
			 	 lastName: this.surname,
			 	 gender: "Male",
			 	 dateOfBirth: this.date
    		})
    		.then(response => 
    		{
    		if (response.data === '') {
    			toast("There is already manager with same username!")
    		}
    		else {
    			toast("You have successfully registered manager!");
    			router.push("asp");
    		}
    		
    		}
    		);
    		
			}
			else
			{
			axios
    		.post("rest/managers/createManager", {
				   username: this.username,
			  password: this.password,
			  name: this.name,
			  lastName: this.surname,
			  gender: "Female",
			  dateOfBirth: this.date		
    		})
    		.then(response => 
    		{
    		if (response.data === '') {
    			toast("There is already manager with same username!")
    		}
    		else {
    			toast("You have successfully registered!");
    			router.push("/");
    		}
    		
    		}
    		);
    		
			}
    	},								
	},
	filters: {
    	dateFormat: function (value, format) {
    		var parsed = moment(value);
    		return parsed.format(format);
    	}
   	},
	mounted () {
    		   },
});