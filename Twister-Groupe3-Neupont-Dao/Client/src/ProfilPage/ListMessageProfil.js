import React, { Component } from 'react';
import axios from 'axios';
import './page_profil.css';

class ListMessageProfil extends Component{
    constructor(props){
	super(props);
	this.state = {list_msg : [] };
	this.send = this.send.bind(this);
	this.maj_liste = this.maj_liste.bind(this);
    }

     maj_liste(){
	 const formData = new URLSearchParams();
	 formData.append("key",this.props.cle);
	 formData.append("id",this.props.id_co);
	 axios.get('http://localhost:8080/TwisterGN&DH/Message/ListMessage?'+formData).then(reponse =>
											    {
												if(reponse.data["status"] === ""){
												    const posts =reponse.data["liste_messages"][0].slice(0,3);
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

    send(date){
	const formData1 = new URLSearchParams();
	formData1.append("key",this.props.cle);
	formData1.append("date",date);
	axios.get('http://localhost:8080/TwisterGN&DH/Message/DeleteMessage?'+formData1).then(reponse =>
											      {
												  if(reponse.data["status"]==="Error"){
												      alert("Erreur de suppression de message");
												  }
												  else{
												      this.maj_liste();
												  }    
												  
											      });
    }
    
    render(){
	return (
	    	<div id="reste_pro">
		{
		    this.state.list_msg.map((msg,index) => (
			    <div class="message_pro">
			    <div id="entete_msg_pro">
			    <div id="msg_nom_pro">
			    {msg["nom"]}
			</div>
			    <div id="msg_date_pro">
			    {msg["date"]}
			</div>
			    </div>
			    <div id="msg_texte_pro">
			    {msg["texte"]}
			</div>
			    <button id="suppr_msg_button_pro" onClick={() => {this.send(msg["date"])}} >suppr</button>
			    </div>
			    
		    ))
		}
	    </div>
	)
    }
}

export default ListMessageProfil;

