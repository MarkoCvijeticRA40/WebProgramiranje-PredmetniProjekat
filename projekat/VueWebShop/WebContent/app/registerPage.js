Vue.component("register-page", {
	data: function () {
		    return {
		      username: "",
		      password: "",
		      name: "",
		      surname: "",
		      picked: null
		    }
	},
	template: ` 
<div>
<ul>
  <li><a class="active" href="#/">Home</a></li>
  <li><a href="#news">News</a></li>
  <li><a href="#/ru">Register</a></li>
  <li><a href="#about">About</a></li>
</ul>
<br>
<br>
<br>
<table>
	<tr>
		<td><label for="username">Username:</label></td>
		<td><input type="text" v-model="username" id="username"></td>
	</tr>
	
	<tr>
		<td><label for="password">Password:</label></td>
		<td><input type="text" v-model="password" id="password"></td>
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
			<input type="radio" id="female" name="gender" value="female">
			<label for="female">Female</label>
		</td>
	</tr>
	
	<tr>
		<td></td>
		<td><button v-on:click="createCustomerAuto()">Register</button></td>
	</tr>
	
</table>
</div>
`
	, 
	methods : {
		createCustomer : function() {
			if (this.picked === true)
			{
			axios
    		.post("rest/customers/create", {
    			username: this.username,
			 	 password: this.password,
			 	 name: this.name,
			 	 lastName: this.surname
    		})
    		.then(response => toast('Customer  created!' ));
			}
			else
			{
			axios
    		.post("rest/customers/create", {
				   username: this.username,
			  password: this.password,
			  name: this.name,
			  lastName: this.surname 		
    		})
    		.then(response => toast('Customer  created!' ));
			}
    	},
    	
    	createCustomerAuto : function() {
    		axios
    		.get("rest/customers/createAuto")
    		.then(response => toast('Customer  created!' ))
    	}
	},
	mounted () {
         
    },
});