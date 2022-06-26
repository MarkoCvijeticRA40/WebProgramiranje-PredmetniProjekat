Vue.component("listTrainer-page", {
	data: function () {
		    return {
		      trainers : null,
		    }
	},
	template: ` 
<div>
<ul>
  <li><a class="active" href="#/asp">Profile</a></li>
  <li><a class="active" href="#/act">Create Trainer</a></li>
  <li><a class="active" href="#/acm">Create Manager</a></li>
  <li><a class="active" href="#/lic">List Customers</a></li>
  <li><a class="active" href="#/lim">List Managers</a></li>
  <li><a class="active" href="#/lit">List Trainers</a></li>
<li><a class="active" href="#/lia">List Administrators</a></li>
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
  <tr v-for="s in trainers">
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
		.get('rest/trainers/getAll')
		.then(response => (this.trainers = response.data))
    		   },
});