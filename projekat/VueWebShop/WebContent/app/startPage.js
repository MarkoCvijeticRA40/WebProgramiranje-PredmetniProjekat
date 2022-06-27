Vue.component("start-page", {
	data: function () {
		    return {
			  sportObjects : null,
			  sportObject : {},
			  searchText : "",
		    }
	},
	template: ` 
<div>
<ul>
  <li><a class="active" href="#/">Home</a></li>
  <li><a href="#/lu">Log in</a></li>
  <li><a href="#/ru">Register</a></li>
  <li><a href="#about">About</a></li>
</ul>
<br>
<table>
	<tr>
		<td><input type="text" v-model="searchText" v-on:keyup="search"></td>
		<td>&#128269</td>
	</tr>
</table>
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
  <tr v-for="s in sportObjects">
  <td v-on:click="selectSportObject()" id="name">{{s.name}}</td>
  <td v-on:click="selectSportObject()">{{s.type}}</td>
  <td v-on:click="selectSportObject()">{{s.content}}</td>
  <td v-on:click="selectSportObject()">{{s.location}}</td>
  <td v-on:click="selectSportObject()">{{s.averageGrade}}</td>
  <td v-on:click="selectSportObject()"><img v-bind:src="s.image" width="260px" Height="160px" alt="bilo sta"></td>
  <td v-on:click="selectSportObject()">{{s.workTime}}</td>
  <td v-on:click="selectSportObject()">{{s.status}}</td>
  </tr>
</table>



	
</div>`
	, 
	methods : {
		search : function() {
		axios 
		.post('rest/sportobject/search', { searchText : this.searchText })
		.then(response => (this.sportObjects = response.data))
		},
		selectSportObject : function() {
			axios
			.post('rest/sportobject/setActiveSportObject', {sportObject : this.sportObject})
			.then(response => toast("You have successfully changed your password!"));
			router.push("/spo");
		}
	},
	mounted () {
        axios 
		.get('rest/sportobject/getAll')
		.then(response => (this.sportObjects = response.data))
    },
});