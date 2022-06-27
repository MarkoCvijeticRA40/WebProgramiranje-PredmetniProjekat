Vue.component("managerContent-page", {
	data: function () {
		    return {
		      manager : null
		    }
	},
	template: ` 
<div>
<ul>
  <li><a class="active" href="#/msp">Profile</a></li>
  <li><a class="active" href="#/msp">Content</a></li>
  <li><a href="#/lu">Log out</a></li>
</ul>
<br>
<br>
<table style="font-size:50">
	<th>Create content</th>
	<tr>
		<td><label for="name">Name:</label></td>
		<td><input type="text"></td>
	</tr>
	
	<tr>
		<td><label for="password">Type:</label></td>
		<td><input type="text"></td>
	</tr>
	
	<tr>
		<td><label for="name">Picture:</label></td>
		<td></td>
	</tr>
	
	<tr>
		<td><label for="surname">Description:</label></td>
		<td><input type="text"></td>
	</tr>
	
	<tr>
		<td><label for="gender">Duration:</label></td>
		<td><input type="text"></td>
	</tr>
</table>

</div>
`
	, 
	methods : {
			  
	},
	mounted () {
      
    },
});