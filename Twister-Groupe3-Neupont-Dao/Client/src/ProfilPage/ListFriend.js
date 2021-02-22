import React, { Component } from 'react';
import axios from 'axios';
import './page_profil.css';

class ListFriend extends Component{
    constructor(props){
	super(props);
	this.state = {list_friend : []}
	this.send = this.send.bind(this);
	this.maj_liste = this.maj_liste.bind(this);
	this.chargeProfil = this.chargeProfil.bind(this);
    }
    
    maj_liste(){
	const formData = new URLSearchParams();
	formData.append("key",this.props.cle);
	formData.append("id",this.props.id_co);
	axios.get('http://localhost:8080/TwisterGN&DH/Friend/ListFriends?'+formData).then(reponse =>
											  {
											      if(reponse.data["status"] === ""){
												  const posts =reponse.data["amis"].slice(0,7);
												  this.setState({list_friend : posts});
											      }
											      else{
												  alert(reponse.data["Erreur"]);
											      }
											  });
    }
    
    componentDidMount(){
	this.maj_liste();
    }

    send(id){
	const formData1 = new URLSearchParams();
	formData1.append("key",this.props.cle);
	formData1.append("id_friend",id);
	axios.get('http://localhost:8080/TwisterGN&DH/Friend/DeleteFriend?'+formData1).then(reponse =>
											  {
											      if(reponse.data["status"]==="Error"){
												  alert("Erreur de suppression d'ami");
											      }
											      else{
												  this.maj_liste();
											      }
											  });
    }

    chargeProfil(id){
	this.props.profil(id);
    }

    render(){
	return (
		<div id="liste_ami_pro">
		<span id="titre_liste_ami_pro">Liste d amis :</span><br></br>
		{
		    this.state.list_friend.map(
			(per,index) =>
			    (
				    <div>
				    <a href="#" onClick={() => {this.chargeProfil(per["id"])}}>{per["prenom"]} {per["nom"]}</a>
				    <br></br>
				    <button id="sup_ami_button" onClick={() => {this.send(per["id"])}}>suppr</button>
				    <br></br>
				    </div>
				    
			    )
		    )
		}
		</div> 	    
	);
    }
}

export default ListFriend;
