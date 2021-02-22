import React, { Component } from 'react';
import axios from 'axios';
import './page_profil_autre.css';

class AddSupprFriend extends Component{
    constructor(props){
	super(props);
	this.state = {is_friend : "false"}
	this.add_friend = this.add_friend.bind(this);
	this.remove_friend = this.remove_friend.bind(this);
    }

    componentDidMount(){
	const formData1 = new URLSearchParams();
	formData1.append("key",this.props.cle);
	formData1.append("id",this.props.id_co);
	formData1.append("id_friend",this.props.id_pro);
	axios.get('http://localhost:8080/TwisterGN&DH/Friend/TestFriends?'+formData1).then(reponse =>
											   {
											       if(reponse.data["status"] == ""){
												   this.setState({is_friend:reponse.data["res"]});
											       }
											       else{
												   alert(reponse.data["Erreur"]);
											       }
											   });
    }

    add_friend(){
	const formData = new URLSearchParams();
	formData.append("key",this.props.cle);
	formData.append("id_friend",this.props.id_pro);
	axios.get('http://localhost:8080/TwisterGN&DH/Friend/AddFriend?'+formData).then(reponse =>
											{
											    if(reponse.data["status"] === ""){
												this.setState({is_friend : "true"});		
											    }
											    else{
												alert(reponse.data["Erreur"]);
											    }	    
											});	
    }

    remove_friend(){
	const formData = new URLSearchParams();
	formData.append("key",this.props.cle);
	formData.append("id_friend",this.props.id_pro);
	axios.get('http://localhost:8080/TwisterGN&DH/Friend/DeleteFriend?'+formData).then(reponse =>
											   {
											       if(reponse.data.status === ""){
												   this.setState({is_friend : "false"});		
											       }
											       else{
												   alert(reponse.data["Erreur"]);
											       }	    
											   });	
    }

    render(){
	return (
		<div id="aj_suppr">
		{this.state.is_friend == "true" ? <button id="button_remove_f" onClick={this.remove_friend}>Retirer ami</button> : <button id="button_add_f" onClick={this.add_friend}>Ajouter ami</button>}
	    </div>
	);
    }
}

export default AddSupprFriend;
