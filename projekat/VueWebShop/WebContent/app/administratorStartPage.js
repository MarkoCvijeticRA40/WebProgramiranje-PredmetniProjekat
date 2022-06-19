Vue.component("administratorStart-page", {
	data: function () {
		    return {
		      administrator : null
		    }
	},
	template: ` 
<div>
<ul>
  <li><a class="active" href="#/asp">Profile</a></li>
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
		}
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