Vue.component("selected-page", {
	data: function () {
		    return {
			sportObject : null
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
<br>


<table style="width:100%" border="1px">
 <tr>
	<th><label v-model="sportObject.username" id="username"></label></td>
 </tr>
 <tr> 
	<th>Type:</th>
	<td></td>
 </tr>
 <tr>	
	<th>Content:</th>
	<td></td>
 </tr>
 <tr>   
	<th>Location:</th>
	<td></td>
 </tr>
 <tr>   
	<th style="width:80px">Average Grade:</th>
	<td></td>
 </tr>
 <tr>	
	<th style="width:260px">Logo:</th>
	<td></td>
 </tr>
 <tr>	
	<th style="width:90px">Work Time:</th>
	<td></td>
 </tr>
 <tr>	
	<th>Status:</th>
	<td></td>
 </tr>
<tr>	
	<th>Comments:</th>
	<td></td>
 </tr>

</table>
</div>`
	, 
	methods : {
	},
	mounted () {
		axios
         .get('rest/users/activeSportObject')
         .then(response => this.sportObject = response.data);
    },
});