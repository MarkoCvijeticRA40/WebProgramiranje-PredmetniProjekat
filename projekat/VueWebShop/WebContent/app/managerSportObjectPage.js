Vue.component("managerSportObject-page", {
	data: function () {
		    return {
			  manager: null,
			  sportObject: null,
			  isThereSportObject: true,
			  print: false,
			  comments: null
		    }
	},
	template: ` 
<div>
<ul>
 <li><a class="active" href="#/msp">Profile</a></li>
 <li><a class="active" href="#/mwc">Welcome Customer</a></li>
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

<div v-if="isThereSportObject">
<table style="width:100%" border="1px">
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
<br>
<table style="width:100%" border="1px">
  <tr>
	<th>Comments</th>
  </tr>
  <tr v-for="c in comments">
  <td>{{c.text}}</td>
  </tr>
</table>
</div>

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
			.then(response => {
			 if (response.data === "") {
			 	this.isThereSportObject = false;
				this.print = true;	
				return;
			 }
			 this.sportObject = response.data;
			 axios
			 .post('rest/comments/getManagerComments', { id: this.sportObject.id })
			 .then(response => this.comments = response.data); 
			 });
			}
		});
		
    },
});