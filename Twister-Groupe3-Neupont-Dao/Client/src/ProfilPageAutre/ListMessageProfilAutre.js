import React, { Component } from 'react';
import axios from 'axios';
import './page_profil_autre.css';

class ListMessageProfilAutre extends Component{
    constructor(props){
	super(props);
	this.state = {list_msg : [] };
	this.maj_liste = this.maj_liste.bind(this);
    }

     maj_liste(){
	 const formData = new URLSearchParams();
	 formData.append("key",this.props.cle);
	 formData.append("id",this.props.id_pro);
	 axios.get('http://localhost:8080/TwisterGN&DH/Message/ListMessage?'+formData).then(reponse =>
											    {
												if(reponse.data["status"] === ""){
												    const posts = reponse.data["liste_messages"][0].slice(0,3);
												    this.setState({list_msg : posts});
												}
												else{
												    alert(reponse.data["Erreur"]);
												}
											  });
     }
    
    componentDidMount(){
	this.maj_liste();
    }
    
    render(){
	return (
	    	<div id="reste_pro_autre">
		{
		    this.state.list_msg.map((msg,index) => (
			    <div class="message_pro_autre">
			    <div id="entete_msg_pro">
			    <div id="msg_nom_pro_autre">
			    {msg["nom"]}
			</div>
			    <div id="msg_date_pro_autre">
			    {msg["date"]}
			</div>
			    </div>
			    <div id="msg_texte_pro_autre">
			    {msg["texte"]}
			</div>
			    </div>
			    
		    ))
		}
	    </div>
	)
    }
}

export default ListMessageProfilAutre;

