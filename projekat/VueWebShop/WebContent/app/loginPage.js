Vue.component("login-page", {
	data: function () {
		    return {
		      username: "",
		      password: "",
		    }
	},
	template: ` 
<div>
<ul>
  <li><a class="active" href="#/">Home</a></li>
  <li><a href="#/lu">Log in</a></li>
  <li><a href="#/ru">Register</a></li>
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
		<td><input type="password" v-model="password" id="password"></td>
	</tr>
	
	<tr>
		<td></td>
		<td><button v-on:click="logIn()">Log in</button></td>
	</tr>
	
</table>
</div>
`
	, 
	methods : {
		logIn : function() {
			if (this.username === "" || this.password === "") {
				toast("All fields must be filled!")
				return;
			}
		
			axios
    		.post("rest/users/login", {
    			username: this.username,
			 	password: this.password,
    		})
    		.then(response => 
    		{
	
    		if (response.data === 'administrator') {
    			router.push("/asp");
    		}
    		else if (response.data === 'customer') {
    			router.push("/csp");
    		}
    		else if (response.data === 'manager') {
    			router.push("/msp");
    		}
    		else if (response.data === 'trainer') {
    			router.push("/tsp");
    		}
    		else {
				toast("Wrong username or password!");
			}
    		
    		}
    		);
    		
			
			
    	}
	},
	mounted () {
         
    },
});