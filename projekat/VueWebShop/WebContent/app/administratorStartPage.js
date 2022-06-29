Vue.component("administratorStart-page", {
	data: function () {
		    return {
		      administrator : null,
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
  <li><a href="#/lu">Log out</a></li>
</ul>
<br>
<br>
<table style="font-size:50">
	<tr>
		<td><label for="username">Username:</label></td>
		<td>&ensp;{{administrator.username}}</td>
	</tr>
	
	
	<tr>
		<td><label for="name">Name:</label></td>
		<td>&ensp;{{administrator.name}}</td>
	</tr>
	
	<tr>
		<td><label for="surname">Surname:</label></td>
		<td>&ensp;{{administrator.lastName}}</td>
	</tr>
	
	<tr>
		<td><label for="date">Date of birth:</label></td>
		<td>&ensp;{{administrator.dateOfBirth | dateFormat('DD.MM.YYYY')}}</td>
	</tr>
	
	<tr>
		<td></td>
		<td><button v-on:click="editAdministrator()">Edit</button></td>
	</tr>
</table>
</div>
`
	, 
	methods : {
		 editAdministrator : function() {
			router.push("/eap");
										},							
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
    			toast("There is already administrator with same username!")
    		}
    		else {
    			toast("You have successfully registered administrato!");
    			router.push("/");
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
         axios
         .get('rest/users/activeAdministrator')
         .then(response => this.administrator = response.data);
    },
});