Vue.component("customerMemberShip-page", {
	data: function () {
		    return {
				memberships : null,
				selected : false,
				selectMembership : null,
				customer:null,
				
		    }
	},
	template: ` 
<div>
<ul>
  <li><a class="active" href="#/csp">Profile</a></li>
  <li><a class="active" href="#/ctvp">Trainings</a></li>
  <li><a class="active" href="#/cst">Schedule training</a></li>
  <li><a class="active" href="#/ccm">Buy Membership</a></li>
  <li><a class="active" href="#/cam">Active Membership</a></li>
  <li><a class="active" href="#/cvo">Visited Objects</a></li>
  <li><a href="#/lu">Log out</a></li>
</ul>
<br>
<br>
	<div v-if="selected != true">
	<table style="width:100%" border="1px">
		<tr>
			<th>Type membership</th>
			<th>Value</th>
			<th>Status</th>
			<th>Number of terms</th>
		</tr>
		<tr v-for="m in memberships" v-on:click="selectedMembership(m)">
			<td>{{m.typeMembership}}</td>
			<td>{{m.value}}</td>
			<td>{{m.membershipStatus}}</td>
			<td>{{m.numberOfTerms}}</td>
		</tr>	
	</table>
	</div>
	
	<div v-if="selected != false">
	<tr><button v-on:click="unselect">Back</button></tr>
	<table style="width:100%" border="1px">
		<tr>
			<th>Type membership</th>
			<th>Value</th>
			<th>Status</th>
			<th>Number of terms</th>
			<th>If you want to buy:</th>
		</tr>
		<tr>
			<td>{{selectMembership.typeMembership}}</td>
			<td>{{selectMembership.value}}</td>
			<td>{{selectMembership.membershipStatus}}</td>
			<td>{{selectMembership.numberOfTerms}}</td>
			<td width="100px"><button style="width:100px;" v-on:click="BuyMembership()">Buy</button></td>
		</tr>	
	</table>
	</div>
</div>
`
	, 
	methods : {
		selectedMembership : function(clanarina) {
			axios
         .get('rest/users/activeCustomer')
         .then(response => (this.customer = response.data))
		 this.selectMembership = clanarina;
		 this.selected = true;
			
		},
		unselect : function(){
			console.log("Vratili smo se na tabelu");
			this.selected = false;
		},
		BuyMembership : function() {
			axios
			.post('rest/customers/updateMembership',{
				customerId : this.customer.id,
				membershipId : this.selectMembership.id
			})
			.then(response => toast("Membership is update!"));
		},
	},
	mounted () {
			axios 
		.get('rest/memberships/getAll')
		.then(response => (this.memberships = response.data))
    },
});