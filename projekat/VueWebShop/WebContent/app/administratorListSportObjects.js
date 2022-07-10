Vue.component("administratorListSportObjects-page", {
	data: function () {
		    return {
			  sportObjects : null
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
  <li><a href="#/lu">Log out</a></li>
</ul>

<br>
	</table>
	<br>
	<table style="width:100%" border="1px">
 	<tr>
		<th> Name </th>
    	<th>Type</th>
		<th>Content</th>
    	<th>Location</th>
    	<th style="width:80px">Average Grade</th>
		<th style="width:260px">Image</th>
		<th style="width:90px">Work Time</th>
		<th>Status</th>
  	</tr>
  	<tr v-for="s in sportObjects">
  		<td>{{s.name}}</td>
  		<td>{{s.type}}</td>
  		<td>{{s.content}}</td>
  		<td>{{s.location}}</td>
  		<td>{{s.averageGrade}}</td>
  		<td><img v-bind:src="s.image" width="260px" Height="160px" alt="bilo sta"></td>
  		<td>{{s.workTime}}</td>
  		<td>{{s.status}}</td>
  		<td><button v-on:click="deleteSportObject(s)">Delete</button></td>
  	</tr>
	</table>	
	
</div>	
`
	, 
	methods : {
		
		deleteSportObject : function(sportObject) {
			axios
			.post('rest/sportobject/deleteSportObject', { id: sportObject.id })
			.then(response => {
				 axios 
				.get('rest/sportobject/getAll')
				.then(response => {
				this.sportObjects = response.data;
				toast("Sport object is deleted!");
				})
			})	
		}
		
	},
	mounted () {
        axios 
		.get('rest/sportobject/getAll')
		.then(response => {
			this.sportObjects = response.data;
		})
    },
});