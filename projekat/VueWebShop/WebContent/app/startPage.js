Vue.component("start-page", {
	data: function () {
		    return {
		      users: null
		    }
	},
	template: ` 
<ul>
  <li><a class="active" href="#/">Home</a></li>
  <li><a href="#news">News</a></li>
  <li><a href="#/ru">Register</a></li>
  <li><a href="#about">About</a></li>
</ul>
`
	, 
	methods : {
		
	},
	mounted () {
        
    },
});