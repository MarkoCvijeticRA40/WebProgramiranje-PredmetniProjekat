Vue.component("managerSportObject-page", {
	data: function () {
		    return {
			  manager: null,
			  sportObject: null,
			  isThereSportObject: true,
			  print: false
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
  <li><a class="active" href="#/mcvp">Content</a></li>
  <li><a class="active" href="#/mtvp">Trainings</a></li>
  <li><a href="#/lu">Log out</a></li>
 </ul>
<br>

<table v-if="isThereSportObject" style="width:100%" border="1px">
 <tr>
	<th>Name</th>
    <th>Type</th>
	<th>Content</th>
    <th>Location</th>
    <th style="width:80px">Average Grade</th>
	<th style="width:260px">Image</th>
	<th style="width:90px">Work Time</th>
	<th>Status</th>
  </tr>
  <tr>
  <td>{{sportObject.name}}</td>
  <td>{{sportObject.type}}</td>
  <td>{{sportObject.content}}</td>
  <td>{{sportObject.location}}</td>
  <td>{{sportObject.averageGrade}}</td>
  <td><img v-bind:src="sportObject.image" width="260px" Height="160px" alt="Logo is not posted."></td>
  <td>{{sportObject.workTime}}</td>
  <td>{{sportObject.status}}</td>
  </tr>
</table>

<span v-if="print">Manager is not in charge of any sport object.</span>

	
</div>`
	, 
	methods : {
	},
	mounted () {
        axios
         .get('rest/users/activeManager')
         .then(response => { 
			this.manager = response.data;
			if (this.manager.sportObject === null) {
				this.isThereSportObject = false;
				this.print = true
			}
			else {
			axios
			.post('rest/sportobject/transformToDTO', { id: this.manager.sportObject.id })
			.then(response => this.sportObject = response.data );
			}
		});
		
    },
});