jQuery(function($){
	$("#btnExcluir").click(function(){
		if(!confirm("Deseja excluir este registro?")){
			return false;	
		}
	});
});
