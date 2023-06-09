Vue.component("customerStart-page", {
	data: function () {
		    return {
		      customer: null
		    }
	},
	template: ` 
<div>
<ul>
  <li><a class="active" href="#/csp">Profile</a></li>
  <li><a class="active" href="#/ctvp">Trainings</a></li>
  <li><a class="active" href="#/cst">Schedule training</a></li>
  <li><a class="active" href="#/ccm">Buy Membership</a></li>
  <li><a class="active" href="#/cam">Active Membership</a></li>
  <li><a class="active" href="#/cvo">Visited Objects</a></li>
  <li><a href="#/lu">Log out</a></li>
</ul>
<br>
<br>
<table style="font-size:50">
	<tr>
		<td><label for="username">Username:</label></td>
		<td>&ensp;{{customer.username}}</td>
	</tr>
	
	
	<tr>
		<td><label for="name">Name:</label></td>
		<td>&ensp;{{customer.name}}</td>
	</tr>
	
	<tr>
		<td><label for="surname">Surname:</label></td>
		<td>&ensp;{{customer.lastName}}</td>
	</tr>
	
	<tr>
		<td><label for="date">Date of birth:</label></td>
		<td>&ensp;{{customer.dateOfBirth | dateFormat('DD.MM.YYYY')}}</td>
	</tr>
	
	<tr>
		<td></td>
		<td><button v-on:click="editCustomer()">Edit</button></td>
	</tr>
</table>

</div>
`
	, 
	methods : {
	
		editCustomer : function() {
			router.push("/ecp");
		},
		CreateAuto:function(){
		axios
		.post("rest/customers/update",{
			id : this.customer.id
		})
		.then(response => toast("Uspesno dodat Type!"));
		}
	},
	mounted () {
         axios
         .get('rest/users/activeCustomer')
         .then(response => this.customer = response.data);
    },
    filters: {
    	dateFormat: function (value, format) {
    		var parsed = moment(value);
    		return parsed.format(format);
    	}
   	}
});