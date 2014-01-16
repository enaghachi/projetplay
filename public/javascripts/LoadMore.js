$(document).ready(function() {
	bindEventsOnReady();
});

function bindEventsOnReady() 
{

	var username;
//	var tailleTweetdebut=$(".box_1").children().length;
//	if(tailleTweetdebut==0){
//		$("#Loadmore").remove();
//		$(".errormessage").append('<b> Vous n\'avez pas encore Tweeter!!</b>');
//		
//	}else{
//		if(tailleTweetdebut>0||tailleTweetdebut)
//		$(".errormessage").remove();
//	}
	$('#Loadmore').click(function() {
		username=$(this);
		LoadMore(username);
	});
	
	BindInitailTweets();
	
		
		
}

function LoadMore(username){
	console.log(username.val());
	console.log("loadMore");  
    var From = $(".box_1").children().length;
    console.log("From"+From);
    var To = From+1;
    console.log("TO"+To);
    
    $.get('/moretweets',
    		
    		{'userconnect': username.val(),
    	
			'From': From,
			
			'To': To },
			
			function(data){
				
				if(data != "")
				{				
					$.each(data, function(key, val) 
						{
						var html = '<div class="box_2"> <img src="/assets/bootstrap/img/box_2.png" alt=""  class="main_img_2" /><div class="text"'+val.id+'><h6>'+val.user.username+'</h6><br/><h5>'+val.creationDate+'</h5><br/><p>';
						
						if(val.sujet != "") {
							
							html = html +val.label+'<a href="@routes.Sujets.affSujet('+val.sujet+')">#'+val.sujet+'</a>'+val.Taguser+'<br/><br />';
							
						}else{
							
							html = html + val.label+" "+val.Taguser+'<br /><br />';
						}
						
						html = html + '<div class="box_comm'+val.id+'  ">'
		
						
						$.get('/CommentTweet',
								
								{'IDtweet': val.id},
								
								function(data2){
									
									if(data2 != "")
									{
										var html2 = 'Les commentaires : <br/>';
										
										$(".box_comm"+val.id).prepend(html2)
									}
									$.each(data2, function(key2, val2) 
											{
												var html3 = "<b>" + val2.label + "</b> Ecrie par : <b>" + val2.user.username + "</b> Le "+val2.creationDate+'<br/>';
												console.log(val2.user.username + " : " + val2.label + ":" + val2.tweet.id);
												$(".box_comm"+val2.tweet.id).append(html3);
											});						
								});
						
						
						html = html + '</div><input type="hidden" name="idTweet" value="'+val.id+'">'
						html = html + '<input type="hidden" name="username" value="'+val.user.username+'">'
						html = html + '<textarea cols="50" rows="3" name="commentaire" placeholder="Mettre un commentaire"></textarea>'
						html = html + '<input  type="submit" class="button gray medium" value="Publier"></p></div></div>'
						
						$(".box_1").prepend(html);
							});
				}else{
					$("#Loadmore").remove();
					$(".errormessage").append('<b> il n\'y a pas d\'autre Tweet à afficher</b>');
				}
			});
    
    
  
}

	
function BindInitailTweets(){
	var userconnect = $('#userconnect').val();
	
	//console.log(userconnect);
	//console.log("BindInitailTweets");
	
	$.get('/tweetsInit',			
			{'userconnect': userconnect},
			function(data){
					$.each(data, function(key, val) 
							{
						if(data.length != 0){
							var html = '<div class="box_2"> <img src="/assets/bootstrap/img/box_2.png" alt=""  class="main_img_2" /><div class="text"'+val.id+'><h6>'+val.user.username+'</h6><br/><h5>'+val.creationDate+'</h5><br/><p>';
						
							if(val.sujet != "") {
							
								html = html +val.label+'<a href="@routes.Sujets.affSujet('+val.sujet+')">#'+val.sujet+'</a>'+val.Taguser+'<br/><br />';
							
							}else{
							
								html = html + val.label+" "+val.Taguser+'<br /><br />';
								}
						
							html = html + '<div class="box_comm'+val.id+'  ">'
		
						
							$.get('/CommentTweet',
								
								{'IDtweet': val.id},
								
								function(data2){
									
									if(data2 != "")
									{
										var html2 = 'Les commentaires : <br/>';
										
										$(".box_comm"+val.id).prepend(html2)
									}
									$.each(data2, function(key2, val2) 
											{
												var html3 = "<b>" + val2.label + "</b> Ecrit par : <b>" + val2.user.username + "</b> Le "+val2.creationDate+'<br/>';
												console.log(val2.user.username + " : " + val2.label + ":" + val2.tweet.id);
												$(".box_comm"+val2.tweet.id).prepend(html3);
											});						
								});
						
						
						html = html + '</div><input type="hidden" name="idTweet" value="'+val.id+'">'
						html = html + '<input type="hidden" name="username" value="'+val.user.username+'">'
						html = html + '<textarea cols="50" rows="3" name="commentaire" placeholder="Mettre un commentaire"></textarea>'
						html = html + '<input  type="submit" class="button gray medium" value="Publier"></p></div></div>'
						
						$(".box_1").prepend(html);
						}else{
							$("#Loadmore").remove();
							$(".errormessage").append('<b> Vous n\'avez pas encore Tweeter!!</b>');
								}
							});
			});
	} 