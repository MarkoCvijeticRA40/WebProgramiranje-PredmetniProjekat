Vue.component("managerViewCustomers-page", {
	data: function () {
		    return {
		      customers : null,
		      manager: null,
		    }
	},
	template: ` 
<div>
<ul>
  <li><a class="active" href="#/msp">Profile</a></li>
  <li><a class="active" href="#/mcp">Add Content</a></li>
  <li><a class="active" href="#/mat">Add Training</a></li>
  <li><a class="active" href="#/mso">Sport Object</a></li>
  <li><a class="active" href="#/mvt">Trainers</a></li>
  <li><a class="active" href="#/mcpp">Customers</a></li>
  <li><a href="#/lu">Log out</a></li>
</ul>
<br>
<table style="width:100%" border="1px">
 <tr>
	<th>Username</th>
    <th>Name</th>
	<th>LastName</th>
    <th>Gender</th>
    <th>Date of Birth</th>
  </tr>
  <tr v-for="c in customers">
  <td>{{c.username}}</td>
  <td>{{c.name}}</td>
  <td>{{c.lastName}}</td>
  <td>{{c.gender}}</td>
  <td>&ensp;{{c.dateOfBirth | dateFormat('DD.MM.YYYY')}}</td>

  </tr>
</table>


<br>
</div>

`
	, 
	methods : {						
										
	},
	filters: {
    	dateFormat: function (value, format) {
    		var parsed = moment(value);
    		return parsed.format(format);
    	}
   	},
	mounted () {
		axios
         .get('rest/users/activeManager')
         .then(response => { 
			this.manager = response.data;
			if (this.manager.sportObject !== null) {
				axios
				.post('rest/sportobject/transformToDTO', { id: this.manager.sportObject.id })
				.then(response => { 
					axios
					.post('rest/sportobject/getCustomersFromSportObject', { id: this.manager.sportObject.id })
					.then(response => this.customers = response.data); 
					});
			}
		});
    },
});