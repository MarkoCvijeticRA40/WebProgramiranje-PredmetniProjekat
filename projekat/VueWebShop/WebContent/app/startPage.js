Vue.component("start-page", {
	data: function () {
		    return {
			  sportObjects : null
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
<table style="width:100%">
 <tr>
	<th>Name</th>
    <th>Type</th>
	<th>Content</th>
    <th>Location</th>
    <th>Avreage Grade</th>
	<th>Image</th>
	<th>WorkTime</th>
  </tr>
  <tr v-for="s in sportObjects">
  <td>{{s.name}}</td>
  <td>{{s.type}}</td>
  <td>{{s.content}}</td>
  <td>{{s.location}}</td>
  <td>{{s.averageGrade}}</td>
  <td>{{s.image}}</td>
  <td>{{s.workTime.startTime}}</td>
  </tr>
</table>
</div>`
	, 
	methods : {
		
	},
	mounted () {
        axios 
		.get('rest/sportobject/getAll')
		.then(response => (this.sportObjects = response.data))
    },
});