Vue.component("listAdministrator-page", {
	data: function () {
		    return {
		      administrators : null,
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
<table style="width:100%" border="1px">
 <tr>
	<th>Username</th>
    <th>Name</th>
	<th>LastName</th>
    <th>Gender</th>
    <th>Date of Birth</th>
  </tr>
  <tr v-for="s in administrators">
  <td>{{s.username}}</td>
  <td>{{s.name}}</td>
  <td>{{s.lastName}}</td>
  <td>{{s.gender}}</td>
  <td>&ensp;{{s.dateOfBirth | dateFormat('DD.MM.YYYY')}}</td>
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
		.get('rest/administrators/getAll')
		.then(response => (this.administrators = response.data))
    		   },
});