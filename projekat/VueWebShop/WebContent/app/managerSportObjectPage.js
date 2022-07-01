Vue.component("managerSportObject-page", {
	data: function () {
		    return {
			  manager: null,
			  sportObject: null
		    }
	},
	template: ` 
<div>
<ul>
  <li><a class="active" href="#/msp">Profile</a></li>
  <li><a class="active" href="#/mcp">Content</a></li>
  <li><a class="active" href="#/mso">Sport Object</a></li>
  <li><a href="#/lu">Log out</a></li>
</ul>
<br>

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
  <td">{{sportObject.averageGrade}}</td>
  <td><img v-bind:src="sportObject.image" width="260px" Height="160px" alt="Logo is not posted."></td>
  <td>{{sportObject.workTime}}</td>
  <td>{{sportObject.status}}</td>
  </tr>
</table>
	
</div>`
	, 
	methods : {
	},
	mounted () {
        axios
         .get('rest/users/activeManager')
         .then(response => { 
			this.manager = response.data;
			axios
			.post('rest/sportobject/transformToDTO', { 
				id: this.manager.sportObject.id,
				name: this.manager.sportObject.name,
				type: this.manager.sportObject.type,
				content: this.manager.sportObject.content,
				location: this.manager.sportObject.location,
				averageGrade: this.manager.sportObject.averageGrade,
				image: this.manager.sportObject.image,
				workTime: this.manager.sportObject.workTime,
				}
			)
			.then(response => this.sportObject = response.data );
		});
    },
});