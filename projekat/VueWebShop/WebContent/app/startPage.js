Vue.component("start-page", {
	data: function () {
		    return {
			  sportObjects : null,
			  searchText : ""
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
		<td><input type="text" v-model="searchText"></td>
		<td><button v-on:click="search">Search</button></td>
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
  <td>{{s.name}}</td>
  <td>{{s.type}}</td>
  <td>{{s.content}}</td>
  <td>{{s.location}}</td>
  <td>{{s.averageGrade}}</td>
  <td><img v-bind:src="s.image" width="260px" Height="160px" alt="bilo sta"></td>
  <td>{{s.workTime}}</td>
  <td>{{s.status}}</td>
  </tr>
</table>
</div>`
	, 
	methods : {
		search : function() {
		axios 
		.post('rest/sportobject/search', { searchText : this.searchText })
		.then(response => (this.sportObjects = response.data))
		}
	},
	mounted () {
        axios 
		.get('rest/sportobject/getAll')
		.then(response => (this.sportObjects = response.data))
    },
});